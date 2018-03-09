package com.gp.mygp.Model;

import java.util.ArrayList;

/**
 * Created by Ahmed Naeem on 2/25/2018.
 */

public class InfoItem {

    private String logo;
    private String title;
    private int rate;
    private String cover;
    private String desc;
    private ArrayList<String> majors;
    private int minPerc;

    public InfoItem(String logo, String title, int rate, String cover, String desc, ArrayList<String> majors, int minPerc) {
        this.logo = logo;
        this.title = title;
        this.rate = rate;
        this.cover = cover;
        this.desc = desc;
        this.majors = majors;
        this.minPerc = minPerc;
    }

    public String getLogo() {
        return logo;
    }

    public String getTitle() {
        return title;
    }

    public int getRate() {
        return rate;
    }

    public String getCover() {
        return cover;
    }

    public String getDesc() {
        return desc;
    }

    public ArrayList<String> getMajors() {
        return majors;
    }

    public int getMinPerc() {
        return minPerc;
    }
}
