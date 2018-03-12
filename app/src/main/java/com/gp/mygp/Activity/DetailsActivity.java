package com.gp.mygp.Activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.gp.mygp.Adapter.FacultyAdapter;
import com.gp.mygp.Model.FacultyItem;
import com.gp.mygp.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Ahmed Naeem on 3/9/2018.
 */

public class DetailsActivity extends AppCompatActivity {

    @BindView(R.id.recycler)
    RecyclerView recyclerView;

    private FacultyAdapter adapter;
    private static int uniId;

    public static void setUniversityId(int universityId) {
        DetailsActivity.uniId = universityId;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        ButterKnife.bind(this);
        initData();
    }

    private void initData() {
        ArrayList<FacultyItem> facultyItems = new ArrayList<>();
        String[] dep = new String[]{"Computer Science", "Engineering", "Business"};
        facultyItems.add(new FacultyItem("dfdsfdff", 90, 5_000, new ArrayList<String>(Arrays.asList(dep))));
        facultyItems.add(new FacultyItem("dfdsfdff", 90, 5_000, new ArrayList<String>(Arrays.asList(dep))));
        facultyItems.add(new FacultyItem("dfdsfdff", 90, 5_000, new ArrayList<String>(Arrays.asList(dep))));
        facultyItems.add(new FacultyItem("dfdsfdff", 90, 5_000, new ArrayList<String>(Arrays.asList(dep))));
        adapter = new FacultyAdapter(facultyItems, this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }
}
