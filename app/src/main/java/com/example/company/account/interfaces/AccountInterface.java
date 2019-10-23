package com.example.company.account.interfaces;

import com.example.company.account.presenter.Group;

public interface AccountInterface {

    interface View {
        void setVisibleAccountActivityProgressBar(final boolean isVisible);

        void setListView();

        void setOptions();
    }

    interface Presenter {
        void setView(AccountInterface.View view);

        void checkUserGroup();

        void setGroup(Group group);

        Group getGroup();
    }
}
