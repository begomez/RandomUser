package com.bernatgomez.apps.randomuser.models


/**
 * API abstraction for user picture elements
 *
 * Created by bernatgomez on 09/09/2017.
 */
data class DataPicture constructor (val large : String, val medium : String, val thumbnail : String) {
    constructor() : this("", "", "")
}