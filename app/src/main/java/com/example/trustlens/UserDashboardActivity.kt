package com.example.trustlens

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.trustlens.repository.UserRepository

class UserDashboardActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        val nameTv = findViewById<TextView>(R.id.profileName)
        val emailTv = findViewById<TextView>(R.id.profileEmail)

        // Fetch and display real user data
        UserRepository.getCurrentUser { user ->
            if (user != null) {
                nameTv.text = user.name
                emailTv.text = user.email
            } else {
                Toast.makeText(this, "Welcome! Please update your profile.", Toast.LENGTH_SHORT).show()
            }
        }

        findViewById<ImageView>(R.id.backButtonProfile).setOnClickListener {
            finish()
        }

        findViewById<ImageView>(R.id.settingsButtonProfile).setOnClickListener {
            startActivity(Intent(this, SettingsActivity::class.java))
        }

        findViewById<androidx.cardview.widget.CardView>(R.id.btnViewPurchased).setOnClickListener {
            startActivity(Intent(this, PurchasedProductsActivity::class.java))
        }

        // Clicks for static products
        findViewById<androidx.cardview.widget.CardView>(R.id.cardProduct1).setOnClickListener {
            openProductDetail("Premium Jacket", "700/-", "@fashion_store")
        }
        findViewById<androidx.cardview.widget.CardView>(R.id.cardProduct2).setOnClickListener {
            openProductDetail("Smart Watch", "1500/-", "@tech_deals")
        }
        findViewById<androidx.cardview.widget.CardView>(R.id.cardProduct3).setOnClickListener {
            openProductDetail("Running Shoes", "800/-", "@sneaker_hub")
        }
    }

    private fun openProductDetail(name: String, price: String, seller: String) {
        val intent = Intent(this, ProductActivity::class.java)
        intent.putExtra("PRODUCT_NAME", name)
        intent.putExtra("PRODUCT_PRICE", price)
        intent.putExtra("PRODUCT_SELLER", seller)
        startActivity(intent)
    }
}
