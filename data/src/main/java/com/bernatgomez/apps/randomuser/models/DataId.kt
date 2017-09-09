package com.bernatgomez.apps.randomuser.models

/**
 * API abstraction for identifier elements
 *
 * Created by bernatgomez on 09/09/2017.
 */
data class DataId constructor (val name : String, val value : String) {

    constructor() : this("", "")

}