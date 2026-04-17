package com.example.trustlens

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class AnalyzeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_analyze)

        val progressBar = findViewById<ProgressBar>(R.id.progressBar)
        val progressText = findViewById<TextView>(R.id.progressText)
        val step2 = findViewById<TextView>(R.id.step2)
        val step3 = findViewById<TextView>(R.id.step3)
        val step4 = findViewById<TextView>(R.id.step4)

        // Simulating AI analysis progress
        Handler(Looper.getMainLooper()).postDelayed({
            progressBar.progress = 50
            progressText.text = "Progress 50%"
            step2.text = "✓ Scanning Reviews"
            step2.setTextColor(getColor(android.R.color.holo_green_dark))
            step3.text = "▶ Detecting Patterns"
            step3.setTextColor(getColor(android.R.color.black))
        }, 1500)

        Handler(Looper.getMainLooper()).postDelayed({
            progressBar.progress = 75
            progressText.text = "Progress 75%"
            step3.text = "✓ Detecting Patterns"
            step3.setTextColor(getColor(android.R.color.holo_green_dark))
            step4.text = "▶ Calculating Trust Score"
            step4.setTextColor(getColor(android.R.color.black))
        }, 3000)

        Handler(Looper.getMainLooper()).postDelayed({
            progressBar.progress = 100
            progressText.text = "Analysis Complete"
            step4.text = "✓ Calculating Trust Score"
            step4.setTextColor(getColor(android.R.color.holo_green_dark))
            
            // Future: Show result screen or dialog
        }, 4500)
    }
}