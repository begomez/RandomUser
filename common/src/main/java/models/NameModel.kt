package models

/**
 * App abstraction for name elements
 *
 * Created by bernatgomez on 09/09/2017.
 */
data class NameModel constructor(
        val first : String, val last : String) : BaseModel {

    constructor() : this("", "")
}