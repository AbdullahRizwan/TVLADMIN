package com.example.admin.View

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.admin.R
import com.example.admin.ViewModel.MonitorComplaintsViewModel
import java.util.*

class MonitorComplaints : AppCompatActivity() {
    private var monitorComplaintsViewModel: MonitorComplaintsViewModel= MonitorComplaintsViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_monitor_complaints)

        val monitorComplaintRecyclerView=findViewById<RecyclerView>(R.id.recyclerview_monitor_complaints)
        monitorComplaintRecyclerView.layoutManager=LinearLayoutManager(
            this,
            LinearLayoutManager.VERTICAL,
            false
        )
        val monitorComplaintAdapter=MonitorComplaintsAdapter(emptyList())
        monitorComplaintRecyclerView.adapter=monitorComplaintAdapter

        monitorComplaintsViewModel.data.observe(this, androidx.lifecycle.Observer {
            Log.d("MonitorComplaint","Monitoring")
            monitorComplaintAdapter.dataSet= it
            monitorComplaintAdapter.notifyDataSetChanged()
        })


        monitorComplaintsViewModel.loadComplaints()










    }
}