package com.patriotnative.android_dashboard.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.patriotnative.android_dashboard.R;
import com.patriotnative.android_dashboard.interfaces.RetrofitInterface;
import com.patriotnative.android_dashboard.models.Data;
import com.patriotnative.android_dashboard.network.RetrofitHttpHandler;
import com.patriotnative.android_dashboard.network.VolleyHttpHandler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;

public class MainActivity extends AppCompatActivity {
    private CardView cardView;
    private Button button;
    private Button button2;
    private TextView textView;
    private TextView textView2;
    private final String url = "https://postman-echo.com/post";
    List<Data> remoteDataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cardView = findViewById(R.id.CardView);
        button = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        textView = findViewById(R.id.textView2);
        textView2 = findViewById(R.id.textView2);
        remoteDataList = new ArrayList<>();


        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, RV.class);
                startActivity(intent);
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        textView.setText(response);
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        textView.setText(error.toString());
                        error.printStackTrace();
                    }
                }) {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {

                        Map<String, String> params = new HashMap<>();
                        params.put("first", "1");
                        params.put("second", "2");


                        return params;
                    }
                };
                VolleyHttpHandler.getInstance(getApplicationContext()).addRequestQueue(stringRequest);
            }
        });


        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RetrofitInterface retrofitInterface = RetrofitHttpHandler.getClient().create(RetrofitInterface.class);
                Call<List<Data>> call = retrofitInterface.getData();
                call.enqueue(new Callback<List<Data>>() {
                    @Override
                    public void onResponse(Call<List<Data>> call, retrofit2.Response<List<Data>> response) {
                        remoteDataList = response.body();

                        textView2.setText(remoteDataList.size() + " Data got it");
                    }

                    @Override
                    public void onFailure(Call<List<Data>> call, Throwable t) {

                    }
                });
            }
        });

    }


}
