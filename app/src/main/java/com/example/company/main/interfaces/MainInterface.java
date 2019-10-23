package com.example.company.main.interfaces;


public interface MainInterface {

    interface View {
        void removeTimeTableFragment();

        void removeMessagesFragment();

        void removeLearningMaterialsFragment();

        void removeNoCompanyFragment();

        void replaceTimeTableFragment();

        void replaceMessagesFragment();

        void replaceLearningMaterialsFragment();

        void replaceNoCompanyFragment();

        void setVisibleProgressBar(final boolean isVisible);

        void showChosenFragment(int itemId);

        int getSelectedItemId();

        void showSnackbar(String message);
    }

    interface Presenter {
        void getView(MainInterface.View view);

        void removeTimeTableFragment();

        void removeMessagesFragment();

        void removeLearningMaterialsFragment();

        void removeNoCompanyFragment();

        void replaceTimeTableFragment();

        void replaceMessagesFragment();

        void replaceLearningMaterialsFragment();

        void replaceNoCompanyFragment();

        void setVisibleMainActivityProgressBar(final boolean isVisible);

        void checkUserHasCompany();
    }
}
