package com.gp.mygp.Dialog;


import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.gp.mygp.R;


/**
 * Created by Ahmed on 9/17/2016.
 */
public class LoadingDialog extends BaseDialog{


    @NonNull
    public static LoadingDialog getInstance() {
        return new LoadingDialog();
    }

    @Override
    protected View createDialogView() {
        View v = LayoutInflater.from(getContext()).inflate(R.layout.dialog_loading, null , false);
        TextView title = (TextView) v.findViewById(R.id.dialog_header_text_view);
        title.setText(R.string.msg_please_wait);
        return v;
    }
}
