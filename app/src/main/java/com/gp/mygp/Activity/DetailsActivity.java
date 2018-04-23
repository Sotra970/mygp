package com.gp.mygp.Activity;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.gp.mygp.Adapter.FacultyAdapter;
import com.gp.mygp.AppController;
import com.gp.mygp.Callback.ConnectionCallback;
import com.gp.mygp.Model.FacultyItem;
import com.gp.mygp.Model.UniversityInfoItem;
import com.gp.mygp.R;
import com.gp.mygp.Response.StatusResponse;
import com.gp.mygp.Service.ApplyBody;
import com.gp.mygp.Service.CallbackWithRetry;
import com.gp.mygp.Service.Config;
import com.gp.mygp.Service.Injector;
import com.gp.mygp.Service.onRequestFailure;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by Ahmed Naeem on 3/9/2018.
 */

public class DetailsActivity extends UplodFileActivity {

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

    @BindView(R.id.apply)
    View apply ;

    @BindView(R.id.waiting_for_response)
    View waiting_for_response ;

    @BindView(R.id.waiting_list)
    View waiting_list ;


    @BindView(R.id.attach_bank_stmt)
    View attach ;


    @BindView(R.id.rejected)
    View rejected ;



    @BindView(R.id.accepted)
    View accepted ;

    @BindView(R.id.toolbar)
    android.support.v7.widget.Toolbar toolbar ;

    @BindView(R.id.collabsing_toolbar)
    CollapsingToolbarLayout collabsing_toolbar ;

    @BindView(R.id.appbar)
    AppBarLayout  appbar ;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        ButterKnife.bind(this);
        getStatus();
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

            appbar.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
                boolean isShow = true;
                int scrollRange = -1;

