package com.example.trustlens

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity

class UserDashboardActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_dashboard)

        findViewById<ImageButton>(R.id.backButtonUser).setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        findViewById<ImageButton>(R.id.btn_settings_user).setOnClickListener {
            startActivity(Intent(this, SettingsActivity::class.java))
        }
    }
}