package com.example.admin.View

import android.graphics.Color
import android.view.LayoutInflater
import androidx.recyclerview.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.admin.R
import com.example.admin.Model.MonitorComplaint

class MonitorComplaintsAdapter(var dataSet:List<MonitorComplaint>):
RecyclerView.Adapter<MonitorComplaintsAdapter.ViewHolder>(){

    class ViewHolder(var view:View):RecyclerView.ViewHolder(view){

        val ownerName:TextView=view.findViewById(R.id.label_ownername)
        val vendorName:TextView=view.findViewById(R.id.label_vendorname)
        val description:TextView=view.findViewById(R.id.label_description)
        val star1:ImageView=view.findViewById(R.id.star1)
        val star2:ImageView=view.findViewById(R.id.star2)
        val star3:ImageView=view.findViewById(R.id.star3)
        val star4:ImageView=view.findViewById(R.id.star4)
        val star5:ImageView=view.findViewById(R.id.star5)

    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
       val view=LayoutInflater.from(viewGroup.context)
           .inflate(R.layout.monitor_complaint_item,viewGroup,false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val feedback=dataSet[position]
        viewHolder.ownerName.text=feedback.ownerName
        viewHolder.vendorName.text=feedback.vendorName
        viewHolder.description.text=feedback.description
        val count=feedback.rating.toString().toInt()
        if(count==1){
            viewHolder.star1.setImageResource(R.drawable.ic_baseline_star_yellow_24)
        }
        if(count==2){
            viewHolder.star1.setImageResource(R.drawable.ic_baseline_star_yellow_24)
            viewHolder.star2.setImageResource(R.drawable.ic_baseline_star_yellow_24)
        }
        if(count==3){
            viewHolder.star1.setImageResource(R.drawable.ic_baseline_star_yellow_24)
            viewHolder.star2.setImageResource(R.drawable.ic_baseline_star_yellow_24)
            viewHolder.star3.setImageResource(R.drawable.ic_baseline_star_yellow_24)


        }
        if(count==4){
            viewHolder.star1.setImageResource(R.drawable.ic_baseline_star_yellow_24)
            viewHolder.star2.setImageResource(R.drawable.ic_baseline_star_yellow_24)
            viewHolder.star3.setImageResource(R.drawable.ic_baseline_star_yellow_24)
            viewHolder.star4.setImageResource(R.drawable.ic_baseline_star_yellow_24)
        }
        if(count==5){
            viewHolder.star1.setImageResource(R.drawable.ic_baseline_star_yellow_24)
            viewHolder.star2.setImageResource(R.drawable.ic_baseline_star_yellow_24)
            viewHolder.star3.setImageResource(R.drawable.ic_baseline_star_yellow_24)
            viewHolder.star4.setImageResource(R.drawable.ic_baseline_star_yellow_24)
            viewHolder.star5.setImageResource(R.drawable.ic_baseline_star_yellow_24)
        }

    }

    override fun getItemCount()=dataSet.size

}