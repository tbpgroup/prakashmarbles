package com.crTech.prakashmarble.ui.Activity.ui.home.ShopsModel;

import com.google.gson.annotations.SerializedName;

public class ShopsListModel {
    @SerializedName("id")
    private String id;
    @SerializedName("store_name")
    private String store_name;
    @SerializedName("store_address")
    private String store_address;

    public String getId() {
        return id;
    }

    public String getStore_name() {
        return store_name;
    }

    public String getStore_address() {
        return store_address;
    }
}
