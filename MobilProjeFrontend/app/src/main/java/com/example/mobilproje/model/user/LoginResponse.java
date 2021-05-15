package com.example.mobilproje.model.user;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoginResponse {

    @SerializedName("id")
    @Expose(serialize = false)
    private final long id;
    @SerializedName("username")
    @Expose(serialize = false)
    private final String username;
    @SerializedName("profileId")
    @Expose(serialize = false)
    private final long profileId;
    @SerializedName("token")
    @Expose(serialize = false)
    private final String token;


    public LoginResponse(long id, String username, long profileId, String token) {
        this.id = id;
        this.username = username;
        this.profileId = profileId;
        this.token = token;
    }

    public long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public long getProfileId() {
        return profileId;
    }

    public String getToken() {
        return token;
    }
}
