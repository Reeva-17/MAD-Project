package com.example.trustlens

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.trustlens.model.User
import com.example.trustlens.repository.UserRepository
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider


class LoginActivity : AppCompatActivity() {

    private lateinit var role: String
    private val auth = FirebaseAuth.getInstance()
    private lateinit var googleSignInClient: GoogleSignInClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // Ensure we get the role from the intent
        role = intent.getStringExtra("ROLE") ?: "USER"

        val etEmail = findViewById<EditText>(R.id.emailInput)
        val etPassword = findViewById<EditText>(R.id.passwordInput)
        val btnLogin = findViewById<Button>(R.id.loginButton)
        val btnSignupTab = findViewById<TextView>(R.id.signupTab)
        val googleButton = findViewById<ImageButton>(R.id.googleButton)
        val instagramButton = findViewById<ImageButton>(R.id.instagramButton)

        // Setup UI based on role
        findViewById<TextView>(R.id.subTitle).text = if (role == "SELLER") "Seller Login" else "User Login"

        // Configure Google Sign In
        try {
            val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build()
            googleSignInClient = GoogleSignIn.getClient(this, gso)
        } catch (e: Exception) {
            // Web Client ID might not be generated yet if Google Auth isn't enabled in console
        }

        btnLogin.setOnClickListener {
            val email = etEmail.text.toString().trim()
            val password = etPassword.text.toString().trim()

            if (isValidEmail(email) && password.length >= 6) {
                Toast.makeText(this, "Logging in...", Toast.LENGTH_SHORT).show()
                auth.signInWithEmailAndPassword(email, password)
                    .addOnSuccessListener {
                        redirectBasedOnRole()
                    }
                    .addOnFailureListener {
                        Toast.makeText(this, "Login Failed: ${it.message}", Toast.LENGTH_SHORT).show()
                    }
            } else {
                Toast.makeText(this, "Invalid email or password (min 6 chars)", Toast.LENGTH_SHORT).show()
            }
        }

        btnSignupTab.setOnClickListener {
            val intent = Intent(this, SignupActivity::class.java)
            intent.putExtra("ROLE", role)
            startActivity(intent)
        }

        googleButton.setOnClickListener {
            if (::googleSignInClient.isInitialized) {
                signInWithGoogle()
            } else {
                Toast.makeText(this, "Google Sign-In not configured in Firebase Console", Toast.LENGTH_LONG).show()
            }
        }

        instagramButton.setOnClickListener {
            Toast.makeText(this, "Instagram Login coming soon!", Toast.LENGTH_SHORT).show()
        }

        findViewById<TextView>(R.id.backToRoleSelection).setOnClickListener {
            finish()
        }
    }

    private fun signInWithGoogle() {
        val signInIntent = googleSignInClient.signInIntent
        launcher.launch(signInIntent)
    }

    private val launcher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == RESULT_OK) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
            try {
                val account = task.getResult(ApiException::class.java)!!
                firebaseAuthWithGoogle(account.idToken!!)
            } catch (e: ApiException) {
                Toast.makeText(this, "Google sign in failed: ${e.message}", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun firebaseAuthWithGoogle(idToken: String) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        auth.signInWithCredential(credential)
            .addOnSuccessListener { result ->
                val user = result.user
                if (user != null) {
                    UserRepository.getCurrentUser { existingUser ->
                        if (existingUser == null) {
                            val newUser = User(
                                id = user.uid,
                                name = user.displayName ?: "User",
                                email = user.email ?: "",
                                role = role
                            )
                            UserRepository.saveUserProfile(newUser, {
                                redirectBasedOnRole()
                            }, {
                                Toast.makeText(this, "Database Error", Toast.LENGTH_SHORT).show()
                            })
                        } else {
                            // If user exists but role was different, update or just proceed
                            redirectBasedOnRole()
                        }
                    }
                }
            }
            .addOnFailureListener {
                Toast.makeText(this, "Authentication Failed", Toast.LENGTH_SHORT).show()
            }
    }

    private fun redirectBasedOnRole() {
        UserRepository.getCurrentUser { user ->
            if (user != null) {
                val intent = if (user.role == "SELLER") {
                    Intent(this, SellerDashboardActivity::class.java)
                } else {
                    Intent(this, HomeActivity::class.java)
                }
                intent.putExtra("ROLE", user.role)
                startActivity(intent)
                finish()
            } else {
                // If user data not in DB for some reason, use the current selection
                val intent = if (role == "SELLER") {
                    Intent(this, SellerDashboardActivity::class.java)
                } else {
                    Intent(this, HomeActivity::class.java)
                }
                intent.putExtra("ROLE", role)
                startActivity(intent)
                finish()
            }
        }
    }

    private fun isValidEmail(email: String): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
}
