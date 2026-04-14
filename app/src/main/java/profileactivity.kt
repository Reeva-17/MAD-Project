package com.example.trustlens  // <-- apna package name yahan likhna

import android.os.Bundle
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ProfileActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)  // layout file ka naam

        val name = findViewById<TextView>(R.id.profileName)
        val email = findViewById<TextView>(R.id.profileEmail)
        val analyses = findViewById<TextView>(R.id.analysesCount)
        val reports = findViewById<TextView>(R.id.reportsCount)
        val saved = findViewById<TextView>(R.id.savedCount)
        val savedProductsContainer = findViewById<LinearLayout>(R.id.savedContainer)

        // Dummy Data
        name.text = "Alex Johnson"
        email.text = "alex.johnson@email.com"
        analyses.text = "47 Analyses"
        reports.text = "12 Reports"
        saved.text = "23 Saved"

        // Example: Adding saved products dynamically
        val products = listOf(
            Triple("Premium Jacket", "@fashion_store", 92),
            Triple("Smart Watch", "@tech_deals", 95),
            Triple("Running Shoes", "@sneaker_hub", 85)
        )

        for (p in products) {
            val tv = TextView(this)
            tv.text = "${p.first} — ${p.second} — Score: ${p.third}"
            tv.textSize = 16f
            tv.setPadding(16, 8, 16, 8)
            savedProductsContainer.addView(tv)
        }
    }
}