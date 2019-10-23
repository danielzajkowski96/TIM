package com.example.company.login.presenter;

import android.util.Log;

import com.example.company.API.ApiCallback;
import com.example.company.API.ApiException;
import com.example.company.API.api.AuthApi;
import com.example.company.API.model.AuthUser;
import com.example.company.API.model.DTOSystemUser;
import com.example.company.R;
import com.example.company.authentication.interceptors.AuthInterceptor;
import com.example.company.authentication.presenter.AuthenticationPresenter;
import com.example.company.firebase.presenter.FirebasePresenter;
import com.example.company.login.interfaces.LoginInterface;
import com.example.company.registration.view.RegistrationActivity;
import com.google.firebase.iid.FirebaseInstanceId;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;
import java.util.Map;

public class LoginPresenter implements LoginInterface.Presenter {

    private static LoginPresenter loginPresenter;

    private LoginInterface.View loginView;

    private DTOSystemUser dtoSystemUser;
    private AuthUser authUser;

    private AuthApi authApi;

    private AuthenticationPresenter authenticationPresenter;

    private LoginPresenter() {
        dtoSystemUser = new DTOSystemUser();
        authUser = new AuthUser();
        authApi = new AuthApi();
        authenticationPresenter = AuthenticationPresenter.getInstance();

    }

    public static LoginPresenter getInstance() {
        if (loginPresenter == null) {
            loginPresenter = new LoginPresenter();
        }
        return loginPresenter;
    }

    @Override
    public void getView(LoginInterface.View view) {
        this.loginView = view;
    }

    @Override
    public void login() {
        String login = getAuthUser().getLogin();
        String password = getAuthUser().getPassword();
        String firebaseToken = FirebasePresenter.getInstance().getToken();

        try {
            authApi.authorizeAsync(login, password, firebaseToken, new ApiCallback<DTOSystemUser>() {
                @Override
                public void onFailure(ApiException e, int statusCode, Map<String, List<String>> responseHeaders) {

                    e.printStackTrace();

                    loginView.setVisibleLoginProgressBar(false);
                    loginView.setEnableAllComponents(true);
                }

                @Override
                public void onSuccess(DTOSystemUser result, int statusCode, Map<String, List<String>> responseHeaders) {


                    AuthInterceptor authInterceptor = AuthInterceptor.getInstance();
                    authInterceptor.setToken(result.getToken());

                    setAuthenticationUser(result, getAuthUser().getPassword());

                    loginView.setVisibleLoginProgressBar(false);
                    loginView.setEnableAllComponents(true);
                    loginView.showMainActivity();
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
            loginView.setVisibleLoginProgressBar(false);
            loginView.setEnableAllComponents(true);
            loginView.showSnackbar(RegistrationActivity.getInstance().getResources().getString(R.string.title_error_occurred));
        }

    }

    @Override
    public void setLogin(String login) {
        authUser.setLogin(login);
    }

    @Override
    public void setPassword(String password) {
        authUser.setPassword(password);
    }

    private String getMessageFromResponseBody(String responseBody) {
        try {
            JSONObject responseBodyJsonObject = new JSONObject(responseBody);
            return responseBodyJsonObject.get("Message").toString();

        } catch (JSONException e1) {
            e1.printStackTrace();
        }
        return null;
    }

    private void setAuthenticationUser(DTOSystemUser dtoSystemUser, String password) {
        authenticationPresenter.saveAuthenticationUser(dtoSystemUser, password);
    }

    @Override
    public AuthUser getAuthUser() {
        return authUser;
    }
}
