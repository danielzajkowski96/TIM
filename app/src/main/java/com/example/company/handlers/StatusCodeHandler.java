package com.example.company.handlers;

import android.view.View;

import com.example.company.R;
import com.example.company.authentication.presenter.AuthenticationPresenter;
import com.example.company.snackbar.SnackbarPresenter;

public class StatusCodeHandler {

    private static StatusCodeHandler statusCodeHandler;
    private String messageFromServer;

    private View view;

    private StatusCodeHandler() {
    }

    public static StatusCodeHandler getInstance() {
        if (statusCodeHandler == null) {
            statusCodeHandler = new StatusCodeHandler();
        }
        return statusCodeHandler;
    }

    public void checkStatusCode(int statusCode) {
        if (statusCode == 400) {
            showSnackbar(getView().getResources().getString(R.string.title_status_code_400));
        } else if (statusCode == 401) {
            AuthenticationPresenter.getInstance().logOut();
        } else if (statusCode == 408) {
            showSnackbar(getView().getResources().getString(R.string.title_status_code_408));
        } else if (statusCode > 400 && statusCode < 500) {
            showSnackbar(getView().getResources().getString(R.string.title_status_code_client_error));
        } else if (statusCode >= 500) {
            showSnackbar(getView().getResources().getString(R.string.title_status_code_server_error));
        } else {
            showSnackbar(getView().getResources().getString(R.string.title_status_code_error_occurred));
        }
    }

    private void showSnackbar(String message) {
        SnackbarPresenter.getInstance().setView(getView());
        SnackbarPresenter.getInstance().showSnackbar(message);
    }


    public void setView(View view) {
        this.view = view;
    }

    private View getView() {
        return view;
    }

    public void setMessageFromServer(String messageFromServer) {
        this.messageFromServer = messageFromServer;
    }
}
