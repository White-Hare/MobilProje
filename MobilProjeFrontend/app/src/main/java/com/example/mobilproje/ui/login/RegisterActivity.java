package com.example.mobilproje.ui.login;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.mobilproje.R;
import com.example.mobilproje.adapter.RegisterAdapter;
import com.example.mobilproje.model.user.MyUser;
import com.example.mobilproje.ui.main.MainActivity;

public class RegisterActivity extends AppCompatActivity {
    private LoginViewModel loginViewModel;
    private RegisterAdapter registerAdapter;

    private EditText usernameEditText;
    private EditText passwordEditText;
    private EditText passwordRepeatEditText;
    private Button registerButton;
    private ProgressBar loadingProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        registerAdapter = new RegisterAdapter(loadingProgressBar) {
            @Override
            public void updateUiWithUser(MyUser model) {
                loadingProgressBar.setVisibility(View.INVISIBLE);
                Toast.makeText(getApplicationContext(), "Hesabınız Başarıyla Oluşturuldu", Toast.LENGTH_LONG).show();


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
        passwordRepeatEditText = findViewById(R.id.password_repeat);
        registerButton = findViewById(R.id.register);
        loadingProgressBar = findViewById(R.id.loading);


        loginViewModel = new LoginViewModel();
        loginViewModel.getLoginFormState().observe(this, new Observer<LogRegFormState>() {
            @Override
            public void onChanged(@Nullable LogRegFormState loginFormState) {
                if (loginFormState == null) {
                    return;
                }
                registerButton.setEnabled(loginFormState.isDataValid());
                if (loginFormState.getUsernameError() != null) {
                    usernameEditText.setError(getString(loginFormState.getUsernameError()));
                }
                if (loginFormState.getPasswordError() != null) {
                    passwordEditText.setError(getString(loginFormState.getPasswordError()));
                }
                if (loginFormState.getPasswordRepeatError() != null) {
                    passwordRepeatEditText.setError(getString(loginFormState.getPasswordRepeatError()));
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
                loginViewModel.registerDataChanged(usernameEditText.getText().toString(),
                        passwordEditText.getText().toString(), passwordRepeatEditText.getText().toString());
            }
        };

        usernameEditText.addTextChangedListener(afterTextChangedListener);
        passwordEditText.addTextChangedListener(afterTextChangedListener);
        passwordRepeatEditText.addTextChangedListener(afterTextChangedListener);
    }

    public void registerButtonOnClick(View v){
        loadingProgressBar.setVisibility(View.VISIBLE);
        MyUser user = registerAdapter.register(usernameEditText.getText().toString(), passwordEditText.getText().toString(), getApplicationContext());
    }

    public void goLoginPageOnClick(View v){
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }
}