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
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Ahmed on 8/29/2017.
 */

public interface ApiInterface {

    @FormUrlEncoded
    @POST("")
    Call<ArrayList<ApplicationItem>> getApplications(@Field("id") int userId);

    @GET("api/unis")
    Call<ArrayList<UniversityInfoItem>> getUnis();

    @GET("api/slider")
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

    @GET("api/search/{name}")
    Call<ArrayList<UniversityInfoItem>> search(
            @Path("name") String query
    );
}
