package com.example.company.timetable.presenter;

import android.content.Context;
import android.util.Log;

import com.example.company.API.ApiCallback;
import com.example.company.API.ApiException;
import com.example.company.API.api.TimeTableApi;
import com.example.company.authentication.model.AuthenticationUser;
import com.example.company.authentication.presenter.AuthenticationPresenter;
import com.example.company.timetable.interfaces.TimeTableInterface;

import java.io.File;
import java.util.List;
import java.util.Map;

public class TimeTablePresenter implements TimeTableInterface.Presenter {

    private static TimeTablePresenter timeTablePresenter;

    private TimeTableInterface.View timeTableView;
    private Context context;

    private AuthenticationPresenter authenticationPresenter;
    private TimeTableApi timeTableApi;

    private TimeTablePresenter() {
        timeTableApi = new TimeTableApi();
    }

    public static TimeTablePresenter getInstance() {
        if (timeTablePresenter == null) {
            timeTablePresenter = new TimeTablePresenter();
        }
        return timeTablePresenter;
    }

    @Override
    public void getView(TimeTableInterface.View view) {
        timeTableView = view;
    }

    @Override
    public void getContext(Context context) {
        this.context = context;
    }

    @Override
    public void getCompanyTimeTable() {
        authenticationPresenter = AuthenticationPresenter.getInstance();
        AuthenticationUser authenticationUser = authenticationPresenter.getAuthenticationUser();

        if (authenticationUser != null && authenticationUser.getUserId() != null) {

            try {
                if (authenticationUser.getCompanyNumber() != null) {
                    timeTableApi.getCompanyTimeTableAsync(authenticationUser.getCompanyNumber(), new ApiCallback<File>() {
                        @Override
                        public void onFailure(ApiException e, int statusCode, Map<String, List<String>> responseHeaders) {

                        }

                        @Override
                        public void onSuccess(File result, int statusCode, Map<String, List<String>> responseHeaders) {
                            timeTableView.loadTimeTableFromFile(result);
                        }

                        @Override
                        public void onUploadProgress(long bytesWritten, long contentLength, boolean done) {

                        }

                        @Override
                        public void onDownloadProgress(long bytesRead, long contentLength, boolean done) {

                        }
                    });
                }

            } catch (ApiException e) {
                e.printStackTrace();
            }
        }

    }
}
