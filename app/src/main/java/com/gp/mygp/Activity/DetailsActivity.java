package com.gp.mygp.Activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.gp.mygp.Adapter.FacultyAdapter;
import com.gp.mygp.AppController;
import com.gp.mygp.Model.UniversityInfoItem;
import com.gp.mygp.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Ahmed Naeem on 3/9/2018.
 */

public class DetailsActivity extends AppCompatActivity {

    @BindView(R.id.recycler)
    RecyclerView recyclerView;

    private FacultyAdapter adapter;

    private static UniversityInfoItem infoItem;

    public static void setInfoItem(UniversityInfoItem infoItem) {
        DetailsActivity.infoItem = infoItem;
    }

    @BindView(R.id.desc)
    TextView desc;
    @BindView(R.id.logo)
    ImageView logo;
    @BindView(R.id.min_grades)
    TextView min_grades;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.cover)
    ImageView cover;
    @BindView(R.id.phone)
    TextView phone;
    @BindView(R.id.email)
    TextView email;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        if (infoItem != null) {
            title.setText(infoItem.getTitle());
            String min = getString(R.string.from) + " " + infoItem.getMinPerc() + "%";
            min_grades.setText(min);
            Glide.with(this)
                    .load(AppController.getImageUrl(infoItem.getLogo()))
                    .into(logo);
            Glide.with(this)
                    .load(AppController.getImageUrl(infoItem.getCover()))
                    .into(cover);
            desc.setText(infoItem.getDesc());

            phone.setText(infoItem.getPhone());
            email.setText(infoItem.getEmail());

            adapter = new FacultyAdapter(infoItem.getFacultyItems(), this);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            recyclerView.setAdapter(adapter);
        }
    }


    @Override
    protected void onStart() {
        super.onStart();
    }

    @OnClick(R.id.call)
    void call() {
        if (infoItem != null) {
            /*Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + infoItem.getPhone()));
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return;
            }
            startActivity(intent);*/
            Intent intent = new Intent(Intent.ACTION_DIAL);
            intent.setData(Uri.parse("tel:" + infoItem.getPhone()));
            startActivity(intent);
        }
    }

    @OnClick(R.id.compose)
    void compose(){
        if(infoItem != null){
            Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                    "mailto",infoItem.getEmail(), null));
            emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Contact");
            emailIntent.putExtra(Intent.EXTRA_TEXT, "Body");
            startActivity(Intent.createChooser(emailIntent, "Send email..."));
        }
    }
}
