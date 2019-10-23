package com.example.company.joinPlatoon.interfaces;


import com.example.company.API.model.DTORequest;
import com.example.company.API.model.Kompania;
import com.example.company.API.model.Pluton;


import java.util.List;

public interface JoinPlatoonInterface {
    interface View {
        void setVisibleJoinPlatoonProgressBar(final boolean isVisible);

        void setEnableAllComponents(final boolean isEnable);

        void startJoinPlatoonActivityAgain();
    }

    interface Presenter {
        void setView(JoinPlatoonInterface.View view);

        void joinPlatoon();

        List<Pluton> getAllPlatoons();

        void setDtoRequest(DTORequest dtoRequest);

        Integer getSentRequestPlatoonNumber();

        Integer getCompanyNumber();
    }
}
