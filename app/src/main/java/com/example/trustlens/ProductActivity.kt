package com.example.trustlens

import android.content.res.ColorStateList
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.trustlens.model.Review
import com.example.trustlens.repository.ReviewRepository
import com.example.trustlens.repository.UserRepository
import com.example.trustlens.util.TrustEngine
import com.google.firebase.auth.FirebaseAuth

class ProductActivity : AppCompatActivity() {
    
    private val auth = FirebaseAuth.getInstance()
    private lateinit var productId: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product)

        // 1. Get detailed data from intent
        val name = intent.getStringExtra("PRODUCT_NAME") ?: "Product Name"
        productId = name.lowercase().replace(" ", "_")
        val price = intent.getStringExtra("PRODUCT_PRICE") ?: "$0.00"
        val seller = intent.getStringExtra("PRODUCT_SELLER") ?: "@seller"
        val imageResId = intent.getIntExtra("PRODUCT_IMAGE", R.drawable.premiumshoes)
        val score = intent.getIntExtra("PRODUCT_SCORE", 85)

        // 2. Update Main Product Info
        findViewById<TextView>(R.id.productNameDetail).text = name
        findViewById<TextView>(R.id.productPriceDetail).text = price
        findViewById<TextView>(R.id.sellerHandleDetail).text = seller
        findViewById<ImageView>(R.id.productImageDetail).setImageResource(imageResId)
        
        // 3. Dynamic Color Coding for Score
        val scoreTv = findViewById<TextView>(R.id.trustScoreValueDetail)
        scoreTv.text = score.toString()
        scoreTv.setTextColor(TrustEngine.getColorForScore(score))
        
        // Update ALL AI Analysis Bars dynamically with colors
        updateProgressBar(R.id.authProgressBar, R.id.authScoreText, score)
        updateProgressBar(R.id.engProgressBar, R.id.engScoreText, (score - 5).coerceAtLeast(0))
        updateProgressBar(R.id.relProgressBar, R.id.relScoreText, (score - 2).coerceAtLeast(0))
        updateProgressBar(R.id.compProgressBar, R.id.compScoreText, (score + 3).coerceAtMost(100))

        // 4. Load Reviews (will vary by product)
        loadReviews(name, score)

        // 5. Buttons
        findViewById<Button>(R.id.btnViewFullAnalysis).setOnClickListener {
            showAddReviewDialog()
        }
        findViewById<ImageView>(R.id.btnBackProduct).setOnClickListener {
            finish()
        }
    }

    private fun updateProgressBar(barId: Int, textId: Int, score: Int) {
        val bar = findViewById<ProgressBar>(barId)
        val text = findViewById<TextView>(textId)
        val color = TrustEngine.getColorForScore(score)
        
        bar.progress = score
        bar.progressTintList = ColorStateList.valueOf(color)
        text.text = "$score%"
        text.setTextColor(color)
    }

    private fun loadReviews(name: String, score: Int) {
        val container = findViewById<LinearLayout>(R.id.feedbackContainer) ?: return
        container.removeAllViews()
        
        // Pass name and score to seed dynamic reviews
        ReviewRepository.seedDemoReviews(productId, name, score)

        ReviewRepository.getReviewsForProduct(productId) { reviews ->
            container.removeAllViews()
            for (review in reviews) {
                val view = LayoutInflater.from(this).inflate(R.layout.item_feedback, container, false)
                view.findViewById<TextView>(R.id.reviewerName).text = review.userName
                view.findViewById<TextView>(R.id.reviewComment).text = review.comment
                view.findViewById<TextView>(R.id.reviewRating).text = "⭐".repeat(review.rating.toInt())
                container.addView(view)
            }
        }
    }

    private fun showAddReviewDialog() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Add a Review")
        val input = EditText(this)
        input.hint = "Your feedback..."
        builder.setView(input)

        builder.setPositiveButton("Submit") { _, _ ->
            val comment = input.text.toString()
            if (comment.isNotEmpty()) {
                UserRepository.getCurrentUser { user ->
                    val review = Review(
                        productId = productId,
                        userId = auth.currentUser?.uid ?: "",
                        userName = user?.name ?: "Anonymous",
                        comment = comment,
                        rating = 5f
                    )
                    ReviewRepository.addReview(review, {
                        Toast.makeText(this, "Review added!", Toast.LENGTH_SHORT).show()
                    }, {
                        Toast.makeText(this, "Failed to add review", Toast.LENGTH_SHORT).show()
                    })
                }
            }
        }
        builder.setNegativeButton("Cancel") { dialog, _ -> dialog.cancel() }
        builder.show()
    }
}
