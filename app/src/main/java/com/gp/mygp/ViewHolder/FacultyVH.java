package com.gp.mygp.ViewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.gp.mygp.R;
import com.nex3z.flowlayout.FlowLayout;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Ahmed Naeem on 3/9/2018.
 */

public class FacultyVH extends RecyclerView.ViewHolder{

    @BindView(R.id.title)
    public TextView title;
    @BindView(R.id.price)
    public TextView cost;
    @BindView(R.id.grade)
    public TextView grade;
    @BindView(R.id.majors)
    public FlowLayout flowLayout;

    public FacultyVH(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}
