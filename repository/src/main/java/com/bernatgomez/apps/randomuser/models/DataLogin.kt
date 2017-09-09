package com.bernatgomez.apps.randomuser.models

/**
 * API abstraction for user login elements
 *
 * Created by bernatgomez on 09/09/2017.
 */
data class DataLogin constructor(
    val username : String, val password : String, val salt : String,
    val md5 : String, val sha1 : String, val sha256 : String) {

    constructor() : this("", "", "", "", "", "")
}