package com.example.trustlens.repository

import com.example.trustlens.model.Report
import com.google.firebase.database.*

object ReportRepository {

    private val ref = FirebaseDatabase.getInstance(
        "https://mad-backend-79e1d-default-rtdb.asia-southeast1.firebasedatabase.app/"
    ).getReference("reports")

    fun addReport(report: Report, onSuccess: () -> Unit, onFailure: (String) -> Unit) {

        val id = ref.push().key ?: return

        ref.child(id).setValue(report)
            .addOnSuccessListener { onSuccess() }
            .addOnFailureListener { onFailure(it.message ?: "Error") }
    }

    fun getReports(callback: (List<Report>) -> Unit) {

        ref.addValueEventListener(object : ValueEventListener {

            override fun onDataChange(snapshot: DataSnapshot) {

                val list = mutableListOf<Report>()

                for (data in snapshot.children) {
                    val report = data.getValue(Report::class.java)
                    if (report != null) list.add(report)
                }

                callback(list)
            }

            override fun onCancelled(error: DatabaseError) {}
        })
    }
}
