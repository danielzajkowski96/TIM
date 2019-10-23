package com.example.company.account.presenter;

import com.example.company.API.ApiCallback;
import com.example.company.API.ApiException;
import com.example.company.API.api.SoldierApi;
import com.example.company.API.model.Zolnierz;
import com.example.company.account.interfaces.AccountInterface;
import com.example.company.authentication.model.AuthenticationUser;
import com.example.company.authentication.presenter.AuthenticationPresenter;

import java.util.List;
import java.util.Map;

public class AccountPresenter implements AccountInterface.Presenter {

    private static AccountPresenter accountPresenter;

    private AccountInterface.View accountView;

    private AuthenticationPresenter authenticationPresenter;
    private SoldierApi soldierApi;
    private Group group;

    private AccountPresenter() {
        soldierApi = new SoldierApi();
    }

    public static AccountPresenter getInstance() {
        if (accountPresenter == null) {
            accountPresenter = new AccountPresenter();
        }
        return accountPresenter;
    }

    @Override
    public void checkUserGroup() {
        accountView.setVisibleAccountActivityProgressBar(true);
        authenticationPresenter = AuthenticationPresenter.getInstance();
        AuthenticationUser authenticationUser = authenticationPresenter.getAuthenticationUser();

        if (authenticationUser != null && authenticationUser.getUserId() != null) {
            try {
                soldierApi.getSoldierDetailsAsync(authenticationUser.getUserId(), new ApiCallback<Zolnierz>() {
                    @Override
                    public void onFailure(ApiException e, int statusCode, Map<String, List<String>> responseHeaders) {
                        e.printStackTrace();
                        accountView.setVisibleAccountActivityProgressBar(false);
                        setGroup(Group.NONE);
                        accountView.setOptions();
                        accountView.setListView();
                    }

                    @Override
                    public void onSuccess(Zolnierz result, int statusCode, Map<String, List<String>> responseHeaders) {
                        if (result.getNrKompanii() != null) {

                            if (result.getNrPlutonu() != null) {

                                if (result.isFunkcyjny()) {
                                    accountView.setVisibleAccountActivityProgressBar(false);
                                    setGroup(Group.PLATOON_ASSISTANT);
                                    accountView.setOptions();
                                    accountView.setListView();
                                } else {
                                    accountView.setVisibleAccountActivityProgressBar(false);
                                    setGroup(Group.PLATOON);
                                    accountView.setOptions();
                                    accountView.setListView();
                                }

                            } else {
                                accountView.setVisibleAccountActivityProgressBar(false);
                                setGroup(Group.COMPANY);
                                accountView.setOptions();
                                accountView.setListView();
                            }

                        } else {
                            accountView.setVisibleAccountActivityProgressBar(false);
                            setGroup(Group.NONE);
                            accountView.setOptions();
                            accountView.setListView();
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
                accountView.setVisibleAccountActivityProgressBar(false);
                setGroup(Group.NONE);
                accountView.setOptions();
                accountView.setListView();
            }
        } else {
            AuthenticationPresenter.getInstance().logOut();
        }

//        if (authenticationUser != null && authenticationUser.getUserId() != null) {
//            accountView.setVisibleAccountActivityProgressBar(false);
//            if (authenticationUser.getCompanyNumber() != null) {
//                setGroup(Group.COMPANY);
//                accountView.setOptions();
//                accountView.setListView();
//                if (authenticationUser.getPlatoonNumber() != null) {
//                    setGroup(Group.PLATOON);
//                    accountView.setOptions();
//                    accountView.setListView();
//                }
//
//            } else {
//                setGroup(Group.NONE);
//                accountView.setOptions();
//                accountView.setListView();
//            }
//        } else {
//            AuthenticationPresenter.getInstance().logOut();
//        }

    }

    @Override
    public void setGroup(Group group) {
        this.group = group;
    }

    @Override
    public Group getGroup() {
        return group;
    }

    @Override
    public void setView(AccountInterface.View accountView) {
        this.accountView = accountView;
    }

    public AccountInterface.View getView() {
        return accountView;
    }
}
