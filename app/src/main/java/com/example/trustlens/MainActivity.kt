package com.example.trustlens

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.*

class MainActivity : AppCompatActivity() {

    private lateinit var ref: DatabaseReference
    private lateinit var resultText: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val nameInput = findViewById<EditText>(R.id.nameInput)
        val saveBtn = findViewById<Button>(R.id.saveBtn)
        resultText = findViewById(R.id.resultText)

        // 🔥 Firebase reference (clean way)
        ref = FirebaseDatabase.getInstance(
            "https://mad-backend-79e1d-default-rtdb.asia-southeast1.firebasedatabase.app/"
        ).getReference("users")

        // 🔥 Read data
        getUsers()

        // 🔥 Save data
        saveBtn.setOnClickListener {

            val name = nameInput.text.toString().trim()

            if (name.isEmpty()) {
                Toast.makeText(this, "Enter name", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val userId = ref.push().key

            if (userId == null) {
                Toast.makeText(this, "Error generating ID", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            ref.child(userId).setValue(name)
                .addOnSuccessListener {
                    Toast.makeText(this, "Saved!", Toast.LENGTH_SHORT).show()
                    nameInput.text.clear()
                }
                .addOnFailureListener {
                    Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                }
        }
    }

    // 🔥 Read users
    private fun getUsers() {

        ref.addValueEventListener(object : ValueEventListener {

            override fun onDataChange(snapshot: DataSnapshot) {

                val list = StringBuilder()

                for (data in snapshot.children) {
                    val value = data.getValue(String::class.java)
                    if (!value.isNullOrEmpty()) {
                        list.append(value).append("\n")
                    }
                }

                resultText.text = if (list.isEmpty()) {
                    "No data found"
                } else {
                    list.toString()
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@MainActivity, error.message, Toast.LENGTH_SHORT).show()
            }
        })
    }
}