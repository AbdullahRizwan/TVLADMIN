package com.example.admin.View

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.admin.R
import com.example.admin.ViewModel.AssociateVehicleViewModel


class AssociateVehicle : AppCompatActivity() {

    private lateinit var viewModel: AssociateVehicleViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_associate_vehicle)

        var recyclerView = findViewById<RecyclerView>(R.id.recyclerViewVehicle)
        viewModel = AssociateVehicleViewModel()
        viewModel.data.observe(this@AssociateVehicle, Observer {
            val recyclerViewAdapter = VehicleCustomAdapter()
            recyclerViewAdapter.setData(viewModel.data)
            recyclerView.layoutManager = LinearLayoutManager(this)
            recyclerView.adapter = recyclerViewAdapter
        })
        var i=1
    }
}