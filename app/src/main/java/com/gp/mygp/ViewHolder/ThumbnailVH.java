package com.gp.mygp.ViewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.gp.mygp.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Ahmed Naeem on 2/25/2018.
 */

public class ThumbnailVH extends RecyclerView.ViewHolder {

    @BindView(R.id.image)
    public ImageView image;

    public ThumbnailVH(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}
