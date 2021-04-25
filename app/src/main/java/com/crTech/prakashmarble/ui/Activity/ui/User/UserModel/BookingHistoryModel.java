package com.crTech.prakashmarble.ui.Activity.ui.User.UserModel;

import com.google.gson.annotations.SerializedName;

public class BookingHistoryModel {
    @SerializedName("prod_name")
    private String prod_name;
    @SerializedName("purchased_date")
    private String purchased_date;
    @SerializedName("paid_amount")
    private String paid_amount;

    public String getProd_name() {
        return prod_name;
    }

    public String getPurchased_date() {
        return purchased_date;
    }

    public String getPaid_amount() {
        return paid_amount;
    }
}
