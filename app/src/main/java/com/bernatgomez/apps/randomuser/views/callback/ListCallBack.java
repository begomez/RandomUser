package com.bernatgomez.apps.randomuser.views.callback;


import android.support.v7.util.SortedList;
import android.support.v7.widget.RecyclerView;

import com.bernatgomez.apps.randomuser.models.UserModel;


/**
 * Callback used to communicate between data list and recycler adapter
 *
 * Created by bernatgomez on 09/09/2017.
 */
public class ListCallBack extends SortedList.Callback<UserModel> {

    private RecyclerView.Adapter adapter;

    private ListComparator comparator;

    /**
     *
     * @param adapter
     */
    public ListCallBack(RecyclerView.Adapter adapter) {
        this.adapter = adapter;

        this.initComponents();
    }

    private void initComponents() {
        this.comparator = new ListComparator();
    }

    @Override
    public int compare(UserModel user1, UserModel user2) {

        return this.comparator.compare(user1, user2);
    }

    @Override
    public boolean areContentsTheSame(UserModel oldItem, UserModel newItem) {
        return oldItem.equals(newItem);
    }

    @Override
    public boolean areItemsTheSame(UserModel user1, UserModel user2) {
        return user1.getName().equals(user2.getName());
    }

    @Override
    public void onChanged(int position, int count) {
        this.adapter.notifyItemChanged(position, count);
    }

    @Override
    public void onInserted(int position, int count) {
        this.adapter.notifyItemRangeInserted(position, count);
    }

    @Override
    public void onRemoved(int position, int count) {
        this.adapter.notifyItemRangeRemoved(position, count);
    }

    @Override
    public void onMoved(int fromPosition, int toPosition) {
        this.adapter.notifyItemMoved(fromPosition, toPosition);
    }
}
