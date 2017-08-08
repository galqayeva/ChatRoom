package com.example.telim2.reglogvolley;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Notification extends AppCompatActivity {

    String username,url;

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private List<RequestModel> requestModelList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        recyclerView=(RecyclerView)findViewById(R.id.recycleview2);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        requestModelList=new ArrayList<>();

        Bundle bundle=getIntent().getExtras();
        username=bundle.getString("username");
        url="http://172.16.205.132/android/allrequests.php?username="+username;
        loadRecycleData();
    }

    private void loadRecycleData(){
        final ProgressDialog progressDialog=new ProgressDialog(this);
        progressDialog.setMessage("loaadinggg...");
        progressDialog.show();

        StringRequest stringRequest=new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();

                        try {
                            JSONObject jsonObject=new JSONObject(response);
                            JSONArray jsonArray=jsonObject.getJSONArray("gresponse");

                            for (int i=0;i<jsonArray.length();i++){
                                JSONObject o=jsonArray.getJSONObject(i);
                                RequestModel item=new RequestModel(
                                        "add",o.getString("friend"),
                                        "https://cdn.pixabay.com/photo/2013/07/13/11/34/apple-158419_960_720.png",
                                        o.getString("username")
                                );
                                requestModelList.add(item);


                            }

                            adapter=new MyAdapter2(requestModelList,getApplicationContext());
                            recyclerView.setAdapter(adapter);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);





    }
}
