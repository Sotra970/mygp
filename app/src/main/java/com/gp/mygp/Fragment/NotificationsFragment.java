package com.gp.mygp.Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gp.mygp.Adapter.NotificationAdapter;
import com.gp.mygp.Callback.NotificationClickListener;
import com.gp.mygp.Model.NotificationItem;
import com.gp.mygp.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

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
        adapter = new NotificationAdapter(null, getContext(), this);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);

        ArrayList<NotificationItem> n = new ArrayList<>();
        n.add(new NotificationItem("Helwan University", "12 nov 2012", getString(R.string.lorem)));
        n.add(new NotificationItem("Helwan University", "12 nov 2012", getString(R.string.lorem)));
        n.add(new NotificationItem("Helwan University", "12 nov 2012", getString(R.string.lorem)));
        n.add(new NotificationItem("Helwan University", "12 nov 2012", getString(R.string.lorem)));
        adapter.updateData(n);
    }

    @Override
    public void onNotificationClicked(NotificationItem notificationItem) {

    }
}
