package com.gp.mygp.Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.gp.mygp.Model.UserItem;
import com.gp.mygp.R;
import com.gp.mygp.Service.CallbackWithRetry;
import com.gp.mygp.Service.Injector;
import com.gp.mygp.Service.onRequestFailure;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by Ahmed Naeem on 3/12/2018.
 */

public class ProfileFragment extends BaseFragment {

    private View theView;

    @BindView(R.id.username_layout)
    TextInputLayout username_layout;
    @BindView(R.id.username)
    EditText username;
    @BindView(R.id.password_layout)
    TextInputLayout password_layout;
    @BindView(R.id.password)
    EditText password;
    @BindView(R.id.confirm_password_layout)
    TextInputLayout confirm_password_layout;
    @BindView(R.id.confirm_password)
    EditText confirm_password;
    @BindView(R.id.phone_layout)
    TextInputLayout phone_layout;
    @BindView(R.id.phone)
    EditText phone;
    @BindView(R.id.email_layout)
    TextInputLayout email_layout;
    @BindView(R.id.email)
    EditText email;

    public static ProfileFragment getInstance() {
        return new ProfileFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if(theView == null){
            theView = inflater.inflate(R.layout.fragment_profile, container, false);
            ButterKnife.bind(this, theView);
            init();
        }
        return theView;
    }

    private void init() {

    }

    private void validate(){

    }

    private void doUpdate(UserItem userItem){
        Call<ResponseBody> call = Injector.Api().updateUserInfo(userItem);
        call.enqueue(new CallbackWithRetry<ResponseBody>(
                call,
                new onRequestFailure() {
                    @Override
                    public void onFailure() {

                    }
                }
        ) {
            @Override
            public void onResponse(@NonNull Call<ResponseBody> call, @NonNull Response<ResponseBody> response) {
                if(response.isSuccessful()){
                    Toast.makeText(getContext(), R.string.profile_updated, Toast.LENGTH_SHORT).show();
                }else{
                    unexpError();
                }
            }
        });
    }
}
