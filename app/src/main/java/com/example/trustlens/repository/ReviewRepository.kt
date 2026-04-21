package com.example.trustlens.repository

import com.example.trustlens.model.Review
import com.google.firebase.database.*

object ReviewRepository {

    private val dbRef: DatabaseReference = FirebaseDatabase.getInstance(
        "https://mad-backend-79e1d-default-rtdb.asia-southeast1.firebasedatabase.app/"
    ).getReference("reviews")

    fun addReview(review: Review, onSuccess: () -> Unit, onFailure: (String) -> Unit) {
        val id = dbRef.push().key ?: return
        val reviewWithId = review.copy(id = id)
        
        dbRef.child(id).setValue(reviewWithId)
            .addOnSuccessListener { onSuccess() }
            .addOnFailureListener { onFailure(it.message ?: "Failed to add review") }
    }

    fun getReviewsForProduct(productId: String, callback: (List<Review>) -> Unit) {
        dbRef.orderByChild("productId").equalTo(productId)
            .addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val list = mutableListOf<Review>()
                    for (data in snapshot.children) {
                        data.getValue(Review::class.java)?.let { list.add(it) }
                    }
                    callback(list)
                }
                override fun onCancelled(error: DatabaseError) {
                    callback(emptyList())
                }
            })
    }
    
    fun seedDemoReviews(productId: String, productName: String, trustScore: Int) {
        val demoReviews = when {
            trustScore >= 85 -> listOf(
                Review(productId = productId, userName = "Sarah M.", comment = "Love this $productName! Genuine product.", rating = 5f),
                Review(productId = productId, userName = "Aman K.", comment = "High quality and fast delivery.", rating = 5f),
                Review(productId = productId, userName = "Priya S.", comment = "Exactly as described. Worth the price.", rating = 4f)
            )
            trustScore >= 65 -> listOf(
                Review(productId = productId, userName = "Rohan G.", comment = "It's okay, but delivery took too long.", rating = 3f),
                Review(productId = productId, userName = "Neha V.", comment = "Average quality for the $productName.", rating = 4f),
                Review(productId = productId, userName = "Vikram L.", comment = "Packaging was damaged, product is fine.", rating = 3f)
            )
            else -> listOf(
                Review(productId = productId, userName = "Deepak P.", comment = "FRAUD ALERT! Don't buy this $productName.", rating = 1f),
                Review(productId = productId, userName = "Ananya T.", comment = "Waste of money. Horrible experience.", rating = 1f),
                Review(productId = productId, userName = "Suresh M.", comment = "Never received my order. Seller is fake.", rating = 1f)
            )
        }
        
        dbRef.orderByChild("productId").equalTo(productId).addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (!snapshot.exists()) {
                    demoReviews.forEach { addReview(it, {}, {}) }
                }
            }
            override fun onCancelled(error: DatabaseError) {}
        })
    }
}
