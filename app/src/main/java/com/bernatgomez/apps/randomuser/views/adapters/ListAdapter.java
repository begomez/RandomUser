package com.bernatgomez.apps.randomuser.views.adapters;

import android.support.constraint.ConstraintLayout;
import android.support.v7.util.SortedList;
import android.support.v7.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bernatgomez.apps.randomuser.R;
import com.bernatgomez.apps.randomuser.views.callback.ListAdapterCallBack;
import com.bernatgomez.apps.randomuser.views.callback.ListComparator;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import com.bernatgomez.apps.randomuser.models.UserModel;


/**
 * Random user adapter for data binding
 *
 * Created by bernatgomez on 09/09/2017.
 */
public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ListHolder> {

    private SortedList<UserModel> data;

    private ListComparator comparator;

    private OnImageListener listener;


    /**
     *
     * @param users
     */
    public ListAdapter(List<UserModel> users, OnImageListener listener, ListComparator comparator) {
        this.listener = listener;

        this.comparator = comparator;

        this.initData(users);
    }

    private void initData(List<UserModel> users) {
        SortedList.Callback<UserModel> listCallback = new ListAdapterCallBack(this, this.comparator);

        this.data = new SortedList<UserModel>(UserModel.class, listCallback);

        this.addElements(users);
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
        this.addElements(users);
    }

    private void addElements(List<UserModel> elements) {
        this.data.addAll(elements);

        //XXX: not needed, sorted list callback notifies changes to the adapter
        //this.notifyDataSetChanged();
    }

    public void replaceElements(List<UserModel> candidates) {
        this.data.beginBatchedUpdates();

        for (int i = this.data.size() - 1; i >= 0; i--) {
            UserModel current = this.data.get(i);

            if (!candidates.contains(current)) {
                this.data.remove(current);
            }
        }

        this.addElements(candidates);

        this.data.endBatchedUpdates();
    }

    public List<UserModel> filter(String query) {
        List<UserModel> temp = new ArrayList<UserModel>();
        for (int i = 0; i < this.data.size(); i++) {
            UserModel current = this.data.get(i);

            if (current.getName().toString().toLowerCase().contains(query)) {
                temp.add(current);
            }
        }

        this.replaceElements(temp);

        return temp;
    }

////////////////////////////////////////////////////////////////////////////////////////////////////
// HOLDER
////////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * List view holder
     */
    final class ListHolder extends RecyclerView.ViewHolder {

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

        @BindView(R.id.item_action)
        protected ImageView imgAction;


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
        }

        public void setData(UserModel user) {
            Picasso.with(this.itemView.getContext()).load(user.getPicture().getMedium()).into(this.imgAvatar);
            this.txtFullName.setText(user.getName().getFullName());
            this.txtEmail.setText(user.getEmail());
            this.txtPhone.setText(user.getPhone());
        }

        @OnClick(R.id.item_avatar)
        public void onImgClick(View view) {
            if (listener != null) {
                listener.onUserSelected(data.get(this.getAdapterPosition()));
            }
        }

        @OnClick(R.id.item_action)
        public void onActionClick(View view) {
            if (listener != null) {
                listener.onUserDisabled(data.get(this.getAdapterPosition()));
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
        public void onUserSelected(UserModel user);
        public void onUserDisabled(UserModel user);
    }

}
