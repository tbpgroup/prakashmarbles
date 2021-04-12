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
    @SerializedName("price")
    private String price;
    @SerializedName("min_price")
    private String min_price;
    @SerializedName("mrp")
    private String mrp;
    @SerializedName("discount")
    private String discount;

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

    public String getPrice() {
        return price;
    }

    public String getMin_price() {
        return min_price;
    }

    public String getMrp() {
        return mrp;
    }

    public String getDiscount() {
        return discount;
    }
}
