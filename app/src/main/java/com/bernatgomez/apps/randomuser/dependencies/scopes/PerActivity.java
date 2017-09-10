package com.bernatgomez.apps.randomuser.dependencies.scopes;


import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;


/**
 * Activity life time scope
 *
 * Created by bernatgomez on 08/09/2017.
 */
@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface PerActivity {
}
