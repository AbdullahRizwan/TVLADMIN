package com.example.admin.Model

class Vendor {
    var id: String? = ""
    var name: String? = ""
    var address: String?=""
    var phone: String?=""

    constructor(id:String?,name:String?,address:String?,phone:String?){
        this.address=address
        this.id=id
        this.name=name
        this.phone=phone
    }

}

