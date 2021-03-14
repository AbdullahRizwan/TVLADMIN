package com.example.admin.repository

import com.google.firebase.firestore.FirebaseFirestore

class VehicleRepository {

    val db : FirebaseFirestore = FirebaseFirestore.getInstance()

    fun addVehicle(make: String, model: String, year: String){
        val data = hashMapOf(
                "make" to make,
                "model" to model,
                "year" to year
        )
        db.collection("Vehicle").add(data)
    }
}