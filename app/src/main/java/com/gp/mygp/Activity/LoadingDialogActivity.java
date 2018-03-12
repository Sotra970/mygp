package com.gp.mygp.Activity;


import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;

import com.gp.mygp.Dialog.LoadingDialog;
import com.gp.mygp.Dialog.MessageActionDialog;
import com.gp.mygp.R;

/**
 * Created by Ahmed on 11/2/2017.
 */

public class LoadingDialogActivity extends BaseActivity {


    private LoadingDialog loadingDialog;
    private MessageActionDialog messageActionDialog;
    private  final  boolean DEBUG = false ;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void showLoadingDialog(){
        if(loadingDialog == null){
            loadingDialog = LoadingDialog.getInstance();
            loadingDialog.setCancelable(DEBUG);
        }
        if(!loadingDialog.isAdded()) {
            runOnUiThread(
                    new Runnable() {
                        @Override
                        public void run() {
                            try {
                                loadingDialog.show(getSupportFragmentManager(), "loading");
                            }
                            catch (Exception e){}
                        }
                    }
            );
        }
    }


    public void showActionDialog(String title  , String action  , final  boolean loading , final MessageActionDialog.ActionClick actionClick){
        hideAllDialogs();
        messageActionDialog = null ;
        if(messageActionDialog == null){
            messageActionDialog = MessageActionDialog.getInstance(title, action , loading, actionClick);
            messageActionDialog.setCancelable(DEBUG);
        }
        if(!messageActionDialog.isAdded()) {
            runOnUiThread(
                    new Runnable() {
                        @Override
                        public void run() {
                            messageActionDialog.show(getSupportFragmentManager(), "action_dialog");
                        }
                    }
            );
        }
    }
    public void hideActionDialog(){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if(messageActionDialog != null) {
                    messageActionDialog.dismissAllowingStateLoss();
                    messageActionDialog = null ;
                }
            }
        });
    }

    private void showNoConnectionDialog(MessageActionDialog.ActionClick actionClick , final boolean loading){
        if(messageActionDialog == null){
            messageActionDialog = MessageActionDialog.getInstance( getString(R.string.msg_no_internet)  ,  getString(R.string.retry)  , actionClick);
            messageActionDialog.setCancelable(DEBUG);
        }

        runOnUiThread(
                new Runnable() {
                    @Override
                    public void run() {
                        try {
                            if(!messageActionDialog.isAdded()) {
                                messageActionDialog.show(getSupportFragmentManager(), null);
                                new Handler().postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        if (!loading) messageActionDialog.loading(false);
                                    }
                                } , 300) ;
                            }else {
                                messageActionDialog.loading(true);
                            }
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                }
        );

    }

    public void  showConnectionLoading(int SWITCH_CASE){

        switch (SWITCH_CASE){
            case MessageActionDialog.SHOW : {
                showNoConnectionDialog(null, true);
                break;
            }

            case MessageActionDialog.HIDE : {
                hideActionDialog();
                break;
            }
        }


    }

    public void  showConnectionLoading(int SWITCH_CASE, MessageActionDialog.ActionClick actionClick){

       switch (SWITCH_CASE){
           case MessageActionDialog.SHOW : {
               showNoConnectionDialog(actionClick , true);
               break;
           }
           case MessageActionDialog.RETRY : {
               try {
                   if (messageActionDialog != null && messageActionDialog.isAdded()){
                       messageActionDialog.setActionClick(actionClick);
                       messageActionDialog.loading(false);
                   }else {
                       showNoConnectionDialog(actionClick , false);
                   }
               }
               catch (Exception ignored){

               }
               break;
           }
           case MessageActionDialog.HIDE : {
               hideActionDialog();
               break;
           }
       }
    }

    public  void hideAllDialogs(){
        hideActionDialog();
        hideLoadingDialog();
    }

    public void hideLoadingDialog(){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if(loadingDialog != null) {
                    loadingDialog.dismissAllowingStateLoss();
                    loadingDialog = null;
                }

                if(dialogFragment != null) {
                    dialogFragment.dismissAllowingStateLoss();
                    dialogFragment = null;
                }
            }
        });
    }

    public void  showLoading(boolean show){
        if (show)showLoadingDialog();
        else  hideLoadingDialog();

    }


    DialogFragment dialogFragment ;
    public void showFragment(DialogFragment fragment , String tag){
        dialogFragment = fragment;
        try {
            fragment.show(getSupportFragmentManager() , tag);
        }
        catch (Exception e){}
    }
}
