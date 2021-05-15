package com.example.mobilproje.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Question {
    @SerializedName("questionParagraph")
    @Expose(serialize = false)
    private final String questionParagraph;

    @SerializedName("options")
    @Expose(serialize = false)
    private final String[] options;

    @SerializedName("answerIndex")
    @Expose(serialize = false)
    private final int answerIndex;


    public Question(String questionParagraph, String[] options, int answer) {
        this.questionParagraph = questionParagraph;
        this.options = options;
        this.answerIndex = answer;
    }

    public int getAnswerIndex() {
        return answerIndex;
    }

    public String[] getOptions() {
        return options;
    }

    public String getQuestionParagraph() {
        return questionParagraph;
    }
}
