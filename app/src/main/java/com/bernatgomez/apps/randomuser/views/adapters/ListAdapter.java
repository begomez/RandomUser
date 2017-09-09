package com.bernatgomez.apps.randomuser.views.adapters;

import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bernatgomez.apps.randomuser.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import com.bernatgomez.apps.randomuser.models.UserModel;


/**
 * Random user adapter for data binding
 *
 * Created by bernatgomez on 09/09/2017.
 */
public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ListHolder> {

    private List<UserModel> data;

    private OnImageListener listener;


    /**
     *
     * @param data
     */
    public ListAdapter(List<UserModel> data, OnImageListener listener) {

        this.data = data;

        this.listener = listener;
    }

    @Override
    public ListHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list, parent, false);
        return new ListHolder(v);
    }

    @Override
    public void onBindViewHolder(ListHolder holder, int position) {
        holder.setData(this.data.get(position));
    }

    @Override
    public int getItemCount() {
        return data != null? data.size() : 0;
    }

    public void resetAdapter(List<UserModel> users) {
        this.data.addAll(users);

        this.notifyDataSetChanged();
    }

////////////////////////////////////////////////////////////////////////////////////////////////////
// HOLDER
////////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * List view holder
     */
    final class ListHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.item_container)
        protected ConstraintLayout container;

        @BindView(R.id.item_avatar)
        protected ImageView imgAvatar;

        @BindView(R.id.item_fullname)
        protected TextView txtFullName;

        @BindView(R.id.item_mail)
        protected TextView txtEmail;

        @BindView(R.id.item_phone)
        protected TextView txtPhone;


        /**
         * Constructor
         *
         * @param itemView
         */
        public ListHolder(View itemView) {
            super(itemView);

            this.init();
        }

        private void init() {
            ButterKnife.bind(this, this.itemView);

            this.imgAvatar.setOnClickListener(this);
        }

        public void setData(UserModel user) {
            Picasso.with(this.itemView.getContext()).load(user.getPicture().getMedium()).into(this.imgAvatar);
            this.txtFullName.setText(user.getName().getFullName());
            this.txtEmail.setText(user.getEmail());
            this.txtPhone.setText(user.getPhone());
        }

        @Override
        public void onClick(View view) {
            if (listener != null) {
                listener.onImageClick(data.get(this.getAdapterPosition()));
            }
        }
    }

////////////////////////////////////////////////////////////////////////////////////////////////////
// CLICK INTERFACE DEFINITION
////////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     *
     */
    public interface OnImageListener {
        public void onImageClick(UserModel user);
    }

}
