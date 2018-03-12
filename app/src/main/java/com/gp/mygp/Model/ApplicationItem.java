package com.gp.mygp.Model;

/**
 * Created by Ahmed Naeem on 3/12/2018.
 */

public class ApplicationItem {

    public static int STATUS_PENDING = 2;

    public ApplicationItem(String uni, String date, int status, int cost, int deposit) {
        this.uni = uni;
        this.date = date;
        this.status = status;
        this.cost = cost;
        this.deposit = deposit;
    }

    private String uni;
    private String date;
    private int status;
    private int cost;
    private int deposit;

    public int getUniversityId() {
        return 0;
    }
}
