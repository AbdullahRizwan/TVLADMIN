package com.example.admin.View

import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.admin.Model.Vehicle
import com.example.admin.R
import com.example.admin.ViewModel.AssociateVehicleViewModel


class AssociateVehicle : AppCompatActivity(), VehicleCustomAdapter.OnClickListener {

    private lateinit var viewModel: AssociateVehicleViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_associate_vehicle)

        var recyclerView = findViewById<RecyclerView>(R.id.recyclerViewVehicle)
        val recyclerViewAdapter = VehicleCustomAdapter()
        viewModel = AssociateVehicleViewModel()
        viewModel.data.observe(this@AssociateVehicle, Observer {

            recyclerViewAdapter.setData(viewModel.data, this.applicationContext, this)
            recyclerView.layoutManager = LinearLayoutManager(this)
            recyclerView.adapter = recyclerViewAdapter
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

    override fun onVehicleClick(vehicle: Vehicle) {
        Log.d("Poistion", "" + vehicle.make)
        val i = Intent(applicationContext, Associate_Part::class.java)
        i.putExtra("vehicle",vehicle )
        startActivity(i)
    }

    fun Back(view: View) {
        finish()
    }
}