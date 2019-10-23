package com.example.company.login.interfaces;

import com.example.company.API.model.AuthUser;

public interface LoginInterface {

    interface View {
        void showMainActivity();

        void setVisibleLoginProgressBar(final boolean isVisible);

        void setEnableAllComponents(final boolean isEnable);

        void showSnackbar(String message);
    }

    interface Presenter {
        void getView(LoginInterface.View view);

        void setLogin(String login);

        void setPassword(String password);

        AuthUser getAuthUser();

        void login();
    }
}
