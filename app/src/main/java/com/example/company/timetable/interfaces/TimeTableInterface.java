package com.example.company.timetable.interfaces;

import android.content.Context;

import java.io.File;

public interface TimeTableInterface {

    interface View {
        void loadTimeTableFromFile(File timeTable);
    }

    interface Presenter {
        void getView(TimeTableInterface.View view);

        void getContext(Context context);

        void getCompanyTimeTable();
    }
}
