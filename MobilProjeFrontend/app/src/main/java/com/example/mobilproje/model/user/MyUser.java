package com.example.mobilproje.model.user;

import com.example.mobilproje.model.Profile;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Data class that captures user information for logged in users retrieved from LoginRepository
 */
public class MyUser {
    @SerializedName("id")
    @Expose(serialize = false)
    private final long id;

    @SerializedName("username")
    @Expose
    private String username;

    @SerializedName("password")
    @Expose
    private String password;

    @SerializedName("profileId")
    @Expose(serialize = false)
    private final long profileId;

    public MyUser(long id, String username, String password, long profileId) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.profileId = profileId;
    }

    public MyUser(long id, String username, long profileId) {
        this.id = id;
        this.username = username;
        this.profileId = profileId;
    }

    public long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public long getProfileId() {
        return profileId;
    }
}