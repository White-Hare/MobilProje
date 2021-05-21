package com.example.mobilproje.ui.login;

import androidx.annotation.Nullable;

/**
 * Data validation state of the login form.
 */
class LogRegFormState {
    @Nullable
    private Integer usernameError;
    @Nullable
    private Integer passwordError;

    @Nullable
    private Integer passwordAgainError;

    private boolean isDataValid;

    LogRegFormState(@Nullable Integer usernameError, @Nullable Integer passwordError) {
        this.usernameError = usernameError;
        this.passwordError = passwordError;
        this.isDataValid = false;
    }

    LogRegFormState(@Nullable Integer usernameError, @Nullable Integer passwordError, @Nullable Integer passwordAgainError) {
        this.usernameError = usernameError;
        this.passwordError = passwordError;
        this.passwordAgainError = passwordAgainError;
        this.isDataValid = false;
    }

    LogRegFormState(boolean isDataValid) {
        this.usernameError = null;
        this.passwordError = null;
        this.isDataValid = isDataValid;
    }

    @Nullable
    Integer getUsernameError() {
        return usernameError;
    }

    @Nullable
    Integer getPasswordError() {
        return passwordError;
    }

    @Nullable
    Integer getPasswordRepeatError() {
        return passwordAgainError;
    }

    boolean isDataValid() {
        return isDataValid;
    }
}