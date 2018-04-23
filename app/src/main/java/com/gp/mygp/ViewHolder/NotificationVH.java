package com.gp.mygp.ViewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.request.target.BitmapThumbnailImageViewTarget;
import com.gp.mygp.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Ahmed Naeem on 3/12/2018.
 */

public class NotificationVH extends RecyclerView.ViewHolder {

    @BindView(R.id.title)
    public TextView title;
    @BindView(R.id.date)
    public TextView date;
    @BindView(R.id.msg)
    public TextView msg;

    @BindView(R.id.image)
    public ImageView image;

    public NotificationVH(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}
