package com.example.company.snackbar;

import android.support.design.widget.Snackbar;
import android.view.View;

public class SnackbarPresenter {

    private static SnackbarPresenter snackbarPresenter;

    private View view;

    private SnackbarPresenter() {
    }

    public static SnackbarPresenter getInstance() {
        if (snackbarPresenter == null) {
            snackbarPresenter = new SnackbarPresenter();
        }
        return snackbarPresenter;
    }

    public void showSnackbar(String message) {
        Snackbar snackbar = Snackbar
                .make(getView(), message, Snackbar.LENGTH_LONG);

        snackbar.show();
    }

    public View getView() {
        return view;
    }

    public void setView(View view) {
        this.view = view;
    }
}
