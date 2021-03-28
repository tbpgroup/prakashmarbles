package com.crTech.prakashmarble.ui.Activity.ui.Category.CategoryModel;

import com.google.gson.annotations.SerializedName;

public class CategoryDataModel {
    @SerializedName("productTypeID")
    private String productTypeID;
    @SerializedName("product_type_name")
    private String product_type_name;

    public String getProductTypeID() {
        return productTypeID;
    }

    public String getProduct_type_name() {
        return product_type_name;
    }
}
