package com.example.trustlens

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.trustlens.repository.UserRepository

class SellerDashboardActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_seller_dashboard2)

        val storeNameTv = findViewById<TextView>(R.id.tv_store_name)
        val storeHandleTv = findViewById<TextView>(R.id.tv_store_handle)

        // Load Seller Data from Backend
        UserRepository.getCurrentUser { user ->
            if (user != null) {
                storeNameTv.text = user.name
                storeHandleTv.text = "@${user.name.lowercase().replace(" ", "_")}"
            }
        }

        setupButtons()
    }

    private fun setupButtons() {
        findViewById<ImageButton>(R.id.backButton).setOnClickListener { finish() }
        findViewById<ImageButton>(R.id.btn_settings_dashboard).setOnClickListener {
            startActivity(Intent(this, SettingsActivity::class.java))
        }
        findViewById<Button>(R.id.btn_add_product).setOnClickListener {
            startActivity(Intent(this, AddProductActivity::class.java))
        }
        findViewById<LinearLayout>(R.id.btn_view_store).setOnClickListener {
            startActivity(Intent(this, ViewStoreActivity::class.java))
        }
    }
}
