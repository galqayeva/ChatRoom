package com.example.telim2.reglogvolley;

import android.app.LauncherActivity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by telim2 on 01.08.2017.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private List<friendModel> friendModels;
    private Context context;

    public MyAdapter(List<friendModel> friendModelList, Context context) {
        this.friendModels = friendModelList;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.listview,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        friendModel friendModel=friendModels.get(position);
        holder.buttonAdd.setText(friendModel.getId());
        holder.textViewFriend.setText(friendModel.getFriend());

    }

    @Override
    public int getItemCount() {
        return friendModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView textViewFriend;
        public Button buttonAdd;


        public ViewHolder(View itemView) {
            super(itemView);

            textViewFriend=(TextView)itemView.findViewById(R.id.friendName);
            buttonAdd=(Button)itemView.findViewById(R.id.addButton);
        }
    }
}
