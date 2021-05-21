package com.example.mobilproje.ui.main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.app.UiModeManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.mobilproje.R;
import com.example.mobilproje.adapter.LoginAdapter;
import com.example.mobilproje.api.SessionManager;
import com.example.mobilproje.ui.login.LoginActivity;
import com.example.mobilproje.ui.question.QuestionActivity;


public class MainActivity extends AppCompatActivity {
    private LoginAdapter loginAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(SessionManager.getInstance().fetchAuthToken() == null){
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            return;
        }
    }


    public void startButtonOnClick(View v){
        Intent intent = new Intent(this, QuestionActivity.class);
        startActivity(intent);
    }

    public void goProfileButtonOnClick(View v){
        Intent intent = new Intent(this, ProfileActivity.class);
        startActivity(intent);
    }

    public void logoutButtonOnClick(View v){
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    public void switchThemeButtonOnClick(View view) {


        if (AppCompatDelegate.getDefaultNightMode() != AppCompatDelegate.MODE_NIGHT_YES) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else{
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }
    }
}