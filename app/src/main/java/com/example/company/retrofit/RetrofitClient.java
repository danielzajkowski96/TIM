package com.example.company.retrofit;

import com.example.company.firebase.interceptors.FCMInterceptor;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class RetrofitClient {

    private static Retrofit retrofit = null;

    public static Retrofit getRetrofit(String baseURL) {

        if (retrofit == null) {
//            OkHttpClient okHttpClient = new OkHttpClient().newBuilder()
//                    .addInterceptor(FCMInterceptor.getInstance())
//                    .build();

            retrofit = new Retrofit.Builder()
                    .baseUrl(baseURL)
//                    .client(okHttpClient)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        return retrofit;
    }
}
