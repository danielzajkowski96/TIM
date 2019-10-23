package com.example.company.companyChecker.presenter;

import android.content.Context;

import com.example.company.API.ApiCallback;
import com.example.company.API.ApiException;
import com.example.company.API.api.SoldierApi;
import com.example.company.API.model.Zolnierz;
import com.example.company.R;
import com.example.company.authentication.model.AuthenticationUser;
import com.example.company.authentication.presenter.AuthenticationPresenter;
import com.example.company.companyChecker.interfaces.NoCompanyInterface;
import com.example.company.main.interfaces.MainInterface;
import com.example.company.main.presenter.MainPresenter;
import com.example.company.main.view.MainActivity;

import java.util.List;
import java.util.Map;

public class NoCompanyPresenter implements NoCompanyInterface.Presenter {

    private static NoCompanyPresenter noCompanyPresenter;

    private NoCompanyInterface.View noCompanyView;
    private Context context;

    private AuthenticationPresenter authenticationPresenter;
    private MainInterface.Presenter mainPresenter;
    private SoldierApi soldierApi;

    private NoCompanyPresenter() {
        soldierApi = new SoldierApi();
        mainPresenter = MainPresenter.getInstance();
    }

    public static NoCompanyPresenter getInstance() {
        if (noCompanyPresenter == null) {
            noCompanyPresenter = new NoCompanyPresenter();
        }
        return noCompanyPresenter;
    }

    @Override
    public void getView(NoCompanyInterface.View view) {
        noCompanyView = view;
    }

    @Override
    public void getContext(Context context) {
        this.context = context;
    }

    @Override
    public void checkUserHasCompany(final int itemId) {
        mainPresenter.setVisibleMainActivityProgressBar(true);
        authenticationPresenter = AuthenticationPresenter.getInstance();
        AuthenticationUser authenticationUser = authenticationPresenter.getAuthenticationUser();

        if (authenticationUser != null && authenticationUser.getUserId() != null) {
            try {
                soldierApi.getSoldierDetailsAsync(authenticationUser.getUserId(), new ApiCallback<Zolnierz>() {
                    @Override
                    public void onFailure(ApiException e, int statusCode, Map<String, List<String>> responseHeaders) {
                        e.printStackTrace();
                        mainPresenter.setVisibleMainActivityProgressBar(false);
                    }

                    @Override
                    public void onSuccess(Zolnierz result, int statusCode, Map<String, List<String>> responseHeaders) {
                        if (result.getNrKompanii() != null) {

                            saveCompanyNumber(result.getNrKompanii());
                            if (result.getNrPlutonu() != null) {
                                savePlatoonNumber(result.getNrPlutonu());
                            }
                            mainPresenter.setVisibleMainActivityProgressBar(false);

                            MainActivity.getInstance().showChosenFragment(itemId);

                        } else {
                            mainPresenter.setVisibleMainActivityProgressBar(false);
                            mainPresenter.removeTimeTableFragment();
                            mainPresenter.removeMessagesFragment();
                            mainPresenter.removeLearningMaterialsFragment();
                            mainPresenter.replaceNoCompanyFragment();
                        }
                    }

                    @Override
                    public void onUploadProgress(long bytesWritten, long contentLength, boolean done) {

                    }

                    @Override
                    public void onDownloadProgress(long bytesRead, long contentLength, boolean done) {

                    }
                });
            } catch (ApiException e) {
                e.printStackTrace();
                mainPresenter.setVisibleMainActivityProgressBar(false);
            }
        } else {
            AuthenticationPresenter.getInstance().logOut();
        }

    }

    private void saveCompanyNumber(int companyNumber) {
        AuthenticationPresenter.getInstance().saveCompanyNumber(companyNumber);
    }

    private void savePlatoonNumber(int platoonNumber) {
        AuthenticationPresenter.getInstance().savePlatoonNumber(platoonNumber);
    }
}
