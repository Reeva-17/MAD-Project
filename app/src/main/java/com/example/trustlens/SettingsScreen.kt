package com.example.trustlens

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.Switch
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class SettingsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        val backArrow = findViewById<ImageView>(R.id.backArrow)
        val darkModeSwitch = findViewById<Switch>(R.id.darkModeSwitch)
        val riskAlertsSwitch = findViewById<Switch>(R.id.riskAlertsSwitch)
        val privacyControls = findViewById<TextView>(R.id.privacyControls)
        val helpFaq = findViewById<TextView>(R.id.helpFaq)
        val sendFeedback = findViewById<TextView>(R.id.sendFeedback)
        val logout = findViewById<TextView>(R.id.logout)

        backArrow.setOnClickListener {
            finish() // go back
        }

        darkModeSwitch.setOnCheckedChangeListener { _, isChecked ->
            // Launch Dark Mode activity or apply theme
            val intent = Intent(this, DarkModeActivity::class.java)
            intent.putExtra("enabled", isChecked)
            startActivity(intent)
        }

        riskAlertsSwitch.setOnCheckedChangeListener { _, isChecked ->
            // Launch Risk Alerts activity
            val intent = Intent(this, RiskAlertsActivity::class.java)
            intent.putExtra("enabled", isChecked)
            startActivity(intent)
        }

        privacyControls.setOnClickListener {
            startActivity(Intent(this, PrivacyControlsActivity::class.java))
        }

        helpFaq.setOnClickListener {
            startActivity(Intent(this, HelpFaqActivity::class.java))
        }

        sendFeedback.setOnClickListener {
            startActivity(Intent(this, FeedbackActivity::class.java))
        }

        logout.setOnClickListener {
            startActivity(Intent(this, LogoutActivity::class.java))
        }
    }
}