package com.example.trustlens

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class ProductDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_detail)

        val name = intent.getStringExtra("PRODUCT_NAME") ?: "Product Name"
        val price = intent.getStringExtra("PRODUCT_PRICE") ?: "Price"
        val seller = intent.getStringExtra("PRODUCT_SELLER") ?: "Seller: @store"

        val tvName = findViewById<TextView>(R.id.tv_product_detail_name)
        val tvPrice = findViewById<TextView>(R.id.tv_product_detail_price)
        val tvSeller = findViewById<TextView>(R.id.tv_product_detail_seller)
        val btnBuyNow = findViewById<Button>(R.id.btn_buy_now)

        tvName.text = name
        tvPrice.text = price
        tvSeller.text = "Seller: $seller"

        btnBuyNow.setOnClickListener {
            Toast.makeText(this, "Buying $name...", Toast.LENGTH_SHORT).show()
        }
    }
}