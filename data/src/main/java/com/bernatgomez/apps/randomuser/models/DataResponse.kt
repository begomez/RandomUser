package com.bernatgomez.apps.randomuser.models


/**
 * API abstraction for random user service response elements
 *
 * Created by bernatgomez on 09/09/2017.
 */
class DataResponse constructor(
        val results : List<DataUser>) : DataBase {

        constructor() : this (emptyList())

}