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
import com.gp.mygp.Callback.ApplicationClickListener;
import com.gp.mygp.Model.ApplicationItem;
import com.gp.mygp.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

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

        ArrayList<ApplicationItem> applicationItems = new ArrayList<>();
        applicationItems.add(new ApplicationItem("Helwan University", "12 nov 2012", ApplicationItem.STATUS_PENDING, 50_000, 5_000));
        applicationItems.add(new ApplicationItem("Helwan University", "12 nov 2012", ApplicationItem.STATUS_PENDING, 50_000, 5_000));
        applicationItems.add(new ApplicationItem("Helwan University", "12 nov 2012", ApplicationItem.STATUS_PENDING, 50_000, 5_000));
        applicationItems.add(new ApplicationItem("Helwan University", "12 nov 2012", ApplicationItem.STATUS_PENDING, 50_000, 5_000));
        applicationItems.add(new ApplicationItem("Helwan University", "12 nov 2012", ApplicationItem.STATUS_PENDING, 50_000, 5_000));
        applicationItems.add(new ApplicationItem("Helwan University", "12 nov 2012", ApplicationItem.STATUS_PENDING, 50_000, 5_000));
        adapter.updateData(applicationItems);

    }

    private void loadData(){

    }

    @Override
    public void onApplicationClicked(ApplicationItem applicationItem) {
        int uni = applicationItem.getUniversityId();
        DetailsActivity.setUniversityId(uni);
        Intent i = new Intent(getContext(), DetailsActivity.class);
        startActivity(i);
    }
}
