package com.example.trustlens

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.trustlens.model.Product
import com.example.trustlens.repository.ProductRepository

class CategoryActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category)

        val categoryName = intent.getStringExtra("CATEGORY_NAME") ?: "Category"
        findViewById<TextView>(R.id.categoryTitle).text = categoryName

        findViewById<ImageView>(R.id.btnBackCategory).setOnClickListener {
            finish()
        }

        loadCategoryProducts(categoryName)
    }

    private fun loadCategoryProducts(category: String) {
        val container = findViewById<LinearLayout>(R.id.categoryProductContainer)
        container.removeAllViews()

        ProductRepository.getProductsByCategory(category) { products ->
            if (products.isEmpty()) {
                val emptyTv = TextView(this)
                emptyTv.text = "No products found in this category"
                emptyTv.textAlignment = View.TEXT_ALIGNMENT_CENTER
                container.addView(emptyTv)
            } else {
                for (product in products) {
                    val productView = LayoutInflater.from(this).inflate(R.layout.item_product, container, false)

                    productView.findViewById<TextView>(R.id.productName).text = product.name
                    productView.findViewById<TextView>(R.id.productPrice).text = product.price
                    productView.findViewById<TextView>(R.id.productSeller).text = product.sellerHandle
                    productView.findViewById<TextView>(R.id.productRating).text = product.trustScore.toString()

                    productView.setOnClickListener {
                        val intent = Intent(this, ProductActivity::class.java)
                        intent.putExtra("PRODUCT_NAME", product.name)
                        intent.putExtra("PRODUCT_PRICE", product.price)
                        intent.putExtra("PRODUCT_SELLER", product.sellerHandle)
                        intent.putExtra("PRODUCT_SCORE", product.trustScore)
                        // Use a default icon since we don't have URLs in demo data yet
                        intent.putExtra("PRODUCT_IMAGE", R.mipmap.ic_jacket_foreground)
                        startActivity(intent)
                    }
                    container.addView(productView)
                }
            }
        }
    }
}
