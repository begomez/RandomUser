package com.bernatgomez.apps.randomuser.mappers;


import com.bernatgomez.apps.randomuser.models.DataError;

import org.modelmapper.ModelMapper;

import com.bernatgomez.apps.randomuser.models.ErrorModel;


/**
 * Mapper to convert api errors into app errors
 *
 * Created by bernatgomez on 09/09/2017.
 */
public abstract class ErrorMapper {

    /**
     * Map error from API to APP model
     *
     * @param apiError
     * @return
     */
    public static ErrorModel mapError(DataError apiError) {
        return new ModelMapper().map(apiError, ErrorModel.class);
    }
}
