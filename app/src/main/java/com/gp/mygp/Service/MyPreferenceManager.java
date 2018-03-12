package com.gp.mygp.Service;


import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;

import com.gp.mygp.Activity.SpalshActivity;
import com.gp.mygp.Model.UserItem;

public class MyPreferenceManager {

    private String TAG = MyPreferenceManager.class.getSimpleName();

    // Shared Preferences
    SharedPreferences pref;

    // Editor for Shared preferences
    SharedPreferences.Editor editor;

    // Context
    Context _context;

    // Shared pref mode
    int PRIVATE_MODE = 0;

    // Sharedpref file name
    private static final String PREF_NAME = "ezzay";





    // All Shared Preferences Keys
    private static final String KEY_USER_ID = "user_id";
    private static final String KEY_NAME= "name";
    private static final String KEY_USER_NAME ="user_name";
    private static final String KEY_USER_PHONE = "user_phone";
    private static final String KEY_USER_IMG = "user_img";
    private static final String KEY_USER_email = "user_email";
    private static final String KEY_USER_COUNTRY_ID = "state_id";
    private static final String KEY_USER_city_ID = "city_id";
    private static final String KEY_USER_ADDRESS = "address";
    private static final String KEY_USER_COMPANY = "company";
    private static final String KEY_USER_WORK_FIELD = "work_field";
    private static final String KEY_USER_CODE = "code";


    public static final String KEY_INCREMENT_NOTFICATiON = "KEY_INCREMENT_NOTFICATiON";




    // Constructor
    public MyPreferenceManager(Context context) {
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }


    public void storeUser(UserItem user) {
        editor.clear();
        editor.commit();

        editor.putInt(KEY_USER_ID, user.getId());
        editor.putString(KEY_USER_NAME, user.getUsername());
        editor.putString(KEY_USER_PHONE , user.getPhone());
        editor.putString(KEY_USER_email , user.getEmail());
        editor.commit();


        Log.e(TAG, "User is stored in shared preferences. " + user.getUsername() +"--"  );
    }

    public UserItem getUser() {
        if (pref.getInt(KEY_USER_ID, 0) != 0) {

             int ID;
             String UserName;
             String Phone;
             String Email;

            ID = pref.getInt(KEY_USER_ID, 0);
            UserName = pref.getString(KEY_USER_NAME, " ");
            Phone  = pref.getString(KEY_USER_PHONE, " ");
            Email  = pref.getString(KEY_USER_email   ," ");

            UserItem user = new UserItem();
            user.setId(ID);
            user.setUsername(UserName);
            user.setPhone(Phone);
            user.setEmail(Email);

            return user;
        }
        return null;
    }


    public void clear(boolean restart) {
        editor.clear();
        editor.commit();
        if (!restart)return;
        Intent intent = new Intent(_context, SpalshActivity.class);
        ComponentName cn = intent.getComponent();
        Intent mainIntent = Intent.makeRestartActivityTask(cn);
        _context.startActivity(mainIntent);
    }



    public int  get_notfication() {
        int prev = pref.getInt(KEY_INCREMENT_NOTFICATiON, 0);
        return prev;
    }



    public void INCREMENT_NOTFICATiON() {
        int prev = pref.getInt(KEY_INCREMENT_NOTFICATiON, 0);
        prev++ ;
        editor.putInt(KEY_INCREMENT_NOTFICATiON , prev) ;
        editor.commit();
    }

    public void CLEAR_NOTFICATiON() {
        editor.putInt(KEY_INCREMENT_NOTFICATiON , 0) ;
        editor.commit();
    }

    /*public void updateUser(UserModel user) {
        if (user.getName()!=null && !user.getName().isEmpty())
        editor.putString(KEY_NAME, user.getName());
        if (user.getUserName()!=null &&!user.getUserName().isEmpty())
        editor.putString(KEY_USER_NAME, user.getUserName());
        if (user.getMobile()!=null &&!user.getMobile().isEmpty())
        editor.putString(KEY_USER_PHONE , user.getMobile());
        if (user.getEmail()!=null &&!user.getEmail().isEmpty())
        editor.putString(KEY_USER_email , user.getEmail());
        if (user.getImage()!=null &&!user.getImage().isEmpty())
        editor.putString(KEY_USER_IMG , user.getImage());
        if (user.getCountryId()!=null &&user.getCountryId()!= null && user.getCountryId()!=0 )
        editor.putInt(KEY_USER_COUNTRY_ID, user.getCountryId());
        if (user.getCityId()!= null && user.getCityId()!=0 )
        editor.putInt(KEY_USER_city_ID, user.getCityId());
        if (user.getAddress()!=null &&!user.getAddress().isEmpty())
        editor.putString(KEY_USER_ADDRESS , user.getAddress());
        if (user.getCompany()!=null &&!user.getCompany().isEmpty())
        editor.putString(KEY_USER_COMPANY, user.getCompany());
        if (user.getWorkField()!=null &&!user.getWorkField().isEmpty())
        editor.putString(KEY_USER_WORK_FIELD, user.getWorkField());

        editor.commit();
    }*/


}
