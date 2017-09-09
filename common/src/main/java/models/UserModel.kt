package models

/**
 * App abstraction for user elements
 *
 * Created by bernatgomez on 09/09/2017.
 */
data class UserModel constructor(
        val gender : String, val name : NameModel, val email : String, val phone : String,
        val picture: PictureModel, val location : LocationModel, val registered : String) : BaseModel {

    constructor() : this("", NameModel(), "", "", PictureModel(), LocationModel(), "")

}