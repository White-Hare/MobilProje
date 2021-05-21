package com.example.mobilproje.ui.login;

import androidx.lifecycle.Observer;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.mobilproje.R;
import com.example.mobilproje.adapter.LoginAdapter;
import com.example.mobilproje.model.user.MyUser;
import com.example.mobilproje.ui.main.MainActivity;


public class LoginActivity extends AppCompatActivity {

    private LoginViewModel loginViewModel;
    private LoginAdapter loginAdapter;

    private EditText usernameEditText;
    private EditText passwordEditText;
    private Button loginButton;
    private ProgressBar loadingProgressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginAdapter = new LoginAdapter(loadingProgressBar){

            @Override
            public void updateUiWithUser(MyUser model) {
                String welcome = getString(R.string.welcome) + model.getUsername();

                loadingProgressBar.setVisibility(View.INVISIBLE);
                Toast.makeText(getApplicationContext(), welcome, Toast.LENGTH_LONG).show();

                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }

            @Override
            public void showProcessFailed(Integer errorString) {
                loadingProgressBar.setVisibility(View.INVISIBLE);
                Toast.makeText(getApplicationContext(), errorString, Toast.LENGTH_SHORT).show();
            }
        };

        usernameEditText = findViewById(R.id.username);
        passwordEditText = findViewById(R.id.password);
        loginButton = findViewById(R.id.login);
        loadingProgressBar = findViewById(R.id.loading);


        loginViewModel = new LoginViewModel();
        loginViewModel.getLoginFormState().observe(this, new Observer<LogRegFormState>() {
            @Override
            public void onChanged(@Nullable LogRegFormState loginFormState) {
                if (loginFormState == null) {
                    return;
                }
                loginButton.setEnabled(loginFormState.isDataValid());
                if (loginFormState.getUsernameError() != null) {
                    usernameEditText.setError(getString(loginFormState.getUsernameError()));
                }
                if (loginFormState.getPasswordError() != null) {
                    passwordEditText.setError(getString(loginFormState.getPasswordError()));
                }
            }
        });



        TextWatcher afterTextChangedListener = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // ignore
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // ignore
            }

            @Override
            public void afterTextChanged(Editable s) {
                loginViewModel.loginDataChanged(usernameEditText.getText().toString(),
                        passwordEditText.getText().toString());
            }
        };
        usernameEditText.addTextChangedListener(afterTextChangedListener);
        passwordEditText.addTextChangedListener(afterTextChangedListener);
    }

    public void loginButtonOnClick(View v){
        loadingProgressBar.setVisibility(View.VISIBLE);
        MyUser user = loginAdapter.login(usernameEditText.getText().toString(), passwordEditText.getText().toString(), getApplicationContext());
    }

    public void goRegisterPageOnClick(View v){
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }
}