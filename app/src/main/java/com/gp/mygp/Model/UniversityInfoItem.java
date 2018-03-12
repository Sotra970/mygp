package com.gp.mygp.Model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by Ahmed Naeem on 2/25/2018.
 */

public class UniversityInfoItem {

    @SerializedName("id")
    private int id;
    @SerializedName("logo")
    private String logo;
    @SerializedName("name")
    private String title;
    @SerializedName("rate")
    private int rate;
    @SerializedName("cover")
    private String cover;
    @SerializedName("desc")
    private String desc;
    @SerializedName("facs")
    private ArrayList<FacultyItem> facultyItems;
    @SerializedName("min_grade")
    private int minPerc;
    @SerializedName("email")
    private String email;
    @SerializedName("phone")
    private String phone;


    public ArrayList<FacultyItem> getFacultyItems() {
        return facultyItems;
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

    public int getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public int getMinPerc() {
        return minPerc;
    }
}
