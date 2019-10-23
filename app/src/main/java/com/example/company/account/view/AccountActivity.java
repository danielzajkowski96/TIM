package com.example.company.account.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.company.BaseActivity;
import com.example.company.R;
import com.example.company.account.interfaces.AccountInterface;
import com.example.company.account.presenter.AccountListViewAdapter;
import com.example.company.account.presenter.AccountPresenter;
import com.example.company.authentication.presenter.AuthenticationPresenter;
import com.example.company.handlers.StatusCodeHandler;
import com.example.company.joinCompany.view.JoinCompanyActivity;
import com.example.company.joinPlatoon.view.JoinPlatoonActivity;

public class AccountActivity extends BaseActivity implements AccountInterface.View, View.OnClickListener {

    private static AccountActivity accountActivity;

    private AccountInterface.Presenter accountPresenter;

    private ListView listView;
    private ProgressBar accountProgressBar;

    private String[] options;
    private Integer[] imageID;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        accountActivity = this;

        setAccountPresenter();

        setViewStatusCodeHandler();

        setAccountProgressBar();

        accountPresenter.checkUserGroup();
    }

    private void setAccountPresenter() {
        accountPresenter = AccountPresenter.getInstance();
        accountPresenter.setView(this);
    }

    private void setViewStatusCodeHandler() {
        StatusCodeHandler.getInstance().setView(getInstance().findViewById(R.id.account_layout));
    }

    @Override
    public void setOptions() {

        switch (accountPresenter.getGroup()) {
            case COMPANY:
                imageID = new Integer[]{R.drawable.ic_join_platoon_foreground, R.drawable.ic_logout_foreground};
                options = new String[]{getResources().getString(R.string.title_account_join_platoon), getResources().getString(R.string.title_account_logout)};
                break;

            case PLATOON:
                imageID = new Integer[]{R.drawable.ic_join_platoon_assistant_foreground, R.drawable.ic_logout_foreground};
                options = new String[]{getResources().getString(R.string.title_account_join_platoon_assistant), getResources().getString(R.string.title_account_logout)};
                break;

            case PLATOON_ASSISTANT:
                imageID = new Integer[]{R.drawable.ic_logout_foreground};
                options = new String[]{getResources().getString(R.string.title_account_logout)};
                break;

            case NONE:
                imageID = new Integer[]{R.drawable.ic_join_platoon_foreground, R.drawable.ic_logout_foreground};
                options = new String[]{getResources().getString(R.string.title_account_join_company), getResources().getString(R.string.title_account_logout)};
                break;

            default:
                imageID = new Integer[]{R.drawable.ic_join_platoon_foreground, R.drawable.ic_logout_foreground};
                options = new String[]{getResources().getString(R.string.title_account_join_company), getResources().getString(R.string.title_account_logout)};
                break;
        }
    }

    @Override
    public void setListView() {
        listView = findViewById(R.id.account_listview);
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                listView.setAdapter(new AccountListViewAdapter(getInstance(), options, imageID));
            }
        });
        onClickItemMenuListView();
    }

    private void setAccountProgressBar() {
        accountProgressBar = findViewById(R.id.account_activity_progressBar);
    }

    @Override
    public void setVisibleAccountActivityProgressBar(final boolean isVisible) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                accountProgressBar.setVisibility(isVisible ? View.VISIBLE : View.GONE);
            }
        });
    }

    public static AccountActivity getInstance() {
        return accountActivity;
    }

    private void onClickItemMenuListView() {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                switch (position) {
                    case 0:
                        switch (accountPresenter.getGroup()) {
                            case COMPANY:
                                showJoinPlatoonActivity();
                                break;

                            case PLATOON:
                                showJoinPlatoonAssistantActivity();
                                break;

                            case PLATOON_ASSISTANT:
                                logout();
                                break;

                            case NONE:
                                showJoinCompanyActivity();
                                break;
                        }
                        break;

                    case 1:
                        logout();
                        break;
                }
            }
        });
    }

    private void showJoinCompanyActivity() {
        Intent intent = new Intent(this, JoinCompanyActivity.class);
        startActivity(intent);
    }

    private void showJoinPlatoonActivity() {
        Intent intent = new Intent(this, JoinPlatoonActivity.class);
        startActivity(intent);
    }

    private void showJoinPlatoonAssistantActivity() {
        Toast.makeText(accountActivity, "pomocnik dcy plutonu", Toast.LENGTH_SHORT).show();
    }

    private void logout() {
        AuthenticationPresenter.getInstance().logOut();
    }

    @Override
    public void onClick(View v) {
    }
}
