package com.example.company.firebase.model;


public class NotificationFCM {

    private String title;
    private String body;

    public NotificationFCM() {
    }

    public NotificationFCM(String title, String body) {
        this.title = title;
        this.body = body;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
