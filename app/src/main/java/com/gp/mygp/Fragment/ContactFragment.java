package com.gp.mygp.Fragment;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gp.mygp.R;

import butterknife.ButterKnife;

/**
 * Created by Ahmed Naeem on 3/12/2018.
 */

public class ContactFragment extends BaseFragment {

    private View theView;

    public static ContactFragment getInstance() {
        return new ContactFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if(theView == null){
            theView = inflater.inflate(R.layout.fragment_contact, container, false);
            ButterKnife.bind(this, theView);
        }
        return theView;
    }
}
