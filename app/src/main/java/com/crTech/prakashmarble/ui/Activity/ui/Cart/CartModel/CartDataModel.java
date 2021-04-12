package com.crTech.prakashmarble.ui.Activity.ui.Cart.CartModel;

import com.google.gson.annotations.SerializedName;

public class CartDataModel {
    @SerializedName("prodCartID")
    private int prodCartID;
    @SerializedName("prodID")
    private int prodID;
    @SerializedName("prod_name")
    private String prod_name;
    @SerializedName("mrp")
    private String mrp;
    @SerializedName("quantity")
    private String quantity;
    @SerializedName("total_price")
    private int total_price;

    public int getProdCartID() {
        return prodCartID;
    }

    public int getProdID() {
        return prodID;
    }

    public String getProd_name() {
        return prod_name;
    }

    public String getMrp() {
        return mrp;
    }

    public String getQuantity() {
        return quantity;
    }

    public int getTotal_price() {
        return total_price;
    }
}
