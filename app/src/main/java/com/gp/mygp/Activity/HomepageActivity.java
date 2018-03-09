package com.gp.mygp.Activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;

import com.gp.mygp.Adapter.InfoAdapter;
import com.gp.mygp.Adapter.SliderAdapter;
import com.gp.mygp.Adapter.ThumbnailAdapter;
import com.gp.mygp.Model.InfoItem;
import com.gp.mygp.Model.ThumbnailItem;
import com.gp.mygp.R;
import com.gp.mygp.Util.PagerSwitcher;
import com.gp.mygp.View.SmoothViewPager;
import com.rd.PageIndicatorView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Ahmed Naeem on 2/25/2018.
 */

public class HomepageActivity extends AppCompatActivity {

    @BindView(R.id.thumbnails)
    RecyclerView thumbnailRecycler;
    @BindView(R.id.cards)
    RecyclerView infoRecycler;
    @BindView(R.id.slider)
    SmoothViewPager slider;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.indicator)
    PageIndicatorView indicator;

    private SliderAdapter sliderAdapter;
    private ThumbnailAdapter thumbnailAdapter;
    private InfoAdapter infoAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);
        ButterKnife.bind(this);

        initSlider();
        initThumbnails();
        initInfo();
        loadData();
    }


    private void initSlider() {
        slider.setAllowManualSwitch(true);
        final PagerSwitcher switcher = new PagerSwitcher(this, slider);
        switcher.setRun(true);
        switcher.startSwitching();
        slider.addOnPageChangeListener(
                new ViewPager.OnPageChangeListener() {
                    @Override
                    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                    }

                    @Override
                    public void onPageSelected(int position) {
                        switcher.setPage(position);
                    }

                    @Override
                    public void onPageScrollStateChanged(int state) {

                    }
                }
        );
        sliderAdapter = new SliderAdapter(this, null);
        slider.setAdapter(sliderAdapter);
    }


    private void initThumbnails() {
        ArrayList<ThumbnailItem> thumbnailItems = new ArrayList<>() ;
        thumbnailItems.add(new ThumbnailItem(R.drawable.oct6_logo+"")) ;
        thumbnailItems.add(new ThumbnailItem(R.drawable.oct6_logo+"")) ;
        thumbnailItems.add(new ThumbnailItem(R.drawable.oct6_logo+"")) ;
        thumbnailItems.add(new ThumbnailItem(R.drawable.oct6_logo+"")) ;
        thumbnailItems.add(new ThumbnailItem(R.drawable.oct6_logo+"")) ;
        thumbnailAdapter = new ThumbnailAdapter(thumbnailItems , this) ;
        thumbnailRecycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        thumbnailRecycler.setAdapter(thumbnailAdapter);
    }

    private void initInfo() {
        infoAdapter = new InfoAdapter(null, this);
        infoRecycler.setLayoutManager(new LinearLayoutManager(this));
        infoRecycler.setAdapter(infoAdapter);
    }

    private void loadData(){
        ArrayList<String> sliderItems = new ArrayList<>();
        sliderItems.add(R.drawable.oct_cover + "");
        sliderItems.add(R.drawable.oct_cover + "");
        sliderItems.add(R.drawable.oct_cover + "");
        sliderItems.add(R.drawable.oct_cover + "");
        sliderAdapter.update(sliderItems);

        ArrayList<InfoItem> infoItems = new ArrayList<>();
        ArrayList<String> maj = new ArrayList<>();
        maj.add("Computer Science");
        maj.add("Engineering");
        maj.add("Business Administration ");
        maj.add("Fine Arts ");

        InfoItem infoItem =  new InfoItem(
                ""+R.drawable.oct6_logo,
                "October University",
                3,
                ""+R.drawable.oct_cover,
                getString(R.string.oct_desc),
                maj,
                70
        );
        infoItems.add(infoItem);
        infoItems.add(infoItem);
        infoItems.add(infoItem);
        infoItems.add(infoItem);
        infoItems.add(infoItem);
        infoAdapter.update(infoItems);
    }
}

