package com.gp.mygp.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.gp.mygp.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Ahmed Naeem on 2/25/2018.
 */

public class SignupActivity extends AppCompatActivity {

    @BindView(R.id.username)
    TextView username;
    @BindView(R.id.username_layout)
    TextInputLayout username_layout;
    @BindView(R.id.email)
    TextView email;
    @BindView(R.id.email_layout)
    TextInputLayout email_layout;
    @BindView(R.id.password)
    TextView password;
    @BindView(R.id.password_layout)
    TextInputLayout password_layout;
    @BindView(R.id.confirm_password)
    TextView confirm_password;
    @BindView(R.id.confirm_password_layout)
    TextInputLayout confirm_password_layout;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.signup)
    void confirm(){
        startActivity(new Intent(getApplicationContext() , HomepageActivity.class));
    }
}
