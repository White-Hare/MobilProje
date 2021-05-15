package com.example.mobilproje.api;

import com.example.mobilproje.model.user.LoginRequest;
import com.example.mobilproje.model.user.LoginResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface IUserApi {
    @POST("Users/Authenticate")
    //@FormUrlEncoded
    Call<LoginResponse> login(@Body LoginRequest loginRequest);
}
