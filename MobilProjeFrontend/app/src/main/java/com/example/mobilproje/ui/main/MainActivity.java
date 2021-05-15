package com.example.mobilproje.ui.main;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.mobilproje.R;
import com.example.mobilproje.adapter.LoginAdapter;
import com.example.mobilproje.api.ApiManager;
import com.example.mobilproje.api.SessionManager;
import com.example.mobilproje.ui.question.QuestionActivity;

public class MainActivity extends AppCompatActivity {
    private LoginAdapter loginAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        loginAdapter = new LoginAdapter(ApiManager.getInstance(), SessionManager.getInstance());
        loginAdapter.login("test", "test",  getApplicationContext());

    }


    public void startButtonOnClick(View v){
        Intent intent = new Intent(this, QuestionActivity.class);
        startActivity(intent);
    }
}