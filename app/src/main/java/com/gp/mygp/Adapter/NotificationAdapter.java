package com.gp.mygp.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.gp.mygp.AppController;
import com.gp.mygp.Callback.NotificationClickListener;
import com.gp.mygp.Model.NotificationItem;
import com.gp.mygp.R;
import com.gp.mygp.Service.Config;
import com.gp.mygp.ViewHolder.NotificationVH;

import java.util.ArrayList;

/**
 * Created by Ahmed Naeem on 3/12/2018.
 */

public class NotificationAdapter extends RecyclerView.Adapter<NotificationVH> {

    private ArrayList<NotificationItem> notificationItems;
    private Context context;
    private NotificationClickListener clickListener;

    public NotificationAdapter(ArrayList<NotificationItem> notificationItems, Context context, NotificationClickListener clickListener) {
        this.notificationItems = notificationItems;
        this.context = context;
        this.clickListener = clickListener;
    }

    @Override
    public NotificationVH onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context)
                .inflate(R.layout.notification_item, parent, false);
        return new NotificationVH(v);
    }

    @Override
    public void onBindViewHolder(NotificationVH holder, int position) {
        final NotificationItem item = notificationItems.get(position);
        holder.title.setText(item.getTitle());
        holder.date.setText(item.getDate()+"");
        holder.msg.setText(item.getBody());
        Glide.with(holder.image.getContext())
                .load(AppController.getImageUrl(item.getUni().getLogo()))
                .apply(new RequestOptions().circleCrop())
                .apply(new RequestOptions().diskCacheStrategy(DiskCacheStrategy.ALL))
                .into(holder.image) ;
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(clickListener != null){
                    clickListener.onNotificationClicked(item);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return  notificationItems == null ? 0 : notificationItems.size();
    }

    public void updateData(ArrayList<NotificationItem> newItems){
        this.notificationItems = newItems;
        notifyDataSetChanged();
    }
}
