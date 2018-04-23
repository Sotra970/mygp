package com.gp.mygp.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.telecom.Call;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gp.mygp.Activity.DetailsActivity;
import com.gp.mygp.Adapter.ApplicationsAdapter;
import com.gp.mygp.Adapter.NotificationAdapter;
import com.gp.mygp.AppController;
import com.gp.mygp.Callback.ApplicationClickListener;
import com.gp.mygp.Callback.ConnectionCallback;
import com.gp.mygp.Model.ApplicationItem;
import com.gp.mygp.Model.NotificationItem;
import com.gp.mygp.R;
import com.gp.mygp.Service.CallbackWithRetry;
import com.gp.mygp.Service.Injector;
import com.gp.mygp.Service.onRequestFailure;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Response;

/**
 * Created by Ahmed Naeem on 3/12/2018.
 */

public class ApplicationsFragment extends BaseFragment
        implements ApplicationClickListener {

    private View theView;

    @BindView(R.id.recycler)
    RecyclerView recyclerView;

    private ApplicationsAdapter adapter;

    public static ApplicationsFragment getInstance() {
        return new ApplicationsFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if(theView == null){
            theView = inflater.inflate(R.layout.fragment_applications, container, false);
            ButterKnife.bind(this, theView);
            init();
        }
        return theView;
    }

    private void init() {
        adapter = new ApplicationsAdapter(null, getContext(), this);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
        loadData();

    }

    private void loadData(){
        retrofit2.Call<ArrayList<ApplicationItem>> call = Injector.Api().getApps( AppController.getInstance().getPrefManager().getUser().getId()+"");
        call.enqueue(new CallbackWithRetry<ArrayList<ApplicationItem>>(call, new onRequestFailure() {
            @Override
            public void onFailure() {
                showNoInternet(new ConnectionCallback() {
                    @Override
                    public void onRetry() {
                        loadData();
                    }
                });
            }
        }) {
            @Override
            public void onResponse(retrofit2.Call<ArrayList<ApplicationItem>> call, Response<ArrayList<ApplicationItem>> response) {
                showProgress(false);
                if (response.isSuccessful() && response.body() !=null){
                    adapter.updateData(response.body());
                }
            }
        });
    }

    @Override
    public void onApplicationClicked(ApplicationItem applicationItem) {
        DetailsActivity.setInfoItem(applicationItem.getUni());
        Intent i = new Intent(getContext(), DetailsActivity.class);
        startActivity(i);
    }
}
