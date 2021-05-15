package com.example.mobilproje.adapter;

import android.content.Context;

import com.example.mobilproje.api.ApiManager;
import com.example.mobilproje.api.SessionManager;
import com.example.mobilproje.model.user.AuthenticationRequest;
import com.example.mobilproje.model.user.AuthenticationResponse;
import com.example.mobilproje.model.user.MyUser;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginAdapter {
    private final ApiManager apiManager;
    private final SessionManager sessionManager;

    private MyUser user;



    public LoginAdapter(ApiManager apiManager, SessionManager sessionManager) {
        this.apiManager = apiManager;
        this.sessionManager = sessionManager;
    }

    public MyUser login(String username, String password, Context context){

        apiManager.getLoginResponse(new Callback<AuthenticationResponse>() {
            @Override
            public void onResponse(Call<AuthenticationResponse> call, Response<AuthenticationResponse> response) {
                if(response.isSuccessful()) {
                    AuthenticationResponse loginResponse = response.body();
                    sessionManager.saveAuthToken(loginResponse.getToken(), context);

                    user = new MyUser(loginResponse.getId(), loginResponse.getUsername(), loginResponse.getProfileId());
                    System.out.println("Login Successful!!!");
                }
                else
                    System.out.println("Login Response Is Not Successful!!!");
            }

            @Override
            public void onFailure(Call<AuthenticationResponse> call, Throwable t) {
                String text = "\n\nFAILED: " + t.getCause() + " Stack Traces: ";
                for(StackTraceElement s : t.getStackTrace())
                    text += s.toString() + '\n';

                System.out.println(text);
            }
        }, new AuthenticationRequest(username, password));

        return user;
    }
}
