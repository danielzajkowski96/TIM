package com.example.company.registration.interfaces;

import com.example.company.API.model.DTORegisterForm;

/**
 * Created by Sebastian Paciorek
 */
public interface RegistrationInterface {

    interface View {
        void showLoginActivity();

        void setVisibleRegistrationProgressBar(boolean isVisible);

        void setEnableAllComponents(boolean isEnable);

        void showSnackbar(String message);
    }

    interface Presenter {
        void getView(RegistrationInterface.View view);

        void setFirstName(String firstName);

        void setSurname(String surname);

        void setPassword(String password);

        DTORegisterForm getRegisterForm();

        void register();
    }
}
