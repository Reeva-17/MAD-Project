package com.example.trustlens

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.example.trustlens.repository.ProductRepository

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        // Seed products in background
        ProductRepository.seedDemoData()

        // Move to RoleSelectionActivity after 2 seconds
        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this, RoleSelectionActivity::class.java)
            startActivity(intent)
            finish()
        }, 2000)
    }
}
