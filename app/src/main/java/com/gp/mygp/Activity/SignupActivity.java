package com.gp.mygp.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.text.TextUtils;
import android.util.Log;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.gp.mygp.AppController;
import com.gp.mygp.Callback.ConnectionCallback;
import com.gp.mygp.Model.UserItem;
import com.gp.mygp.R;
import com.gp.mygp.Response.PdfUploadResponse;
import com.gp.mygp.Service.CallbackWithRetry;
import com.gp.mygp.Service.Injector;
import com.gp.mygp.Service.onRequestFailure;
import com.gp.mygp.Util.Validation;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by Ahmed Naeem on 2/25/2018.
 */

public class SignupActivity extends UplodFileActivity {

    @BindView(R.id.username)
    EditText username;
    @BindView(R.id.username_layout)
    TextInputLayout username_layout;
    @BindView(R.id.email)
    EditText email;
    @BindView(R.id.email_layout)
    TextInputLayout email_layout;
    @BindView(R.id.phone)
    EditText phone;
    @BindView(R.id.phone_layout)
    TextInputLayout phone_layout;
    @BindView(R.id.grade)
    EditText grade;
    @BindView(R.id.grade_layout)
    TextInputLayout grade_layout;
    @BindView(R.id.password)
    EditText password;
    @BindView(R.id.password_layout)
    TextInputLayout password_layout;
    @BindView(R.id.confirm_password)
    EditText confirm_password;
    @BindView(R.id.confirm_password_layout)
    TextInputLayout confirm_password_layout;
    @BindView(R.id.attach_icon)
    ImageView attachIcon;
    @BindView(R.id.attach_text)
    TextView attachText;

    private String uploadedFile;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.signup)
    void confirm(){
        if(validate()){
            doSignup();
        }
    }

    private void doSignup() {
        showProgress(true);
        String usernameS;
        String emailS;
        String passwordS;
        String phoneS;
        double gradeS;
        try {
            usernameS = username.getText().toString();
            emailS = email.getText().toString();
            phoneS = phone.getText().toString();
            passwordS = password.getText().toString();
            gradeS = Double.parseDouble(grade.getText().toString());
        }catch (Exception e){
            Log.e("sign_up", e.getMessage() + "");
            return;
        }
        UserItem userItem = new UserItem(-1, usernameS, passwordS, emailS, phoneS, uploadedFile, gradeS);
        Call<UserItem> call = Injector.Api().register(userItem);
        call.enqueue(new CallbackWithRetry<UserItem>(
                call,
                new onRequestFailure() {
                    @Override
                    public void onFailure() {
                        showNoInternet(new ConnectionCallback() {
                            @Override
                            public void onRetry() {
                                confirm();
                            }
                        });
                    }
                }
        ) {
            @Override
            public void onResponse(@NonNull Call<UserItem> call, @NonNull Response<UserItem> response) {
                if(response.isSuccessful()){
                    Toast.makeText(SignupActivity.this, R.string.account_created, Toast.LENGTH_SHORT).show();
                    AppController.getInstance().getPrefManager().storeUser(response.body());
                    startActivity(new Intent(getApplicationContext() , HomepageActivity.class));
                    finish();
                }else{
                    switch (response.code()){
                        case 409 : showToast(getApplicationContext() , "duplicated email");break;
                        case 410 : showToast(getApplicationContext() , "duplicated phone");break;
                    }
                }
                showProgress(false);
            }
        });
    }


    @OnClick(R.id.attach)
    void attach(){
        pick_File_permission(1, new String[]{"pdf"}, "file[]", new onUploadResponse() {
            @Override
            public void onSuccess(ArrayList<String> web_path_names, ArrayList<String> paths) {
                attachIcon.setImageResource(R.drawable.ic_check_black_24dp);
                uploadedFile = web_path_names.get(0);
                Log.e("attach" , "onSuccess"  + uploadedFile );
            }

            @Override
            public void onFailure() {
                    showProgress(false);
            }
        });
    }

    private boolean validate(){
        boolean b = !Validation.isEditTextEmpty(username, username_layout) &&
                !Validation.isEditTextEmpty(email, email_layout) &&
                !Validation.isEditTextEmpty(phone, phone_layout) &&
                !Validation.isEditTextEmpty(password, password_layout) &&
                !Validation.isEditTextEmpty(confirm_password, confirm_password_layout) &&
                !Validation.isEmailValid(email, email_layout) &&
                !Validation.isPasswordsTheSame(confirm_password, confirm_password_layout, password) &&
                !Validation.isGradeValid(grade, grade_layout)&&
                !TextUtils.isEmpty(uploadedFile);

        if (TextUtils.isEmpty(uploadedFile)){
            showToast(this , "upload ur application pdf ");
        }
        return b;
    }


}
