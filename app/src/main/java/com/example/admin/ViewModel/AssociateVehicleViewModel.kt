package com.example.admin.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.admin.Model.Result
import com.example.admin.Model.Vehicle
import com.example.admin.form.AddVehicleFormState
import com.google.firebase.firestore.FirebaseFirestore


class AssociateVehicleViewModel : ViewModel()  {
    var data= MutableLiveData<ArrayList<Vehicle>>()
    val vehicleFormData: LiveData<ArrayList<Vehicle>> = data

    private val _vehicleForm = MutableLiveData<AddVehicleFormState>()
    val vehicleForm : LiveData<AddVehicleFormState> = _vehicleForm

    private val _result = MutableLiveData<Result<String>>()
    val result : LiveData<Result<String>> = _result

    private val userLiveData = MutableLiveData<ArrayList<Vehicle>>()
    val userArrayList: LiveData<ArrayList<Vehicle>> = userLiveData

    init{
        getData()
    }

    private fun getData(){
        var list = ArrayList<Vehicle>()
        val db : FirebaseFirestore = FirebaseFirestore.getInstance()
        var col = db.collection("Vehicle").get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    var a = document.data
                    list.add(Vehicle(a["model"] as String?,a["make"] as String? , a["year"] as String?))

                }
                data.value = list
            }
            .addOnFailureListener { exception ->

            }
    }


    private fun checkYear(year: String): Boolean {
        return year.isNotBlank()
    }


}