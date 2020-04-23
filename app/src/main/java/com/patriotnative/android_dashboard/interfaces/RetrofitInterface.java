package com.patriotnative.android_dashboard.interfaces;

import com.patriotnative.android_dashboard.models.Data;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RetrofitInterface {

    @GET("data.json")
    Call<List<Data>> getData();


}


