package com.crTech.prakashmarble.ui.Activity.ui.Category.CategoryModel;

import com.google.gson.annotations.SerializedName;

public class CategoryInput {
    @SerializedName("catID")
    private int catID;

    public void setCatID(int catID) {
        this.catID = catID;
    }
}
