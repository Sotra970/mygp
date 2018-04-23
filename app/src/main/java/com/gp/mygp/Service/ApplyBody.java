package com.gp.mygp.Service;

import com.gp.mygp.Model.FacultyItem;

/**
 * Created by Sotra on 3/27/2018.
 */

public class ApplyBody {
    FacultyItem facultyItem ;
    int id   , uni_id;

    public ApplyBody(FacultyItem facultyItem, int id, int uni_id) {
        this.facultyItem = facultyItem;
        this.id = id;
        this.uni_id = uni_id;
    }
}
