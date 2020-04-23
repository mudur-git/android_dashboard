package com.patriotnative.android_dashboard.network;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class VolleyHttpHandler {
    private static VolleyHttpHandler instance;
    private RequestQueue requestQueue;
    private static Context ctx;

    private VolleyHttpHandler(Context context) {
        ctx = context;
        requestQueue = getRequestQueue();
    }

    public RequestQueue getRequestQueue() {
        if (requestQueue == null) {
            requestQueue = Volley.newRequestQueue(ctx.getApplicationContext());
        }
        return requestQueue;
    }

    public static synchronized VolleyHttpHandler getInstance(Context context) {
        if (instance == null) {
            instance = new VolleyHttpHandler(context);
        }
        return instance;
    }

    public <T> void addRequestQueue (Request<T> request) {
        requestQueue.add(request);
    }
}
