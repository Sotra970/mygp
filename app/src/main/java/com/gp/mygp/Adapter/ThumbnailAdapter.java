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
import com.gp.mygp.Model.ThumbnailItem;
import com.gp.mygp.Model.UniversityInfoItem;
import com.gp.mygp.R;
import com.gp.mygp.ViewHolder.ThumbnailVH;

import java.util.ArrayList;

/**
 * Created by Ahmed Naeem on 2/25/2018.
 */

public class ThumbnailAdapter extends RecyclerView.Adapter<ThumbnailVH> {

    private ArrayList<UniversityInfoItem> data;
    private Context context;

    public ThumbnailAdapter(ArrayList<UniversityInfoItem> data, Context context) {
        this.data = data;
        this.context = context;
    }

    @Override
    public ThumbnailVH onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.thumbnail_layout, parent, false);
        return new ThumbnailVH(v);
    }

    @Override
    public void onBindViewHolder(ThumbnailVH holder, int position) {
        UniversityInfoItem item = data.get(position);
        Glide.with(context)
                .load(AppController.getImageUrl(item.getLogo()))
                //.apply(new RequestOptions().fitCenter())
                //.apply(new RequestOptions().circleCrop())
                .into(holder.image);
    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    public void update(ArrayList<UniversityInfoItem> allData) {
        Log.e("thumbnail_adapter", "data_size == " + (allData == null ? 0 : allData.size()));
        this.data = allData;
        notifyDataSetChanged();
    }
}
