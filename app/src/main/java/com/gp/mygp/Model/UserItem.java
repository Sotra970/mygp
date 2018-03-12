package com.gp.mygp.Model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Ahmed Naeem on 3/12/2018.
 */

public class UserItem {

    @SerializedName("id")
    private int id;
    @SerializedName("name")
    private String username;
    @SerializedName("password")
    private String password;
    @SerializedName("email")
    private String email;
    @SerializedName("phone")
    private String phone;
    @SerializedName("doc_name")
    private String docUrl;
    @SerializedName("grade")
    private double grade;

    public UserItem(int id, String username, String password, String email, String phone, String docUrl, double grade) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.docUrl = docUrl;
        this.grade = grade;
    }

    public double getGrade() {
        return grade;
    }

    public UserItem() {
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setDocUrl(String docUrl) {
        this.docUrl = docUrl;
    }

    public String getEmail() {
        return email;
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getPhone() {
        return phone;
    }

    public String getDocUrl() {
        return docUrl;
    }
}
