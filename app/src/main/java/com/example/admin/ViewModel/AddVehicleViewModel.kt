package com.example.admin.ViewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.admin.Model.Vehicle
import com.example.admin.form.AddVehicleFormState
import com.example.admin.repository.VehicleRepository
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import com.example.admin.Model.Result


class AddVehicleViewModel : ViewModel()  {
    var data= MutableLiveData<Vehicle>()
    val vehicleFormData: LiveData<Vehicle> = data

    private val _vehicleForm = MutableLiveData<AddVehicleFormState>()
    val vehicleForm : LiveData<AddVehicleFormState> = _vehicleForm

    private val _result = MutableLiveData<Result<String>>()
    val result : LiveData<Result<String>> = _result

    val db : FirebaseFirestore = FirebaseFirestore.getInstance()

    fun addVehicle(make: String, model: String, year: String){
        val data = hashMapOf(
                "make" to make,
                "model" to model,
                "year" to year
        )
        db.collection("Vehicle").add(data).addOnSuccessListener { documentReference ->
            _result.value = Result.Success<String>(documentReference.toString())
            Log.d("YEAR", documentReference.toString())
        }.addOnFailureListener { e ->
            _result.value = Result.Error(e)
            Log.d("YEAR", e.toString())
        }
    }

    fun formDataChanged(model: String, year: String){
        _vehicleForm.value = AddVehicleFormState(false, yearValid = false, isValid = false)
        if(checkYear(year)){
            _vehicleForm.value = AddVehicleFormState(yearValid = true, isValid = false)
        }
        if(model.isNotBlank()){
            _vehicleForm.value = AddVehicleFormState(modelValid = true, isValid = false)
        }
        if(model.isNotBlank() && checkYear(year)){
            _vehicleForm.value = AddVehicleFormState(modelValid = true, yearValid = true, isValid = true);
        }
    }

    private fun checkYear(year: String): Boolean {
        return year.isNotBlank()
    }


}