                @Override
                public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                    if (scrollRange == -1) {
                        scrollRange = appBarLayout.getTotalScrollRange();
                    }
                    if (scrollRange + verticalOffset == 0) {
                        collabsing_toolbar.setTitle(infoItem.getTitle());
                        isShow = true;
                    } else if(isShow) {
                        collabsing_toolbar.setTitle(" ");//carefull there should a space between double quote otherwise it wont work
                        isShow = false;
                    }
                }
            });
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



    private void showStatus(int wich) {
        switch (wich){

            case Config.APP : {
                hideAllStats();
                apply.setVisibility(View.VISIBLE);
                break;
            }
            case Config.ACC : {
                hideAllStats();
                accepted.setVisibility(View.VISIBLE);
                break;
            }

            case Config.REJ : {
                hideAllStats();
                rejected.setVisibility(View.VISIBLE);
                break;
            }

            case Config.WL : {
                hideAllStats();
                waiting_list.setVisibility(View.VISIBLE);
                break;
            }


            case Config.WR : {
                hideAllStats();
                waiting_for_response.setVisibility(View.VISIBLE);
                break;
            }

            case Config.ATT : {
                hideAllStats();
                attach.setVisibility(View.VISIBLE);
                break;
            }

        }
    }


    int check_item_indx= -1 ;
    FacultyItem check_item;
    @OnClick(R.id.apply)
    void apply (){

        if(AppController.getInstance().getPrefManager().getUser().getGrade() < infoItem.getMinPerc()){
            showToast(this , "you can't apply for this faculty ");
        }
        CharSequence[] items = new CharSequence[infoItem.getFacultyItems().size()];
        int i = 0 ;
        for (FacultyItem facultyItem : infoItem.getFacultyItems()){
            items[i] = facultyItem.getTitle() ;
            i++;
        }

       new AlertDialog.Builder(this)
                .setTitle("Select your faculty")
                .setSingleChoiceItems( items , check_item_indx, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        check_item = infoItem.getFacultyItems().get(i);
                        check_item_indx = i ;
                        if(AppController.getInstance().getPrefManager().getUser().getGrade() < check_item.getGrade()){
                            showToast(DetailsActivity.this , "you can't apply for this faculty ");
                            check_item_indx = -1  ;
                            ((AlertDialog) dialogInterface).getListView().setItemChecked(i, false);

                        }
                    }
                })
                .setPositiveButton("Apply", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (check_item_indx == -1){
                            showToast(DetailsActivity.this , "please select faculty to apply");
                        }else if (check_item !=null){
                            applyApi(check_item);
                        }
                    }
                }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                }).create().show();


    }



    private void hideAllStats() {
        apply.setVisibility(View.GONE);
        attach.setVisibility(View.GONE);
        accepted.setVisibility(View.GONE);
        rejected.setVisibility(View.GONE);
        waiting_for_response.setVisibility(View.GONE);
        waiting_list.setVisibility(View.GONE);
    }



    StatusResponse statusResponse ;

    void  getStatus(){
        showProgress(true);
        Call<StatusResponse> call = Injector.Api().checkStatus(AppController.getInstance().getPrefManager().getUser().getId() , infoItem.getId());
        call.enqueue(new CallbackWithRetry<StatusResponse>(call, new onRequestFailure() {
            @Override
            public void onFailure() {
                showNoInternet(new ConnectionCallback() {
                    @Override
                    public void onRetry() {
                        getStatus();
                    }
                });
            }
        }) {
            @Override
            public void onResponse(Call<StatusResponse> call, Response<StatusResponse> response) {
                showProgress(false);
                if (response.isSuccessful() && response.body() !=null ){
                    showStatus(response.body().getStatus());
                    statusResponse  = response.body()  ;
                }else {
                    showStatus(Config.APP);
                }
            }
        });
    }
    private void applyApi(final FacultyItem facultyItem) {
        showProgress(true);
        Call<StatusResponse> call = Injector.Api().apply(new ApplyBody(facultyItem , AppController.getInstance().getPrefManager().getUser().getId() , infoItem.getId()));
        call.enqueue(new CallbackWithRetry<StatusResponse>(call, new onRequestFailure() {
            @Override
            public void onFailure() {
                showNoInternet(new ConnectionCallback() {
                    @Override
                    public void onRetry() {
                        applyApi(facultyItem);
                    }
                });
            }
        }) {
            @Override
            public void onResponse(Call<StatusResponse> call, Response<StatusResponse> response) {
                showProgress(false);
                if (response.isSuccessful()){
                    showToast(DetailsActivity.this , "your request has been sent");
                    statusResponse  = response.body()  ;
                    showStatus(statusResponse.getStatus());
                }
            }
        });
    }

    String stmt_link = null ;


    @OnClick(R.id.attach_bank_stmt)
    void attach(){
        pick_File_permission(1, new String[]{"pdf"}, "file[]", new UplodFileActivity.onUploadResponse() {
            @Override
            public void onSuccess(ArrayList<String> web_path_names, ArrayList<String> paths) {
                if (web_path_names!=null && !web_path_names.isEmpty()){
                    stmt_link = web_path_names.get(0);
                    attachApi();
                }
                Log.e("attach" , "onSuccess"  + stmt_link );

            }

            @Override
            public void onFailure() {
                showProgress(false);
            }
        });
    }


    private void attachApi() {
        if (TextUtils.isEmpty(stmt_link)){
            showToast(this , "you have to upload your bank statement link ");
        }
        if (statusResponse == null){
            return;
        }
        showProgress(true);
        Call<StatusResponse> call = Injector.Api().attach( AppController.getInstance().getPrefManager().getUser().getId() , infoItem.getId() , stmt_link);
        call.enqueue(new CallbackWithRetry<StatusResponse>(call, new onRequestFailure() {
            @Override
            public void onFailure() {
                showNoInternet(new ConnectionCallback() {
                    @Override
                    public void onRetry() {
                        attachApi();
                    }
                });
            }
        }) {
            @Override
            public void onResponse(Call<StatusResponse> call, Response<StatusResponse> response) {
                showProgress(false);
                if (response.isSuccessful()){
                    showToast(DetailsActivity.this , "your bank statment has been sent");
                    statusResponse  = response.body()  ;
                    showStatus(statusResponse.getStatus());
                }
            }
        });
    }




}
