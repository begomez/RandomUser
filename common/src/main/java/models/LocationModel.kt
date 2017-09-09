package models

/**
 * App abstraction for location elements
 *
 * Created by bernatgomez on 09/09/2017.
 */
data class LocationModel constructor(
        var street : String, var city : String, var state : String) : BaseModel {

    constructor() : this("", "", "")
}