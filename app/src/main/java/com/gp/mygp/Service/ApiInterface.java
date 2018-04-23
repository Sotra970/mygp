package com.gp.mygp.Service;


import com.gp.mygp.Model.ApplicationItem;
import com.gp.mygp.Model.FacultyItem;
import com.gp.mygp.Model.NotificationItem;
import com.gp.mygp.Model.SliderItem;
import com.gp.mygp.Model.UniversityInfoItem;
import com.gp.mygp.Model.UserItem;
import com.gp.mygp.Response.StatusResponse;

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

    @GET("api/unis")
    Call<ArrayList<UniversityInfoItem>> getUnis();

    @GET("api/slider")
    Call<ArrayList<SliderItem>> getSliders();


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

    @GET("api/noti/{user_id}")
    Call<ArrayList<NotificationItem>> getNotfications(
            @Path("user_id") String user_id
    );


    @GET("api/apps/{user_id}")
    Call<ArrayList<ApplicationItem>> getApps(
            @Path("user_id") String user_id
    );

    @GET("api/checkStatus/{userId}/{uni_id}")
    Call<StatusResponse> checkStatus(
            @Path("userId") int userId,
            @Path("uni_id") int uni_id
    );

    @POST("api/apply")
    Call<StatusResponse> apply(
            @Body ApplyBody applyBody
    );

    @FormUrlEncoded
    @POST("api/attach")
    Call<StatusResponse> attach(
           @Field("user_id") int user_id,
           @Field("uni_id") int uni_id,
           @Field("stmt_link") String stmt_link
    );

    Call<ResponseBody> updateUserInfo(UserItem userItem);
}
