package com.example.company.authentication.interceptors;

import android.util.Log;

import com.example.company.authentication.presenter.AuthenticationPresenter;
import com.squareup.okhttp.Interceptor;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;

public class AuthInterceptor implements Interceptor {

    private static AuthInterceptor authInterceptor;

    private String token = "";

    private AuthInterceptor() {
        setToken();
    }

    public static AuthInterceptor getInstance() {
        if (authInterceptor == null) {
            authInterceptor = new AuthInterceptor();
        }
        return authInterceptor;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();

        Request.Builder builder = request.newBuilder();

        builder.addHeader("Authorization", getToken());


        return chain.proceed(builder.build());
    }

    private void setToken() {
        this.token = "Bearer " + AuthenticationPresenter.getInstance().getAuthenticationUser().getToken();
    }

    public void setToken(String token) {
        this.token = "Bearer " + token;
    }

    public String getToken() {
        return token;
    }
}
