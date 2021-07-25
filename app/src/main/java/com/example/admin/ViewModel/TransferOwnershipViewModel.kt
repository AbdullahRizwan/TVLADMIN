package com.example.admin.ViewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.admin.Model.TransferRequest
import com.google.firebase.Timestamp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class TransferOwnershipViewModel {
    private lateinit var auth: FirebaseAuth
    val db: FirebaseFirestore = FirebaseFirestore.getInstance()

    var data = MutableLiveData<List<TransferRequest>>()

    fun loadAppointments(request: String){
        auth = FirebaseAuth.getInstance()
        val appointments = mutableListOf<TransferRequest>()

        db.collection("TransferRequests").get().addOnSuccessListener { result ->
            for (document in result) {
                var a = document.data
                Log.d("Id", document.id)
                Log.d("OUTPUT", a["plateNo"].toString())
                    var id: String? = document.id
                    var buyerEmail: String? = a["buyerEmail"] as String
                    var ownerEmail: String? = a["ownerEmail"] as String
                    var plateNo: String? = a["plateNo"].toString()
                    var status: String? = a["status"].toString()
//                var price:Int?=a["price"].toString().toInt()
                    //              var count:Int?=a["quantity"].toString().toInt()
                if(status.equals("Pendind"))
                    appointments.add(TransferRequest(id, buyerEmail, ownerEmail, plateNo, status))

            }
            data.value = appointments
        }.addOnFailureListener{
                Log.d("ERROR", it.toString())
                data.value = null
            }
    }

    fun approve(request: TransferRequest) {
        if(request.requestID != null) {
            db.collection("TransferRequests").document(request.requestID.toString())
                .update("status", "Approved")
            //Get uid

            db.collection("Owner").get().addOnSuccessListener { result ->
                for (document in result) {
                    var a = document.data
                    Log.d("Id", document.id)
                    Log.d("OUTPUT", a["plateNo"].toString())
                    var id: String = document.id
                    var email: String = a["email"].toString()
                    var buyerEmail:String=request.buyerEmail.toString()
                    var ownerEmail:String= request.ownerEmail.toString()
                    if(buyerEmail.contains(email)){
                        var buyerUid=a["uid"] as String
                        db.collection("UserVehicle").get().addOnSuccessListener { res->
                            for(doc in res){
                                var record=doc.data
                                var userVehicleId: String = doc.id as String
                              //  var ownerEmail:String=record["uid"] as String
                                var plateNo:String=record["lisenceNumber"]as String
                              //  var requestEmail:String=request.ownerEmail.toString()
                                var requestPlateNO:String=request.plateNo.toString()
                                if(requestPlateNO.contains(plateNo))
                                {
                                    db.collection("UserVehicle").document(userVehicleId).update(
                                        "uid",buyerUid)

                                }
                            }
                        }
                    }

                }

            }.addOnFailureListener{
                Log.d("ERROR", it.toString())

            }



        }

    }

    fun remove(request: TransferRequest) {
        if(request.requestID != null)
            db.collection("TransferRequests").document(request.requestID.toString())
                .delete()
    }



}

/* db.collection("TransferRequests").
             whereEqualTo("status", request).get().addOnSuccessListener { res ->

                 val appointments = mutableListOf<TransferRequest>()
                 val owner_ids = mutableListOf<String>()
                 val buyer_ids = mutableListOf<String>()

                 for (doc in res){
                     owner_ids.add(doc["ownerEmail"] as String)
                     buyer_ids.add(doc["buyerEmail"] as String)
                     appointments.add(TransferRequest(doc.id,doc["buyerEmail"] as String?,
                     doc["ownerEmail"] as String?,doc["plateNo"] as String?,
                         doc["status"] as String?))


                 }
             data.value=appointments
             }*/
