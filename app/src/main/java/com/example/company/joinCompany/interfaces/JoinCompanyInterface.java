package com.example.company.joinCompany.interfaces;

import com.example.company.API.model.DTORequest;
import com.example.company.API.model.Kompania;

import java.util.List;

public interface JoinCompanyInterface {

    interface View {
        void setVisibleJoinCompanyProgressBar(final boolean isVisible);

        void setEnableAllComponents(final boolean isEnable);

        void startJoinCompanyActivityAgain();
    }

    interface Presenter {
        void getView(JoinCompanyInterface.View view);

        void joinCompany();

        List<Kompania> getAllCompanies();

        void setDtoRequest(DTORequest dtoRequest);

        Integer getSentRequestCompanyNumber();
    }
}
