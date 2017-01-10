package com.example.lenovo.retrofitapp;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Lenovo on 1/10/2017.
 */

public interface MyAPIEndPointInterface {
    @GET("search/users")
    Call<User> getUserName(@Query("q") String name);
}
