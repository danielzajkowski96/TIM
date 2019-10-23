package com.example.company.registration.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.company.R;
import com.example.company.handlers.StatusCodeHandler;
import com.example.company.login.view.LoginActivity;
import com.example.company.registration.interfaces.RegistrationInterface;
import com.example.company.registration.presenter.RegistrationPresenter;

public class RegistrationActivity extends AppCompatActivity implements RegistrationInterface.View {

    private static RegistrationActivity registrationActivity;

    private RegistrationInterface.Presenter registrationPresenter;

    private ProgressBar registrationProgressBar;
    private EditText firstName;
    private EditText surname;
    private EditText password;
    private EditText repeatPassword;
    private Button registerButton;
    private Button loginButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        registrationActivity = this;

        setViewStatusCodeHandler();

        setComponents();
        setRegisterButton();
        setLoginButton();
        setVisibleRegistrationProgressBar(false);

        setRegistrationPresenter();
    }

    private void setComponents() {
        registrationProgressBar = findViewById(R.id.registration_progressBar);
        firstName = findViewById(R.id.firstName_editText);
        surname = findViewById(R.id.surname_editText);
        password = findViewById(R.id.password_editText);
        repeatPassword = findViewById(R.id.repeat_password_editText);
        registerButton = findViewById(R.id.register_button);
        loginButton = findViewById(R.id.login_registration_activiity_button);
    }

    @Override
    public void setVisibleRegistrationProgressBar(final boolean isVisible) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                registrationProgressBar.setVisibility(isVisible ? View.VISIBLE : View.GONE);
            }
        });
    }

    private void setViewStatusCodeHandler() {
        StatusCodeHandler.getInstance().setView(getInstance().findViewById(R.id.registerLayout));
    }

    private void setRegistrationPresenter(){
        registrationPresenter = RegistrationPresenter.getInstance();
        registrationPresenter.getView(registrationActivity);
    }

    @Override
    public void setEnableAllComponents(final boolean isEnable) {
        final ConstraintLayout constraintLayout = findViewById(R.id.registerLayout);

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

    private void setRegisterButton() {
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkFirstName() && checkSurname() && checkPassword()) {
                    setFirstName();
                    setSurname();
                    setPassword();
                    setVisibleRegistrationProgressBar(true);
                    setEnableAllComponents(false);
                    register();
                }
            }
        });
    }

    private void setLoginButton() {
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showHasAccountLoginActivity();
            }
        });
    }

    @Override
    public void showSnackbar(String message) {
        Snackbar snackbar = Snackbar
                .make(getInstance().findViewById(R.id.registerLayout), message, Snackbar.LENGTH_LONG);

        snackbar.show();
    }

    private void setFirstName() {
        registrationPresenter.setFirstName(firstName.getText().toString());
    }

    private void setSurname() {
        registrationPresenter.setSurname(surname.getText().toString());
    }

    private void setPassword() {
        registrationPresenter.setPassword(password.getText().toString());
    }

    private void register() {
        registrationPresenter.register();
    }

    private boolean checkFirstName() {
        if (!isEditTextEmpty(firstName)) {
            return true;
        } else {
            showToast(getResources().getString(R.string.title_registration_firstNameEmpty));
            setFocusable(firstName);
            return false;
        }
    }

    private boolean checkSurname() {
        if (!isEditTextEmpty(surname)) {
            return true;
        } else {
            showToast(getResources().getString(R.string.title_registration_surnameEmpty));
            setFocusable(surname);
            return false;
        }
    }

    private boolean checkPassword() {
        if (!isEditTextEmpty(password)) {
            if (!isEditTextEmpty(password)) {
                if (checkPasswordLength(password.getText().toString())) {
                    if (checkPasswordsEquality(password.getText().toString(), repeatPassword.getText().toString())) {
                        return true;
                    } else {
                        showToast(getResources().getString(R.string.title_registration_passwordsNotEqual));
                        setFocusable(repeatPassword);
                        return false;
                    }
                } else {
                    showToast(getResources().getString(R.string.title_registration_password_length_error));
                    setFocusable(password);
                    return false;
                }

            } else {
                showToast(getResources().getString(R.string.title_registration_repeatPasswordEmpty));
                setFocusable(repeatPassword);
                return false;
            }
        } else {
            showToast(getResources().getString(R.string.title_registration_passwordEmpty));
            setFocusable(password);
            return false;
        }
    }

    @Override
    public void showLoginActivity() {
        Intent intent = new Intent(getInstance(), LoginActivity.class);
        intent.putExtra("registration", "yes");
        startActivity(intent);
        finish();
    }

    private void showHasAccountLoginActivity() {
        Intent intent = new Intent(getInstance(), LoginActivity.class);
        startActivity(intent);
        finish();
    }

    private boolean isEditTextEmpty(EditText editText) {
        return editText.getText().toString().isEmpty();
    }

    private boolean checkPasswordsEquality(String password, String repeatedPassword) {
        return password.equals(repeatedPassword);
    }

    private boolean checkPasswordLength(String password) {
        return password.length() >= 3;
    }

    private void setFocusable(EditText editText) {
        editText.setFocusable(true);
        editText.requestFocus();
    }

    private void showToast(String message) {
        Toast.makeText(registrationActivity, message, Toast.LENGTH_SHORT).show();
    }

    public static RegistrationActivity getInstance() {
        return registrationActivity;
    }

}
