package com.example.madfirebase

import com.google.firebase.database.*

object UserRepository {

    private val ref: DatabaseReference = FirebaseDatabase.getInstance(
        "https://mad-backend-79e1d-default-rtdb.asia-southeast1.firebasedatabase.app/"
    ).getReference("users")

    // 🔹 ADD USER
    fun addUser(user: User, onSuccess: () -> Unit, onFailure: (String) -> Unit) {

        val userId = ref.push().key ?: return

        ref.child(userId).setValue(user)
            .addOnSuccessListener { onSuccess() }
            .addOnFailureListener { onFailure(it.message ?: "Error") }
    }

    // 🔹 GET USERS
    fun getUsers(callback: (List<User>) -> Unit) {

        ref.addValueEventListener(object : ValueEventListener {

            override fun onDataChange(snapshot: DataSnapshot) {

                val list = ArrayList<User>()

                for (data in snapshot.children) {
                    val user = data.getValue(User::class.java)
                    if (user != null) list.add(user)
                }

                callback(list)
            }

            override fun onCancelled(error: DatabaseError) {}
        })
    }
}