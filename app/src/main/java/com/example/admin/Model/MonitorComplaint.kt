package com.example.admin.Model

class MonitorComplaint {
    var feedbackID:String?=""
    var vendorID:String?= ""
    var ownerID:String?= ""
    var rating:String?= ""
    var description:String?= ""
    var ownerName:String?=""
    var vendorName:String?=""

    constructor(feedbackID:String?,vendorID: String?, ownerID: String?, rating: String?,description:String?,
    ownerName:String?, vendorName:String?) {
        this.feedbackID=feedbackID
        this.vendorID = vendorID
        this.ownerID = ownerID
        this.rating = rating
        this.description=description
        this.ownerName=ownerName
        this.vendorName=vendorName
    }
    @JvmName("setOwnerName1")
    fun setOwnerName(ownerName: String?){
        this.ownerName=ownerName
    }
    @JvmName("setVendorName1")
    fun setVendorName(vendorName: String?){
        this.vendorName=vendorName
    }

}