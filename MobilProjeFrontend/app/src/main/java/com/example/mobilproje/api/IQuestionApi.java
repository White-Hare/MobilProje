package com.example.mobilproje.api;

import com.example.mobilproje.model.Question;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface IQuestionApi {
    @GET("api/Questions/Random")
    Call<Question> getRandomQuestion();
}
