package com.example.telim2.reglogvolley;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class Notification extends AppCompatActivity {

    String username,friend;

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private List<friendModel> friendModels;
    DatabaseReference root;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        recyclerView=(RecyclerView)findViewById(R.id.recycleview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        friendModels=new ArrayList<>();

        Bundle bundle=getIntent().getExtras();
        username=bundle.getString("username");
        loadRecycleData();

    }
    private void loadRecycleData(){
        root = FirebaseDatabase.getInstance().getReference().child("KqvHGZ5k4cnq6dm64K1");

        root.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Iterator i = dataSnapshot.getChildren().iterator();


                while (i.hasNext()){
                    friend=(String)((DataSnapshot)i.next()).getValue();

                    friendModel item=new friendModel(
                            "add",friend,
                            "https://cdn.pixabay.com/photo/2013/07/13/11/34/apple-158419_960_720.png",
                            username
                    );

                }

                adapter=new MyFireAdapter(friendModels,getApplicationContext());
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        })


    }
}
