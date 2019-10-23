package com.example.company.authentication.interceptors;

import android.util.Log;

import com.example.company.handlers.StatusCodeHandler;
import com.squareup.okhttp.Interceptor;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;

public class RequestResponseInterceptor implements Interceptor {

    private static RequestResponseInterceptor requestResponseInterceptor;

    public RequestResponseInterceptor() {
    }

    public static RequestResponseInterceptor getInstance() {
        if (requestResponseInterceptor == null) {
            requestResponseInterceptor = new RequestResponseInterceptor();
        }
        return requestResponseInterceptor;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        Response response = chain.proceed(request);


        if (response.isSuccessful()) {

        } else {
            StatusCodeHandler.getInstance().checkStatusCode(response.code());
            StatusCodeHandler.getInstance().setMessageFromServer(response.message());


        }


        return response;
    }
}
