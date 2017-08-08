package com.example.telim2.reglogvolley;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentNavigableMap;

/**
 * Created by telim2 on 08.08.2017.
 */

public class MyAdapter2 extends RecyclerView.Adapter<MyAdapter2.ViewHolder> {



    private  List<RequestModel> requestModelList;
    private  Context context;
    String url="http://172.16.205.132/android/updateFriendship.php";
    String url2="http://172.16.205.132/android/deleteFriendship.php";



    public MyAdapter2 (List<RequestModel> requestModelList,Context context){

        this.requestModelList=requestModelList;
        this.context=context;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.listview2,parent,false);
        return new MyAdapter2.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final RequestModel requestModel=requestModelList.get(position);
        holder.buttonAdd.setText(requestModel.getId());
        holder.textViewFriend.setText(requestModel.getFriend());
        Picasso.with(context).load(requestModel.getImageLink()).into(holder.imageView);

        holder.buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                StringRequest stringRequest=new StringRequest(Request.Method.POST, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                holder.buttonAdd.setEnabled(false);
                                holder.buttonAdd.setText("delete");



                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(context,"error",Toast.LENGTH_LONG).show();

                            }
                        }){

                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String,String> params=new HashMap<String, String>();
                        params.put("user",requestModel.getGname());
                        params.put("friend",requestModel.getFriend());
                        return params;
                    }
                };

                MySingleTon.getInstance(context).addToRequestQueue(stringRequest);



            }
        });

        holder.buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                StringRequest stringRequest=new StringRequest(Request.Method.POST, url2,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                holder.buttonCancel.setEnabled(false);
                                holder.buttonCancel.setText("friendship deleted");



                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(context,"error",Toast.LENGTH_LONG).show();

                            }
                        }){

                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String,String> params=new HashMap<String, String>();
                        params.put("user",requestModel.getGname());
                        params.put("friend",requestModel.getFriend());
                        return params;
                    }
                };

                MySingleTon.getInstance(context).addToRequestQueue(stringRequest);



            }
        });


    }

    @Override
    public int getItemCount() {
        return requestModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView textViewFriend;
        public Button buttonAdd;
        public ImageView imageView;
        public Button buttonCancel;


        public ViewHolder(View itemView) {
            super(itemView);

            textViewFriend=(TextView)itemView.findViewById(R.id.textView2);
            buttonAdd=(Button)itemView.findViewById(R.id.buttonOk);
            imageView=(ImageView)itemView.findViewById(R.id.imageView);
            buttonCancel=(Button)itemView.findViewById(R.id.buttonCancel);
        }
    }
}
