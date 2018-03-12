package com.gp.mygp.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gp.mygp.Model.FacultyItem;
import com.gp.mygp.R;
import com.gp.mygp.ViewHolder.FacultyVH;
import com.gp.mygp.ViewHolder.MajorVH;

import java.util.ArrayList;

/**
 * Created by Ahmed Naeem on 3/9/2018.
 */

public class FacultyAdapter extends RecyclerView.Adapter<FacultyVH>{

    private ArrayList<FacultyItem> facultyItems;
    private Context context;

    public FacultyAdapter(ArrayList<FacultyItem> facultyItems, Context context) {
        this.facultyItems = facultyItems;
        this.context = context;
    }

    @Override
    public FacultyVH onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context)
                .inflate(R.layout.faculty_item, parent, false);
        return new FacultyVH(v);
    }

    @Override
    public void onBindViewHolder(FacultyVH holder, int position) {
        FacultyItem facultyItem = facultyItems.get(position);
        /*holder.title.setText(facultyItem.getTitle());
        holder.cost.setText(facultyItem.getCost() + "");
        holder.grade.setText(facultyItem.getGrade() + "");*/
        ArrayList<String> dep = facultyItem.getDeparts();
        if(dep != null && !dep.isEmpty()){
            for(String s : dep){
                holder.flowLayout.addView(getMajorView(s));
            }
        }
    }

    View getMajorView(String major){
        View v = LayoutInflater.from(context)
                .inflate(R.layout.major_item, null, false);
        MajorVH vh = new MajorVH(v) ;
        vh.title.setText(major);
        return vh.itemView ;
    }

    @Override
    public int getItemCount() {
        return facultyItems == null ? 0 : facultyItems.size();
    }
}
