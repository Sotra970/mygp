package com.gp.mygp.Activity;

import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.gp.mygp.Callback.ConnectionCallback;
import com.gp.mygp.R;

import butterknife.BindView;

/**
 * Created by Ahmed Naeem on 3/12/2018.
 */

public abstract class BaseActivity extends AppCompatActivity {

    @Nullable
    @BindView(R.id.loading)
    View loading;
    @Nullable
    @BindView(R.id.no_internet)
    View noInternet;
    @Nullable
    @BindView(R.id.retry)
    View retry;
    @Nullable
    @BindView(R.id.no_data)
    View noData;

    public void showProgress(boolean show){
        if(loading != null){
            loading.setVisibility(show ? View.VISIBLE : View.GONE);
        }
    }

    public void unexpError(){
        Toast.makeText(this, R.string.unexpected_error, Toast.LENGTH_SHORT).show();
    }

    public void showNoInternet(final ConnectionCallback connectionCallback){
        if(noInternet != null && retry != null && connectionCallback != null){
            showProgress(false);
            noInternet.setVisibility(View.VISIBLE);
            retry.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    connectionCallback.onRetry();
                    showProgress(true);
                    hideNoInternet();
                }
            });
        }
    }

    private void hideNoInternet(){
        if(noInternet != null ){
            noInternet.setVisibility(View.GONE);
        }
    }

    public void showNoData(boolean show){
        if(noData != null ){
            noData.setVisibility(show ? View.VISIBLE : View.GONE);
        }
    }
}
