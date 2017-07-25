package com.example.telim2.reglogvolley;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

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

public class MainActivity extends AppCompatActivity {

    Button button;
    EditText Name,Username,Password,Mail;
    String name,username,password,mail;

    String url="http://172.16.205.132/android/sign.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        button=(Button)findViewById(R.id.button);
        Name=(EditText)findViewById(R.id.editText);
        Username=(EditText)findViewById(R.id.editText2);
        Password=(EditText)findViewById(R.id.editText5);
        Mail=(EditText)findViewById(R.id.editText4);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name=Name.getText().toString();
                username=Username.getText().toString();
                password=Password.getText().toString();
                mail=Mail.getText().toString();


                StringRequest stringRequest=new StringRequest(Request.Method.POST, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {

                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {

                            }
                        }){
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String,String> params=new HashMap<String, String>();
                        params.put("name",name);
                        params.put("username",username);
                        params.put("mail",mail);
                        params.put("password",password);
                        return params;
                    }
                };

                MySingleTon.getInstance(MainActivity.this).addToRequestQueue(stringRequest);



            }

        });
    }
}
