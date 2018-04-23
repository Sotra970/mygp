package com.gp.mygp.Util;

import android.support.design.widget.TextInputLayout;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.util.Patterns;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.gp.mygp.AppController;
import com.gp.mygp.R;


public class Validation {


    static  public boolean isEditTextEmpty(EditText editText){
        if (TextUtils.isEmpty(editText.getText().toString()))
        {
            editText.setError(AppController.getInstance().getApplicationContext().getString(R.string.empty_error));
            editText.requestFocus();
            return true;
        }else
            return false;
    }


    static  public boolean isEditTextEmpty(EditText editText , TextInputLayout textInputLayout){
        if (TextUtils.isEmpty(editText.getText().toString()))
        {
            textInputLayout.setError(AppController.getInstance().getApplicationContext().getString(R.string.empty_error));
            editText.requestFocus();
            return true;
        }
        else{
            textInputLayout.setError(null);
            return false;
        }

    }


    public static boolean isEditTextEmpty(EditText editText, String default_s) {
        if (TextUtils.isEmpty(editText.getText().toString()) && !editText.getText().toString().trim().equals(default_s.trim()))
        {
            editText.setError(AppController.getInstance().getApplicationContext().getString(R.string.empty_error));
            editText.requestFocus();
            return true;
        }else
            return false;
    }

    public static boolean isEditTextEmpty(EditText editText , TextInputLayout textInputLayout, String default_s) {
        if (TextUtils.isEmpty(editText.getText().toString()) && !editText.getText().toString().trim().equals(default_s.trim()))
        {
            textInputLayout.setError(AppController.getInstance().getApplicationContext().getString(R.string.empty_error));
            editText.requestFocus();
            return true;
        }else{
            textInputLayout.setError(null);
            return false;
        }

    }

    public static boolean isTextViewEmpty(TextView editText, String default_s) {
        if (TextUtils.isEmpty(editText.getText().toString()) )
        {
            editText.setError(AppController.getInstance().getApplicationContext().getString(R.string.empty_error));
            editText.requestFocus();
            return true;
        }else if ( editText.getText().toString().trim().equals(default_s.trim())){
            editText.setError(AppController.getInstance().getApplicationContext().getString(R.string.empty_error));
            editText.requestFocus();
            return true;
        }
            return false;
    }

   static  public boolean isSpinnerEmpty(Spinner spinner , String ID) {
        TextView textView = ((TextView) spinner.getChildAt(0));
        Log.e("txtview" , textView.getText().toString());
        if ( ID == null  || ID.equals("0") ){
            textView.setError(AppController.getInstance().getApplicationContext().getString(R.string.empty_error));
            spinner.requestFocus();
            Log.e("err" , "true");
            return true;
        } else {
            textView.setError(null);
        }

        return false;
    }

    static public boolean isName(String name){
        if (!name.matches("^[a-zA-Z]+$"))
            return false;
        else
            return true;
    }

    static public boolean isEmailValid(EditText editText){
        if (!isEditTextEmpty(editText))
        {
            if (!editText.getText().toString().matches("^\\w+([-+.']\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$"))
            {
                editText.setError(AppController.getInstance().getApplicationContext().getString(R.string.email_error));
                editText.requestFocus();
                return true;
            }
            else
                return false;
        }else return true ;
    }



    static public boolean isEmailMatch(EditText editText){
        if (!isEditTextEmpty(editText))
        {
            if (!editText.getText().toString().matches("^\\w+([-+.']\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$"))
            {

                return false;
            }
            else
                return true;
        }else return false ;
    }

    static public boolean isEmailValid(EditText editText , TextInputLayout textInputLayout){
        if (!isEditTextEmpty(editText , textInputLayout))
        {
            if (!editText.getText().toString().matches("^\\w+([-+.']\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$"))
            {
                textInputLayout.setError(AppController.getInstance().getApplicationContext().getString(R.string.email_error));
                editText.requestFocus();
                return true;
            }
            else{
                textInputLayout.setError(null);
                return false;
            }

        }else return true ;
    }


    static public boolean validateLink(EditText editText){
        if (!isEditTextEmpty(editText))
        {
            if (!Patterns.WEB_URL.matcher(editText.getText()).matches())
            {
                editText.setError(AppController.getInstance().getApplicationContext().getString(R.string.link_error));
                editText.requestFocus();
                return true;
            }
            else
                return false;
        }else return true ;
    }

