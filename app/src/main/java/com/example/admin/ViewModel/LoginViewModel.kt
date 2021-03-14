package com.example.admin.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.admin.repository.loginRepository
import com.google.firebase.auth.FirebaseUser

class LoginViewModel  : ViewModel() {
    var data= MutableLiveData<FirebaseUser>()
    get() = field
    val loginFormState: LiveData<FirebaseUser> = data
    val loginRepository: loginRepository = loginRepository()

    private val loginRepo : loginRepository?=null


    fun login(username: String, password: String) {
        // can be launched in a separate asynchronous job
        this.data = this.loginRepository.SignIn(username, password)

    }
}