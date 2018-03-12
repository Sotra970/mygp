package com.gp.mygp.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.gp.mygp.AppController;
import com.gp.mygp.R;

/**
 * Created by Ahmed Naeem on 2/25/2018.
 */

public class SpalshActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Handler ui = new Handler(Looper.getMainLooper());
        if(AppController.getInstance().isUserSigned()){
            ui.postDelayed(
                    new Runnable() {
                        @Override
                        public void run() {
                            startActivity(new Intent(getApplicationContext(), HomepageActivity.class));
                            finish();
                        }
                    }, 3000L
            );
        }else{
            ui.postDelayed(new Runnable() {
                @Override
                public void run() {
                    startActivity(new Intent(getApplicationContext() , LoginActivity.class));
                    finish();
                }
            }, 3000L);
        }
    }
}
