package com.example.trustlens.model

data class Product(
    val id: String = "",
    val name: String = "",
    val price: String = "",
    val sellerHandle: String = "",
    val category: String = "",
    val imageUrl: String = "",
    val trustScore: Int = 85
)
