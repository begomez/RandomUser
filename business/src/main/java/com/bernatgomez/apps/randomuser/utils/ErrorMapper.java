package com.bernatgomez.apps.randomuser.utils;

import com.bernatgomez.apps.randomuser.models.DataError;
import com.bernatgomez.apps.randomuser.models.DataUser;

import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

import models.ErrorModel;

/**
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
