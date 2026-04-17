package com.example.trustlens

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import androidx.appcompat.widget.SwitchCompat
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class SettingsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        val backButton = findViewById<ImageButton>(R.id.backButton)
        val darkModeSwitch = findViewById<SwitchCompat>(R.id.darkModeSwitch)
        val riskAlertsSwitch = findViewById<SwitchCompat>(R.id.riskAlertsSwitch)
        val privacyControls = findViewById<Button>(R.id.privacyControls)
        val helpFaq = findViewById<Button>(R.id.helpFaq)
        val sendFeedback = findViewById<Button>(R.id.sendFeedback)
        val logout = findViewById<Button>(R.id.logout)

        backButton.setOnClickListener {
            finish()
        }

        darkModeSwitch.setOnCheckedChangeListener { _, isChecked ->
            Toast.makeText(this, "Dark Mode: $isChecked", Toast.LENGTH_SHORT).show()
        }

        riskAlertsSwitch.setOnCheckedChangeListener { _, isChecked ->
            Toast.makeText(this, "Risk Alerts: $isChecked", Toast.LENGTH_SHORT).show()
        }

        privacyControls.setOnClickListener {
            Toast.makeText(this, "Privacy Controls clicked", Toast.LENGTH_SHORT).show()
        }

        helpFaq.setOnClickListener {
            Toast.makeText(this, "Help & FAQ clicked", Toast.LENGTH_SHORT).show()
        }

        sendFeedback.setOnClickListener {
            Toast.makeText(this, "Feedback clicked", Toast.LENGTH_SHORT).show()
        }

        logout.setOnClickListener {
            val intent = Intent(this, RoleSelectionActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
        }
    }
}