package com.example.trustlens

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import androidx.cardview.widget.CardView

class TrustAnalysisActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_trust_analysis)

        val cardReport = findViewById<CardView>(R.id.tvReportIssue)

        // TrustAnalysisActivity.kt ke onCreate mein:
        val btnViewDetails = findViewById<Button>(R.id.viewbtn) // ID check kar lena

        btnViewDetails.setOnClickListener {
            startActivity(Intent(this, RiskDetailsActivity::class.java))
        }

        cardReport.setOnClickListener {
            val intent = Intent(this, ReportIssueActivity::class.java)
            startActivity(intent)
        }
    }
}