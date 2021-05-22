package com.example.mobilproje.ui.main;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.mobilproje.R;
import com.example.mobilproje.adapter.ProfileAdapter;

public class ProfileActivity extends AppCompatActivity {

    private TextView usernameView;
    private TextView correctAnswerView;
    private TextView wrongAnswerView;
    private TextView totalAnswerView;
    private TextView successView;
    private TextView bestScoreView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        usernameView = findViewById(R.id.usernameText);
        correctAnswerView = findViewById(R.id.totalCorrectAnswersText);
        wrongAnswerView = findViewById(R.id.totalWrongAnswersText);
        totalAnswerView = findViewById(R.id.totalAnswersText);
        successView = findViewById(R.id.successRateText);
        bestScoreView = findViewById(R.id.best_score_text);


        ProfileAdapter adapter = new ProfileAdapter(usernameView, correctAnswerView, wrongAnswerView, totalAnswerView, successView, bestScoreView);
        adapter.setProfileLayout();
    }

    public void goMainPageOnClick(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}