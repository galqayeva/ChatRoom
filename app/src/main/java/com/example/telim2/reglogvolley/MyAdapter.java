package com.example.telim2.reglogvolley;

import android.app.LauncherActivity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by telim2 on 01.08.2017.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private List<friendModel> friendModels;
    private Context context;
    String temp_key;
    private DatabaseReference root ;




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
        final friendModel friendModel=friendModels.get(position);
        holder.buttonAdd.setText(friendModel.getId());
        holder.textViewFriend.setText(friendModel.getFriend());
        Picasso.with(context).load(friendModel.getImageLink()).into(holder.imageView);

        root=FirebaseDatabase.getInstance().getReference().child("friends").child("KqvHGZ5k4cnq6dm64K1");

        final String friendName=friendModel.getFriend();
        final String Gname=friendModel.getGname();

        holder.buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                DatabaseReference rootList=root.child(friendModel.getGname()).child("friendList").push();
//                Map<String,Object> map=new HashMap<String, Object>();
//                map.put("name",friendModel.getFriend());
//                rootList.updateChildren(map);

                DatabaseReference rootRequest=root.child("Gunay").child("friendRequest").push();
                Map<String,Object> map2=new HashMap<String, Object>();
                map2.put("name",friendModel.getFriend());
                rootRequest.updateChildren(map2);
            }
        });

    }

    @Override
    public int getItemCount() {
        return friendModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView textViewFriend;
        public Button buttonAdd;
        public ImageView imageView;


        public ViewHolder(View itemView) {
            super(itemView);

            textViewFriend=(TextView)itemView.findViewById(R.id.friendName);
            buttonAdd=(Button)itemView.findViewById(R.id.addButton);
            imageView=(ImageView)itemView.findViewById(R.id.image);
        }
    }
}
