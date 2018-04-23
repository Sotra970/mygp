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
import com.gp.mygp.Callback.ApplicationClickListener;
import com.gp.mygp.Model.ApplicationItem;
import com.gp.mygp.Model.NotificationItem;
import com.gp.mygp.R;
import com.gp.mygp.Service.Config;
import com.gp.mygp.ViewHolder.ApplicationVH;

import java.util.ArrayList;

/**
 * Created by Ahmed Naeem on 3/12/2018.
 */

public class ApplicationsAdapter extends RecyclerView.Adapter<ApplicationVH> {

    private ArrayList<ApplicationItem> applicationItems;
    private Context context;
    private ApplicationClickListener clickListener;

    public ApplicationsAdapter(ArrayList<ApplicationItem> applicationItems, Context context, ApplicationClickListener clickListener) {
        this.applicationItems = applicationItems;
        this.context = context;
        this.clickListener = clickListener;
    }

    @Override
    public ApplicationVH onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.application_item, parent, false);
        return new ApplicationVH(v);
    }

    @Override
    public void onBindViewHolder(ApplicationVH holder, int position) {
        final ApplicationItem item = applicationItems.get(position);
        holder.title.setText(item.getUni().getTitle());
        holder.date.setText(item.getDate());
        holder.faculty.setText(item.getFac().getTitle());
        holder.cost.setText(item.getFac().getCost()+"");
        holder.deposit.setText(item.getFac().getDeposit()+"");
        Glide.with(holder.image.getContext())
                .load(AppController.getImageUrl(item.getUni().getLogo()))
                .apply(new RequestOptions().circleCrop())
                .apply(new RequestOptions().diskCacheStrategy(DiskCacheStrategy.ALL))
                .into(holder.image) ;

        switch (item.getStatus()){

            case Config.APP : {
                holder.status.setText("Apply");
                break;
            }
            case Config.ACC : {
                holder.status.setText("Accepted");
                break;
            }

            case Config.REJ : {
                holder.status.setText("Rejected");
                break;
            }

            case Config.WL : {
                holder.status.setText("Waiting list");
                break;
            }


            case Config.WR : {
                holder.status.setText("Waiting for response");
                break;
            }

            case Config.ATT : {
                holder.status.setText("Attach bank statement");
                break;
            }

        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(clickListener != null){
                    clickListener.onApplicationClicked(item);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return applicationItems == null ? 0 : applicationItems.size();
    }

    public void updateData(ArrayList<ApplicationItem> newItems){
        this.applicationItems = newItems;
        notifyDataSetChanged();
    }
}
