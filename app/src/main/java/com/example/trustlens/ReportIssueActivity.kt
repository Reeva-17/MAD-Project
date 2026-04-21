package com.example.trustlens

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import com.example.trustlens.model.Report
import com.example.trustlens.repository.ReportRepository
import com.google.firebase.auth.FirebaseAuth

class ReportIssueActivity : AppCompatActivity() {

    private var selectedIssueType: String = ""
    private val auth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_report_issue)

        val etHandle = findViewById<EditText>(R.id.handleInput)
        val etDetails = findViewById<EditText>(R.id.detailsInput)
        val btnSubmit = findViewById<Button>(R.id.submitreport)

        // Setup issue type selection
        val cards = listOf(
            findViewById<CardView>(R.id.cardScam) to "Scam",
            findViewById<CardView>(R.id.cardFakeProduct) to "Fake Product",
            findViewById<CardView>(R.id.cardPaymentFraud) to "Payment Fraud",
            findViewById<CardView>(R.id.cardHarassment) to "Harassment",
            findViewById<CardView>(R.id.cardOther) to "Other"
        )

        cards.forEach { (card, type) ->
            card.setOnClickListener {
                selectedIssueType = type
                // Visual feedback (optional: add highlight logic)
                Toast.makeText(this, "Selected: $type", Toast.LENGTH_SHORT).show()
            }
        }

        findViewById<ImageView>(R.id.backButtonReport).setOnClickListener {
            finish()
        }

        btnSubmit.setOnClickListener {
            val handle = etHandle.text.toString().trim()
            val details = etDetails.text.toString().trim()

            if (handle.isEmpty()) {
                Toast.makeText(this, "Please enter a handle or link", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (selectedIssueType.isEmpty()) {
                Toast.makeText(this, "Please select an issue type", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val report = Report(
                userId = auth.currentUser?.uid ?: "anonymous",
                targetHandle = handle,
                issueType = selectedIssueType,
                details = details
            )

            ReportRepository.addReport(report,
                onSuccess = {
                    Toast.makeText(this, "Report Submitted Successfully", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, ReportSuccessActivity::class.java)
                    startActivity(intent)
                    finish()
                },
                onFailure = { error ->
                    Toast.makeText(this, "Error: $error", Toast.LENGTH_SHORT).show()
                }
            )
        }
    }
}
