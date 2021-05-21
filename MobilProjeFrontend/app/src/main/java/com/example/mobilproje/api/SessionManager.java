package com.example.mobilproje.api;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.mobilproje.R;
import com.example.mobilproje.model.user.MyUser;

public class SessionManager {
    private static SessionManager instance;

    private SharedPreferences preferences;
    private final String USER_TOKEN = "user_token";
    private MyUser user;

    private SessionManager(){ }

    public static SessionManager getInstance(){
        if(instance == null)
            instance = new SessionManager();

        return instance;
    }


    public void saveAuthToken(String token, MyUser user, Context context){
        preferences = context.getSharedPreferences(context.getString(R.string.app_name), Context.MODE_PRIVATE);

        SharedPreferences.Editor editor =preferences.edit();
        editor.putString(USER_TOKEN, token);
        editor.apply();

        this.user = user;
    }

    public String fetchAuthToken(){
        if(preferences == null)
            return null;

        return preferences.getString(USER_TOKEN, null);
    }


    public MyUser getUser() {
        return user;
    }
}
