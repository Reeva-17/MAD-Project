package com.example.trustlens

import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class RiskDetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_risk_details)

        val backButton = findViewById<ImageView>(R.id.backbtn)

        backButton?.setOnClickListener {
            finish()
        }
    }
}