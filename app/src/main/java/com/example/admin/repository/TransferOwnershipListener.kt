package com.example.admin.repository

import android.view.View
import com.example.admin.Model.TransferRequest

interface TransferOwnershipListener {
    fun onItemClick(view: View, transferRequest:TransferRequest,position:Int, status: String)
}