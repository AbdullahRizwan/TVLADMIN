package com.example.admin.ViewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.admin.Model.Result
import com.example.admin.form.AddPartFormState
import com.example.admin.form.AddVehicleFormState
import com.google.firebase.firestore.FirebaseFirestore

class AddPartViewModel : ViewModel() {

    private val _partForm = MutableLiveData<AddPartFormState>()
    val partForm : LiveData<AddPartFormState> = _partForm

    private val _result = MutableLiveData<Result<String>>()
    val result : LiveData<Result<String>> = _result


    val db : FirebaseFirestore = FirebaseFirestore.getInstance()

    fun addPart(name: String, type: String, life: String, description: String){
        val data = hashMapOf(
                "name" to name,
                "type" to type,
                "life" to life,
                "description" to description
        )
        db.collection("Part").add(data).addOnSuccessListener { documentReference ->
            _result.value = Result.Success<String>(documentReference.toString())
            Log.d("Result", documentReference.toString())
        }.addOnFailureListener { e ->
            _result.value = Result.Error(e)
            Log.d("Result", e.toString())
        }
    }

    fun formDataChanged(name: String, type: String, life: String, description: String){
        _partForm.value = AddPartFormState()
        if(!checkNameValid(name))
            _partForm.value = AddPartFormState(nameValid = false)
        else if(!checkTypeValid(type))
            _partForm.value = AddPartFormState(typeValid = false)
        else if(!checkTypeValid(life))
            _partForm.value = AddPartFormState(lifeValid = false)
        else if(!checkDescriptionValid((description)))
            _partForm.value = AddPartFormState(descriptionValid = false)
        else
            _partForm.value = AddPartFormState(isValid = true)
    }

    private fun checkNameValid(text: String): Boolean {
        return text.isNotBlank()
    }
    private fun checkTypeValid(text: String): Boolean {
        return text.isNotBlank()
    }
    private fun checkLifeValid(text: String): Boolean {
        return text.isNotBlank()
    }
    private fun checkDescriptionValid(text: String): Boolean {
        return text.isNotBlank()
    }

}