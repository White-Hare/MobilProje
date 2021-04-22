package com.example.mobilproje.ui.question;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.mobilproje.R;
import com.example.mobilproje.ui.main.MainActivity;

public class QuestionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);
    }

    public void returnMainMenuButtonOnClick(View v){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}