package com.example.company;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.company.application.Company;

import io.realm.Realm;

public class BaseActivity extends AppCompatActivity {

    private Company company;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        company = (Company) getApplicationContext();

        company.setCurrentActivity(this);
    }

    protected void onResume() {
        super.onResume();
        company.setCurrentActivity(this);
    }

    protected void onPause() {
        clearReferences();
        super.onPause();
    }

    protected void onDestroy() {
        clearReferences();
        super.onDestroy();

    }

    private void clearReferences() {
        Activity currActivity = company.getCurrentActivity();
        if (this.equals(currActivity))
            company.setCurrentActivity(null);
    }
}
