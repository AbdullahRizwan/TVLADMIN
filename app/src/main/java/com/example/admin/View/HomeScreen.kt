package com.example.admin.View

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.admin.Model.Admin
import com.example.admin.R
import com.google.firebase.firestore.FirebaseFirestore


class HomeScreen : AppCompatActivity() {
    var falg = false
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        var id = intent.getStringExtra("user")


        setContentView(R.layout.activity_home_screen)

        val db = FirebaseFirestore.getInstance()
        db.collection("Admin")
            .get()
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    for (document in task.result!!) {
                        if(document.id == id)
                            Admin.setValues(document.data["name"].toString(),
                                document.data["email"].toString(),
                                document.data["cnic"].toString(),
                                document.data["phone"].toString(),
                                document.id
                            )
                        findViewById<TextView>(R.id.username).text= Admin.displayName
                        findViewById<ProgressBar>(R.id.progressBar).visibility = View.INVISIBLE
                    }
                } else {
                    Log.w("TAG", "Error getting documents.", task.exception)
                }
            }



    }

    override fun onResume() {
        super.onResume()
        findViewById<TextView>(R.id.username).text= Admin.displayName
    }


    fun Profile(view: View) {
        val value  = Intent(this, Profile()::class.java)
        startActivity(value)
    }

    fun addVehicle(view: View) {
        val value  = Intent(this, Add_Vehicle::class.java)
        startActivity(value)

    }
    fun addPart(view: View) {
        val value  = Intent(this, Add_Part::class.java)
        startActivity(value)

    }
}