package com.bernatgomez.apps.randomuser.models

/**
 * App abstraction for name elements
 *
 * Created by bernatgomez on 09/09/2017.
 */
data class NameModel constructor(
        var first : String, var last : String) : BaseModel {

    constructor() : this("", "")

    /**
     * Full name accessor
     */
    fun getFullName() : String = this.first + " " + this.last

}