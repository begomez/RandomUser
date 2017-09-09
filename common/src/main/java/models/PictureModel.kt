package models

/**
 * App abstraction for picture elements
 *
 * Created by bernatgomez on 09/09/2017.
 */
data class PictureModel constructor(
        val medium : String, val thumbnail : String) : BaseModel {

    constructor() : this("", "")
}