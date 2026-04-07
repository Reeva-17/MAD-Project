package com.example.trustlens

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home_screen)  // your home screen layout file

        // Find the button by its ID
        val analyzeButton = findViewById<Button>(R.id.analyzeButton)

        // Set click listener
        analyzeButton.setOnClickListener {
            // Create intent to move from HomeActivity to AnalyzeActivity
            val intent = Intent(this, AnalyzeActivity::class.java)
            startActivity(intent)
        }
    }
}