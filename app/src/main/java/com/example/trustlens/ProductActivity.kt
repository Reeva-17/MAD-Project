package com.example.trustlens

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class ProductActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product)

        // Back button
        findViewById<ImageView>(R.id.btnBackProduct).setOnClickListener {
            finish()
        }

        // "View Full Analysis" button
        findViewById<Button>(R.id.btnViewFullAnalysis).setOnClickListener {
            val intent = Intent(this, AnalyzeActivity::class.java)
            startActivity(intent)
        }
    }
}
