package com.example.mobilproje.ui.question;

import androidx.appcompat.app.AppCompatActivity;

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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

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

    public void returnMainMenuButtonOnClick(View v){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    private void gameOver(){

    }

    private void correctAnswer(){

    }
}