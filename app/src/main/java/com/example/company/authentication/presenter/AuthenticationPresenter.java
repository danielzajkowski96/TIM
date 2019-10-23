package com.example.company.authentication.presenter;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;

import com.example.company.API.api.AuthApi;
import com.example.company.API.model.DTOSystemUser;
import com.example.company.application.Company;
import com.example.company.authentication.model.AuthenticationUser;
import com.example.company.authentication.model.RoleName;
import com.example.company.login.view.LoginActivity;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmResults;

public class AuthenticationPresenter {

    private static AuthenticationPresenter authenticationPresenter;

    private Realm realm;

    private AuthenticationUser authenticationUser;

    private AuthApi authApi;

    private AuthenticationPresenter() {
        realm = Realm.getDefaultInstance();
        createAuthenticationUserRealmObject();
        authApi = new AuthApi();
    }

    public static AuthenticationPresenter getInstance() {
        if (authenticationPresenter == null) {
            authenticationPresenter = new AuthenticationPresenter();
        }
        return authenticationPresenter;
    }

    public void saveAuthenticationUser(final DTOSystemUser dtoSystemUser, final String password) {
        createAuthenticationUserRealmObject();
        realm = Realm.getDefaultInstance();

        final AuthenticationUser authenticationUser = realm.where(AuthenticationUser.class).equalTo("id", 1).findFirst();

        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                if (authenticationUser != null) {
                    authenticationUser.setUserId(dtoSystemUser.getUserId());
                    authenticationUser.setUserName(dtoSystemUser.getUserName());
                    authenticationUser.setToken(dtoSystemUser.getToken());
                    authenticationUser.setPassword(encodePassword(password));

                    for (String roleName : dtoSystemUser.getRoleNames()
                    ) {
                        authenticationUser.getRoleNames().add(new RoleName(roleName));
                    }

                }
                realm.insertOrUpdate(authenticationUser);
            }
        });


//        getRoleNames();

    }

    public List<RoleName> getRoleNames() {
        final List<RoleName> roleNameList = new ArrayList<>();

        final AuthenticationUser authenticationUser = realm.where(AuthenticationUser.class).equalTo("id", 1).findFirst();

        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                if (authenticationUser != null) {
                    RealmList<RoleName> roleNameRealmList = authenticationUser.getRoleNames();

                    roleNameList.addAll(roleNameRealmList);
                }
            }
        });

        for (RoleName roleName : roleNameList
        ) {

        }

        return roleNameList;
    }

    public void addNewRoleName(final String roleName) {
        createAuthenticationUserRealmObject();
        realm = Realm.getDefaultInstance();

        final AuthenticationUser authenticationUser = realm.where(AuthenticationUser.class).equalTo("id", 1).findFirst();

        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                if (authenticationUser != null) {
                    authenticationUser.getRoleNames().add(new RoleName(roleName));
                }
                realm.insertOrUpdate(authenticationUser);
            }
        });
    }

    public void saveAuthenticationUser(AuthenticationUser authenticationUserModel) {
        realm = Realm.getDefaultInstance();
        realm.beginTransaction();

        AuthenticationUser authenticationUser = realm.where(AuthenticationUser.class).equalTo("id", 1).findFirst();
        if (authenticationUser != null) {
            authenticationUser.setUserId(authenticationUserModel.getUserId());
            authenticationUser.setUserName(authenticationUserModel.getUserName());
            authenticationUser.setToken(authenticationUserModel.getToken());
            authenticationUser.setRoleNames(authenticationUserModel.getRoleNames());

            realm.insertOrUpdate(authenticationUser);
        }

        realm.commitTransaction();
    }

    public void saveSentRequestCompanyNumber(final int companyNumber) {
        realm = Realm.getDefaultInstance();

        final AuthenticationUser authenticationUser = realm.where(AuthenticationUser.class).equalTo("id", 1).findFirst();

        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                if (authenticationUser != null) {
                    authenticationUser.setSentRequestCompanyNumber(companyNumber);
                }
                realm.insertOrUpdate(authenticationUser);
            }
        });
    }

    public void saveCompanyNumber(final int companyNumber) {
        realm = Realm.getDefaultInstance();

        final AuthenticationUser authenticationUser = realm.where(AuthenticationUser.class).equalTo("id", 1).findFirst();

        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                if (authenticationUser != null) {
                    authenticationUser.setCompanyNumber(companyNumber);
                }
                realm.insertOrUpdate(authenticationUser);
            }
        });
    }

    public void saveSentRequestPlatoonNumber(final int platoonNumber) {
        realm = Realm.getDefaultInstance();

        final AuthenticationUser authenticationUser = realm.where(AuthenticationUser.class).equalTo("id", 1).findFirst();

        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                if (authenticationUser != null) {
                    authenticationUser.setSentRequestPlatoonNumber(platoonNumber);
                }
                realm.insertOrUpdate(authenticationUser);
            }
        });
    }

    public void savePlatoonNumber(final int platoonNumber) {
        realm = Realm.getDefaultInstance();

        final AuthenticationUser authenticationUser = realm.where(AuthenticationUser.class).equalTo("id", 1).findFirst();

        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                if (authenticationUser != null) {
                    authenticationUser.setPlatoonNumber(platoonNumber);
                }
                realm.insertOrUpdate(authenticationUser);
            }
        });
    }

    private void deleteUserFromRealm() {

        realm = Realm.getDefaultInstance();

        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                RealmResults<AuthenticationUser> users = realm.where(AuthenticationUser.class).findAll();
                users.deleteAllFromRealm();
            }
        });
    }

    public AuthenticationUser getAuthenticationUser() {
        realm = Realm.getDefaultInstance();
        final AuthenticationUser[] authenticationUser = {null};
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                authenticationUser[0] = realm.where(AuthenticationUser.class).equalTo("id", 1).findFirst();
            }
        });

        return authenticationUser[0];
    }

    private void createAuthenticationUserRealmObject() {
        realm = Realm.getDefaultInstance();

        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                if (realm.where(AuthenticationUser.class).findAll().isEmpty()) {
                    realm.createObject(AuthenticationUser.class, 1);
                }
            }
        });
    }

    private String encodePassword(String decodedPassword) {
        byte[] hash = (decodedPassword.getBytes(StandardCharsets.UTF_8));
        String string = android.util.Base64.encodeToString(hash, android.util.Base64.DEFAULT);
        return string;
    }

    private String decodePassword(String password) {
        byte[] data = android.util.Base64.decode(password, android.util.Base64.DEFAULT);
        return new String(data, StandardCharsets.UTF_8);
    }

    public void logOut() {

        deleteUserFromRealm();
        if (realm != null) {
            realm.close();
            realm = null;
        }
        authenticationPresenter = null;
        Activity currentActivity = Company.getInstance().getCurrentActivity();
        Intent intent = new Intent(currentActivity.getApplicationContext(), LoginActivity.class);
//        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        currentActivity.startActivity(intent);
        currentActivity.finish();
    }

}
