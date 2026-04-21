package com.example.trustlens.model

data class Review(
    val id: String = "",
    val productId: String = "",
    val userId: String = "",
    val userName: String = "",
    val comment: String = "",
    val rating: Float = 0f,
    val timestamp: Long = System.currentTimeMillis()
)
