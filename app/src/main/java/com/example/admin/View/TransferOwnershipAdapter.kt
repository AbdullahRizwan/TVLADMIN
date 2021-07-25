package com.example.admin.View

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.admin.Model.TransferRequest
import com.example.admin.R
import com.example.admin.repository.TransferOwnershipListener

class TransferOwnershipAdapter(var dataset:MutableList<TransferRequest>,private val itemClickListener: TransferOwnershipListener):
    RecyclerView.Adapter<TransferOwnershipAdapter.ViewHolder>() {

    class ViewHolder(var view: View) : RecyclerView.ViewHolder(view) {
        val plateNo: TextView = view.findViewById(R.id.label_plateNo)
        val owner: TextView = view.findViewById(R.id.label_owner)
        val buyer: TextView = view.findViewById(R.id.label_buyer)
        val approveButton: Button = view.findViewById(R.id.button_approve)
        val rejectButton: Button = view.findViewById(R.id.button_reject)
        var _view: View = view

        fun bind(appointment: TransferRequest, position: Int, clickListener: TransferOwnershipListener)
        {
            approveButton.setOnClickListener {
                clickListener.onItemClick(_view, appointment, position, "Approved")
            }
            rejectButton.setOnClickListener {
                clickListener.onItemClick(_view, appointment, position, "Rejected")
            }
        }

    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.tranfer_ownership_item, viewGroup, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val req =dataset[position]
        viewHolder.plateNo.text=req.plateNo
        viewHolder.buyer.text=req.buyerEmail
        viewHolder.owner.text=req.ownerEmail
        viewHolder.bind(dataset[position],position,itemClickListener)
    }

    override fun getItemCount()=dataset.size

    fun removeItem(position: Int) {
        dataset.removeAt(position)
        notifyDataSetChanged()
    }

}