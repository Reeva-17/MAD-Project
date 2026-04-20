package com.example.trustlens

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class UserDashboardActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        findViewById<ImageView>(R.id.backButtonProfile).setOnClickListener {
            finish()
        }

        findViewById<ImageView>(R.id.settingsButtonProfile).setOnClickListener {
            startActivity(Intent(this, SettingsActivity::class.java))
        }

        findViewById<androidx.cardview.widget.CardView>(R.id.btnViewPurchased).setOnClickListener {
            startActivity(Intent(this, PurchasedProductsActivity::class.java))
        }

        findViewById<androidx.cardview.widget.CardView>(R.id.cardProduct1).setOnClickListener {
            startActivity(Intent(this, ProductActivity::class.java))
        }
        findViewById<androidx.cardview.widget.CardView>(R.id.cardProduct2).setOnClickListener {
            startActivity(Intent(this, ProductActivity::class.java))
        }
        findViewById<androidx.cardview.widget.CardView>(R.id.cardProduct3).setOnClickListener {
            startActivity(Intent(this, ProductActivity::class.java))
        }
    }
}
