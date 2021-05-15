package com.example.mobilproje.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Profile {
    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("totalCorrectAnswers")
    @Expose
    private int totalCorrectAnswers;
    @SerializedName("totalWrongAnswers")
    @Expose
    private int totalWrongAnswers;
    @SerializedName("bestScore")
    @Expose
    private int bestScore;

    public Profile(int id, int totalCorrectAnswers, int totalWrongAnswers, int bestScore) {
        this.id = id;
        this.totalCorrectAnswers = totalCorrectAnswers;
        this.totalWrongAnswers = totalWrongAnswers;
        this.bestScore = bestScore;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTotalCorrectAnswers() {
        return totalCorrectAnswers;
    }

    public void setTotalCorrectAnswers(int totalCorrectAnswers) {
        this.totalCorrectAnswers = totalCorrectAnswers;
    }

    public int getTotalWrongAnswers() {
        return totalWrongAnswers;
    }

    public void setTotalWrongAnswers(int totalWrongAnswers) {
        this.totalWrongAnswers = totalWrongAnswers;
    }

    public int getBestScore() {
        return bestScore;
    }

    public void setBestScore(int bestScore) {
        this.bestScore = bestScore;
    }
}
