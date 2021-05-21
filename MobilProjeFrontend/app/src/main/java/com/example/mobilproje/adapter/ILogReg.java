package com.example.mobilproje.adapter;

import androidx.annotation.StringRes;

import com.example.mobilproje.model.user.MyUser;

public interface ILogReg {
    void updateUiWithUser(MyUser model);
    void showProcessFailed(@StringRes Integer errorString);
}
