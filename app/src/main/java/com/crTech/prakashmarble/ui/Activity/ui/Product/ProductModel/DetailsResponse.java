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
    @SerializedName("related_products")
    private ArrayList<DetailsDataModel> related_products;

    public boolean isError() {
        return error;
    }

    public int getError_code() {
        return error_code;
    }

    public ArrayList<DetailsDataModel> getData() {
        return data;
    }

    public ArrayList<DetailsDataModel> getRelated_products() {
        return related_products;
    }
}
