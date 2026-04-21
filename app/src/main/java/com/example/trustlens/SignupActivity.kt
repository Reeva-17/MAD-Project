package com.example.trustlens

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.trustlens.model.User
import com.example.trustlens.repository.UserRepository
import com.google.firebase.auth.FirebaseAuth

class SignupActivity : AppCompatActivity() {

    private lateinit var role: String
    private val auth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        // Get role passed from LoginActivity
        role = intent.getStringExtra("ROLE") ?: "USER"

        val etName = findViewById<EditText>(R.id.nameInput)
        val etEmail = findViewById<EditText>(R.id.emailInput)
        val etPassword = findViewById<EditText>(R.id.passwordInput)
        val etConfirmPassword = findViewById<EditText>(R.id.confirmPasswordInput)
        val btnSignupConfirm = findViewById<Button>(R.id.signUpButton)

        btnSignupConfirm.setOnClickListener {
            val name = etName.text.toString().trim()
            val email = etEmail.text.toString().trim()
            val password = etPassword.text.toString().trim()
            val confirmPassword = etConfirmPassword.text.toString().trim()

            if (name.isEmpty()) {
                Toast.makeText(this, "Please enter your name", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Validate email format
            if (!isValidEmail(email)) {
                Toast.makeText(this, "Invalid email format", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Validate password length
            if (password.length < 6) {
                Toast.makeText(this, "Password must be at least 6 characters", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Validate confirm password
            if (password != confirmPassword) {
                Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Register with Firebase Auth
            auth.createUserWithEmailAndPassword(email, password)
                .addOnSuccessListener { result ->
                    val firebaseUser = result.user
                    if (firebaseUser != null) {
                        // Create User model with the entered name
                        val newUser = User(
                            id = firebaseUser.uid,
                            name = name,
                            email = email,
                            role = role
                        )
                        
                        // Save to Realtime Database
                        UserRepository.saveUserProfile(newUser, 
                            onSuccess = {
                                Toast.makeText(this, "Registration Successful", Toast.LENGTH_SHORT).show()
                                val intent = Intent(this, HomeActivity::class.java)
                                intent.putExtra("ROLE", role)
                                startActivity(intent)
                                finish()
                            },
                            onFailure = { error ->
                                Toast.makeText(this, "Database Error: $error", Toast.LENGTH_SHORT).show()
                            }
                        )
                    }
                }
                .addOnFailureListener {
                    Toast.makeText(this, "Signup Failed: ${it.message}", Toast.LENGTH_SHORT).show()
                }
        }
    }

    private fun isValidEmail(email: String): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
}
