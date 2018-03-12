package com.gp.mygp.Service;

import com.gp.mygp.Response.PdfUploadResponse;

import java.util.ArrayList;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

/**
 * Created by sotra on 5/10/2017.
 */
public interface UploadFileService {


    Call<PdfUploadResponse> uploadPdf(MultipartBody.Part part);
}
