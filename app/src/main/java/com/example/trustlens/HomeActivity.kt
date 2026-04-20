package com.example.trustlens

import android.content.Intent
import android.os.Bundle
import android.view.inputmethod.EditorInfo
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.imageview.ShapeableImageView

class HomeActivity : AppCompatActivity() {

    private lateinit var role: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home_screen)

        // Get role passed from LoginActivity or SignupActivity
        role = intent.getStringExtra("ROLE") ?: "USER"

        setupHeader()
        setupCategories()
        setupProducts()
        setupOtherFeatures()
    }

    private fun setupHeader() {
        // Profile icon → UserDashboard or SellerDashboard
        val profileIcon = findViewById<ShapeableImageView>(R.id.profileButton)
        profileIcon.setOnClickListener {
            if (role == "USER") {
                val intent = Intent(this, UserDashboardActivity::class.java)
                startActivity(intent)
            } else {
                startActivity(Intent(this, SellerDashboardActivity::class.java))
            }
        }

        // Settings icon → SettingsActivity
        val settingsIcon = findViewById<ImageButton>(R.id.btnSettings)
        settingsIcon.setOnClickListener {
            startActivity(Intent(this, SettingsActivity::class.java))
        }
    }

    private fun setupCategories() {
        // Category clicks
        findViewById<Button>(R.id.catWomen).setOnClickListener { openCategory("Women Clothes") }
        findViewById<Button>(R.id.catMen).setOnClickListener { openCategory("Men Clothes") }
        findViewById<Button>(R.id.catKids).setOnClickListener { openCategory("Kids Clothes") }
        findViewById<Button>(R.id.catAccessories).setOnClickListener { openCategory("Accessories") }
        findViewById<Button>(R.id.catCrochet).setOnClickListener { openCategory("Crochet") }
        findViewById<Button>(R.id.catDecor).setOnClickListener { openCategory("Decor Items") }
    }

    private fun openCategory(name: String) {
        val intent = Intent(this, CategoryActivity::class.java)
        intent.putExtra("CATEGORY_NAME", name)
        startActivity(intent)
    }

    private fun setupProducts() {
        // Product 1
        findViewById<LinearLayout>(R.id.productCard1).setOnClickListener {
            openProductDetail("Premium Jacket", "700/-", "@fashion_store")
        }
        // Product 2
        findViewById<LinearLayout>(R.id.productCard2).setOnClickListener {
            openProductDetail("Skincare Set", "1000/-", "@beauty_essentials")
        }
        // Product 3
        findViewById<LinearLayout>(R.id.productCard3).setOnClickListener {
            openProductDetail("Running Shoes", "800/-", "@sneaker_hub")
        }
        // Product 4
        findViewById<LinearLayout>(R.id.productCard4).setOnClickListener {
            openProductDetail("Smart Watch", "1500/-", "@tech_deals")
        }
        // Product 5
        findViewById<LinearLayout>(R.id.productCard5).setOnClickListener {
            openProductDetail("Casual Shirt", "435/-", "@fashion_store")
        }
        // Product 6
        findViewById<LinearLayout>(R.id.productCard6).setOnClickListener {
            openProductDetail("Perfume", "234/-", "@beauty_essentials")
        }
        // Product 7
        findViewById<LinearLayout>(R.id.productCard7).setOnClickListener {
            openProductDetail("Headphones", "2530/-", "@tech_deals")
        }
        // Product 8
        findViewById<LinearLayout>(R.id.productCard8).setOnClickListener {
            openProductDetail("Sneakers", "950/-", "@sneaker_hub")
        }
    }

    private fun openProductDetail(name: String, price: String, seller: String) {
        val intent = Intent(this, ProductActivity::class.java)
        intent.putExtra("PRODUCT_NAME", name)
        intent.putExtra("PRODUCT_PRICE", price)
        intent.putExtra("PRODUCT_SELLER", seller)
        startActivity(intent)
    }

    private fun setupOtherFeatures() {
        // Search bar
        val searchBar = findViewById<EditText>(R.id.searchBar)
        searchBar.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH || actionId == EditorInfo.IME_ACTION_DONE) {
                val query = searchBar.text.toString().trim()
                if (query.isNotEmpty()) {
                    val intent = Intent(this, AnalyzeActivity::class.java)
                    intent.putExtra("SEARCH_QUERY", query)
                    startActivity(intent)
                }
                true
            } else {
                false
            }
        }
        
        // Scam Card
        findViewById<LinearLayout>(R.id.scamCard).setOnClickListener {
            startActivity(Intent(this, AnalyzeActivity::class.java))
        }
    }
}
