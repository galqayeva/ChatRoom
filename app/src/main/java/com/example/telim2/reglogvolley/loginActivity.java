package com.example.telim2.reglogvolley;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class loginActivity extends AppCompatActivity {

    TextView name,mail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        name=(TextView)findViewById(R.id.textView4);
        mail=(TextView)findViewById(R.id.textView3);

        Bundle bundle=getIntent().getExtras();
        name.setText("Xosgelmisiniz "+bundle.getString("username"));
        mail.setText("your mail is "+bundle.getString("mail"));
    }
}
