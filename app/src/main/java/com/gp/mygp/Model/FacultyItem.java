package com.gp.mygp.Model;

import java.util.ArrayList;

/**
 * Created by Ahmed Naeem on 3/9/2018.
 */

public class FacultyItem {

    private String title;
    private int grade;
    private int cost;
    private ArrayList<String> departs;

    public FacultyItem(String title, int grade, int cost, ArrayList<String> departs) {
        this.title = title;
        this.grade = grade;
        this.cost = cost;
        this.departs = departs;
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

    public ArrayList<String> getDeparts() {
        return departs;
    }
}