    static public boolean validatePhone(EditText editText){
        if (!isEditTextEmpty(editText))
        {
            String phone  = editText.getText().toString().trim() ;
            boolean start_with_plus_or0  = phone.startsWith("+") || phone.startsWith("0") ;
            phone = phone.substring(1,phone.length()-1);
            Log.e("phone" , phone);
            boolean start_with_11_12_10_15  = phone.startsWith("11") || phone.startsWith("12") || phone.startsWith("10") || phone.startsWith("15") ;
            if (!start_with_plus_or0 || !start_with_11_12_10_15 )
            {
                Log.e("phone" , "notvalid");
                editText.setError(AppController.getInstance().getApplicationContext().getString(R.string.phone_error));
                editText.requestFocus();
                return true;
            }
            else{
                Log.e("phone" , "valid");
                return false;
            }

        }else return true ;
    }

    static public boolean validatePhoneMatch(EditText editText){
        if (!isEditTextEmpty(editText))
        {
            String phone  = editText.getText().toString().trim() ;
            boolean start_with_plus_or0  = phone.startsWith("+") || phone.startsWith("0") ;
            phone = phone.substring(1,phone.length()-1);
            Log.e("phone" , phone);
            boolean start_with_11_12_10_15  = phone.startsWith("11") || phone.startsWith("12") || phone.startsWith("10") || phone.startsWith("15") ;
            if (!start_with_plus_or0 || !start_with_11_12_10_15 )
            {
                Log.e("phone" , "notvalid");
                editText.setError(AppController.getInstance().getApplicationContext().getString(R.string.phone_error));
                editText.requestFocus();
                return false;
            }
            else{
                Log.e("phone" , "valid");
                return true;
            }

        }else return false ;
    }

    static public boolean validatePhone(EditText editText , TextInputLayout textInputLayout){
        if (!isEditTextEmpty(editText))
        {
            if (!Patterns.PHONE.matcher(editText.getText()).matches() )
            {
                Log.e("phone" , "notvalid");
                textInputLayout.setError(AppController.getInstance().getApplicationContext().getString(R.string.phone_error));
                editText.requestFocus();
                return false;
            }
            else{
                Log.e("phone" , "valid");
                textInputLayout.setError(null);
                return true;
            }

        }else return false ;
    }

    public static boolean isGradeValid(EditText grade, TextInputLayout grade_layout) {
        try {
            double gradeS = Double.parseDouble(grade.getText().toString());
            if (!(gradeS > 0 && gradeS < 100))
            {
                Log.e("grade" , "notvalid");
                grade_layout.setError(AppController.getInstance().getApplicationContext().getString(R.string.grade_error));
                grade.requestFocus();
                return true;
            }
            else{
                Log.e("grade" , "valid");
                grade_layout.setError(null);
                return false;
            }
        }catch (Exception e){
            e.printStackTrace();
            return true;
        }
    }

    /*public static boolean validateCheckbox(CheckBox terms_check_bx) {
        if (!terms_check_bx.isChecked()){
            terms_check_bx.setError(AppController.getInstance().getString(R.string.required));
            terms_check_bx.requestFocus();
            return false ;
        }
        return true;
    }*/


    public static class PackageCountTextWatcher implements TextWatcher {
        EditText pack_count_txt ;
         public  PackageCountTextWatcher(EditText pack_count_txt) {
            this.pack_count_txt = pack_count_txt;
        }

        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        public void afterTextChanged(Editable editable) {
                    int count  = Integer.parseInt(pack_count_txt.getText().toString()) ;
                    if (count  <1) pack_count_txt.setText("1");

        }
    }

    static  public boolean isPasswordsTheSame(EditText editText , TextInputLayout textInputLayout , EditText editText2){
        if (!isEditTextEmpty(editText,textInputLayout))
        {
            if (!editText.getText().toString().equals(editText2.getText().toString()))
            {
                textInputLayout.setError(AppController.getInstance().getApplicationContext().getString(R.string.password_not_match));
                textInputLayout.requestFocus();
                return true;
            }
            else
                return false;
        }
        else return true ;

    }


}
