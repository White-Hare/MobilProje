package com.example.mobilproje.ui.login;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.mobilproje.R;


public class LoginViewModel extends ViewModel {

    private MutableLiveData<LogRegFormState> loginFormState = new MutableLiveData<>();

    public LiveData<LogRegFormState> getLoginFormState() {
        return loginFormState;
    }


    public void loginDataChanged(String username, String password) {
        if (!isUserNameValid(username)) {
            loginFormState.setValue(new LogRegFormState(R.string.invalid_username, null));
        } else if (!isPasswordValid(password)) {
            loginFormState.setValue(new LogRegFormState(null, R.string.invalid_password));
        } else {
            loginFormState.setValue(new LogRegFormState(true));
        }
    }

    public void registerDataChanged(String username, String password, String passwordAgain) {
        if (!isUserNameValid(username)) {
            loginFormState.setValue(new LogRegFormState(R.string.invalid_username, null));
        } else if (!isPasswordValid(password)) {
            loginFormState.setValue(new LogRegFormState(null, R.string.invalid_password));
        }
        else if(!password.equals(passwordAgain)){
            loginFormState.setValue(new LogRegFormState(null, null, R.string.invalid_password_repeat));
        }
        else {
            loginFormState.setValue(new LogRegFormState(true));
        }
    }

    // A placeholder username validation check
    private boolean isUserNameValid(String username) {
        return username != null && username.trim().length() > 4;

    }

    // A placeholder password validation check
    private boolean isPasswordValid(String password) {
        return password != null && password.trim().length() > 4;
    }
}