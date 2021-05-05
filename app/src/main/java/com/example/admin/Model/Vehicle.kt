package com.example.admin.Model

import java.io.Serializable

class Vehicle : Serializable{
    var vehicleId:String ?= ""
    var model:String ?= ""
    var make:String ?= ""
    var year:String ?= ""
    val parts : ArrayList<Part?> ?= ArrayList()

    constructor(model: String?, make: String?, year: String?) {
        this.model = model
        this.make = make
        this.year = year
    }
}