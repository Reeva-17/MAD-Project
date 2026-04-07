package com.example.trustlens

import android.content.Intent
import android.os.Bundle
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity

class RoleSelectionActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_role_selection)

        val user = findViewById<LinearLayout>(R.id.userCard)
        val seller = findViewById<LinearLayout>(R.id.sellerCard)

        user.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }

        seller.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }
    }
}