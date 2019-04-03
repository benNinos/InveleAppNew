package com.ninositsolution.inveleapp.login;

import com.google.gson.annotations.SerializedName;

public class LoginRequest {

    @SerializedName("user_name")
    public String user_name;

    @SerializedName("password")
    public String password;

    public LoginRequest(String user_name, String password) {
        this.user_name = user_name;
        this.password = password;
    }
}
