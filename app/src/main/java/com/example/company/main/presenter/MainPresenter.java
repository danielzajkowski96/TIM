package com.example.company.main.presenter;

import android.util.Log;

import com.example.company.companyChecker.presenter.NoCompanyPresenter;
import com.example.company.firebase.model.NotificationFCM;
import com.example.company.firebase.model.Response;
import com.example.company.firebase.model.Sender;
import com.example.company.main.interfaces.MainInterface;
import com.example.company.main.view.MainActivity;
import com.example.company.retrofit.APIServiceFCM;
import com.example.company.retrofit.RetrofitFCMSettings;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.messaging.FirebaseMessaging;

import retrofit2.Call;
import retrofit2.Callback;

public class MainPresenter implements MainInterface.Presenter {

    private static MainPresenter mainPresenter;

    private MainInterface.View mainView;

    private APIServiceFCM apiServiceFCM;

    private MainPresenter() {

    }

    public static MainPresenter getInstance() {
        if (mainPresenter == null) {
            mainPresenter = new MainPresenter();
        }
        return mainPresenter;
    }

    @Override
    public void getView(MainInterface.View view) {
        mainView = view;
    }

    @Override
    public void removeTimeTableFragment() {
        mainView.removeTimeTableFragment();
    }

    @Override
    public void removeMessagesFragment() {
        mainView.removeMessagesFragment();
    }

    @Override
    public void removeLearningMaterialsFragment() {
        mainView.removeLearningMaterialsFragment();
    }

    @Override
    public void removeNoCompanyFragment() {
        mainView.removeNoCompanyFragment();
    }

    @Override
    public void replaceTimeTableFragment() {
        mainView.replaceTimeTableFragment();
    }

    @Override
    public void replaceMessagesFragment() {
        mainView.replaceMessagesFragment();
    }

    @Override
    public void replaceLearningMaterialsFragment() {
        mainView.replaceLearningMaterialsFragment();
    }

    @Override
    public void replaceNoCompanyFragment() {
        mainView.replaceNoCompanyFragment();
    }

    @Override
    public void checkUserHasCompany() {
        NoCompanyPresenter.getInstance().checkUserHasCompany(MainActivity.getInstance().getSelectedItemId());
    }

    @Override
    public void setVisibleMainActivityProgressBar(boolean isVisible) {
        mainView.setVisibleProgressBar(isVisible);
    }

}
