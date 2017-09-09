package com.bernatgomez.apps.randomuser.models


import com.bernatgomez.apps.randomuser.models.BaseModel


/**
 * App transaction for db operation results
 *
 * Created by bernatgomez on 09/09/2017.
 */

data class DbTransactionResult(
        var operationName : String, var operationSuccess: Boolean) : BaseModel {

    constructor() : this("", false);

}
