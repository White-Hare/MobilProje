package com.example.mobilproje.api;

import com.example.mobilproje.api.interfaces.IProfileApi;
import com.example.mobilproje.api.interfaces.IQuestionApi;
import com.example.mobilproje.api.interfaces.IUserApi;
import com.example.mobilproje.model.Profile;
import com.example.mobilproje.model.Question;
import com.example.mobilproje.model.user.AuthenticationRequest;
import com.example.mobilproje.model.user.AuthenticationResponse;
import com.example.mobilproje.model.user.MyUser;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiManager {
    private static IQuestionApi questionService;
    private static IProfileApi profileService;
    private static IUserApi userService;
    private static ApiManager instance;


    private ApiManager(){
        Retrofit retrofit = new Retrofit.Builder().
                baseUrl("http://10.0.2.2:5000/").
                addConverterFactory(GsonConverterFactory.create()).
                client(okHttpClient()).
                build();

        questionService = retrofit.create(IQuestionApi.class);
        userService = retrofit.create(IUserApi.class);
        profileService = retrofit.create(IProfileApi.class);
    }

    public static ApiManager getInstance(){
        if (instance == null)
            instance = new ApiManager();

        return instance;
    }

    private OkHttpClient okHttpClient(){
        return new OkHttpClient.Builder().
                addInterceptor(new AuthInspector()).build();
    }

    public void getRandomQuestion(Callback<Question> questionCallback){
        Call<Question> questionCall = questionService.getRandomQuestion();
        questionCall.enqueue(questionCallback);
    }

    public void getLoginResponse(Callback<AuthenticationResponse> loginResponseCallback, AuthenticationRequest loginRequest){
        Call<AuthenticationResponse> loginRequestCall = userService.login(loginRequest);
        loginRequestCall.enqueue(loginResponseCallback);
    }

    public void register(Callback<MyUser> registerCallback, AuthenticationRequest registerRequest){
        Call<MyUser> registerRequestCall = userService.register(registerRequest);
        registerRequestCall.enqueue(registerCallback);
    }

    public void getProfile(Callback<Profile> profileCallback, long id){
        Call<Profile> profileCall = profileService.getProfile(id);
        profileCall.enqueue(profileCallback);
    }

    public void updateProfile(Callback<Profile> profileCallback, long id, Profile profile){
        Call<Profile> profileCall = profileService.updateProfile(id, profile);
        profileCall.enqueue(profileCallback);
    }
}
