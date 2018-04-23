package com.gp.mygp.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gp.mygp.Activity.DetailsActivity;
import com.gp.mygp.Adapter.InfoAdapter;
import com.gp.mygp.Adapter.SliderAdapter;
import com.gp.mygp.Adapter.ThumbnailAdapter;
import com.gp.mygp.Callback.ConnectionCallback;
import com.gp.mygp.Callback.InfoItemClickListener;
import com.gp.mygp.Model.SliderItem;
import com.gp.mygp.Model.UniversityInfoItem;
import com.gp.mygp.Model.ThumbnailItem;
import com.gp.mygp.R;
import com.gp.mygp.Service.CallbackWithRetry;
import com.gp.mygp.Service.Injector;
import com.gp.mygp.Service.onRequestFailure;
import com.gp.mygp.Util.PagerSwitcher;
import com.gp.mygp.View.SmoothViewPager;
import com.rd.PageIndicatorView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by Ahmed Naeem on 3/12/2018.
 */

public class HomepageFragment extends BaseFragment implements InfoItemClickListener {

    private View theView;

    @BindView(R.id.thumbnails)
    RecyclerView thumbnailRecycler;
    @BindView(R.id.cards)
    RecyclerView infoRecycler;
    @BindView(R.id.slider)
    SmoothViewPager slider;
    @BindView(R.id.slider_layout)
    View sliderLayout;
    @BindView(R.id.indicator)
    PageIndicatorView indicator;
    private SliderAdapter sliderAdapter;
    private ThumbnailAdapter thumbnailAdapter;
    private InfoAdapter infoAdapter;

    private ArrayList<UniversityInfoItem> allData;
    private ArrayList<UniversityInfoItem> searchRes;

    public static Fragment getInstance() {
        return new HomepageFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (theView == null){
            theView = inflater.inflate(R.layout.fragment_homepage, container, false);
            ButterKnife.bind(this, theView);
            initSlider();
            initThumbnails();
            initInfo();
            getSliders();
            //loadData();
        }
        return theView;
    }

    private void initThumbnails() {
        thumbnailAdapter = new ThumbnailAdapter(null, getContext());
        thumbnailRecycler.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        thumbnailRecycler.setAdapter(thumbnailAdapter);
    }

    private void initSlider() {
        slider.setAllowManualSwitch(true);
        final PagerSwitcher switcher = new PagerSwitcher(getContext(), slider, 0);
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
        sliderAdapter = new SliderAdapter(getContext(), null);
        slider.setAdapter(sliderAdapter);
        indicator.setViewPager(slider);
    }


    private void initInfo() {
        infoAdapter = new InfoAdapter(null, getContext(), this);
        infoRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        infoRecycler.setAdapter(infoAdapter);
    }

    @Override
    public void onInfoItemClicked(UniversityInfoItem universityInfoItem) {
        DetailsActivity.setInfoItem(universityInfoItem);
        Intent i = new Intent(getContext(), DetailsActivity.class);
        startActivity(i);
    }

    private void getSliders(){
        showProgress(true);
        final Call<ArrayList<SliderItem>> sliderItemCall =
                Injector.Api().getSliders();
        sliderItemCall.enqueue(new CallbackWithRetry<ArrayList<SliderItem>>(
                sliderItemCall,
                new onRequestFailure() {
                    @Override
                    public void onFailure() {
                        showNoInternet(new ConnectionCallback() {
                            @Override
                            public void onRetry() {
                                getSliders();
                            }
                        });
                    }
                }
        ) {
            @Override
            public void onResponse(@NonNull Call<ArrayList<SliderItem>> call, @NonNull Response<ArrayList<SliderItem>> response) {
                if(response.isSuccessful()){
                    ArrayList<SliderItem> sliderItems = response.body();
                    if(sliderAdapter != null)
                        sliderAdapter.update(sliderItems);
                    getUniversities();
                }
            }
        });

    }

    public void getUniversities() {
        Call<ArrayList<UniversityInfoItem>> call =
                Injector.Api().getUnis();
        call.enqueue(new CallbackWithRetry<ArrayList<UniversityInfoItem>>(
                call,
                new onRequestFailure() {
                    @Override
                    public void onFailure() {
                        showNoInternet(new ConnectionCallback() {
                            @Override
                            public void onRetry() {
                                getUniversities();
                            }
                        });
                    }
                }
        ) {
            @Override
            public void onResponse(@NonNull Call<ArrayList<UniversityInfoItem>> call, @NonNull Response<ArrayList<UniversityInfoItem>> response) {
                if(response.isSuccessful()){
                    allData = response.body();
                    if(infoAdapter != null)
                        infoAdapter.update(allData);
                    if(thumbnailAdapter != null) {
                        thumbnailAdapter.update(allData);
                        indicator.setViewPager(slider);
                        indicator.setCount(allData == null ? 0 : allData.size());
                    }
                    showProgress(false);
                }
            }
        });
    }

    public void doSearch(final String query) {
        showProgress(true);
        Call<ArrayList<UniversityInfoItem>> call = Injector.Api().search(query);
        call.enqueue(new CallbackWithRetry<ArrayList<UniversityInfoItem>>(
                call,
                new onRequestFailure() {
                    @Override
                    public void onFailure() {
                        showNoInternet(new ConnectionCallback() {
                            @Override
                            public void onRetry() {
                                doSearch(query);
                            }
                        });
                    }
                }
        ) {
            @Override
            public void onResponse(@NonNull Call<ArrayList<UniversityInfoItem>> call, @NonNull Response<ArrayList<UniversityInfoItem>> response) {
                sliderLayout.setVisibility(View.GONE);
                if(response.isSuccessful()){
                    searchRes = response.body();
                    if(infoAdapter != null){
                        infoAdapter.update(searchRes);
                    }
                    if(thumbnailAdapter != null){
                        thumbnailAdapter.update(null);
                    }
                    showNoData(false);
                }else{
                    if(response.code() == 404){
                        // not found
                        if(infoAdapter != null){
                            infoAdapter.update(null);
                        }
                        if(thumbnailAdapter != null){
                            thumbnailAdapter.update(null);
                        }
                    }
                    showNoData(true);
                }
                showProgress(false);
            }
        });
    }

    public void clearSearch() {
        showNoData(false);
        sliderLayout.setVisibility(View.VISIBLE);
        if(infoAdapter != null){
            infoAdapter.update(allData);
        }
        if(thumbnailAdapter != null){
            thumbnailAdapter.update(allData);
        }
    }
}
