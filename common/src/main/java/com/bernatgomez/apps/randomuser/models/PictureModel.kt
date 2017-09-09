package com.bernatgomez.apps.randomuser.models

/**
 * App abstraction for picture elements
 *
 * Created by bernatgomez on 09/09/2017.
 */
data class PictureModel constructor(
        var large : String, var medium : String, var thumbnail : String) : BaseModel {

    constructor() : this("", "", "")
}