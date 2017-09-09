package models

/**
 * App abstraction for user elements
 *
 * Created by bernatgomez on 09/09/2017.
 */
data class UserModel constructor(
        var gender : String, var name : NameModel, var email : String, var phone : String,
        var picture: PictureModel, var location : LocationModel, var registered : String) : BaseModel {

    constructor() : this("", NameModel(), "", "", PictureModel(), LocationModel(), "")
}