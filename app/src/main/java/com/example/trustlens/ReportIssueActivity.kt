package com.example.trustlens

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

import android.widget.ImageView

class ReportIssueActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_report_issue)

        val btnSubmit = findViewById<Button>(R.id.submitreport)

        findViewById<ImageView>(R.id.backButtonReport).setOnClickListener {
            finish()
        }

        btnSubmit.setOnClickListener {
            val intent = Intent(this, ReportSuccessActivity::class.java)
            startActivity(intent)

            finish()
        }
    }
}