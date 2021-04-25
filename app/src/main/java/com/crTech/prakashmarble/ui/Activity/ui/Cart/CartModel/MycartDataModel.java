package com.crTech.prakashmarble.ui.Activity.ui.Cart.CartModel;

import com.google.gson.annotations.SerializedName;

public class MycartDataModel {
    @SerializedName("prodCartID")
    private String prodCartID;
    @SerializedName("prodID")
    private String prodID;
    @SerializedName("prod_name")
    private String prod_name;
    @SerializedName("image")
    private String image;
    @SerializedName("price")
    private String price;
    @SerializedName("min_price")
    private String min_price;
    @SerializedName("mrp")
    private String mrp;
    @SerializedName("quantity")
    private String quantity;
    @SerializedName("total_price")
    private String total_price;
    @SerializedName("grand_total")
    private int grand_total;

    public String getProdCartID() {
        return prodCartID;
    }

    public String getProdID() {
        return prodID;
    }

    public String getProd_name() {
        return prod_name;
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

    public String getQuantity() {
        return quantity;
    }

    public String getTotal_price() {
        return total_price;
    }

    public int getGrand_total() {
        return grand_total;
    }
}
