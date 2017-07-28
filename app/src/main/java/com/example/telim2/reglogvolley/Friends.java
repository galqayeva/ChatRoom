package com.example.telim2.reglogvolley;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

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
    TextView textView;

    String url="http://172.16.205.132/android/friendlist.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friends);

        Bundle bundle=getIntent().getExtras();
        username=bundle.getString("username");

        textView=(TextView)findViewById(R.id.textView2);

        //textView.setText(username);


        StringRequest stringRequest=new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {


                        try {
                            JSONArray jsonArray=new JSONArray(response);
                            JSONObject jsonObject;
                            String k="";

                            for(int i=0;i<jsonArray.length();i++){
                                jsonObject=jsonArray.getJSONObject(i);
                                k=k+jsonObject.getString("friend");
                            }

                            textView.setText(k);



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
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params=new HashMap<String, String>();
                params.put("username","ada");

                return params;
            }
        };

        MySingleTon.getInstance(Friends.this).addToRequestQueue(stringRequest);







    }
}
