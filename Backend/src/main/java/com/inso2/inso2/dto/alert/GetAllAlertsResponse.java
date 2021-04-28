package com.inso2.inso2.dto.alert;

import java.io.Serializable;

public class GetAllAlertsResponse implements Serializable {
    private String title, message, date;
    private boolean read;

    public GetAllAlertsResponse() {
    }

    public GetAllAlertsResponse(String title, String message, String date, boolean read) {
        this.title = title;
        this.message = message;
        this.date = date;
        this.read = read;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public boolean isRead() {
        return read;
    }

    public void setRead(boolean read) {
        this.read = read;
    }
}
