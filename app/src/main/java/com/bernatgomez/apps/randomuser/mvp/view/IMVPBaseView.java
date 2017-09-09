package com.bernatgomez.apps.randomuser.mvp.view;


import com.bernatgomez.apps.randomuser.views.activs.BaseActivity;
import com.bernatgomez.apps.randomuser.views.interfaces.ILoading;

/**
 * MVP base view
 *
 * Created by bernatgomez on 08/09/2017.
 */
public interface IMVPBaseView extends ILoading {
    public BaseActivity getContainerActivity();
}
