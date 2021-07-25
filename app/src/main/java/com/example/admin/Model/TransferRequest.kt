package com.example.admin.Model

import java.io.Serializable

class TransferRequest:Serializable {
        var requestID:String?=""
        var buyerEmail:String ?= ""
        var ownerEmail:String ?= ""
        var plateNo:String ?= ""
        var status:String ?= ""

        constructor(requestID:String?,buyerEmail: String?, ownerEmail: String?, plateNo: String?,status:String?) {
            this.requestID=requestID
            this.buyerEmail = buyerEmail
            this.ownerEmail = ownerEmail
            this.plateNo = plateNo
            this.status=status
        }

}