package com.gp.mygp.Adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.gp.mygp.Model.SliderItem;

import java.util.ArrayList;

/**
 * Created by Ahmed Naeem on 2/25/2018.
 */

public class SliderAdapter extends PagerAdapter {

    private Context context;
    private ArrayList<SliderItem> images;


    public SliderAdapter(Context context, ArrayList<SliderItem> images) {
        this.context = context;
        this.images = images;
    }

    @Override
    public int getCount() {
        return images == null ? 0 : images.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }


    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        SliderItem sliderItem = images.get(position);
        String image = sliderItem.getUrl();

        LinearLayout linearLayout = new LinearLayout(context);
        ViewPager.LayoutParams params = new ViewPager.LayoutParams();
        params.height = ViewPager.LayoutParams.MATCH_PARENT;
        params.width = ViewPager.LayoutParams.MATCH_PARENT;
        linearLayout.setLayoutParams(params);


        ImageView imageView =
                new ImageView(context);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
        );
        imageView.setLayoutParams(layoutParams);
        imageView.setAdjustViewBounds(false);

        imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);

        linearLayout.addView(imageView);

        Glide.with(context)
                .load(Integer.parseInt(image))
                .into(imageView);

        container.addView(linearLayout);

        return linearLayout;
    }

    @Override
    public void finishUpdate(ViewGroup container) {
        super.finishUpdate(container);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        try {
            container.removeView((View) object);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void update(ArrayList<SliderItem> data){
        this.images = data;
        notifyDataSetChanged();
    }
}
