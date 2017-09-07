package com.bernatgomez.apps.randomuser.mvp.presenter;

import com.bernatgomez.apps.randomuser.mvp.view.IMVPListView;
import com.squareup.otto.Bus;

/**
 * Created by bernatgomez on 08/09/2017.
 */

public class ListPresenter extends BasePresenter<IMVPListView> {
    public ListPresenter(Bus bus) {
        super(bus);
    }
}
