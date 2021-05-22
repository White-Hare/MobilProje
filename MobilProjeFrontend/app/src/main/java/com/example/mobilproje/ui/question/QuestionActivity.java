package com.example.mobilproje.ui.question;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.mobilproje.R;
import com.example.mobilproje.adapter.QuestionAdapter;
import com.example.mobilproje.ui.main.MainActivity;

public class QuestionActivity extends AppCompatActivity {
    private QuestionAdapter questionAdapter;

    private boolean isWaiting = false;
    private int currentScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);


        currentScore = 0;

        questionAdapter = new QuestionAdapter(
                (TextView)findViewById(R.id.question_title),
                (TextView)findViewById(R.id.question_paragraph),
                new Button[]{
                        (Button)findViewById(R.id.option_a),
                        (Button)findViewById(R.id.option_b),
                        (Button)findViewById(R.id.option_c),
                        (Button)findViewById(R.id.option_d)
                }
        ){

            @Override
            protected View.OnClickListener createOnClickListener(Button button, int buttonId, int answerId) {
                return v -> {
                    if(isWaiting)
                        return;

                    isWaiting = true;

                    if(buttonId == answerId){
                        //Correct Answer
                        button.setBackgroundColor(getResources().getColor(R.color.green));
                        currentScore += 10;

                    }
                    else{
                        //Wrong Answer
                        button.setBackgroundColor(getResources().getColor(R.color.red));
                        gameOver();
                        return;
                    }


                    (new Handler()).postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            questionAdapter.setQuestion();
                            button.setBackgroundColor(getResources().getColor(R.color.blue));
                            isWaiting =false;
                        }
                    }, 2000);
                };
            }
        };

        questionAdapter.setQuestion();
    }

    private void gameOver(){
        Bundle args = new Bundle();
        args.putInt(GameOverFragment.ARG_CURRENT_SCORE, currentScore);

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();

        GameOverFragment fragment = new GameOverFragment();
        fragment.setArguments(args);
        ft.replace(R.id.game_over_fragment, fragment);

        ft.commit();

        findViewById(R.id.game_over_fragment).setVisibility(View.VISIBLE);
    }

    public void restartGameOnClickButton(View v){
        Intent intent = getIntent();
        finish();
        startActivity(intent);
    }

    public void returnMainMenuButtonOnClick(View v){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}