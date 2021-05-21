package com.example.mobilproje.api.interfaces;

import com.example.mobilproje.model.Profile;
import com.example.mobilproje.model.Question;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface IProfileApi {
    @GET("api/Profiles/{id}")
    Call<Profile> getProfile(@Path("id") long id);

    @PUT("api/Profiles/{id}")
    Call<Profile> updateProfile(@Path("id") long id, @Body Profile profile);
}
