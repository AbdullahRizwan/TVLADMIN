package com.example.admin.View

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import androidx.core.widget.doAfterTextChanged
import com.example.admin.R
import com.example.admin.ViewModel.AddPartViewModel
import com.example.admin.ViewModel.AddVehicleViewModel
import androidx.lifecycle.Observer
import com.example.admin.Model.Result

class Add_Part : AppCompatActivity() {

    private lateinit var viewModel: AddPartViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add__part)

        viewModel = AddPartViewModel()

        val name = findViewById<EditText>(R.id.name)
        val type = findViewById<EditText>(R.id.type)
        val life = findViewById<EditText>(R.id.life)
        val description = findViewById<EditText>(R.id.description)

        val progressBar = findViewById<ProgressBar>(R.id.progressBar)

        val submitButton = findViewById<Button>(R.id.addPart)
        submitButton.isEnabled = false

        viewModel.partForm.observe(this@Add_Part, Observer{
            val form = viewModel.partForm.value
            if(form!!.nameValid == false){
                name.error = "Model cannot be blank"
            }
            else{
                name.error = null
            }
            if(form.typeValid == false){
                type.error = "Model cannot be blank"
            }
            else{
                type.error = null
            }
            if(form.lifeValid == false){
                life.error = "Model cannot be blank"
            }
            else{
                life.error = null
            }
            if(form.descriptionValid == false){
                description.error = "Model cannot be blank"
            }
            else{
                description.error = null
            }
            submitButton.isEnabled = form.isValid
        })

        name.doAfterTextChanged {
            viewModel.formDataChanged(name.text.toString(), type.text.toString(),
                    life.text.toString(), description.text.toString())
        }
        type.doAfterTextChanged {
            viewModel.formDataChanged(name.text.toString(), type.text.toString(),
                    life.text.toString(), description.text.toString())
        }
        life.doAfterTextChanged {
            viewModel.formDataChanged(name.text.toString(), type.text.toString(),
                    life.text.toString(), description.text.toString())
        }
        description.doAfterTextChanged {
            viewModel.formDataChanged(name.text.toString(), type.text.toString(),
                    life.text.toString(), description.text.toString())
        }
        submitButton.setOnClickListener{
            viewModel.addPart(name.text.toString(), type.text.toString(), life.text.toString(), description.text.toString())
            submitButton.isEnabled = false
            progressBar.visibility = View.VISIBLE
            name.isEnabled = false
            type.isEnabled = false
            life.isEnabled = false
            description.isEnabled = false
        }

        viewModel.result.observe(this@Add_Part, Observer{
            progressBar.visibility = View.GONE
            submitButton.isEnabled = true
            name.isEnabled = true
            type.isEnabled = true
            life.isEnabled = true
            description.isEnabled = true

            val result = it ?: return@Observer
            if(result is Result.Success){
                Toast.makeText(this,  "Added Successfully", Toast.LENGTH_SHORT).show()
            }
            else{
                Toast.makeText(this,  "Vehicle not Added", Toast.LENGTH_SHORT).show()
            }
        })
    }

    fun Back(view: View) {
        finish()
    }
}