package com.example.company.messages.presenter;

import android.content.Context;

import com.example.company.API.ApiCallback;
import com.example.company.API.ApiException;
import com.example.company.API.api.NotificationApi;
import com.example.company.API.model.DTONotificationForm;
import com.example.company.R;
import com.example.company.authentication.presenter.AuthenticationPresenter;
import com.example.company.main.view.MainActivity;
import com.example.company.messages.interfaces.MessagesInterface;
import com.example.company.registration.view.RegistrationActivity;

import java.util.List;
import java.util.Map;

public class MessagesPresenter implements MessagesInterface.Presenter {

    private static MessagesPresenter messagesPresenter;

    private MessagesInterface.View messagesView;
    private Context context;

    private NotificationApi notificationApi;
    private DTONotificationForm dtoNotificationForm;

    private MessagesPresenter() {
        notificationApi = new NotificationApi();
        dtoNotificationForm = new DTONotificationForm();
        setCompanyId(AuthenticationPresenter.getInstance().getAuthenticationUser().getCompanyNumber());
        setPlatoonId(AuthenticationPresenter.getInstance().getAuthenticationUser().getPlatoonNumber());
    }

    public static MessagesPresenter getInstance() {
        if (messagesPresenter == null) {
            messagesPresenter = new MessagesPresenter();
        }
        return messagesPresenter;
    }

    @Override
    public void getView(MessagesInterface.View view) {
        messagesView = view;
    }

    @Override
    public void getContext(Context context) {
        this.context = context;
    }

    @Override
    public void sendMessage() {

        try {
            notificationApi.sendNotificatinAsync(getDtoNotificationForm(), new ApiCallback<Void>() {
                @Override
                public void onFailure(ApiException e, int statusCode, Map<String, List<String>> responseHeaders) {
                    messagesView.setEnableAllComponents(true);
                    messagesView.setVisibleMainActivityProgressBar(false);
                }

                @Override
                public void onSuccess(Void result, int statusCode, Map<String, List<String>> responseHeaders) {
                    messagesView.setEnableAllComponents(true);
                    messagesView.setVisibleMainActivityProgressBar(false);
                    messagesView.clearTitle();
                    messagesView.clearBody();
                    messagesView.showSnackbar(MainActivity.getInstance().getResources().getString(R.string.title_messages_send_success));
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
            messagesView.setEnableAllComponents(true);
            messagesView.setVisibleMainActivityProgressBar(false);
            messagesView.showSnackbar(MainActivity.getInstance().getResources().getString(R.string.title_error_occurred));
        }
    }

    public void setCompanyId(Integer companyId) {
        getDtoNotificationForm().setCompanyId(companyId);
    }

    public void setPlatoonId(Integer platoonId) {
        getDtoNotificationForm().setPlatoonId(platoonId);
    }

    public void setOnlyAssistants(Boolean onlyAssistants) {
        dtoNotificationForm.setOnlyAssistants(onlyAssistants);
    }

    public void setTitle(String title) {
        String messageFrom = AuthenticationPresenter.getInstance().getAuthenticationUser().getUserName();
        dtoNotificationForm.setTitle(messageFrom + ": " + title);
    }

    public void setBody(String body) {
        dtoNotificationForm.setBody(body);
    }

    public DTONotificationForm getDtoNotificationForm() {
        return dtoNotificationForm;
    }

    public void setDtoNotificationForm(DTONotificationForm dtoNotificationForm) {
        this.dtoNotificationForm = dtoNotificationForm;
    }

    public Integer getCompanyId(){
        return getDtoNotificationForm().getCompanyId();
    }

    @Override
    public Integer getPlatoonId(){
        return getDtoNotificationForm().getPlatoonId();
    }

}
