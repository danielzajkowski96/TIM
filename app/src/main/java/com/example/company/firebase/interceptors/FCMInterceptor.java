package com.example.company.firebase.interceptors;

import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;


import java.io.IOException;
import java.util.Objects;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class FCMInterceptor implements Interceptor {

    private static FCMInterceptor fcmInterceptor;

    private String token = "";

    private FCMInterceptor() {
        setToken();
    }

    public static FCMInterceptor getInstance() {
        if (fcmInterceptor == null) {
            fcmInterceptor = new FCMInterceptor();
        }
        return fcmInterceptor;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request original = chain.request();

        Request.Builder requestBuilder = original.newBuilder()
                .header("Authorization:key=", getToken());

        Request request = requestBuilder.build();
        return chain.proceed(request);
    }

    public void setToken(String token) {
        this.token = token;
    }

    private void setToken() {
        FirebaseInstanceId.getInstance().getInstanceId()
                .addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
                    @Override
                    public void onComplete(@NonNull Task<InstanceIdResult> task) {
                        if (!task.isSuccessful()) {

                            return;
                        }
                        setToken(Objects.requireNonNull(task.getResult()).getToken());
                    }
                });
    }

    private String getToken() {
        return token;
    }


}
