package com.example.telim2.reglogvolley;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by telim2 on 25.07.2017.
 */

public class MySingleTon {
    private static MySingleTon mInstance;
    private RequestQueue requestQueue;
    private static Context mCxt;

    private MySingleTon(Context context){

        mCxt=context;
        requestQueue=getRequestQueue();

    }

    public RequestQueue getRequestQueue(){


        if(requestQueue==null){

            requestQueue= Volley.newRequestQueue(mCxt.getApplicationContext());
        }
        return requestQueue;

    }

    public static synchronized MySingleTon getInstance(Context context){

        if(mInstance==null){

            mInstance=new MySingleTon(context);
        }
        return mInstance;

    }

    public <T>void addToRequestQueue(Request<T> request){

        requestQueue.add(request);

    }




}
