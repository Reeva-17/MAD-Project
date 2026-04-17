package com.example.trustlens

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class HomeActivity : AppCompatActivity() {

    private lateinit var role: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home_screen)

        // Get role passed from LoginActivity or SignupActivity
        role = intent.getStringExtra("ROLE") ?: "USER"

        // Profile icon → UserDashboard or SellerDashboard
        val profileIcon = findViewById<ImageView>(R.id.profileButton)
        profileIcon.setOnClickListener {
            if (role == "USER") {
                startActivity(Intent(this, UserDashboardActivity::class.java))
            } else {
                startActivity(Intent(this, SellerDashboardActivity::class.java))
            }
        }

        // Settings icon → SettingsActivity
        val settingsIcon = findViewById<ImageView>(R.id.btnSettings)
        settingsIcon.setOnClickListener {
            startActivity(Intent(this, SettingsActivity::class.java))
        }

        // Category clicks → CategoryActivity (reuse one activity, pass category name)
        val womenCategory = findViewById<TextView>(R.id.catWomen)
        womenCategory.setOnClickListener {
            val intent = Intent(this, CategoryActivity::class.java)
            intent.putExtra("CATEGORY", "WOMEN")
            startActivity(intent)
        }

        val menCategory = findViewById<TextView>(R.id.catMen)
        menCategory.setOnClickListener {
            val intent = Intent(this, CategoryActivity::class.java)
            intent.putExtra("CATEGORY", "MEN")
            startActivity(intent)
        }

        val kidsCategory = findViewById<TextView>(R.id.catKids)
        kidsCategory.setOnClickListener {
            val intent = Intent(this, CategoryActivity::class.java)
            intent.putExtra("CATEGORY", "KIDS")
            startActivity(intent)
        }

        // Example product clicks → ProductDetailActivity (reuse one product screen)
        val product1 = findViewById<TextView>(R.id.productName1)
        product1.setOnClickListener {
            val intent = Intent(this, ProductDetailActivity::class.java)
            intent.putExtra("PRODUCT_NAME", "Premium Jacket")
            intent.putExtra("PRODUCT_PRICE", "700/-")
            intent.putExtra("PRODUCT_SELLER", "@fashion_store")
            startActivity(intent)
        }

        val product2 = findViewById<TextView>(R.id.productName2)
        product2.setOnClickListener {
            val intent = Intent(this, ProductDetailActivity::class.java)
            intent.putExtra("PRODUCT_NAME", "Skincare Set")
            intent.putExtra("PRODUCT_PRICE", "1000/-")
            intent.putExtra("PRODUCT_SELLER", "@beauty_essentials")
            startActivity(intent)
        }

        val product3 = findViewById<TextView>(R.id.productna3)
        product3.setOnClickListener {
            val intent = Intent(this, ProductDetailActivity::class.java)
            intent.putExtra("PRODUCT_NAME", "Running Shoes")
            intent.putExtra("PRODUCT_PRICE", "800/-")
            intent.putExtra("PRODUCT_SELLER", "@sneeker_hub")
            startActivity(intent)
        }

        val product4 = findViewById<TextView>(R.id.product4)
        product4.setOnClickListener {
            val intent = Intent(this, ProductDetailActivity::class.java)
            intent.putExtra("PRODUCT_NAME", "Smart Watch")
            intent.putExtra("PRODUCT_PRICE", "900/-")
            intent.putExtra("PRODUCT_SELLER", "@tech_deals")
            startActivity(intent)
        }
    }
}
