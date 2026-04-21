package com.example.trustlens

import android.content.Intent
import android.os.Bundle
import android.view.inputmethod.EditorInfo
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.trustlens.model.Product
import com.example.trustlens.repository.ProductRepository
import com.example.trustlens.util.TrustEngine
import com.google.android.material.imageview.ShapeableImageView

class HomeActivity : AppCompatActivity() {

    private lateinit var role: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home_screen)

        role = intent.getStringExtra("ROLE") ?: "USER"

        setupHeader()
        setupCategories()
        loadDemoDataIntoCards()
        setupOtherFeatures()
    }

    private fun setupHeader() {
        val profileIcon = findViewById<ShapeableImageView>(R.id.profileButton)
        profileIcon.setOnClickListener {
            if (role == "USER") {
                startActivity(Intent(this, UserDashboardActivity::class.java))
            } else {
                startActivity(Intent(this, SellerDashboardActivity::class.java))
            }
        }

        findViewById<ImageButton>(R.id.btnSettings).setOnClickListener {
            startActivity(Intent(this, SettingsActivity::class.java))
        }
    }

    private fun setupCategories() {
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

    private fun loadDemoDataIntoCards() {
        ProductRepository.getAllProducts { products ->
            if (products.isNotEmpty()) {
                // Update Cards using dynamic score calculation
                updateCard(1, products.getOrNull(0), R.mipmap.ic_jacket_foreground)
                updateCard(2, products.getOrNull(1), R.mipmap.ic_skincare_foreground)
                updateCard(3, products.getOrNull(2), R.mipmap.ic_shoes_foreground)
                updateCard(4, products.getOrNull(3), R.mipmap.ic_watch_foreground)
                updateCard(5, products.getOrNull(4), R.mipmap.ic_shirt_foreground)
                updateCard(6, products.getOrNull(5), R.mipmap.ic_perfume_foreground)
                updateCard(7, products.getOrNull(6), R.mipmap.ic_headphones_foreground)
                updateCard(8, products.getOrNull(7), R.mipmap.ic_sneakers_foreground)
            }
        }
    }

    private fun updateCard(index: Int, product: Product?, imageResId: Int) {
        if (product == null) return
        
        val dynamicScore = TrustEngine.calculateScore(product)
        
        val cardId = resources.getIdentifier("productCard$index", "id", packageName)
        val nameId = resources.getIdentifier("productName$index", "id", packageName)
        val priceId = resources.getIdentifier("productPrice$index", "id", packageName)
        val sellerId = resources.getIdentifier("productSeller$index", "id", packageName)
        val scoreId = resources.getIdentifier("productRating$index", "id", packageName)

        findViewById<TextView>(nameId)?.text = product.name
        findViewById<TextView>(priceId)?.text = product.price
        findViewById<TextView>(sellerId)?.text = product.sellerHandle
        findViewById<TextView>(scoreId)?.text = dynamicScore.toString()

        findViewById<LinearLayout>(cardId)?.setOnClickListener {
            openProductDetail(product.name, product.price, product.sellerHandle, imageResId, dynamicScore)
        }
    }

    private fun openProductDetail(name: String, price: String, seller: String, imageRes: Int, score: Int) {
        val intent = Intent(this, ProductActivity::class.java)
        intent.putExtra("PRODUCT_NAME", name)
        intent.putExtra("PRODUCT_PRICE", price)
        intent.putExtra("PRODUCT_SELLER", seller)
        intent.putExtra("PRODUCT_IMAGE", imageRes)
        intent.putExtra("PRODUCT_SCORE", score)
        startActivity(intent)
    }

    private fun setupOtherFeatures() {
        val searchBar = findViewById<EditText>(R.id.searchBar)
        searchBar.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
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
    }
}
