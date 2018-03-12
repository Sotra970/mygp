package com.gp.mygp.Service;


import com.gp.mygp.Model.ApplicationItem;
import com.gp.mygp.Model.NotificationItem;
import com.gp.mygp.Model.SliderItem;
import com.gp.mygp.Model.UniversityInfoItem;
import com.gp.mygp.Model.UserItem;

import java.util.ArrayList;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by Ahmed on 8/29/2017.
 */

public interface ApiInterface {

    @FormUrlEncoded
    @POST("")
    Call<ArrayList<ApplicationItem>> getApplications(@Field("id") int userId);

    @POST("")
    Call<ArrayList<UniversityInfoItem>> getUnis();

    @POST("")
    Call<ArrayList<SliderItem>> getSliders();

    @POST("")
    Call<ResponseBody> updateUserInfo(@Body UserItem userItem);

    @FormUrlEncoded
    @POST("")
    Call<ArrayList<NotificationItem>> getNotifs(@Field("id") int userId);

    @FormUrlEncoded
    @POST("api/login")
    Call<UserItem> login(
            @Field("username") String username ,
            @Field("password") String password,
            @Field("token") String token
    );

    @POST("api/regitser")
    Call<UserItem> register(
            @Body UserItem userModel
    );

    @POST("")
    Call<ArrayList<UniversityInfoItem>> search(
            @Field("query") String query
    );
}
