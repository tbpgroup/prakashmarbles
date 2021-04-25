package com.crTech.prakashmarble.ui.Activity.ui.home.ShopsModel;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class ShopResponseModel {
    @SerializedName("error")
    private boolean error;
    @SerializedName("error_code")
    private int error_code;
    @SerializedName("message")
    private String message;
    @SerializedName("data")
    private ArrayList<ShopsListModel> data;

    public boolean isError() {
        return error;
    }

    public int getError_code() {
        return error_code;
    }

    public String getMessage() {
        return message;
    }

    public ArrayList<ShopsListModel> getData() {
        return data;
    }
}
