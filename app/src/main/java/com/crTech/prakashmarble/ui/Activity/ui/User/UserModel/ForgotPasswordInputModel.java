package com.crTech.prakashmarble.ui.Activity.ui.User.UserModel;

import com.google.gson.annotations.SerializedName;

public class ForgotPasswordInputModel {
    @SerializedName("email")
    private String email;

    public void setEmail(String email) {
        this.email = email;
    }
}
