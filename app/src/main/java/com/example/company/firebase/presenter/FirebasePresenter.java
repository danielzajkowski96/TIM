package com.example.company.firebase.presenter;

import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;

import java.util.Objects;

/**
 * Created by Sebastian Paciorek
 */
public class FirebasePresenter {

    private static FirebasePresenter firebasePresenter;

    private FirebasePresenter() {
    }

    public static FirebasePresenter getInstance() {
        if (firebasePresenter == null) {
            firebasePresenter = new FirebasePresenter();
        }
        return firebasePresenter;
    }

    public String getToken() {
        return FirebaseInstanceId.getInstance().getToken();
    }
}
