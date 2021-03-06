package com.example.admin.ViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.admin.Model.Vehicle
import com.google.firebase.firestore.FirebaseFirestore


class AssociateVehicleViewModel : ViewModel()  {
    var data = MutableLiveData<ArrayList<Vehicle>>()

    init {
        getData()
    }

    private fun getData() {
        var list = ArrayList<Vehicle>()
        val db: FirebaseFirestore = FirebaseFirestore.getInstance()
        var col = db.collection("Vehicle").get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    var a = document.data
                    list.add(
                        Vehicle(
                            a["model"] as String?,
                            a["make"] as String?,
                            a["year"] as String?
                        )
                    )

                }
                data.value = list
            }
            .addOnFailureListener { exception ->

            }
    }

}