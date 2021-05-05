package com.example.admin.ViewModel

import androidx.lifecycle.MutableLiveData
import com.example.admin.Model.Part
import com.example.admin.Model.Vehicle
import com.google.firebase.firestore.FirebaseFirestore

class AssociatePartViewModel {
    var data = MutableLiveData<ArrayList<Part>>()

    init {
        getData()
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
                data.value = list
            }
            .addOnFailureListener { exception ->

            }
    }

}