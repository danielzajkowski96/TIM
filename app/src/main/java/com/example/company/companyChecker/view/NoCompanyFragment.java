package com.example.company.companyChecker.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.company.R;
import com.example.company.companyChecker.interfaces.NoCompanyInterface;
import com.example.company.companyChecker.presenter.NoCompanyPresenter;
import com.example.company.joinCompany.view.JoinCompanyActivity;

public class NoCompanyFragment extends Fragment implements NoCompanyInterface.View {

    private static NoCompanyFragment noCompanyFragment;

    private NoCompanyInterface.Presenter noCompanyPresenter;

    private FloatingActionButton joinCompanyButton;

    public NoCompanyFragment() {
    }

    public static NoCompanyFragment getInstance() {
        if (noCompanyFragment == null) {
            noCompanyFragment = new NoCompanyFragment();
        }
        return noCompanyFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setNoCompanyPresenter();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_no_company, container, false);

        noCompanyPresenter.getView(this);
        noCompanyPresenter.getContext(getContext());

        setComponents(view);
        setJoinCompanyButton();

        return view;
    }

    private void setNoCompanyPresenter() {
        noCompanyPresenter = NoCompanyPresenter.getInstance();
    }

    private void setComponents(View view) {
        joinCompanyButton = view.findViewById(R.id.join_company_no_company_fragment_button);
    }

    private void setJoinCompanyButton() {
        joinCompanyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showJoinCompanyActivity();
            }
        });
    }

    @Override
    public void showJoinCompanyActivity() {
        Intent intent = new Intent(getActivity(), JoinCompanyActivity.class);
        startActivity(intent);
    }
}
