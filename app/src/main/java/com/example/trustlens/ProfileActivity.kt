package com.example.trustlens

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.trustlens.repository.UserRepository
import com.google.firebase.auth.FirebaseAuth

class ProfileActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        val nameTv = findViewById<TextView>(R.id.profileName)
        val emailTv = findViewById<TextView>(R.id.profileEmail)
        
        findViewById<ImageView>(R.id.backButtonProfile).setOnClickListener {
            finish()
        }

        // Fetch Real Data from Firebase
        UserRepository.getCurrentUser { user ->
            if (user != null) {
                nameTv.text = user.name
                emailTv.text = user.email
            } else {
                Toast.makeText(this, "Failed to load profile", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
