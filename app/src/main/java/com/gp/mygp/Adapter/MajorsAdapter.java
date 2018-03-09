package com.gp.mygp.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gp.mygp.R;
import com.gp.mygp.ViewHolder.MajorVH;

import java.util.ArrayList;

/**
 * Created by Ahmed Naeem on 2/25/2018.
 */

public class MajorsAdapter extends RecyclerView.Adapter<MajorVH> {

    private ArrayList<String> items;
    private Context context;

    public MajorsAdapter(ArrayList<String> items, Context context) {
        this.items = items;
        this.context = context;
    }

    @Override
    public MajorVH onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context)
                .inflate(R.layout.major_item, parent, false);
        return new MajorVH(v);
    }

    @Override
    public void onBindViewHolder(MajorVH holder, int position) {
        String s = items.get(position);
        holder.title.setText(s);
    }

    @Override
    public int getItemCount() {
        return items == null ? 0 : items.size();
    }

    public void update(ArrayList<String> data){
        this.items = data;
        notifyDataSetChanged();
    }
}
