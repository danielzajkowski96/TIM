package com.example.company.firebase.model;

public class Sender {

    private NotificationFCM notification;
    private String to;

    public Sender() {
    }

    public Sender(NotificationFCM notification, String to) {
        this.notification = notification;
        this.to = to;
    }

    public NotificationFCM getNotification() {
        return notification;
    }

    public void setNotification(NotificationFCM notification) {
        this.notification = notification;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }
}
