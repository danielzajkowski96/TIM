package com.example.company.retrofit;


public class RetrofitFCMSettings {

    private static String baseURL = "https://fcm.googleapis.com";

    public static APIServiceFCM getFCMClient() {
        return RetrofitClient.getRetrofit(baseURL).create(APIServiceFCM.class);
    }
}
