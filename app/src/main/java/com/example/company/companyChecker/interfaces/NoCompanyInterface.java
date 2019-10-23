package com.example.company.companyChecker.interfaces;

import android.content.Context;

public interface NoCompanyInterface {

    interface View {
        void showJoinCompanyActivity();
    }

    interface Presenter {
        void getView(NoCompanyInterface.View view);

        void getContext(Context context);

        void checkUserHasCompany(final int itemId);
    }
}
