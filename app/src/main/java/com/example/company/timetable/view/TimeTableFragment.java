package com.example.company.timetable.view;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.company.R;
import com.example.company.companyChecker.interfaces.NoCompanyInterface;
import com.example.company.companyChecker.presenter.NoCompanyPresenter;
import com.example.company.main.view.MainActivity;
import com.example.company.timetable.interfaces.TimeTableInterface;
import com.example.company.timetable.presenter.TimeTablePresenter;

import java.io.File;

public class TimeTableFragment extends Fragment implements TimeTableInterface.View {

    private static TimeTableFragment timeTableFragment;

    private TimeTableInterface.Presenter timeTablePresenter;

    private ImageView timeTableImageView;

    public TimeTableFragment() {
    }

    public static TimeTableFragment getInstance() {
        if (timeTableFragment == null) {
            timeTableFragment = new TimeTableFragment();
        }
        return timeTableFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setTimeTablePresenter();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_time_table, container, false);

        timeTablePresenter.getView(this);
        timeTablePresenter.getContext(getContext());

        setTimeTableImageView(view);

        timeTablePresenter.getCompanyTimeTable();


        return view;
    }

    private void setTimeTablePresenter() {
        timeTablePresenter = TimeTablePresenter.getInstance();
    }

    private void setTimeTableImageView(View view) {
        timeTableImageView = view.findViewById(R.id.time_table_imageView);
    }

    @Override
    public void loadTimeTableFromFile(final File timeTable) {

        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (timeTable != null) {
                    Bitmap bitmap = BitmapFactory.decodeFile(timeTable.getAbsolutePath());
                    timeTableImageView.setImageBitmap(bitmap);
                }
            }
        });
    }

}
