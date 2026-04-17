package com.example.trustlens

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class CategoryActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category)

        val categoryName = intent.getStringExtra("CATEGORY_NAME") ?: "Category"
        findViewById<TextView>(R.id.categoryTitle).text = categoryName

        // Future: Initialize RecyclerView with items filtered by category
    }
}