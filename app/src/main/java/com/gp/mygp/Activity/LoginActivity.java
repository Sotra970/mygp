package com.gp.mygp.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import com.gp.mygp.AppController;
import com.gp.mygp.Callback.ConnectionCallback;
import com.gp.mygp.Model.UserItem;
import com.gp.mygp.R;
import com.gp.mygp.Service.CallbackWithRetry;
import com.gp.mygp.Service.Injector;
import com.gp.mygp.Service.onRequestFailure;
import com.gp.mygp.Util.Validation;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by Ahmed Naeem on 2/25/2018.
 */

public class LoginActivity extends BaseActivity {

    @BindView(R.id.username)
    EditText username;
    @BindView(R.id.username_layout)
    TextInputLayout username_layout;
    @BindView(R.id.password)
    EditText password;
    @BindView(R.id.password_layout)
    TextInputLayout password_layout;

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
        if(validate()){
            doLogin();
        }
    }

    private void doLogin() {
        showProgress(true);
        String usernameS;
        String passwordS;
        String tokenS;
        try {
            usernameS = username.getText().toString();
            passwordS = password.getText().toString();
            tokenS = "TEMP";
        }catch (Exception e){
            Log.e("login", e.getMessage() + "");
            return;
        }
        Call<UserItem> call = Injector.Api().login(usernameS, passwordS, tokenS);
        call.enqueue(new CallbackWithRetry<UserItem>(
                call,
                new onRequestFailure() {
                    @Override
                    public void onFailure() {
                        showNoInternet(new ConnectionCallback() {
                            @Override
                            public void onRetry() {
                                login();
                            }
                        });
                    }
                }
        ) {
            @Override
            public void onResponse(@NonNull Call<UserItem> call, @NonNull Response<UserItem> response) {
                showProgress(false);
                if(response.isSuccessful()){
                    AppController.getInstance().getPrefManager().storeUser(response.body());
                    startActivity(new Intent(getApplicationContext() , HomepageActivity.class));
                    finish();
                }else{
                    loginError(response.code());
                }
            }
        });
    }


    public void loginError(int code){
        if (code == 403)
            Toast.makeText(this, R.string.wrong_password, Toast.LENGTH_SHORT).show();
        if (code == 404)
            Toast.makeText(this, R.string.wrong_username, Toast.LENGTH_SHORT).show();
    }

    private boolean validate(){
        return !Validation.isEditTextEmpty(username, username_layout) &&
                !Validation.isEditTextEmpty(password, password_layout);
    }

}
