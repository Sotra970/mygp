package com.gp.mygp.Fragment;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.Toast;

import com.gp.mygp.Activity.BaseActivity;
import com.gp.mygp.Callback.ConnectionCallback;
import com.gp.mygp.R;

import butterknife.BindView;

/**
 * Created by Ahmed Naeem on 3/11/2018.
 */

public class BaseFragment extends Fragment {

    protected void showProgress(boolean show){
        try {
            ((BaseActivity) getActivity()).showProgress(show);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    protected void unexpError(){
        try {
            ((BaseActivity) getActivity()).unexpError();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    protected void showNoInternet(final ConnectionCallback connectionCallback){
        try {
            ((BaseActivity) getActivity()).showNoInternet(connectionCallback);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
