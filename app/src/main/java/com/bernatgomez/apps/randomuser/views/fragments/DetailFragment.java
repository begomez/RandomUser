package com.bernatgomez.apps.randomuser.views.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.transition.Slide;
import android.view.Gravity;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bernatgomez.apps.randomuser.R;
import com.bernatgomez.apps.randomuser.mvp.view.IMVPBaseView;
import com.bernatgomez.apps.randomuser.utils.AndroidLogger;
import com.bernatgomez.apps.randomuser.utils.Constants;
import com.bernatgomez.apps.randomuser.utils.TextUtils;
import com.f2prateek.dart.InjectExtra;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import com.bernatgomez.apps.randomuser.models.UserModel;

import org.w3c.dom.Text;


/**
 * Detail screen fragment containing info about random users
 *
 * Created by bernatgomez on 09/09/2017.
 */
public class DetailFragment extends BaseFragment implements IMVPBaseView {

    @Nullable
    @InjectExtra(Constants.EXTRA_USER)
    protected UserModel user;

    @BindView(R.id.item_avatar)
    protected ImageView imgAvatar;

    @BindView(R.id.item_info_container)
    protected LinearLayout infoContainer;

    @BindView(R.id.item_fullname)
    protected TextView txtName;

    @BindView(R.id.item_location)
    protected TextView txtLocation;

    @BindView(R.id.item_gender)
    protected TextView txtGender;

    @BindView(R.id.item_mail)
    protected TextView txtMail;

    @BindView(R.id.item_reg_date)
    protected TextView txtRegDate;

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
    public void onResume() {
        super.onResume();

        this.launchEnterAnimation();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.layoutId = R.layout.fragment_detail;
    }

    @Override
    protected void configViews() {
        super.configViews();

        String path = this.user.getPicture().getLarge();

        if (TextUtils.isValidAndNotEmptyString(path)) {
            Picasso.with(this.getContext()).load(path).into(this.imgAvatar);
        } else {
            Picasso.with(this.getContext()).load(R.drawable.default_avatar).into(this.imgAvatar);
        }
        this.txtMail.setText(this.user.getEmail());
        this.txtGender.setText(TextUtils.capitalize(this.user.getGender()));
        this.txtLocation.setText(TextUtils.capitalizeSentence(this.user.getLocation().getFullLocation()));
        this.txtName.setText(TextUtils.capitalizeSentence(this.user.getName().getFullName()));

        try {
            this.txtRegDate.setText(this.user.getRegistered());

        } catch (Exception e) {
            AndroidLogger.logError(TAG, "configViews()", e);

            e.printStackTrace();
        }
    }

    private void launchEnterAnimation() {

        //FIXME: not working...?
        Slide slide = new Slide(Gravity.BOTTOM);

        slide.addTarget(R.id.item_info_container);
        slide.setDuration(this.getResources().getInteger(R.integer.anim_duration));
        slide.setInterpolator(new LinearInterpolator());

        this.getActivity().getWindow().setEnterTransition(slide);
    }
}
