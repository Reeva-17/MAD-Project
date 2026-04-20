package com.example.trustlens   // use your actual package name

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)  // your XML file name

        // Delay for 2 seconds, then move to RoleSelectionActivity
        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this, RoleSelectionActivity::class.java)
            startActivity(intent)
            finish()  // close splash so user can’t go back to it
        }, 2000)
    }
}

