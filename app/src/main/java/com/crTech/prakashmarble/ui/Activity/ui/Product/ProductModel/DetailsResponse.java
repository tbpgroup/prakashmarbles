package com.crTech.prakashmarble.ui.Activity.ui.Product.ProductModel;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class DetailsResponse {
    @SerializedName("error")
    private boolean error;
    @SerializedName("error_code")
    private int error_code;
    @SerializedName("data")
    private ArrayList<DetailsDataModel> data;

    public boolean isError() {
        return error;
    }

    public int getError_code() {
        return error_code;
    }

    public ArrayList<DetailsDataModel> getData() {
        return data;
    }
}
