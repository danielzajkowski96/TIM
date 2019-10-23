package com.example.company.joinPlatoon.view;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.FloatingActionButton;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.company.API.model.DTORequest;
import com.example.company.API.model.Pluton;
import com.example.company.BaseActivity;
import com.example.company.R;
import com.example.company.handlers.StatusCodeHandler;
import com.example.company.joinPlatoon.interfaces.JoinPlatoonInterface;
import com.example.company.joinPlatoon.presenter.JoinPlatoonPresenter;

import java.util.ArrayList;
import java.util.List;

public class JoinPlatoonActivity extends BaseActivity implements JoinPlatoonInterface.View, AdapterView.OnItemSelectedListener {

    private static JoinPlatoonActivity joinPlatoonActivity;

    private JoinPlatoonInterface.Presenter joinPlatoonPresenter;

    private Button joinPlatoonButton;
    private Spinner plattoonsSpinner;
    private FloatingActionButton refreshButton;
    private TextView nullPlatoonTextView;
    private TextView sentRequestTextView;
    private TextView platoonTitleTextView;
    private ProgressBar joinPlatoonProgressBar;

    List<String> platoons = new ArrayList<>();
    Integer platoonNumber = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join_platoon);

        joinPlatoonActivity = this;

        setJoinPlatoonPresenter();

        setViewStatusCodeHandler();

        setComponents();
        setJoinPlatoonButton();
        setRefreshButton();
        setPlatoonSpinnerListener();

        setEnableAllComponents(false);

        checkUserSentRequest();
    }

    public static JoinPlatoonActivity getInstance() {
        return joinPlatoonActivity;
    }

    private void setComponents() {
        joinPlatoonButton = findViewById(R.id.join_join_platoon_activity_button);
        plattoonsSpinner = findViewById(R.id.join_join_platoon_activity_spinner);
        refreshButton = findViewById(R.id.refresh_get_all_platoons_join_platoon_button);
        nullPlatoonTextView = findViewById(R.id.null_platoon_join_platoon_textView);
        sentRequestTextView = findViewById(R.id.join_platoon_sent_request_textView);
        platoonTitleTextView = findViewById(R.id.platoon_title_spinner_textView);
        joinPlatoonProgressBar = findViewById(R.id.join_platoon_progressBar);
    }

    private void setJoinPlatoonButton() {
        joinPlatoonButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setVisibleJoinPlatoonProgressBar(true);
                setEnableAllComponents(false);
                joinPlatoonPresenter.joinPlatoon();
            }
        });
    }

    private void setRefreshButton() {
        refreshButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showNullPlatoonInfoComponents(false);
                setEnableAllComponents(false);
                setPlatoonsSpinner();
            }
        });
    }

    private void setJoinPlatoonPresenter(){
        joinPlatoonPresenter = JoinPlatoonPresenter.getInstance();
        joinPlatoonPresenter.setView(this);
    }

    private void setViewStatusCodeHandler() {
        StatusCodeHandler.getInstance().setView(getInstance().findViewById(R.id.join_platoon_layout));
    }

    private void setPlatoonSpinnerListener() {
        plattoonsSpinner.setOnItemSelectedListener(this);
    }

    @SuppressLint("StaticFieldLeak")
    private void setPlatoonsSpinner() {
        setVisibleJoinPlatoonProgressBar(true);
        new AsyncTask<Object, Object, Object>() {
            @Override
            protected Object doInBackground(Object... objects) {

                List<Pluton> platoonList = joinPlatoonPresenter.getAllPlatoons();
                if (platoonList != null && platoonList.size() > 0) {
                    for (Pluton platoon : platoonList
                    ) {
                        platoons.add(String.valueOf(platoon.getNrPlutonu()));
                    }
                }
                return platoons;
            }

            @Override
            protected void onPostExecute(Object o) {
                super.onPostExecute(o);
                if (platoons != null && platoons.size() > 0) {
                    showNullPlatoonInfoComponents(false);

                    ArrayAdapter<String> adapter = new ArrayAdapter<>(getInstance(), android.R.layout.simple_spinner_item, platoons);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                    plattoonsSpinner.setAdapter(adapter);
                    plattoonsSpinner.setSelection(0);
                } else {
                    showNullPlatoonInfoComponents(true);
                }

                setEnableAllComponents(true);
                setVisibleJoinPlatoonProgressBar(false);
            }
        }.execute();
    }

    @SuppressLint("StaticFieldLeak")
    private void checkUserSentRequest() {
        new AsyncTask<Object, Object, Object>() {
            @Override
            protected Object doInBackground(Object... objects) {
                platoonNumber = joinPlatoonPresenter.getSentRequestPlatoonNumber();

                return platoonNumber;
            }

            @Override
            protected void onPostExecute(Object o) {
                super.onPostExecute(o);

                if (platoonNumber != null) {
                    setSentRequestTextView(platoonNumber);
                    setVisiblePlatoonTitleTextView(false);
                    setVisiblePlatoonsSpinner(false);
                    setVisibleJoinPlatoonButton(false);
                    setVisibleSentRequestTextView(true);
                    setEnableAllComponents(true);
                } else {
                    setPlatoonsSpinner();
                }
            }
        }.execute();
    }

    @Override
    public void setVisibleJoinPlatoonProgressBar(final boolean isVisible) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                joinPlatoonProgressBar.setVisibility(isVisible ? View.VISIBLE : View.GONE);
            }
        });
    }

    @Override
    public void setEnableAllComponents(final boolean isEnable) {
        final ConstraintLayout constraintLayout = findViewById(R.id.join_platoon_layout);

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < constraintLayout.getChildCount(); i++) {
                    View child = constraintLayout.getChildAt(i);

                    child.setEnabled(isEnable);
                }
            }
        });
    }

    @Override
    public void startJoinPlatoonActivityAgain() {
        finish();
        startActivity(getIntent());
    }

    @SuppressLint("RestrictedApi")
    private void showNullPlatoonInfoComponents(boolean show) {
        nullPlatoonTextView.setVisibility(show ? View.VISIBLE : View.GONE);
        refreshButton.setVisibility(show ? View.VISIBLE : View.GONE);
    }

    private void setVisibleJoinPlatoonButton(final boolean isVisible) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                joinPlatoonButton.setVisibility(isVisible ? View.VISIBLE : View.GONE);
            }
        });
    }

    private void setVisiblePlatoonsSpinner(final boolean isVisible) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                plattoonsSpinner.setVisibility(isVisible ? View.VISIBLE : View.GONE);
            }
        });
    }

    private void setVisiblePlatoonTitleTextView(final boolean isVisible) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                platoonTitleTextView.setVisibility(isVisible ? View.VISIBLE : View.GONE);
            }
        });
    }

    private void setVisibleSentRequestTextView(final boolean isVisible) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                sentRequestTextView.setVisibility(isVisible ? View.VISIBLE : View.GONE);
            }
        });
    }

    private void setSentRequestTextView(Integer platoonNumber) {
        sentRequestTextView.setText(String.format("%s %s plutonu", sentRequestTextView.getText(), String.valueOf(platoonNumber)));
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        DTORequest request = new DTORequest();
        request.setCompanyId(joinPlatoonPresenter.getCompanyNumber());
        request.setPlatoonId(Integer.valueOf(platoons.get(position)));
        request.setRequestType(DTORequest.RequestTypeEnum.NUMBER_4); //only join platoon

        joinPlatoonPresenter.setDtoRequest(request);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
