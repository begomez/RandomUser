package com.bernatgomez.apps.randomuser.views.fragments;


import android.media.Image;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ImageView;
import android.widget.TextView;

import com.bernatgomez.apps.randomuser.R;
import com.bernatgomez.apps.randomuser.mvp.view.IDetailView;
import com.bernatgomez.apps.randomuser.utils.Constants;
import com.f2prateek.dart.InjectExtra;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import butterknife.BindView;
import models.UserModel;


/**
 * Detail screen fragment containing info about random users
 *
 * Created by bernatgomez on 09/09/2017.
 */
public class DetailFragment extends BaseFragment implements IDetailView {

    @Nullable
    @InjectExtra(Constants.EXTRA_USER)
    protected UserModel user;

    @BindView(R.id.item_avatar)
    protected ImageView imgAvatar;

    @BindView(R.id.item_fullname)
    protected TextView txtName;

    @BindView(R.id.item_location)
    protected TextView txtLocation;

    @BindView(R.id.item_gender)
    protected TextView txtGender;

    @BindView(R.id.item_mail)
    protected TextView txtMail;


////////////////////////////////////////////////////////////////////////////////////////////////////
// CONSTRUCTOR
////////////////////////////////////////////////////////////////////////////////////////////////////
    public static DetailFragment newInstance(UserModel user) {
        DetailFragment detail = new DetailFragment();

        Bundle b = new Bundle();

        b.putSerializable(Constants.EXTRA_USER, user);

        detail.setArguments(b);

        return detail;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.layoutId = R.layout.fragment_detail;


    }

    @Override
    protected void configViews() {
        super.configViews();

        this.txtMail.setText(this.user.getEmail());
        this.txtGender.setText(this.user.getGender());
        this.txtLocation.setText(this.user.getLocation().getFullLocation());
        this.txtName.setText(this.user.getName().getFullName());
        Picasso.with(this.getContext()).load(this.user.getPicture().getMedium()).into(this.imgAvatar);
    }
}
