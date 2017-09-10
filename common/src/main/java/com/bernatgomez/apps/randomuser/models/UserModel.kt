package com.bernatgomez.apps.randomuser.models

/**
 * App abstraction for user elements
 *
 * Created by bernatgomez on 09/09/2017.
 */
data class UserModel constructor(
        var gender : String, var name : NameModel, var email : String, var phone : String,
        var picture: PictureModel, var location : LocationModel, var registered : String, var disabled : Boolean) : BaseModel {

    constructor() : this("", NameModel(), "", "", PictureModel(), LocationModel(), "", false)

    fun matchesCriteria(criteria : String) : Boolean
        = this.name.toString().toLowerCase().contains(criteria.toLowerCase())
            || this.email.toLowerCase().contains(criteria.toLowerCase())
}