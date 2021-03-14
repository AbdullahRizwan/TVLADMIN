package com.example.admin.View

import android.content.Intent
import android.opengl.Visibility
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.example.admin.Model.Admin
import com.example.admin.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class Profile : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_profile)
        if (savedInstanceState == null) {
            update()
        }
        update()

    }
    fun update(){
        findViewById<EditText>(R.id.name).setText(Admin.displayName)
        findViewById<EditText>(R.id.email).setText(Admin.email())
        findViewById<EditText>(R.id.phoneNumber).setText(Admin.phone)
        findViewById<EditText>(R.id.cnic).setText(Admin.cnicNumber)


    }
    fun Back(view: View) {
        finish()
    }

    fun edit(view: View) {
        findViewById<TextView>(R.id.edit).visibility = View.INVISIBLE
        findViewById<Button>(R.id.logout).isClickable = false
        findViewById<Button>(R.id.save).isClickable = true
        findViewById<Button>(R.id.save).visibility = View.VISIBLE
        findViewById<Button>(R.id.logout).visibility = View.INVISIBLE
        findViewById<EditText>(R.id.name).isEnabled = true
        findViewById<EditText>(R.id.phoneNumber).isEnabled = true
        findViewById<EditText>(R.id.cnic).isEnabled = true
    }
    fun save(view: View){
        findViewById<TextView>(R.id.edit).visibility = View.VISIBLE
        findViewById<Button>(R.id.logout).isClickable = true
        findViewById<Button>(R.id.save).visibility = View.INVISIBLE
        findViewById<Button>(R.id.save).isClickable = false
        findViewById<Button>(R.id.logout).visibility = View.VISIBLE
        findViewById<EditText>(R.id.name).isEnabled = false
        findViewById<EditText>(R.id.phoneNumber).isEnabled = false
        findViewById<EditText>(R.id.cnic).isEnabled = false
        Admin.displayName = findViewById<EditText>(R.id.name).text.toString()
        Admin.cnicNumber = findViewById<EditText>(R.id.cnic).text.toString()
        Admin.phone = findViewById<EditText>(R.id.phoneNumber).text.toString()
        val db = FirebaseFirestore.getInstance()
        var col = db.collection("Admin").document(Admin.uid)
        col.update("name",Admin.displayName)
        col.update("cnic",Admin.cnicNumber)
        col.update("phone",Admin.phone)



    }

    fun Logout(view: View) {
        FirebaseAuth.getInstance().signOut()
        val value  = Intent(this, MainActivity::class.java)
        value.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(value)

    }
}