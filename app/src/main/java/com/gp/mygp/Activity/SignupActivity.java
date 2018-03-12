package com.gp.mygp.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
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

import javax.xml.validation.Validator;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.ResponseBody;
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
       // startActivity(new Intent(getApplicationContext() , HomepageActivity.class));
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
        try {
            usernameS = username.getText().toString();
            emailS = email.getText().toString();
            phoneS = phone.getText().toString();
            passwordS = password.getText().toString();
        }catch (Exception e){
            Log.e("sign_up", e.getMessage() + "");
            return;
        }
        UserItem userItem = new UserItem(-1, usernameS, passwordS, emailS, phoneS, uploadedFile);
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
                    unexpError();
                }
                showProgress(false);
            }
        });
    }


    @OnClick(R.id.attach)
    void attach(){
        pick_File_permission(1, new String[]{"pdf"}, "pdf_file", new onUploadResponse() {
            @Override
            public void onSuccess(PdfUploadResponse pdfUploadResponse, ArrayList<String> paths) {
                attachIcon.setImageResource(R.drawable.ic_check_black_24dp);
                uploadedFile = paths.get(0);
            }

            @Override
            public void onFailure() {
            }
        });
    }

    private boolean validate(){
        boolean b = !Validation.isEditTextEmpty(username, username_layout) &&
                !Validation.isEditTextEmpty(email, email_layout) &&
                !Validation.isEditTextEmpty(phone, phone_layout) &&
                !Validation.isEditTextEmpty(password, password_layout) &&
                !Validation.isEditTextEmpty(confirm_password, confirm_password_layout) &&
                Validation.isEmailValid(email, email_layout) &&
                Validation.validatePhone(phone, phone_layout) &&
                Validation.isPasswordsTheSame(password, confirm_password_layout, confirm_password);
        if(b){
            if(uploadedFile == null){
                Toast.makeText(this, R.string.select_doc, Toast.LENGTH_SHORT).show();
                return false;
            }else{
                return true;
            }
        }else{
            return false;
        }
    }


}
