package models

/**
 * App abstraction for location elements
 *
 * Created by bernatgomez on 09/09/2017.
 */
data class LocationModel constructor(
        val street : String, val city : String, val state : String) : BaseModel {

    constructor() : this("", "", "")
}