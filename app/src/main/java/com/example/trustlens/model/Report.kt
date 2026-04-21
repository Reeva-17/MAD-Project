package com.example.trustlens.model

data class Report(
    val id: String = "",
    val userId: String = "",
    val targetHandle: String = "",
    val issueType: String = "",
    val details: String = "",
    val status: String = "pending",
    val timestamp: Long = System.currentTimeMillis()
)
