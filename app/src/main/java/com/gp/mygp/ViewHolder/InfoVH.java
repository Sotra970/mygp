package com.gp.mygp.ViewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.gp.mygp.R;
import com.nex3z.flowlayout.FlowLayout;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Ahmed Naeem on 2/25/2018.
 */

public class InfoVH extends RecyclerView.ViewHolder {

    @BindView(R.id.cover)
    public ImageView cover;
    @BindView(R.id.title)
    public TextView title;
    @BindView(R.id.min_grades)
    public TextView grades;
    @BindView(R.id.desc)
    public TextView desc;
    @BindView(R.id.logo)
    public ImageView logo;
    @BindView(R.id.majors)
    public FlowLayout majors;

    public InfoVH(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}
