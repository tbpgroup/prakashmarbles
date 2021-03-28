package com.crTech.prakashmarble.ui.Activity.ui.Product.ProductModel;

import com.google.gson.annotations.SerializedName;

public class ProductInputModel {
    @SerializedName("productTypeID")
    private int productTypeID;

    public void setProductTypeID(int productTypeID) {
        this.productTypeID = productTypeID;
    }
}
