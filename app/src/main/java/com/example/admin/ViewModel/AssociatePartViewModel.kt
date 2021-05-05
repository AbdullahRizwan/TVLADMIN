package com.example.admin.ViewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.admin.Model.Part
import com.example.admin.Model.Result
import com.example.admin.Model.Vehicle
import com.google.firebase.firestore.FirebaseFirestore

class AssociatePartViewModel {
    var data = MutableLiveData<ArrayList<Part>>()
    private val _result = MutableLiveData<Result<String>>()
    val result : LiveData<Result<String>> = _result
    val db: FirebaseFirestore = FirebaseFirestore.getInstance()


    init {
        getData()
    }

    fun addCompleteVehicle(vehicle: Vehicle) {
        val data = hashMapOf(
            "make" to vehicle.make,
            "model" to vehicle.model,
            "year" to vehicle.year,
            "parts" to vehicle.parts
        )
        db.collection("CompleteVehicle").add(data).addOnSuccessListener { documentReference ->
            _result.value = Result.Success<String>(documentReference.toString())
            Log.d("Result", documentReference.toString())
        }.addOnFailureListener { e ->
            _result.value = Result.Error(e)
            Log.d("Result", e.toString())
        }
    }

    private fun getData() {
        var list = ArrayList<Part>()
        val db: FirebaseFirestore = FirebaseFirestore.getInstance()
        var col = db.collection("Part").get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    var a = document.data
                    list.add(
                        Part(
                            document.id,
                            a["name"] as String?,
                            a["type"] as String?,
                            a["life"] as String?,
                            a["description"] as String?
                        )
                    )

                }
                list.sortByDescending { it  -> it.name }
                list.reverse()
                data.value = list
            }
            .addOnFailureListener { exception ->

            }
    }

}