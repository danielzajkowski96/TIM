package com.example.company.main.view;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import com.example.company.BaseActivity;
import com.example.company.R;
import com.example.company.account.view.AccountActivity;
import com.example.company.authentication.presenter.AuthenticationPresenter;
import com.example.company.companyChecker.view.NoCompanyFragment;
import com.example.company.handlers.StatusCodeHandler;
import com.example.company.learningMaterials.view.LearningMaterialsFragment;
import com.example.company.main.interfaces.MainInterface;
import com.example.company.main.presenter.MainPresenter;
import com.example.company.messages.view.MessagesFragment;
import com.example.company.permissionAccess.interfaces.PermissionAccessInterface;
import com.example.company.permissionAccess.presenter.PermissionAccessPresenter;
import com.example.company.timetable.view.TimeTableFragment;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.FirebaseMessaging;

public class MainActivity extends BaseActivity implements MainInterface.View, PermissionAccessInterface.View {

    private static MainActivity mainActivity;

    private BottomNavigationView navigation;

    private TimeTableFragment timeTableFragment;
    private MessagesFragment messagesFragment;
    private NoCompanyFragment noCompanyFragment;
    private LearningMaterialsFragment learningMaterialsFragment;

    private MainInterface.Presenter mainPresenter;
    private PermissionAccessInterface.Presenter permissionAccessPresenter;

    private ProgressBar mainActivityProgressBar;

    private Toolbar toolbar;

    private int selectedItemId = -1;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            setSelectedItemId(item.getItemId());

