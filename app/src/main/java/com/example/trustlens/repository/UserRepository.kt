package com.example.trustlens.repository

import com.example.trustlens.model.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

object UserRepository {

    private val auth = FirebaseAuth.getInstance()
    private val dbRef: DatabaseReference = FirebaseDatabase.getInstance(
        "https://mad-backend-79e1d-default-rtdb.asia-southeast1.firebasedatabase.app/"
    ).getReference("users")

    // 🔹 SAVE USER PROFILE TO DATABASE
    fun saveUserProfile(user: User, onSuccess: () -> Unit, onFailure: (String) -> Unit) {
        val userId = user.id.ifEmpty { auth.currentUser?.uid ?: return }
        
        dbRef.child(userId).setValue(user)
            .addOnSuccessListener { onSuccess() }
            .addOnFailureListener { onFailure(it.message ?: "Failed to save profile") }
    }

    // 🔹 GET CURRENT USER DATA
    fun getCurrentUser(callback: (User?) -> Unit) {
        val userId = auth.currentUser?.uid ?: return callback(null)
        
        dbRef.child(userId).addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val user = snapshot.getValue(User::class.java)
                callback(user)
            }
            override fun onCancelled(error: DatabaseError) {
                callback(null)
            }
        })
    }
}
