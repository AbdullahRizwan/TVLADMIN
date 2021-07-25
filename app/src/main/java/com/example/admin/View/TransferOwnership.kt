package com.example.admin.View

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.admin.Model.TransferRequest
import com.example.admin.R
import com.example.admin.ViewModel.TransferOwnershipViewModel
import com.example.admin.repository.TransferOwnershipListener

class TransferOwnership : AppCompatActivity(), TransferOwnershipListener {
    private var requestViewModel: TransferOwnershipViewModel = TransferOwnershipViewModel()
    private lateinit var requestAdapter: TransferOwnershipAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_transfer_ownership)
        val requestRecyclerView = findViewById<RecyclerView>(R.id.recyclerview_transfer_ownership)
        requestRecyclerView.layoutManager = LinearLayoutManager(
            this,
            LinearLayoutManager.VERTICAL,
            false
        )
        requestAdapter = TransferOwnershipAdapter(mutableListOf(), this)

        requestRecyclerView.adapter=requestAdapter
        requestViewModel.data.observe(this, Observer {
            Log.d("REQUEST","Processing")
            requestAdapter.dataset=it.toMutableList()
            requestAdapter.notifyDataSetChanged()
        })
        requestViewModel.loadAppointments("Pendind")

    }

    override fun onItemClick(
        view: View,
        transferRequest: TransferRequest,
        position: Int,
        status: String
    ) {
       if(status.equals("Approved")){
           requestViewModel.approve(transferRequest)
       }
        else{
            requestViewModel.remove(transferRequest)
       }
        val anim: Animation = AnimationUtils.loadAnimation(this,
            android.R.anim.slide_out_right)
        anim.duration = 300
        view.startAnimation(anim)
        Handler(Looper.getMainLooper()).postDelayed({
            requestAdapter.removeItem(position)
        }, anim.duration)

    }
}