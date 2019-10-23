package com.example.company.messages.interfaces;

import android.content.Context;

public interface MessagesInterface {

    interface View {
        void setEnableAllComponents(final boolean isEnable);

        void setVisibleMainActivityProgressBar(final boolean isVisible);

        void showSnackbar(String message);

        void clearTitle();

        void clearBody();
    }

    interface Presenter {
        void getView(MessagesInterface.View view);

        void getContext(Context context);

        void sendMessage();

        void setTitle(String title);

        void setBody(String body);

        Integer getPlatoonId();
    }
}
