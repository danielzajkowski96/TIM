package com.example.company.joinCompany.view;

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
import com.example.company.API.model.Kompania;
import com.example.company.API.model.Prosba;
import com.example.company.BaseActivity;
import com.example.company.R;
import com.example.company.handlers.StatusCodeHandler;
import com.example.company.joinCompany.interfaces.JoinCompanyInterface;
import com.example.company.joinCompany.presenter.JoinCompanyPresenter;

import java.util.ArrayList;
import java.util.List;

public class JoinCompanyActivity extends BaseActivity implements JoinCompanyInterface.View, AdapterView.OnItemSelectedListener {

    private static JoinCompanyActivity joinCompanyActivity;

    private JoinCompanyInterface.Presenter joinCompanyPresenter;

    private Button joinCompanyButton;
    private Spinner companiesSpinner;
    private FloatingActionButton refreshButton;
    private TextView nullCompanyTextView;
    private TextView sentRequestTextView;
    private TextView companyTitleTextView;
    private ProgressBar joinCompanyProgressBar;

    List<String> companies = new ArrayList<>();
    Integer companyNumber = null;

    public static JoinCompanyActivity getInstance() {
        return joinCompanyActivity;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join_company);

        joinCompanyActivity = this;

        setJoinCompanyPresenter();

        setViewStatusCodeHandler();

        setComponents();
        setJoinCompanyButton();
        setRefreshButton();
        setCompaniesSpinnerListener();

        setEnableAllComponents(false);

        checkUserSentRequest();
    }

    private void setComponents() {
        joinCompanyButton = findViewById(R.id.join_join_company_activity_button);
        companiesSpinner = findViewById(R.id.join_join_activity_spinner);
        refreshButton = findViewById(R.id.refresh_get_all_companies_join_company_button);
        nullCompanyTextView = findViewById(R.id.null_company_join_company_textView);
        sentRequestTextView = findViewById(R.id.join_company_sent_request_textView);
        companyTitleTextView = findViewById(R.id.company_title_spinner_textView);
        joinCompanyProgressBar = findViewById(R.id.join_company_progressBar);
    }

    private void setViewStatusCodeHandler() {
        StatusCodeHandler.getInstance().setView(getInstance().findViewById(R.id.join_company_layout));
    }

    private void setJoinCompanyPresenter() {
        joinCompanyPresenter = JoinCompanyPresenter.getInstance();
        joinCompanyPresenter.getView(this);
    }

    @SuppressLint("StaticFieldLeak")
    private void setCompaniesSpinner() {
        setVisibleJoinCompanyProgressBar(true);
        new AsyncTask<Object, Object, Object>() {
            @Override
            protected Object doInBackground(Object... objects) {

                List<Kompania> companyList = joinCompanyPresenter.getAllCompanies();
                if (companyList != null && companyList.size() > 0) {
                    for (Kompania company : companyList
                    ) {
                        companies.add(String.valueOf(company.getNrKompanii()));
                    }
                }
                return companies;
            }

            @Override
            protected void onPostExecute(Object o) {
                super.onPostExecute(o);
                if (companies != null && companies.size() > 0) {
                    showNullCompanyInfoComponents(false);

                    ArrayAdapter<String> adapter = new ArrayAdapter<>(getInstance(), android.R.layout.simple_spinner_item, companies);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                    companiesSpinner.setAdapter(adapter);
                    companiesSpinner.setSelection(0);
                } else {
                    showNullCompanyInfoComponents(true);
                }

                setEnableAllComponents(true);
                setVisibleJoinCompanyProgressBar(false);
            }
        }.execute();
    }

    @SuppressLint("StaticFieldLeak")
    private void checkUserSentRequest() {
        new AsyncTask<Object, Object, Object>() {
            @Override
            protected Object doInBackground(Object... objects) {
                companyNumber = joinCompanyPresenter.getSentRequestCompanyNumber();

                return companyNumber;
            }

            @Override
            protected void onPostExecute(Object o) {
                super.onPostExecute(o);

                if (companyNumber != null) {
                    setSentRequestTextView(companyNumber);
                    setVisibleCompanyTitleTextView(false);
                    setVisibleCompaniesSpinner(false);
                    setVisibleJoinCompanyButton(false);
                    setVisibleSentRequestTextView(true);
                    setEnableAllComponents(true);
                } else {
                    setCompaniesSpinner();
                }
            }
        }.execute();
    }

    private void setJoinCompanyButton() {
        joinCompanyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setVisibleJoinCompanyProgressBar(true);
                setEnableAllComponents(false);
                joinCompanyPresenter.joinCompany();
            }
        });
    }

    private void setRefreshButton() {
        refreshButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showNullCompanyInfoComponents(false);
                setEnableAllComponents(false);
                setCompaniesSpinner();
            }
        });
    }

    private void setCompaniesSpinnerListener() {
        companiesSpinner.setOnItemSelectedListener(this);
    }

    @Override
    public void setEnableAllComponents(final boolean isEnable) {
        final ConstraintLayout constraintLayout = findViewById(R.id.join_company_layout);

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

    private void setVisibleJoinCompanyButton(final boolean isVisible) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                joinCompanyButton.setVisibility(isVisible ? View.VISIBLE : View.GONE);
            }
        });
    }

    private void setVisibleCompaniesSpinner(final boolean isVisible) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                companiesSpinner.setVisibility(isVisible ? View.VISIBLE : View.GONE);
            }
        });
    }

    private void setVisibleCompanyTitleTextView(final boolean isVisible) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                companyTitleTextView.setVisibility(isVisible ? View.VISIBLE : View.GONE);
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

    @Override
    public void setVisibleJoinCompanyProgressBar(final boolean isVisible) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                joinCompanyProgressBar.setVisibility(isVisible ? View.VISIBLE : View.GONE);
            }
        });
    }

    @SuppressLint("RestrictedApi")
    private void showNullCompanyInfoComponents(boolean show) {
        nullCompanyTextView.setVisibility(show ? View.VISIBLE : View.GONE);
        refreshButton.setVisibility(show ? View.VISIBLE : View.GONE);
    }

    @Override
    public void startJoinCompanyActivityAgain() {
        finish();
        startActivity(getIntent());
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        DTORequest request = new DTORequest();
        request.setCompanyId(Integer.valueOf(companies.get(position)));
        request.setRequestType(DTORequest.RequestTypeEnum.NUMBER_3); //only join company

        joinCompanyPresenter.setDtoRequest(request);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    private void setSentRequestTextView(Integer companyNumber) {
        sentRequestTextView.setText(String.format("%s %s kompanii", sentRequestTextView.getText(), String.valueOf(companyNumber)));
    }
}
