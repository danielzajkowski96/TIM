package com.example.company.retrofit;

import com.example.company.firebase.model.Response;
import com.example.company.firebase.model.Sender;

import retrofit2.Call;

import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;


public interface APIServiceFCM {

    @Headers({
            "content-type:application/json",
            "Authorization:key=AAAAbL5IGZk:APA91bHAsjMPUDTsmXRGEfsyFPcVAcCqzLJNm3WkvLO_pXzuWA-1dlUDiHgru1k3dyAjcEJJG5CpIA7wrSL2XDrvwtO1RZKl70nBlvRsfwwAubPWQ06bukIkMU_P6_h5jFvgQW6srPoK"
    })
    @POST("fcm/send")
    Call<Response> sendNotification(@Body Sender body);
}
