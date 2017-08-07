package com.example.telim2.reglogvolley;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.google.firebase.database.DatabaseReference;

import java.util.List;

/**
 * Created by telim2 on 07.08.2017.
 */

public class MyFireAdapter extends RecyclerView.Adapter<MyFireAdapter.ViewHolder> {


    private List<friendModel> friendModels;
    private Context context;
    String temp_key;
    private DatabaseReference root ;

    public MyFireAdapter(List<friendModel> friendModelList, Context context) {
        this.friendModels = friendModelList;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolder {
    }
}
