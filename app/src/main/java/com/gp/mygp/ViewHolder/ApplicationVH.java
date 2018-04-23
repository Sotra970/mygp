package com.gp.mygp.ViewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.gp.mygp.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Ahmed Naeem on 3/12/2018.
 */

public class ApplicationVH extends RecyclerView.ViewHolder {

    @BindView(R.id.status)
    public TextView status ;

    @BindView(R.id.faculty)
    public TextView faculty ;

    @BindView(R.id.cost)
    public  TextView cost ;

    @BindView(R.id.deposit)
    public TextView deposit ;

    @BindView(R.id.title)
    public  TextView title ;

    @BindView(R.id.date)
    public  TextView date ;

    @BindView(R.id.image)
    public  ImageView image ;


    public ApplicationVH(View itemView) {
        super(itemView);
        ButterKnife.bind(this , itemView) ;
    }
}
