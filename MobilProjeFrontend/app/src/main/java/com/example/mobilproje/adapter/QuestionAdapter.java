package com.example.mobilproje.adapter;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.mobilproje.api.ApiManager;
import com.example.mobilproje.model.Question;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class QuestionAdapter {
    private final ApiManager apiManager;
    private int currentQuestion;
    private final Random random;


    private final TextView title, questionParagraph;
    private final Button[] buttons;


    public QuestionAdapter(TextView title, TextView questionParagraph, Button[] buttons){
        currentQuestion = 1;
        apiManager = ApiManager.getInstance();
        
        this.title = title;
        this.questionParagraph = questionParagraph;
        this.buttons = buttons;

        this.random = new Random();
    }

    public void setQuestion(){
        Callback<Question> callback = new Callback<Question>() {
            @Override
            public void onResponse(Call<Question> call, Response<Question> response) {
                Question question;
                if (response.isSuccessful()) {
                    question = response.body();

                    title.setText("Soru " + currentQuestion++);
                    questionParagraph.setText(question.getQuestionParagraph());


                    String[] options = question.getOptions();
                    List<Integer> indicies = new ArrayList<Integer>();
                    for(int i = 0; i < options.length; i++)
                        indicies.add(i);



                    int answer = question.getAnswerIndex();
                    for(int i = 0; i< buttons.length; i++) {
                        int index = indicies.get(random.nextInt(indicies.size()));
                        buttons[index].setText(options[i]);
                        buttons[index].setOnClickListener(createOnClickListener(i, answer));
                        indicies.remove((Integer) index);
                    }
                }
            }

            @Override
            public void onFailure(Call<Question> call, Throwable t) {
                String text = "\n\nFAILED: " + t.getCause() + " Stack Traces: ";
                for(StackTraceElement s : t.getStackTrace())
                    text += s.toString() + '\n';

                System.out.println(text);
            }
        };

        apiManager.getRandomQuestion(callback);
    }

    private View.OnClickListener createOnClickListener(int buttonId, int answerId){
        return v -> {
            if(buttonId == answerId){
                //Correct Answer
            }
            else{
                //Wrong Answer
            }
            
            setQuestion();
        };
    }
}
