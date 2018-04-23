package com.gp.mygp.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gp.mygp.Activity.DetailsActivity;
import com.gp.mygp.Adapter.NotificationAdapter;
import com.gp.mygp.AppController;
import com.gp.mygp.Callback.ConnectionCallback;
import com.gp.mygp.Callback.NotificationClickListener;
import com.gp.mygp.Model.NotificationItem;
import com.gp.mygp.R;
import com.gp.mygp.Response.StatusResponse;
import com.gp.mygp.Service.CallbackWithRetry;
import com.gp.mygp.Service.Injector;
import com.gp.mygp.Service.onRequestFailure;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by Ahmed Naeem on 3/12/2018.
 */

public class NotificationsFragment extends BaseFragment implements NotificationClickListener {

    private View theView;

    @BindView(R.id.recycler)
    RecyclerView recyclerView;

    private NotificationAdapter adapter;

    public static NotificationsFragment getInstance() {
        return new NotificationsFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if(theView == null){
            theView = inflater.inflate(R.layout.fragment_notifications, container, false);
            ButterKnife.bind(this, theView);
            init();
        }
        return theView;
    }

    private void init() {
        showProgress(true);
        adapter = new NotificationAdapter(null, getContext(), this);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);

        getData();
    }

    @Override
    public void onNotificationClicked(NotificationItem notificationItem) {
        DetailsActivity.setInfoItem(notificationItem.getUni());
        Intent i = new Intent(getContext(), DetailsActivity.class);
        startActivity(i);
    }


    private void getData() {
        Call<ArrayList<NotificationItem>> call = Injector.Api().getNotfications( AppController.getInstance().getPrefManager().getUser().getId()+"");
        call.enqueue(new CallbackWithRetry<ArrayList<NotificationItem>>(call, new onRequestFailure() {
            @Override
            public void onFailure() {
                showNoInternet(new ConnectionCallback() {
                    @Override
                    public void onRetry() {
                        getData();
                    }
                });
            }
        }) {
            @Override
            public void onResponse(Call<ArrayList<NotificationItem>> call, Response<ArrayList<NotificationItem>> response) {
                showProgress(false);
                if (response.isSuccessful() && response.body() !=null){
                    adapter.updateData(response.body());
                }
            }
        });
    }

}
