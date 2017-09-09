package models

/**
 * App abstraction for error elements
 *
 * Created by bernatgomez on 09/09/2017.
 */
data class ErrorModel constructor(
        val msg : String) : BaseModel {

    constructor() : this("")
}