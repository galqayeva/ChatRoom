package com.example.telim2.reglogvolley;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Friends extends AppCompatActivity {

    String username;

    String url="http://172.16.205.132/android/login.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friends);

        Bundle bundle=getIntent().getExtras();
        username=bundle.getString("username");


//        String[] friends={"ada","gun","admin"};
//
//        ListAdapter adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,friends);
//        ListView friendList=(ListView)findViewById(R.id.find_friends);
//        friendList.setAdapter(adapter);

        StringRequest stringRequest=new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONArray jsonArray = new JSONArray(response);
                            JSONObject jsonobject=jsonArray.getJSONObject(0);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }

        ){

            @Override
            protected Map<String> getParams() throws AuthFailureError {
                Map<String> params=new HashMap<String>();
                params.put("username",username);
                return params;
            }
        };

        MySingleTon.getInstance(Friends.this).addToRequestQueue(stringRequest);







    }
}
