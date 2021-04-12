package com.crTech.prakashmarble.ui.Activity.ui.Product.ProductModel;

import com.google.gson.annotations.SerializedName;

public class ProductInputModel {
    @SerializedName("productTypeID")
    private int productTypeID;

    @SerializedName("category_name")
    private String category_name;

    public void setProductTypeID(int productTypeID) {
        this.productTypeID = productTypeID;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }
}
