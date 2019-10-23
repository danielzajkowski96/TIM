package com.example.company.login.view;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.company.BaseActivity;
import com.example.company.R;
import com.example.company.handlers.StatusCodeHandler;
import com.example.company.login.interfaces.LoginInterface;
import com.example.company.login.presenter.LoginPresenter;
import com.example.company.main.view.MainActivity;
import com.example.company.registration.view.RegistrationActivity;

public class LoginActivity extends BaseActivity implements LoginInterface.View {

    private static LoginActivity loginActivity;

    private LoginInterface.Presenter loginPresenter;

    private ProgressBar loginProgressBar;
    private EditText login;
    private EditText password;
    private Button loginButton;
    private Button registerButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginActivity = this;

        setViewStatusCodeHandler();

        setComponents();

        setLoginButton();
        setRegisterButton();

        setLoginPresenter();

        checkExtrasBundle();
    }

    private void setComponents() {
        loginProgressBar = findViewById(R.id.login_progressBar);
        login = findViewById(R.id.login_login_editText);
        password = findViewById(R.id.login_password_editText);
        registerButton = findViewById(R.id.register_login_activity_button);
        loginButton = findViewById(R.id.login_button);
    }

    @Override
    public void setVisibleLoginProgressBar(final boolean isVisible) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                loginProgressBar.setVisibility(isVisible ? View.VISIBLE : View.GONE);
            }
        });
    }

    @Override
    public void setEnableAllComponents(final boolean isEnable) {
        final ConstraintLayout constraintLayout = findViewById(R.id.login_layout);

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < constraintLayout.getChildCount(); i++) {
                    View child = constraintLayout.getChildAt(i);

                    child.setEnabled(isEnable);
                }
            }
        });
    }

    private void setViewStatusCodeHandler() {
        StatusCodeHandler.getInstance().setView(getInstance().findViewById(R.id.login_layout));
    }

    private void setLoginPresenter() {
        loginPresenter = LoginPresenter.getInstance();
        loginPresenter.getView(this);
    }

    private void checkExtrasBundle() {
        String registration = null;
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if (bundle != null) {
            registration = bundle.getString("registration");
        }
        if (registration != null) {
            setLoginText(); //action after registration - autocomplete EditText login with new generated login from server
            showAlertDialogLoginInfo();
        }
    }

    public void showAlertDialogLoginInfo() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getInstance());
        builder.setTitle(getResources().getString(R.string.title_login_title_alert_dialog));
        builder.setMessage(getResources().getString(R.string.title_login_message_alert_dialog) + " " + loginPresenter.getAuthUser().getLogin() + "\n\n" + getResources().getString(R.string.title_login_message_info_alert_dialog));

        builder.setPositiveButton(getResources().getString(R.string.title_login_positive_button_alert_dialog), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        AlertDialog alert = builder.create();
        alert.show();
    }

    public void setLoginText() {
        if (loginPresenter.getAuthUser().getLogin() != null) {
            login.setText(loginPresenter.getAuthUser().getLogin());
        }
    }

    private void setLoginButton() {
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkLogin() && checkPassword()) {
                    setLogin();
                    setPassword();
                    setVisibleLoginProgressBar(true);
                    setEnableAllComponents(false);
                    login();
                }
            }
        });
    }

    private void setRegisterButton() {
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showRegistrationActivity();
            }
        });
    }

    @Override
    public void showSnackbar(String message) {
        Snackbar snackbar = Snackbar
                .make(getInstance().findViewById(R.id.login_layout), message, Snackbar.LENGTH_LONG);

        snackbar.show();
    }

    private void login() {
        loginPresenter.login();
    }

    private void setLogin() {
        loginPresenter.setLogin(login.getText().toString());
    }

    private void setPassword() {
        loginPresenter.setPassword(password.getText().toString());
    }

    private boolean checkLogin() {
        if (!isEditTextEmpty(login)) {
            return true;
        } else {
            showToast(getResources().getString(R.string.title_login_loginEmpty));
            setFocusable(login);
            return false;
        }
    }

    private boolean checkPassword() {
        if (!isEditTextEmpty(password)) {
            return true;
        } else {
            showToast(getResources().getString(R.string.title_login_passwordEmpty));
            setFocusable(password);
            return false;
        }
    }

    @Override
    public void showMainActivity() {
        Intent intent = new Intent(getInstance(), MainActivity.class);
        startActivity(intent);
        finish();
    }

    private void showRegistrationActivity() {
        Intent intent = new Intent(this, RegistrationActivity.class);
        startActivity(intent);
        finish();
    }

    private boolean isEditTextEmpty(EditText editText) {
        return editText.getText().toString().isEmpty();
    }

    private void setFocusable(EditText editText) {
        editText.setFocusable(true);
        editText.requestFocus();
    }

    private void showToast(String message) {
        Toast.makeText(loginActivity, message, Toast.LENGTH_SHORT).show();
    }

    public static LoginActivity getInstance() {
        return loginActivity;
    }

}
