package com.example.company.authentication.model;

import io.realm.RealmObject;

public class RoleName extends RealmObject {

    private String roleName;

    public RoleName() {
    }

    public RoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
