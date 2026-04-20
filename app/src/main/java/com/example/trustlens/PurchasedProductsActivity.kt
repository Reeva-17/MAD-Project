package com.example.trustlens

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RatingBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class PurchasedProductsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.item_purchased_card)

        setupClickListeners()
        setupRatingInteractions()
    }

    private fun setupRatingInteractions() {
        val ratings = listOf(
            R.id.rating1, R.id.rating2, R.id.rating3
        )
        
        ratings.forEach { id ->
            findViewById<RatingBar>(id).setOnRatingBarChangeListener { ratingBar, rating, fromUser ->
                if (fromUser) {
                    Toast.makeText(this, "Rating: ${rating.toInt()} Stars", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun setupClickListeners() {
        // Report Buttons
        findViewById<TextView>(R.id.btnReportItem1).setOnClickListener { navigateToReport() }
        findViewById<TextView>(R.id.btnReportItem2).setOnClickListener { navigateToReport() }
        findViewById<TextView>(R.id.btnReportItem3).setOnClickListener { navigateToReport() }

        // Submit Buttons
        findViewById<Button>(R.id.btnSubmit1).setOnClickListener { 
            val rating = findViewById<RatingBar>(R.id.rating1).rating
            val review = findViewById<EditText>(R.id.etReview1).text.toString()
            submitFeedback(rating, review)
        }
        findViewById<Button>(R.id.btnSubmit2).setOnClickListener { 
            val rating = findViewById<RatingBar>(R.id.rating2).rating
            val review = findViewById<EditText>(R.id.etReview2).text.toString()
            submitFeedback(rating, review)
        }
        findViewById<Button>(R.id.btnSubmit3).setOnClickListener { 
            val rating = findViewById<RatingBar>(R.id.rating3).rating
            val review = findViewById<EditText>(R.id.etReview3).text.toString()
            submitFeedback(rating, review)
        }

        // Card Clicks for Product Detail
        findViewById<androidx.cardview.widget.CardView>(R.id.purchasedCard1).setOnClickListener { openProductDetail() }
        findViewById<androidx.cardview.widget.CardView>(R.id.purchasedCard2).setOnClickListener { openProductDetail() }
        findViewById<androidx.cardview.widget.CardView>(R.id.purchasedCard3).setOnClickListener { openProductDetail() }
    }

    private fun openProductDetail() {
        startActivity(Intent(this, ProductActivity::class.java))
    }

    private fun navigateToReport() {
        startActivity(Intent(this, ReportIssueActivity::class.java))
    }

    private fun submitFeedback(rating: Float, review: String) {
        if (rating == 0f) {
            Toast.makeText(this, "Please provide a star rating", Toast.LENGTH_SHORT).show()
            return
        }
        if (review.isBlank()) {
            Toast.makeText(this, "Please write a short review", Toast.LENGTH_SHORT).show()
            return
        }
        Toast.makeText(this, "Submitted! Rating: ${rating.toInt()} Stars\nReview: $review", Toast.LENGTH_LONG).show()
    }
}
