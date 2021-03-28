package com.crTech.prakashmarble.ui.Activity.ui.Product.ProductModel;

import com.google.gson.annotations.SerializedName;

public class ProductDataModel {
    @SerializedName("prodID")
    private String prodID;
    @SerializedName("prod_name")
    private String prod_name;
    @SerializedName("brand")
    private String brand;
    @SerializedName("category_name")
    private String category_name;
    @SerializedName("image")
    private String image;

    public String getProdID() {
        return prodID;
    }

    public String getProd_name() {
        return prod_name;
    }

    public String getBrand() {
        return brand;
    }

    public String getCategory_name() {
        return category_name;
    }

    public String getImage() {
        return image;
    }
}
