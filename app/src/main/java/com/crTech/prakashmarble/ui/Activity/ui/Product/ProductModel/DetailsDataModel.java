package com.crTech.prakashmarble.ui.Activity.ui.Product.ProductModel;

import com.google.gson.annotations.SerializedName;

public class DetailsDataModel {
    @SerializedName("prodID")
    private String prodID;
    @SerializedName("prod_name")
    private String prod_name;
    @SerializedName("brand")
    private String brand;
    @SerializedName("prod_type")
    private String prod_type;
    @SerializedName("size")
    private String size;
    @SerializedName("product_category")
    private String product_category;
    @SerializedName("prod_description")
    private String prod_description;
    @SerializedName("prod_variant")
    private String prod_variant;
    @SerializedName("stock")
    private String stock;
    @SerializedName("gallery1")
    private String gallery1;
    @SerializedName("gallery2")
    private String gallery2;
    @SerializedName("gallery3")
    private String gallery3;
    @SerializedName("gallery4")
    private String gallery4;
    @SerializedName("gallery5")
    private String gallery5;
    @SerializedName("gallery6")
    private String gallery6;
    @SerializedName("offers")
    private String offers;
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

    public String getProd_type() {
        return prod_type;
    }

    public String getSize() {
        return size;
    }

    public String getProduct_category() {
        return product_category;
    }

    public String getProd_description() {
        return prod_description;
    }

    public String getProd_variant() {
        return prod_variant;
    }

    public String getStock() {
        return stock;
    }

    public String getGallery1() {
        return gallery1;
    }

    public String getGallery2() {
        return gallery2;
    }

    public String getGallery3() {
        return gallery3;
    }

    public String getGallery4() {
        return gallery4;
    }

    public String getGallery5() {
        return gallery5;
    }

    public String getGallery6() {
        return gallery6;
    }

    public String getOffers() {
        return offers;
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
