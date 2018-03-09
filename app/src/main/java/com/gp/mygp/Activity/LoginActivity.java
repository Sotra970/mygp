package com.gp.mygp.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.gp.mygp.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Ahmed Naeem on 2/25/2018.
 */

public class LoginActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
    }


    @OnClick(R.id.signup)
    void signUp (){
        startActivity(new Intent(getApplicationContext()  , SignupActivity.class));
    }
    @OnClick(R.id.login)
    void login (){
        startActivity(new Intent(getApplicationContext()  , HomepageActivity.class));
    }
}
