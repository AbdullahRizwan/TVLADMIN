package com.example.admin.Model

class Vehicle {
    var vehicleId:String ?= ""
    var model:String ?= ""
    var make:String ?= ""
    var year:String ?= ""
    val parts : Array<Part?> ?=null

    constructor(model: String?, make: String?, year: String?) {
        this.model = model
        this.make = make
        this.year = year
    }
}