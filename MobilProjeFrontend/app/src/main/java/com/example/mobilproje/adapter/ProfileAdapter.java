package com.example.mobilproje.adapter;

import android.widget.TextView;

import com.example.mobilproje.api.ApiManager;
import com.example.mobilproje.api.SessionManager;
import com.example.mobilproje.model.Profile;
import com.example.mobilproje.model.Question;
import com.example.mobilproje.model.user.MyUser;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileAdapter {
    private TextView usernameView;
    private TextView correctAnswerView;
    private TextView wrongAnswerView;
    private TextView totalAnswerView;
    private TextView successView;
    private TextView bestScoreView;

    public ProfileAdapter(TextView usernameView, TextView correctAnswerView, TextView wrongAnswerView, TextView totalAnswerView, TextView successView, TextView bestScoreView) {
        this.usernameView = usernameView;
        this.correctAnswerView = correctAnswerView;
        this.wrongAnswerView = wrongAnswerView;
        this.totalAnswerView = totalAnswerView;
        this.successView = successView;
        this.bestScoreView = bestScoreView;
    }

    public void setProfileLayout(){
        MyUser user = SessionManager.getInstance().getUser();

        if(user == null) {
            System.out.println("fuck");
            return;
        }

        Callback<Profile> callback = new Callback<Profile>() {
            @Override
            public void onResponse(Call<Profile> call, Response<Profile> response) {
                Profile profile;
                if (response.isSuccessful()) {
                    profile = response.body();

                    int correctAnswers = profile.getTotalCorrectAnswers();
                    int wrongAnswers = profile.getTotalWrongAnswers();
                    int totalAnswers = correctAnswers + wrongAnswers;

                    usernameView.setText("Kullanıcı Adı: " + user.getUsername());
                    correctAnswerView.setText("Toplam Doğru Cevap: " + correctAnswers);
                    wrongAnswerView.setText("Toplam Yanlış Cevap: " + wrongAnswers);
                    totalAnswerView.setText("Toplam Cevap Sayısı: " + totalAnswers);


                    if(totalAnswers != 0)
                        successView.setText("Başarı Oranı: %" + (correctAnswers / totalAnswers * 100.f));
                    else
                        successView.setText("Başarı Oranı: %0" );

                    bestScoreView.setText("En İyi Puan: " + profile.getBestScore());
                }
            }


            @Override
            public void onFailure(Call<Profile> call, Throwable t) {
                String text = "\n\nFAILED: " + t.getCause() + " Stack Traces: ";
                for(StackTraceElement s : t.getStackTrace())
                    text += s.toString() + '\n';

                System.out.println(text);
            }
        };

        ApiManager.getInstance().getProfile(callback, user.getProfileId());
    }
}
