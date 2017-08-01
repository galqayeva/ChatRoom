package com.example.telim2.reglogvolley;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class FindFriends extends AppCompatActivity {
String username,url;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_friends);

        Bundle bundle=getIntent().getExtras();
        username=bundle.getString("username");
        url="http://172.16.205.132/android/friendlist.php?username="+username;
    }
}
