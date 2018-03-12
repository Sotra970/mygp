package com.gp.mygp.Model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by Ahmed Naeem on 3/9/2018.
 */

public class FacultyItem {

    @SerializedName("id")
    private int id;
    @SerializedName("uni_id")
    private int uni_id;
    @SerializedName("max_count")
    private int max_count;
    @SerializedName("name")
    private String title;
    @SerializedName("deposit")
    private int deposit;
    @SerializedName("appyly_from")
    private int grade;
    @SerializedName("emp")
    private int cost;
    @SerializedName("majors")
    private ArrayList<String> majors;

    public int getId() {
        return id;
    }

    public int getUni_id() {
        return uni_id;
    }

    public int getMax_count() {
        return max_count;
    }

    public int getDeposit() {
        return deposit;
    }

    public String getTitle() {
        return title;
    }

    public int getGrade() {
        return grade;
    }

    public int getCost() {
        return cost;
    }

    public ArrayList<String> getMajors() {
        return majors;
    }
}
