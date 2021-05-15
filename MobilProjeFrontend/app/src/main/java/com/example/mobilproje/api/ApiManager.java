package com.example.mobilproje.api;

import android.app.DownloadManager;

import com.example.mobilproje.model.Question;
import com.example.mobilproje.model.user.LoginRequest;
import com.example.mobilproje.model.user.LoginResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiManager {
    private static IQuestionApi questionService;
    private static IUserApi userService;
    private static ApiManager instance;


    private ApiManager(){
        Retrofit retrofit = new Retrofit.Builder().
                baseUrl("http://10.0.2.2:5000/").
                addConverterFactory(GsonConverterFactory.create()).
                build();

        questionService = retrofit.create(IQuestionApi.class);
        userService = retrofit.create(IUserApi.class);
    }

    public static ApiManager getInstance(){
        if (instance == null)
            instance = new ApiManager();

        return instance;
    }

    public void getRandomQuestion(Callback<Question> questionCallback){
        Call<Question> questionCall = questionService.getRandomQuestion();
        questionCall.enqueue(questionCallback);
    }

    public void getLoginResponse(Callback<LoginResponse> loginResponseCallback, LoginRequest loginRequest){
        Call<LoginResponse> loginRequestCall = userService.login(loginRequest);
        loginRequestCall.enqueue(loginResponseCallback);
    }
}
