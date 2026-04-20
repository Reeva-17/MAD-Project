package com.example.trustlens

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView

class TrustAnalysisActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_trust_analysis)

        val tvReportIssue = findViewById<android.widget.TextView>(R.id.tvReportIssue)

        // TrustAnalysisActivity.kt ke onCreate mein:
        val btnViewDetails = findViewById<android.widget.Button>(R.id.viewbtn) // ID check kar lena

        findViewById<ImageView>(R.id.backButtonTrust).setOnClickListener {
            finish()
        }

        btnViewDetails.setOnClickListener {
            startActivity(Intent(this, RiskDetailsActivity::class.java))
        }

        tvReportIssue.setOnClickListener {
            val intent = Intent(this, ReportIssueActivity::class.java)
            startActivity(intent)
        }
    }
}