package com.example.trustlens

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class AnalyzeActivity : AppCompatActivity() {
    
    private val handler = Handler(Looper.getMainLooper())
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_analyze)

        val progressBar = findViewById<ProgressBar>(R.id.progressBar)
        val progressText = findViewById<TextView>(R.id.progressText)
        val step2 = findViewById<TextView>(R.id.step2)
        val step3 = findViewById<TextView>(R.id.step3)
        val step4 = findViewById<TextView>(R.id.step4)
        val analyzingTitle = findViewById<TextView>(R.id.analyzingTitle)

        // Get the search query from Intent
        val query = intent.getStringExtra("SEARCH_QUERY") ?: "Product"
        analyzingTitle.text = getString(R.string.analyzing_business, query)

        // Simulating AI analysis progress
        handler.postDelayed({
            if (!isFinishing && !isDestroyed) {
                progressBar.progress = 50
                progressText.text = "Progress 50%"
                step2.text = "✓ Scanning Reviews"
                step2.setTextColor(getColor(android.R.color.holo_green_dark))
                step3.text = "▶ Detecting Patterns"
                step3.setTextColor(getColor(android.R.color.black))
            }
        }, 1000)

        handler.postDelayed({
            if (!isFinishing && !isDestroyed) {
                progressBar.progress = 100
                progressText.text = "Analysis Complete"
                step3.text = "✓ Detecting Patterns"
                step3.setTextColor(getColor(android.R.color.holo_green_dark))
                step4.text = "✓ Calculating Trust Score"
                step4.setTextColor(getColor(android.R.color.holo_green_dark))
                
                // Navigate to TrustAnalysisActivity after analysis
                val intent = Intent(this, TrustAnalysisActivity::class.java)
                startActivity(intent)
                finish()
            }
        }, 2500)
    }

    override fun onDestroy() {
        super.onDestroy()
        handler.removeCallbacksAndMessages(null)
    }
}