package com.example.trustlens

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity

class SellerDashboardActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_seller_dashboard2)

        setupButtons()
    }

    private fun setupButtons() {
        // Back Button
        findViewById<ImageButton>(R.id.backButton).setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        // Settings Button
        findViewById<ImageButton>(R.id.btn_settings_dashboard).setOnClickListener {
            startActivity(Intent(this, SettingsActivity::class.java))
        }

        // View Store Button
        findViewById<LinearLayout>(R.id.btn_view_store).setOnClickListener {
            startActivity(Intent(this, ViewStoreActivity::class.java))
        }
    }
}
