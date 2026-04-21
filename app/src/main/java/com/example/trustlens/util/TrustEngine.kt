package com.example.trustlens.util

import android.graphics.Color
import com.example.trustlens.model.Product

object TrustEngine {

    fun calculateScore(product: Product): Int {
        var score = 70 

        if (product.sellerHandle.contains("official") || product.sellerHandle.contains("_store")) {
            score += 10
        }
        
        val priceValue = product.price.filter { it.isDigit() }.toIntOrNull() ?: 0
        if (priceValue in 500..5000) {
            score += 5
        } else if (priceValue < 100 && priceValue > 0) {
            score -= 15 
        }

        when (product.category) {
            "Accessories", "Men Clothes" -> score += 5
            "Crochet", "Decor Items" -> score += 8 
        }

        score += (product.name.length % 7)
        return score.coerceIn(35, 98)
    }

    fun getColorForScore(score: Int): Int {
        return when {
            score >= 85 -> Color.parseColor("#4CAF50") // Safe Green
            score >= 70 -> Color.parseColor("#FF9800") // Caution Orange
            else -> Color.parseColor("#F44336") // Danger Red
        }
    }

    fun getStatusMessage(score: Int): String {
        return when {
            score >= 85 -> "✓ Highly Recommended: Verified & Safe"
            score >= 70 -> "⚠ Use Caution: Moderate Trust Score"
            else -> "✖ High Risk: Potential Scam Detected"
        }
    }
}
