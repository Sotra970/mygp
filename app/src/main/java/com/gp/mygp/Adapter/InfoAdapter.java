package com.gp.mygp.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.gp.mygp.AppController;
import com.gp.mygp.Callback.InfoItemClickListener;
import com.gp.mygp.Model.FacultyItem;
import com.gp.mygp.Model.UniversityInfoItem;
import com.gp.mygp.R;
import com.gp.mygp.ViewHolder.InfoVH;
import com.gp.mygp.ViewHolder.MajorVH;

import java.util.ArrayList;

/**
 * Created by Ahmed Naeem on 2/25/2018.
 */

public class InfoAdapter extends RecyclerView.Adapter<InfoVH> {

    private ArrayList<UniversityInfoItem> data;
    private Context context;
    private InfoItemClickListener clickListener;

    public InfoAdapter(ArrayList<UniversityInfoItem> data, Context context, InfoItemClickListener clickListener) {
        this.data = data;
        this.context = context;
        this.clickListener = clickListener;
    }

    @Override
    public InfoVH onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context)
                .inflate(R.layout.info_layout, parent, false);
        return new InfoVH(v);
    }

    @Override
    public void onBindViewHolder(InfoVH holder, int position) {
        final UniversityInfoItem item = data.get(position);
        holder.itemView.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(clickListener != null){
                            clickListener.onInfoItemClicked(item);
                        }
                    }
                }
        );
        holder.title.setText(item.getTitle());
        holder.desc.setText(item.getDesc());
        String from = context.getText(R.string.from) + " " + item.getMinPerc() + "%";
        holder.grades.setText(from);
        Glide.with(context)
                .load(AppController.getImageUrl(item.getCover()))
                .apply(new RequestOptions().centerCrop())
                .into(holder.cover);
        Glide.with(context)
                .load(AppController.getImageUrl(item.getLogo()))
                .apply(new RequestOptions().fitCenter())
                .apply(new RequestOptions().circleCrop())
                .into(holder.logo);
        holder.majors.removeAllViews();
        for (FacultyItem facultyItem : item.getFacultyItems()){
            holder.majors.addView(getMajorView(facultyItem.getTitle()));
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
        return data == null ? 0 : data.size();
    }

    public void update(ArrayList<UniversityInfoItem> data){
        Log.e("info_adapter", "data_size == " + (data == null ? 0 : data.size()));
        this.data = data;
        notifyDataSetChanged();
    }
}
