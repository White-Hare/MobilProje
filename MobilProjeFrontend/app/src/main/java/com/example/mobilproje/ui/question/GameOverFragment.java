package com.example.mobilproje.ui.question;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mobilproje.R;
import com.example.mobilproje.adapter.ProfileAdapter;
import com.example.mobilproje.ui.main.MainActivity;


public class GameOverFragment extends Fragment {

    public static final String ARG_CURRENT_SCORE = "current_score";

    private int currentScore;

    public GameOverFragment() {
        // Required empty public constructor
    }

    public static GameOverFragment newInstance(int currentScore) {
        GameOverFragment fragment = new GameOverFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_CURRENT_SCORE, currentScore);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            currentScore = getArguments().getInt(ARG_CURRENT_SCORE);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_game_over, container, false);

        ProfileAdapter adapter = new ProfileAdapter(v.findViewById(R.id.best_score_text));
        adapter.setGameOverLayout(currentScore);

        ((TextView)v.findViewById(R.id.score_text)).setText("Skorunuz: " + currentScore);

        return v;
    }
}