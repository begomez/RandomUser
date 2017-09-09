package com.bernatgomez.apps.randomuser.models


/**
 * API abstraction for random user elements
 *
 * Created by bernatgomez on 09/09/2017.
 */
data class DataUser constructor(
        val gender : String, val name : DataName, val location : DataLocation, val email : String, val login : DataLogin,
        val dob : String, val registered : String, val phone : String, val cell : String, val id : DataId, val picture : DataPicture,
        val nat : String
        ) {

        constructor() : this("", DataName(), DataLocation(), "", DataLogin(), "", "", "", "", DataId(), DataPicture(), "")

}