package com.bernatgomez.apps.randomuser.models


/**
 * API abstraction for error elements
 *
 * Created by bernatgomez on 08/09/2017.
 */
class DataError constructor(val msg: String) : DataBase {

    constructor() : this("")

}
