package com.example.mobilproje.api.interfaces;

import com.example.mobilproje.model.Profile;
import com.example.mobilproje.model.Question;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;

public interface IProfileApi {
    @GET("api/Profiles/{id}")
    Call<Profile> getProfile(long id);

    @PUT("api/Profiles/{id}")
    Call<Profile> updateProfile(long id, Profile profile);
}
