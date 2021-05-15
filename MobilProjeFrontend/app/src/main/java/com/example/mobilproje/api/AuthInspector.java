package com.example.mobilproje.api;

import android.content.Context;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class AuthInspector implements Interceptor{
    @NotNull
    @Override
    public Response intercept(@NotNull Chain chain) throws IOException {
        Request.Builder builder = chain.request().newBuilder();

        String token = SessionManager.getInstance().fetchAuthToken();

        if(token != null)
            builder.addHeader("Authorization", "Bearer " + token);

        return chain.proceed(builder.build());
    }
}
