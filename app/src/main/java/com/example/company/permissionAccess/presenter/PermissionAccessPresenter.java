package com.example.company.permissionAccess.presenter;

import com.example.company.API.ApiCallback;
import com.example.company.API.ApiException;
import com.example.company.API.api.SoldierApi;
import com.example.company.API.model.Zolnierz;
import com.example.company.account.presenter.Group;
import com.example.company.authentication.model.AuthenticationUser;
import com.example.company.authentication.presenter.AuthenticationPresenter;
import com.example.company.learningMaterials.presenter.LearningMaterialsPresenter;
import com.example.company.permissionAccess.interfaces.PermissionAccessInterface;

import java.util.List;
import java.util.Map;

public class PermissionAccessPresenter implements PermissionAccessInterface.Presenter {

    private static PermissionAccessPresenter permissionAccessPresenter;

    private PermissionAccessInterface.View permissionAccessView;

    private AuthenticationPresenter authenticationPresenter;
    private SoldierApi soldierApi;

    private PermissionAccessPresenter() {
        soldierApi = new SoldierApi();
    }

    public static PermissionAccessPresenter getInstance() {
        if (permissionAccessPresenter == null) {
            permissionAccessPresenter = new PermissionAccessPresenter();
        }
        return permissionAccessPresenter;
    }

    @Override
    public void setView(PermissionAccessInterface.View view) {
        permissionAccessView = view;
    }

    @Override
    public void checkUserHasAccessForMessages() {

        authenticationPresenter = AuthenticationPresenter.getInstance();
        AuthenticationUser authenticationUser = authenticationPresenter.getAuthenticationUser();

        if (authenticationUser != null && authenticationUser.getUserId() != null) {

            try {
                soldierApi.getSoldierDetailsAsync(authenticationUser.getUserId(), new ApiCallback<Zolnierz>() {
                    @Override
                    public void onFailure(ApiException e, int statusCode, Map<String, List<String>> responseHeaders) {
                        permissionAccessView.setVisibleProgressBar(false);
                    }

                    @Override
                    public void onSuccess(Zolnierz result, int statusCode, Map<String, List<String>> responseHeaders) {
                        if (result.getNrKompanii() != null) {

                            if (result.getNrPlutonu() != null) {

                                if (result.isFunkcyjny()) {
                                    permissionAccessView.showMessagesNavigationMenuItem(true);
                                }
                            }
                        }
                        permissionAccessView.setVisibleProgressBar(false);
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
                permissionAccessView.setVisibleProgressBar(false);
            }
        } else {
            authenticationPresenter.logOut();
        }

    }

    @Override
    public void checkUserHasAccessForModifyLearningMaterials() {

        authenticationPresenter = AuthenticationPresenter.getInstance();
        AuthenticationUser authenticationUser = authenticationPresenter.getAuthenticationUser();

        if (authenticationUser != null && authenticationUser.getUserId() != null) {

            try {
                soldierApi.getSoldierDetailsAsync(authenticationUser.getUserId(), new ApiCallback<Zolnierz>() {
                    @Override
                    public void onFailure(ApiException e, int statusCode, Map<String, List<String>> responseHeaders) {
                        permissionAccessView.setVisibleProgressBar(false);
                    }

                    @Override
                    public void onSuccess(Zolnierz result, int statusCode, Map<String, List<String>> responseHeaders) {
                        if (result.getNrKompanii() != null) {

                            if (result.getNrPlutonu() != null) {

                                permissionAccessView.setVisibleLearningMaterialsAddButton(result.isFunkcyjny());
                                LearningMaterialsPresenter.getInstance().setAccessForModifyLearningMaterials(result.isFunkcyjny());
                            }
                        }
                        LearningMaterialsPresenter.getInstance().getCompanyFoldersFromRoot();
                        permissionAccessView.setVisibleProgressBar(false);
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
                permissionAccessView.setVisibleProgressBar(false);
            }
        } else {
            authenticationPresenter.logOut();
        }

    }
}
