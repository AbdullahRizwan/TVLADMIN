package com.example.admin.View

import android.R.attr.country
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doAfterTextChanged
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.example.admin.R
import com.example.admin.ViewModel.AddVehicleViewModel

import com.example.admin.Model.Result

class Add_Vehicle : AppCompatActivity(), AdapterView.OnItemSelectedListener {

    val companynames =arrayOf("Honda", "Toyota", "Suzuki", "Hyundai", "Daihatsu")

    private lateinit var viewModel: AddVehicleViewModel
    private var make: String = "Honda"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add__vehicle)
        val spinner = findViewById<Spinner>(R.id.spinner)
        spinner.onItemSelectedListener = this

        val aa: ArrayAdapter<*> =
            ArrayAdapter<Any?>(this, android.R.layout.simple_spinner_item, companynames)
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        //Setting the ArrayAdapter data on the Spinner
        //Setting the ArrayAdapter data on the Spinner
        spinner.adapter = aa

        viewModel = AddVehicleViewModel()
        val model = findViewById<EditText>(R.id.model)
        val year = findViewById<EditText>(R.id.year)

        val nextButton = findViewById<Button>(R.id.submit)
        val progressBar = findViewById<ProgressBar>(R.id.progressBar)
        nextButton.isEnabled = false

        viewModel.vehicleForm.observe(this@Add_Vehicle, Observer{
            if(viewModel.vehicleForm.value!!.modelValid == false){
                model.error = "Model cannot be blank"
            }
            else{
                model.error = null
            }
            if(viewModel.vehicleForm.value!!.yearValid == false){
                year.error = "Year is invalid"
            }
            else{
                year.error = null
            }
            nextButton.isEnabled = viewModel.vehicleForm.value!!.isValid
        })
        
        model.doAfterTextChanged{
            viewModel.formDataChanged(model.text.toString(), year.text.toString())
        }
        year.doAfterTextChanged {
            viewModel.formDataChanged(model.text.toString(), year.text.toString())
        }

        nextButton.setOnClickListener{
            viewModel.addVehicle(make, model.text.toString(), year.text.toString())
            nextButton.isEnabled = false
            progressBar.visibility = View.VISIBLE
            model.isEnabled = false
            year.isEnabled = false
        }

        viewModel.result.observe(this@Add_Vehicle, Observer{
            progressBar.visibility = View.GONE
            nextButton.isEnabled = true
            model.isEnabled = true
            year.isEnabled = true

            val result = it ?: return@Observer
            if(result is Result.Success){
                Toast.makeText(this,  "Added Successfully", Toast.LENGTH_SHORT).show()
            }
            else{
                Toast.makeText(this,  "Vehicle not Added", Toast.LENGTH_SHORT).show()
            }
        })
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        Log.d("company name",companynames[position])
        make = companynames[position]
        Toast.makeText(this, "Name: "+companynames[position], Toast.LENGTH_LONG).show()
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {

    }

    fun Back(view: View) {
        finish()
    }
}