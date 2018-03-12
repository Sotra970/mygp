package com.gp.mygp.Model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Ahmed Naeem on 3/12/2018.
 */

public class SliderItem {

    @SerializedName("id")
    private int id;
    @SerializedName("image")
    private String image;
    @SerializedName("uni")
    private UniversityInfoItem universityInfoItem;
    @SerializedName("uni_id")
    private int uni_id;

    public int getId() {
        return id;
    }

    public String getImage() {
        return image;
    }

    public UniversityInfoItem getUniversityInfoItem() {
        return universityInfoItem;
    }

    public int getUni_id() {
        return uni_id;
    }
}
