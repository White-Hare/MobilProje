package com.example.mobilproje.adapter;

import android.content.Context;
import android.widget.ProgressBar;

import com.example.mobilproje.R;
import com.example.mobilproje.api.ApiManager;
import com.example.mobilproje.api.SessionManager;
import com.example.mobilproje.model.user.AuthenticationRequest;
import com.example.mobilproje.model.user.AuthenticationResponse;
import com.example.mobilproje.model.user.MyUser;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public abstract class LoginAdapter implements ILogReg{
    private final ApiManager apiManager;
    private final SessionManager sessionManager;

    private ProgressBar loadingProgressBar;

    private MyUser user;


    public LoginAdapter(ProgressBar loadingProgressBar) {
        this.apiManager = ApiManager.getInstance();
        this.sessionManager = SessionManager.getInstance();
        this.loadingProgressBar = loadingProgressBar;
    }

    public MyUser login(String username, String password, Context context){


        apiManager.getLoginResponse(new Callback<AuthenticationResponse>() {
            @Override
            public void onResponse(Call<AuthenticationResponse> call, Response<AuthenticationResponse> response) {
                if(response.isSuccessful()) {
                    AuthenticationResponse loginResponse = response.body();

                    user = new MyUser(loginResponse.getId(), loginResponse.getUsername(), loginResponse.getProfileId());
                    sessionManager.saveAuthToken(loginResponse.getToken(), user, context);

                    updateUiWithUser(user);
                    System.out.println("Login Successful!!!");
                }
                else {
                    System.out.println("Login Response Is Not Successful!!!");
                    showProcessFailed(R.string.login_failed);
                }
            }

            @Override
            public void onFailure(Call<AuthenticationResponse> call, Throwable t) {
                String text = "\n\nFAILED: " + t.getCause() + " Stack Traces: ";
                for(StackTraceElement s : t.getStackTrace())
                    text += s.toString() + '\n';

                showProcessFailed(R.string.login_failed);
                System.out.println(text);
            }
        }, new AuthenticationRequest(username, password));

        return user;
    }
}
