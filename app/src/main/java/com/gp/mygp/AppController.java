package com.gp.mygp;


import android.app.Application;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.gp.mygp.Service.MyPreferenceManager;

import java.text.SimpleDateFormat;
import java.util.Date;

import static com.gp.mygp.Service.Config.BASE_IMAGE_URL;

/**
 * Created by Ahmed on 8/17/2017.
 */

public class AppController extends Application {

    private static AppController mInstance;
    private MyPreferenceManager pref;


    public static boolean isUserSigned() {
        return mInstance.getPrefManager().getUser() != null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
    }


    public static synchronized AppController getInstance() {
        return mInstance;
    }

    public MyPreferenceManager getPrefManager() {
        if (pref == null) {
            pref = new MyPreferenceManager(this);
        }

        return pref;
    }

    public static boolean hasNetwork ()
    {
        return mInstance.checkIfHasNetwork();
    }

    public static String getImageUrl(String name){
        return BASE_IMAGE_URL + "/" + name;
    }

    public boolean checkIfHasNetwork()
    {
        ConnectivityManager cm = (ConnectivityManager) getSystemService( Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnected();
    }

    public String currentDateFormat() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd HH:mm");
        String  currentTimeStamp = dateFormat.format(new Date());
        return currentTimeStamp;
    }
}
