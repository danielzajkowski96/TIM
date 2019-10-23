package com.example.company.registration.presenter;


import android.util.Log;

import com.example.company.API.ApiCallback;
import com.example.company.API.ApiException;
import com.example.company.API.api.RegisterApi;
import com.example.company.API.model.DTORegisterForm;
import com.example.company.R;
import com.example.company.handlers.StatusCodeHandler;
import com.example.company.login.interfaces.LoginInterface;
import com.example.company.login.presenter.LoginPresenter;
import com.example.company.registration.interfaces.RegistrationInterface;
import com.example.company.registration.view.RegistrationActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;
import java.util.Map;

public class RegistrationPresenter implements RegistrationInterface.Presenter {

    private static RegistrationPresenter registrationPresenter;

    private RegistrationInterface.View registrationView;

    private RegisterApi registerApi;

    private DTORegisterForm registerForm;

    private LoginInterface.Presenter loginPresenter;

    private RegistrationPresenter() {
        registerForm = new DTORegisterForm();
        registerApi = new RegisterApi();
        loginPresenter = LoginPresenter.getInstance();
        StatusCodeHandler.getInstance().setView(RegistrationActivity.getInstance().findViewById(R.id.registerLayout));
    }

    public static RegistrationPresenter getInstance() {
        if (registrationPresenter == null) {
            registrationPresenter = new RegistrationPresenter();
        }
        return registrationPresenter;
    }

    @Override
    public void getView(RegistrationInterface.View view) {
        this.registrationView = view;
    }

    @Override
    public void register() {

        try {
            registerApi.registerAsync(getRegisterForm(), new ApiCallback<String>() {
                @Override
                public void onFailure(ApiException e, int statusCode, Map<String, List<String>> responseHeaders) {

                    e.printStackTrace();

                    StatusCodeHandler.getInstance().setMessageFromServer(getMessageFromResponseBody(e.getResponseBody()));
                    StatusCodeHandler.getInstance().checkStatusCode(statusCode);


                    registrationView.setVisibleRegistrationProgressBar(false);
                    registrationView.setEnableAllComponents(true);
                }

                @Override
                public void onSuccess(String result, int statusCode, Map<String, List<String>> responseHeaders) {

                    setAuthLogin(result);
                    registrationView.setVisibleRegistrationProgressBar(false);
                    registrationView.setEnableAllComponents(true);
                    registrationView.showLoginActivity();
                }

                @Override
                public void onUploadProgress(long bytesWritten, long contentLength, boolean done) {

                }

                @Override
                public void onDownloadProgress(long bytesRead, long contentLength, boolean done) {

                }
            });
        } catch (ApiException e) {
            e.printStackTrace();
            registrationView.setVisibleRegistrationProgressBar(false);
            registrationView.setEnableAllComponents(true);
            registrationView.showSnackbar(RegistrationActivity.getInstance().getResources().getString(R.string.title_error_occurred));
        }

    }

    private String getMessageFromResponseBody(String responseBody) {
        if (responseBody != null) {
            try {
                JSONObject responseBodyJsonObject = new JSONObject(responseBody);
                return responseBodyJsonObject.get("Message").toString();

            } catch (JSONException e1) {
                e1.printStackTrace();
            }
        }

        return null;
    }

    private void setAuthLogin(String login) {
        loginPresenter.setLogin(login);
    }

    @Override
    public void setFirstName(String firstName) {
        registerForm.setFirstName(firstName);
    }

    @Override
    public void setSurname(String surname) {
        registerForm.setFamilyName(surname);
    }

    @Override
    public void setPassword(String password) {
        registerForm.setPassword(password);
    }

    @Override
    public DTORegisterForm getRegisterForm() {
        return registerForm;
    }
}
