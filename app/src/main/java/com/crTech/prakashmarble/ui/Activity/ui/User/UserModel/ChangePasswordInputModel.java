package com.crTech.prakashmarble.ui.Activity.ui.User.UserModel;

import com.google.gson.annotations.SerializedName;

public class ChangePasswordInputModel {
    @SerializedName("userID")
    private String userID;
    @SerializedName("oldPass")
    private String oldPass;
    @SerializedName("newPass")
    private String newPass;

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public void setOldPass(String oldPass) {
        this.oldPass = oldPass;
    }

    public void setNewPass(String newPass) {
        this.newPass = newPass;
    }
}
