package com.example.admin.ViewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.admin.Model.MonitorComplaint
import com.example.admin.Model.Owner
import com.example.admin.Model.Vendor
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore


class MonitorComplaintsViewModel {
    private lateinit var auth:FirebaseAuth
    val db:FirebaseFirestore= FirebaseFirestore.getInstance()

    var data= MutableLiveData<List<MonitorComplaint>>()

    fun loadComplaints() {
        auth=FirebaseAuth.getInstance()
        val complaint = mutableListOf<MonitorComplaint>()
        db.collection("Feedback").get().addOnSuccessListener { result->
            for(document in result){
                var doc=document.data
                var id:String?=document.id
                var ownerId:String=doc["owner_id"].toString()
                var vendorId:String=doc["vendor_id"].toString()
                var desc:String=doc["description"].toString()
                var count:String=doc["rating"].toString()
                var ownerName:String?= doc["ownerName"].toString()
                var vendorName:String?= doc["vendorName"].toString()

                complaint.add(MonitorComplaint(id,vendorId,ownerId,count,desc,ownerName,vendorName))
            }
            data.value=complaint

        }.addOnFailureListener{
            data.value=null
        }
    }


}
/*db.collection("Vendor").get().addOnSuccessListener { res->
                      for(doc in res){
                          var a1=doc.data
                          var id:String=doc.id
                          var uid1:String?=a1["uid"] as String
                          if(uid1==vendorId) {
                              name = a1["name"].toString()
                              vendorName=name
                              break
                          }
                      }

                  }*/

/*  db.collection("Owner").get().addOnSuccessListener { result ->
                      for (document in result) {
                          var a = document.data
                          var id: String = document.id
                          var uid: String? = a["uid"] as String
                          if (uid == ownerId) {
                              val name = a["name"].toString()
                              ownerName=name
                              if(flagV==true){

                              }

                          }
                      }*/

/* docRef.get().addOnCompleteListener { task ->
                   if (task.isSuccessful) {
                       val record: DocumentSnapshot = task.getResult()!!
                       if (record != null) {
                           val name:String=record.getString("name").toString()
                           ownerName=record.getString("name").toString()
                           ownerName=name
                       } else {
                           Log.d("LOGGER", "No such document")
                       }
                   } else {
                       Log.d("LOGGER", "get failed with ", task.exception)
                   }
               }*/
/*docRef1.get().addOnCompleteListener { task ->
    if (task.isSuccessful) {
        val record1: DocumentSnapshot = task.getResult()!!
        if (record1 != null) {
            val name:String=record1.getString("name").toString()
            vendorName=record1.getString("name").toString()


        } else {
            Log.d("LOGGER", "No such document")
        }
    } else {
        Log.d("LOGGER", "get failed with ", task.exception)
    }
}*/
