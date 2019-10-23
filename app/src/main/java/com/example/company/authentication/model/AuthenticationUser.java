package com.example.company.authentication.model;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class AuthenticationUser extends RealmObject {

    @PrimaryKey
    private int id;

    private Integer userId;

    private String userName;

    private String password;

    private String token;

    private Integer sentRequestCompanyNumber;

    private Integer sentRequestPlatoonNumber;

    private Integer companyNumber;

    private Integer platoonNumber;

    private RealmList<RoleName> roleNames;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Integer getSentRequestCompanyNumber() {
        return sentRequestCompanyNumber;
    }

    public void setSentRequestCompanyNumber(Integer sentRequestCompanyNumber) {
        this.sentRequestCompanyNumber = sentRequestCompanyNumber;
    }

    public Integer getSentRequestPlatoonNumber() {
        return sentRequestPlatoonNumber;
    }

    public void setSentRequestPlatoonNumber(Integer sentRequestPlatoonNumber) {
        this.sentRequestPlatoonNumber = sentRequestPlatoonNumber;
    }

    public Integer getCompanyNumber() {
        return companyNumber;
    }

    public void setCompanyNumber(Integer companyNumber) {
        this.companyNumber = companyNumber;
    }

    public Integer getPlatoonNumber() {
        return platoonNumber;
    }

    public void setPlatoonNumber(Integer platoonNumber) {
        this.platoonNumber = platoonNumber;
    }

    public RealmList<RoleName> getRoleNames() {
        return roleNames;
    }

    public void setRoleNames(RealmList<RoleName> roleNames) {
        this.roleNames = roleNames;
    }
}
