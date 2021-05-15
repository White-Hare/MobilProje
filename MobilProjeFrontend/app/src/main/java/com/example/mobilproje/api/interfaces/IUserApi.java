package com.example.mobilproje.api.interfaces;

import com.example.mobilproje.model.user.AuthenticationRequest;
import com.example.mobilproje.model.user.AuthenticationResponse;
import com.example.mobilproje.model.user.MyUser;
import com.google.gson.annotations.Expose;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface IUserApi {
    @POST("Users/Authenticate")
    Call<AuthenticationResponse> login(@Body AuthenticationRequest loginRequest);

    @POST("Users/Register")
    Call<MyUser> register(@Body AuthenticationRequest registerRequest);
}
