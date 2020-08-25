package com.shraddha.myretroapplication.services;

//import com.shraddha.myretrofitbasicapplication.model.User;
import com.shraddha.myretroapplication.model.User;

import com.shraddha.myretroapplication.model.User;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ServiceApi {
    @GET("register.php")
    Call<User> doRegisteration(
            @Query("name") String name,
            @Query("email") String email,
            @Query("phone") String phone,
            @Query("password") String password
    );

    @GET("login.php")
    Call<User> doLogin(
            @Query("email") String email,
            @Query("password") String password
    );

}

