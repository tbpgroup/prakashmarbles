package com.crTech.prakashmarble.ui.Activity.ui.Cart.CartModel;

import com.google.gson.annotations.SerializedName;

public class CartInputModel {
    @SerializedName("userID")
    private int userID;
    @SerializedName("prodID")
    private int prodID;
    @SerializedName("quantity")
    private String quantity;

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public void setProdID(int prodID) {
        this.prodID = prodID;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }
}
