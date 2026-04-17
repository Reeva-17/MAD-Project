package com.example.trustlens

import android.content.Intent
import android.os.Bundle
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity

class RoleSelectionActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_role_selection)

        val userOption = findViewById<LinearLayout>(R.id.userOption)
        val sellerOption = findViewById<LinearLayout>(R.id.sellerOption)

        userOption.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            intent.putExtra("ROLE", "USER")
            startActivity(intent)
        }

        sellerOption.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            intent.putExtra("ROLE", "SELLER")
            startActivity(intent)
        }
    }
}

