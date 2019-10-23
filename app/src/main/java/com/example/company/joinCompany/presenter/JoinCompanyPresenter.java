package com.example.company.joinCompany.presenter;

import com.example.company.API.ApiCallback;
import com.example.company.API.ApiException;
import com.example.company.API.api.CompanyApi;
import com.example.company.API.api.RequestApi;
import com.example.company.API.api.SoldierApi;
import com.example.company.API.model.DTORequest;
import com.example.company.API.model.Kompania;
import com.example.company.authentication.presenter.AuthenticationPresenter;
import com.example.company.joinCompany.interfaces.JoinCompanyInterface;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class JoinCompanyPresenter implements JoinCompanyInterface.Presenter {

    private static JoinCompanyPresenter joinCompanyPresenter;

    private JoinCompanyInterface.View joinCompanyView;

    private CompanyApi companyApi;
    private RequestApi requestApi;
    private SoldierApi soldierApi;

    private DTORequest dtoRequest;

    private JoinCompanyPresenter() {
        companyApi = new CompanyApi();
        requestApi = new RequestApi();
        dtoRequest = new DTORequest();
        soldierApi = new SoldierApi();
    }

    public static JoinCompanyPresenter getInstance() {
        if (joinCompanyPresenter == null) {
            joinCompanyPresenter = new JoinCompanyPresenter();
        }
        return joinCompanyPresenter;
    }

    @Override
    public void getView(JoinCompanyInterface.View joinCompanyView) {
        this.joinCompanyView = joinCompanyView;
    }

    @Override
    public List<Kompania> getAllCompanies() {
        try {
            return companyApi.getCompanyList();
        } catch (ApiException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    @Override
    public void joinCompany() {
        try {
            dtoRequest = getDtoRequest();
            requestApi.sendRequestAsync(getDtoRequest(), new ApiCallback<Void>() {
                @Override
                public void onFailure(ApiException e, int statusCode, Map<String, List<String>> responseHeaders) {
                    joinCompanyView.setVisibleJoinCompanyProgressBar(false);
                    joinCompanyView.setEnableAllComponents(true);
                }

                @Override
                public void onSuccess(Void result, int statusCode, Map<String, List<String>> responseHeaders) {
                    saveSentRequestCompanyNumber(getDtoRequest().getCompanyId());
                    joinCompanyView.setVisibleJoinCompanyProgressBar(false);
                    startJoinCompanyActivityAgain();
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
            joinCompanyView.setVisibleJoinCompanyProgressBar(false);
            joinCompanyView.setEnableAllComponents(true);
        }
    }

    private void saveSentRequestCompanyNumber(Integer companyNumber) {
        AuthenticationPresenter.getInstance().saveSentRequestCompanyNumber(companyNumber);
    }

    @Override
    public Integer getSentRequestCompanyNumber() {
        return AuthenticationPresenter.getInstance().getAuthenticationUser().getSentRequestCompanyNumber();
    }

    private void startJoinCompanyActivityAgain() {
        joinCompanyView.startJoinCompanyActivityAgain();
    }

    public DTORequest getDtoRequest() {
        return dtoRequest;
    }

    @Override
    public void setDtoRequest(DTORequest dtoRequest) {
        this.dtoRequest = dtoRequest;
    }
}
