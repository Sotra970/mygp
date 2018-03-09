package com.gp.mygp.ViewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.gp.mygp.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Ahmed Naeem on 2/25/2018.
 */

public class MajorVH extends RecyclerView.ViewHolder{

    @BindView(R.id.title)
    public TextView title;

    public MajorVH(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}