            switch (item.getItemId()) {
                case R.id.navigation_timetable:
                    removeMessagesFragment();
                    removeLearningMaterialsFragment();
                    removeNoCompanyFragment();
                    mainPresenter.checkUserHasCompany();
                    return true;
                case R.id.navigation_messages:
                    removeTimeTableFragment();
                    removeLearningMaterialsFragment();
                    removeNoCompanyFragment();
                    mainPresenter.checkUserHasCompany();
                    return true;
                case R.id.navigation_learning_materials:
                    removeTimeTableFragment();
                    removeMessagesFragment();
                    removeNoCompanyFragment();
                    mainPresenter.checkUserHasCompany();
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainActivity = this;

        setComponents();

        setToolbar();

        setVisibleProgressBar(true);

        setViewStatusCodeHandler();

        setMainPresenter();

        setPermissionAccessPresenter();

        if (!isUserLoggedIn()) {
            AuthenticationPresenter.getInstance().logOut();
        } else {
            showMessagesNavigationMenuItem(false);
            checkUserHasPermissionMessages();
            showContentViewsOnStart();

            mainPresenter.checkUserHasCompany();
        }

        initFirebase();


        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    @Override
    public void showMessagesNavigationMenuItem(final boolean isVisible) {
        Menu menu = navigation.getMenu();

        final MenuItem navigationMessages = menu.getItem(1);

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                navigationMessages.setVisible(isVisible);
            }
        });
    }

    private void setComponents() {
        mainActivityProgressBar = findViewById(R.id.main_activity_progressBar);
        navigation = findViewById(R.id.navigation);
    }

    private void setMainPresenter() {
        mainPresenter = MainPresenter.getInstance();
        mainPresenter.getView(this);
    }

    private void checkUserHasPermissionMessages() {
        permissionAccessPresenter.checkUserHasAccessForMessages();
    }

    private void setPermissionAccessPresenter() {
        permissionAccessPresenter = PermissionAccessPresenter.getInstance();
        permissionAccessPresenter.setView(this);
    }

    private boolean isUserLoggedIn() {
        return AuthenticationPresenter.getInstance().getAuthenticationUser() != null;
    }

    private void showContentViewsOnStart() {
        timeTableFragment = TimeTableFragment.getInstance();
        messagesFragment = MessagesFragment.getInstance();
        noCompanyFragment = NoCompanyFragment.getInstance();
        learningMaterialsFragment = LearningMaterialsFragment.getInstance();

        setSelectedItemId(R.id.navigation_timetable);

//        removeTimeTableFragment();
//        removeMessagesFragment();
//        removeLearningMaterialsFragment();
//        replaceTimeTableFragment();
    }

    private void setViewStatusCodeHandler() {
        StatusCodeHandler.getInstance().setView(getInstance().findViewById(R.id.main_layout));
    }

    private void initFirebase() {
        FirebaseMessaging.getInstance().setAutoInitEnabled(true);
    }

    private void setToolbar() {
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitleTextColor(Color.WHITE);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.app_toolbar_navigation, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.account:
                showAccountActivity();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void showAccountActivity() {
        Intent intent = new Intent(this, AccountActivity.class);
        startActivity(intent);
    }

    @Override
    public void setVisibleProgressBar(final boolean isVisible) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mainActivityProgressBar.setVisibility(isVisible ? View.VISIBLE : View.GONE);
            }
        });
    }

    @Override
    public void setVisibleLearningMaterialsAddButton(boolean isVisible) {
    }

    public static MainActivity getInstance() {
        return mainActivity;
    }

    private FragmentTransaction beginFragmentManagerTransaction() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        return fragmentManager.beginTransaction();
    }

    @Override
    public void removeTimeTableFragment() {
        FragmentTransaction fragmentTransaction = beginFragmentManagerTransaction();

        fragmentTransaction.remove(timeTableFragment).commit();
    }

    @Override
    public void removeMessagesFragment() {
        FragmentTransaction fragmentTransaction = beginFragmentManagerTransaction();

        fragmentTransaction.remove(messagesFragment).commit();
    }

    @Override
    public void removeLearningMaterialsFragment() {
        FragmentTransaction fragmentTransaction = beginFragmentManagerTransaction();

        fragmentTransaction.remove(learningMaterialsFragment).commit();
    }

    @Override
    public void removeNoCompanyFragment() {
        FragmentTransaction fragmentTransaction = beginFragmentManagerTransaction();

        fragmentTransaction.remove(noCompanyFragment).commit();
    }

    @Override
    public void replaceTimeTableFragment() {
        FragmentTransaction fragmentTransaction = beginFragmentManagerTransaction();

        fragmentTransaction.replace(R.id.fragmentContainerMainActivity, timeTableFragment).commit();
    }

    @Override
    public void replaceMessagesFragment() {
        FragmentTransaction fragmentTransaction = beginFragmentManagerTransaction();

        fragmentTransaction.replace(R.id.fragmentContainerMainActivity, messagesFragment).commit();
    }

    @Override
    public void replaceLearningMaterialsFragment() {
        FragmentTransaction fragmentTransaction = beginFragmentManagerTransaction();

        fragmentTransaction.replace(R.id.fragmentContainerMainActivity, learningMaterialsFragment).commit();
    }

    @Override
    public void replaceNoCompanyFragment() {
        FragmentTransaction fragmentTransaction = beginFragmentManagerTransaction();

        fragmentTransaction.replace(R.id.fragmentContainerMainActivity, noCompanyFragment).commit();
    }

    @Override
    public void showChosenFragment(int itemId) {
        switch (itemId) {
            case R.id.navigation_timetable:
                removeMessagesFragment();
                removeLearningMaterialsFragment();
                removeNoCompanyFragment();
                replaceTimeTableFragment();
                break;
            case R.id.navigation_messages:
                removeTimeTableFragment();
                removeLearningMaterialsFragment();
                removeNoCompanyFragment();
                replaceMessagesFragment();
                break;
            case R.id.navigation_learning_materials:
                removeTimeTableFragment();
                removeMessagesFragment();
                removeNoCompanyFragment();
                replaceLearningMaterialsFragment();
                break;
        }
    }

    @Override
    public int getSelectedItemId() {
        return selectedItemId;
    }

    public void setSelectedItemId(int selectedItemId) {
        this.selectedItemId = selectedItemId;
    }

    @Override
    public void showSnackbar(String message) {
        Snackbar snackbar = Snackbar
                .make(getInstance().findViewById(R.id.main_layout), message, Snackbar.LENGTH_LONG);

        snackbar.show();
    }


}
