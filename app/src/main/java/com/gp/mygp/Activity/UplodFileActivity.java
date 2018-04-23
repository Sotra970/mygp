package com.gp.mygp.Activity;


import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.widget.Toast;

import com.gp.mygp.AppController;
import com.gp.mygp.Dialog.MessageActionDialog;
import com.gp.mygp.R;
import com.gp.mygp.Response.PdfUploadResponse;
import com.gp.mygp.Service.Injector;
import com.gp.mygp.Service.ProgressRequestBody;

import java.io.File;
import java.util.ArrayList;

import droidninja.filepicker.FilePickerBuilder;
import droidninja.filepicker.FilePickerConst;
import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.gp.mygp.Service.Config.MY_PERMISSIONS_REQUEST_STORAGE;

public class UplodFileActivity extends LoadingDialogActivity {

    public  static  int MAX_FILE_COUNT = 1 ;
    onUploadResponse onUploadResponse ;
    private String MultipartBodyKey;

    public interface  onUploadResponse{
        void onSuccess(ArrayList<String> web_path_name, ArrayList<String> paths);
        void onFailure();
    }
    String[] ext_arr;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public  void pick_File_permission(int count  , String [] ext_arr , String MultipartBodyKey, onUploadResponse onUploadResponse  ) {
        this.ext_arr = ext_arr;
        this.MultipartBodyKey = MultipartBodyKey;
        this.onUploadResponse = onUploadResponse ;
        MAX_FILE_COUNT = count ;
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED ) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, MY_PERMISSIONS_REQUEST_STORAGE);
        }else {
            pick_File(MAX_FILE_COUNT);
        }
    }





    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        super.onRequestPermissionsResult(requestCode,permissions,grantResults);
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_STORAGE: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    pick_File(MAX_FILE_COUNT);
                } else {
                    Toast.makeText(this," Storage permission id needed  to get into your images ", Toast.LENGTH_SHORT).show();
                }
                return;
            }
        }
    }

    ArrayList<String> filePathsTemp  = new ArrayList<>();
    private void pick_File(int count){
        filePathsTemp = new ArrayList<>() ;
        FilePickerBuilder.getInstance().setMaxCount(count)
                .setSelectedFiles(filePathsTemp)
                //.setActivityTheme(R.style.file_picker_theme)
                .enableDocSupport(false)
                .addFileSupport(getString(R.string.select_doc_text) , ext_arr)
                .pickFile(this);
    }



    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        /// select from gallery section
        super.onActivityResult(requestCode , resultCode , data);
        if (requestCode == FilePickerConst.REQUEST_CODE_DOC && resultCode == this.RESULT_OK && data!=null) {
            filePathsTemp = new ArrayList<>();
            filePathsTemp.addAll(data.getStringArrayListExtra(FilePickerConst.KEY_SELECTED_DOCS));
            prepare_upload_files(filePathsTemp);
        }
    }
    private void prepare_upload_files(ArrayList<String> paths ){

        if (paths.isEmpty())
            return;
        ArrayList<File>  files ;
        files = new ArrayList<>() ;
        for (String path  : paths){
            files.add(new File(path));
        }
        upload_Files(files ,  MAX_FILE_COUNT == 1 );
    }


    int current_body = 1   ;
    private void upload_Files(final ArrayList<File> files_2_upload , final boolean single) {
        Log.e("upload_files", files_2_upload.get(0) + "");
        Log.e("upload_files", files_2_upload.size() + "");
        current_body = 1 ;
        showProgressDialog(getString(R.string.dialog_msg_loading));
        ProgressRequestBody.UploadCallbacks  UploadCallbacks = new ProgressRequestBody.UploadCallbacks() {
            @Override
            public void onProgressUpdate(int percentage) {
                Log.e("OkHttp_upload_exp",percentage +"") ;
                updateProgressDialogMessage(percentage+" %");

            }

            @Override
            public void onError() {
                Log.e("OkHttp_upload_exp","error") ;

            }

            @Override
            public void onFinish() {
                Log.e("OkHttp_upload_exp","finish") ;
            }
        };

        ArrayList< MultipartBody.Part> multipartBodies = new ArrayList<>();
        for (File child : files_2_upload) {
            ProgressRequestBody fileBody = new ProgressRequestBody(child, UploadCallbacks, current_body, files_2_upload.size());
            String child_name = child.getName() ;
            MultipartBody.Part body =
                    MultipartBody.Part.createFormData(
                            MultipartBodyKey,
                            AppController.getInstance().currentDateFormat()+"."+child_name.substring(child_name.lastIndexOf("."),child_name.length()),
                            fileBody
                    );
            multipartBodies.add(body);
            current_body++;
        }
        Call<ArrayList<String>> call = Injector.UploadApi().uploadPdf(
                    multipartBodies
            );
        call.enqueue(new Callback<ArrayList<String>>() {
            @Override
            public void onResponse(Call<ArrayList<String>> call,
                                   Response<ArrayList<String>> response) {
                Log.e("OkHttp_upload_exp", "success");
                if (response.isSuccessful() && response.body()!=null && !response.body().isEmpty() ){
                    onUploadResponse.onSuccess(response.body() , filePathsTemp) ;
                }else {
                  onUploadResponse.onFailure();
                }
                hideProgressDialog();
            }

            @Override
            public void onFailure(Call<ArrayList<String>> call, Throwable t) {
                Log.e("OkHttp_upload_exp"," error"+ t.getMessage()+"  " + t.toString());
                hideProgressDialog();
                onUploadResponse.onFailure();
                showConnectionLoading(MessageActionDialog.RETRY, new MessageActionDialog.ActionClick() {
                    @Override
                    public void onClick(MessageActionDialog messageActionDialog) {
                        if(messageActionDialog != null){
                            messageActionDialog.loading(true);
                        }
                        upload_Files(files_2_upload, single);
                    }
                });

            }
        });
    }


    private ProgressDialog progressDialog;
    protected void showProgressDialog(String message) {
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage(message);
        progressDialog.setCancelable(false);
        progressDialog.show();
    }

    protected void hideProgressDialog() {
        if (progressDialog != null) {
            progressDialog.dismiss();
        }
    }
    void updateProgressDialogMessage(String message){
        progressDialog.setMessage(message);
    }




}
