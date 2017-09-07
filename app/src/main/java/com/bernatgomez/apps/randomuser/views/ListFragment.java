package com.bernatgomez.apps.randomuser.views;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.bernatgomez.apps.randomuser.R;
import com.bernatgomez.apps.randomuser.utils.Logger;

import butterknife.BindView;

/**
 * Created by bernatgomez on 08/09/2017.
 */

public class ListFragment extends BaseFragment {

    @BindView(R.id.txtSample)
    protected TextView txtSample;


    public static ListFragment newInstance() {
        return new ListFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.layoutId = R.layout.fragment_list;
    }
}
