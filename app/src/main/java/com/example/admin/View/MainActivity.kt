package com.example.admin.View

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.admin.R
import com.example.admin.ViewModel.LoginViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class MainActivity : AppCompatActivity() {

    private var loginViewModel:LoginViewModel = LoginViewModel()
    private var firebaseUser:FirebaseUser ?= null



    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if(FirebaseAuth.getInstance().currentUser!=null){

            val value  = Intent(this, HomeScreen::class.java)
            value.putExtra("user",FirebaseAuth.getInstance().currentUser?.uid)
            value.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(value)
        }
    }

    fun onSignup(view: View) {
        val value  = Intent(this, Signup::class.java)
        value.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(value)
    }


    fun SignIn(view: View){
        val email = findViewById<EditText>(R.id.email);
        val pass = findViewById<EditText>(R.id.pass);
        if(email.text.contains("@") && pass.text.length >8){
            loginViewModel.login(email.text.toString(), pass.text.toString())
            Log.d ("Main ",this.loginViewModel.data.value?.uid.toString())
            loginViewModel.data.observe(this, Observer {
                val value  = Intent(this, HomeScreen::class.java)
                value.putExtra("user",FirebaseAuth.getInstance().currentUser?.uid)
                value.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(value)
            })
        }
        else{
            Toast.makeText(this, "Please write correct format of email and password", Toast.LENGTH_SHORT).show()
        }
    }


}