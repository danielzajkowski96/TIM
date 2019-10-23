package com.example.company.joinPlatoon.presenter;

import com.example.company.API.ApiCallback;
import com.example.company.API.ApiException;
import com.example.company.API.api.PlatoonApi;
import com.example.company.API.api.RequestApi;
import com.example.company.API.model.DTORequest;
import com.example.company.API.model.Kompania;
import com.example.company.API.model.Pluton;
import com.example.company.authentication.presenter.AuthenticationPresenter;
import com.example.company.joinPlatoon.interfaces.JoinPlatoonInterface;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class JoinPlatoonPresenter implements JoinPlatoonInterface.Presenter {

    private static JoinPlatoonPresenter joinPlatoonPresenter;

    private JoinPlatoonInterface.View joinPlatoonView;

    private PlatoonApi platoonApi;
    private RequestApi requestApi;
    private DTORequest dtoRequest;

    private Integer companyNumber;

    private JoinPlatoonPresenter() {
        setCompanyNumber();
        platoonApi = new PlatoonApi();
        requestApi = new RequestApi();
        dtoRequest = new DTORequest();
    }

    public static JoinPlatoonPresenter getInstance() {
        if (joinPlatoonPresenter == null) {
            joinPlatoonPresenter = new JoinPlatoonPresenter();
        }
        return joinPlatoonPresenter;
    }


    @Override
    public void setView(JoinPlatoonInterface.View view) {
        joinPlatoonView = view;
    }

    @Override
    public void joinPlatoon() {
        try {
            requestApi.sendRequestAsync(getDtoRequest(), new ApiCallback<Void>() {
                @Override
                public void onFailure(ApiException e, int statusCode, Map<String, List<String>> responseHeaders) {
                    joinPlatoonView.setVisibleJoinPlatoonProgressBar(false);
                    joinPlatoonView.setEnableAllComponents(true);
                }

                @Override
                public void onSuccess(Void result, int statusCode, Map<String, List<String>> responseHeaders) {
                    saveSentRequestPlatoonNumber(getDtoRequest().getPlatoonId());
                    joinPlatoonView.setVisibleJoinPlatoonProgressBar(false);
                    startJoinPlatoonActivityAgain();
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
            joinPlatoonView.setVisibleJoinPlatoonProgressBar(false);
            joinPlatoonView.setEnableAllComponents(true);
        }
    }

    private void saveSentRequestPlatoonNumber(Integer platoonNumber) {
        AuthenticationPresenter.getInstance().saveSentRequestPlatoonNumber(platoonNumber);
    }

    @Override
    public List<Pluton> getAllPlatoons() {
        try {
            if (getCompanyNumber() != null) return platoonApi.getPlatoonList(getCompanyNumber());
        } catch (ApiException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    @Override
    public void setDtoRequest(DTORequest dtoRequest) {
        this.dtoRequest = dtoRequest;
    }

    private DTORequest getDtoRequest() {
        return dtoRequest;
    }

    @Override
    public Integer getSentRequestPlatoonNumber() {
        return AuthenticationPresenter.getInstance().getAuthenticationUser().getSentRequestPlatoonNumber();
    }

    @Override
    public Integer getCompanyNumber() {
        return companyNumber;
    }

    private void setCompanyNumber() {
        if (AuthenticationPresenter.getInstance().getAuthenticationUser() != null)
            this.companyNumber = AuthenticationPresenter.getInstance().getAuthenticationUser().getCompanyNumber();
    }

    private void startJoinPlatoonActivityAgain() {
        joinPlatoonView.startJoinPlatoonActivityAgain();
    }
}
