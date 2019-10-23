package com.example.company.application;

import android.app.Activity;
import android.app.Application;

import io.realm.Realm;

public class Company extends Application {

    public Activity currentActivity;

    private static Company company;

    public static Company getInstance() {
        return company;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);

        company = this;
    }

    public Activity getCurrentActivity() {
        return currentActivity;
    }

    public void setCurrentActivity(Activity currentActivity) {
        this.currentActivity = currentActivity;
    }
}
