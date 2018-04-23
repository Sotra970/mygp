package com.gp.mygp.Response;

import com.gp.mygp.Model.FacultyItem;

import java.io.Serializable;

/**
 * Created by Sotra on 3/27/2018.
 */

public class StatusResponse  implements Serializable{

    FacultyItem facultyItem ;
    int status ;
    String due_amount ;


    public FacultyItem getFacultyItem() {
        return facultyItem;
    }

    public void setFacultyItem(FacultyItem facultyItem) {
        this.facultyItem = facultyItem;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getDue_amount() {
        return due_amount;
    }

    public void setDue_amount(String due_amount) {
        this.due_amount = due_amount;
    }
}
