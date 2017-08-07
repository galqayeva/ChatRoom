package com.example.telim2.reglogvolley;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.Map;

public class Main2Activity extends AppCompatActivity {

    TextView signIn;
    EditText Username,Pass;
    Button login;
    String username,pass,shusername,shpass;
    String url="http://172.16.205.132/android/login.php";
    AlertDialog.Builder builder;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        signIn=(TextView)findViewById(R.id.textView);

        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(Main2Activity.this,MainActivity.class));
            }
        });


        builder=new AlertDialog.Builder(Main2Activity.this);

        login=(Button)findViewById(R.id.button2);
        Username=(EditText)findViewById(R.id.editText3);
        Pass=(EditText)findViewById(R.id.editText6);



        SharedPreferences sharedPreferences=Main2Activity.this.getSharedPreferences(getString(R.string.file),MODE_PRIVATE);
        shusername=sharedPreferences.getString(getString(R.string.username),"");
        if (!(shusername.equals(""))){
            Intent intent=new Intent(Main2Activity.this,loginActivity.class);
            Bundle bundle=new Bundle();
            bundle.putString("username",shusername);
            intent.putExtras(bundle);
            startActivity(intent);
        }


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                username=Username.getText().toString();
                pass=Pass.getText().toString();
                if(username.equals("") || pass.equals("")){

                    builder.setTitle("enter usernmae or pass");
                    displayAlert("enter valid usernmae");
                }

                else{

                    StringRequest stringRequest=new StringRequest(Request.Method.POST, url,
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {

                                    try {
                                        JSONArray jsonArray=new JSONArray(response);
                                        JSONObject jsonobject=jsonArray.getJSONObject(0);
                                        String code=jsonobject.getString("code");
                                        if(code.equals("Login Failed")){

                                            builder.setTitle("failed");
                                            displayAlert(jsonobject.getString("message"));

                                        }

                                        else
                                        {
                                            saveSettings();
                                            Intent intent=new Intent(Main2Activity.this,loginActivity.class);
                                           Bundle bundle=new Bundle();
                                           bundle.putString("username",jsonobject.getString("username"));
                                           bundle.putString("mail",jsonobject.getString("mail"));
                                            intent.putExtras(bundle);
                                            startActivity(intent);
                                        }
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }

                                }
                            },
                            new Response.ErrorListener() {
                                 @Override
                                 public void onErrorResponse(VolleyError error) {

                                     Toast.makeText(Main2Activity.this,"errrooor",Toast.LENGTH_LONG).show();

                        }
                    }){

                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            Map<String,String> params=new HashMap<String, String>();
                            params.put("username",username);
                            params.put("password",pass);
                            return params;
                        }
                    };

                    MySingleTon.getInstance(Main2Activity.this).addToRequestQueue(stringRequest);



                }

            }
        });
    }


    public void displayAlert(String message){
        builder.setMessage(message);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Username.setText("");
                Pass.setText("");

            }
        });
        AlertDialog alerdialog=builder.create();
        alerdialog.show();
    }

    public void saveSettings(){

        SharedPreferences sharedPreferences=Main2Activity.this.getSharedPreferences(getString(R.string.file),MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString(getString(R.string.username),username);
        editor.commit();


    }
}
