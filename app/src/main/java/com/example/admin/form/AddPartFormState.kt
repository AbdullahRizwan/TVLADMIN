package com.example.admin.form

data class AddPartFormState (
        var nameValid : Boolean? = true,
        var typeValid : Boolean? = true,
        var lifeValid : Boolean? = true,
        var descriptionValid : Boolean? = true,
        var isValid : Boolean = false
)