package com.example.mobilproje.adapter;

import android.content.Context;
import android.widget.ProgressBar;

import com.example.mobilproje.R;
import com.example.mobilproje.api.ApiManager;
import com.example.mobilproje.api.SessionManager;
import com.example.mobilproje.model.user.AuthenticationRequest;
import com.example.mobilproje.model.user.MyUser;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public abstract class RegisterAdapter implements ILogReg{
    private final ApiManager apiManager;
    private final SessionManager sessionManager;

    private ProgressBar loadingProgressBar;

    private MyUser user;


    public RegisterAdapter(ProgressBar loadingProgressBar) {
        this.apiManager = ApiManager.getInstance();
        this.sessionManager = SessionManager.getInstance();
        this.loadingProgressBar = loadingProgressBar;
    }

    public MyUser register(String username, String password, Context context){


        apiManager.register(new Callback<MyUser>() {
            @Override
            public void onResponse(Call<MyUser> call, Response<MyUser> response) {
                if(response.isSuccessful()) {
                    MyUser user = response.body();

                    updateUiWithUser(user);
                    System.out.println("Register Successful!!!");
                }
                else {
                    System.out.println("Register Is Not Successful!!!");
                    showProcessFailed(R.string.register_failed);
                }
            }

            @Override
            public void onFailure(Call<MyUser> call, Throwable t) {
                String text = "\n\nFAILED: " + t.getCause() + " Stack Traces: ";
                for(StackTraceElement s : t.getStackTrace())
                    text += s.toString() + '\n';

                showProcessFailed(R.string.register_failed);
                System.out.println(text);
            }
        }, new AuthenticationRequest(username, password));

        return user;
    }
}
