package com.gp.mygp.Model;

/**
 * Created by Ahmed Naeem on 3/12/2018.
 */

public class NotificationItem {

    private String id;
    private String user_id;
    private String uni_id;
    private String title;
    private String body;

    private UniversityInfoItem uni;
    private String date;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getUni_id() {
        return uni_id;
    }

    public void setUni_id(String uni_id) {
        this.uni_id = uni_id;
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

    public UniversityInfoItem getUni() {
        return uni;
    }

    public void setUni(UniversityInfoItem uni) {
        this.uni = uni;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
