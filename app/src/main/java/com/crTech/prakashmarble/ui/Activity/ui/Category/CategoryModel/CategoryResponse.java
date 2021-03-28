package com.crTech.prakashmarble.ui.Activity.ui.Category.CategoryModel;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class CategoryResponse {
    @SerializedName("error")
    private boolean error;
    @SerializedName("error_code")
    private int error_code;
    @SerializedName("message")
    private String message;
    @SerializedName("data")
    private ArrayList<CategoryDataModel> data;

    public boolean isError() {
        return error;
    }

    public int getError_code() {
        return error_code;
    }

    public String getMessage() {
        return message;
    }

    public ArrayList<CategoryDataModel> getData() {
        return data;
    }
}
