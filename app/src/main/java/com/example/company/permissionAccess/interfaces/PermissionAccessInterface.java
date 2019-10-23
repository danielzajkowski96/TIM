package com.example.company.permissionAccess.interfaces;

public interface PermissionAccessInterface {

    interface View {
        void showMessagesNavigationMenuItem(boolean isVisible);

        void setVisibleProgressBar(final boolean isVisible);

        void setVisibleLearningMaterialsAddButton(boolean isVisible);
    }

    interface Presenter {
        void setView(PermissionAccessInterface.View view);

        void checkUserHasAccessForMessages();

        void checkUserHasAccessForModifyLearningMaterials();
    }
}
