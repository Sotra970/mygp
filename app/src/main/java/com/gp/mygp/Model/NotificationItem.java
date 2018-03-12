package com.gp.mygp.Model;

/**
 * Created by Ahmed Naeem on 3/12/2018.
 */

public class NotificationItem {

    private String uni;
    private String date;
    private String msg;

    public NotificationItem(String uni, String date, String msg) {
        this.uni = uni;
        this.date = date;
        this.msg = msg;
    }

    public String getUni() {
        return uni;
    }

    public String getDate() {
        return date;
    }

    public String getMsg() {
        return msg;
    }
}
