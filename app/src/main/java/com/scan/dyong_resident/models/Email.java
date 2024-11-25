package com.scan.dyong_resident.models;

public class Email {
    private String title;
    private String content;
    private String date;

    public Email(String title, String content, String date) {
        this.title = title;
        this.content = content;
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String getDate() {
        return date;
    }
}

