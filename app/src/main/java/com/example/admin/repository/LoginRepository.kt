package com.example.admin.repository

import android.app.Application
import android.content.Context
import android.os.Debug
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.example.admin.View.MainActivity
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import android.util.Log


class loginRepository {

    private  var auth: FirebaseAuth
    private val data= MutableLiveData<FirebaseUser>()
    get() = field

    constructor(){
        auth = FirebaseAuth.getInstance()
    }

    public fun SignIn(email: String, pass: String):MutableLiveData<FirebaseUser> {

        auth.signInWithEmailAndPassword(email, pass).
        addOnCompleteListener { task: Task<AuthResult> ->
            if (task.isSuccessful){
                data.postValue(auth.currentUser)
            }
        }
        return data
    }

    fun get_Data() : MutableLiveData<FirebaseUser>{
        return data
    }
}