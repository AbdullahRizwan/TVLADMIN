package com.example.admin.View

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.admin.Model.Part
import com.example.admin.Model.Vehicle
import com.example.admin.R
import com.example.admin.ViewModel.AssociatePartViewModel
import com.example.admin.ViewModel.AssociateVehicleViewModel

class Associate_Part : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewModel: AssociatePartViewModel
    private var partslist: ArrayList<Part> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_associate__part)
        val vehicle :Bundle ?=intent.extras
        var vehicleGot :Vehicle
        if (vehicle!=null) {
            vehicleGot = vehicle.get("vehicle") as Vehicle
            Log.d("getBundleExtra", vehicleGot.make.toString())
            setUpView()
        }
        else{
            Toast.makeText(this,"Error getting vehicle information",Toast.LENGTH_LONG).show()
            finish()
        }
    }

    private fun setUpView() {
        recyclerView = findViewById(R.id.recyclerViewPart)
        val recyclerViewAdapter = AssociatePartCustomAdapter()
        viewModel = AssociatePartViewModel()
        viewModel.data.observe(this, Observer {
            recyclerViewAdapter.setData(viewModel.data, this.applicationContext)
            recyclerView.layoutManager = LinearLayoutManager(this)
            recyclerView.adapter = recyclerViewAdapter
            recyclerViewAdapter.setCallback(object : AssociatePartCustomAdapter.Callback{
                override fun onCheckedChanged(part: Part?, isChecked: Boolean) {
                    if(isChecked == true)
                        partslist.add(part!!)
                    else
                        partslist.remove(part)
                    Log.d("list",partslist.toString())
                }
            })
        })

        var searchView = findViewById<SearchView>(R.id.searchVehicle)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(s: String): Boolean {
                return false
            }

            override fun onQueryTextChange(s: String): Boolean {
                recyclerViewAdapter.filter.filter(s)
                return true
            }
        })

    }

    fun Back(view: View) {
        finish()
    }

    fun saveRecord(view: View) {


    }


}