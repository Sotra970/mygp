package com.gp.mygp.Response;

import com.google.gson.annotations.SerializedName;

/**
 * Created by ahmed on 15/11/2017.
 */

public class PdfUploadResponse {

    @SerializedName("pdf_name")
    private String uploadedPdfName;

    @SerializedName("pdf_path")
    private String path;

    public String getUploadedPdfName() {
        return uploadedPdfName;
    }

    public String getPath() {
        return path;
    }
}
