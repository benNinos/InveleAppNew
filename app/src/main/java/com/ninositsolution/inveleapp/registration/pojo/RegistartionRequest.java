package com.ninositsolution.inveleapp.registration.pojo;

import com.google.gson.annotations.SerializedName;

public class RegistartionRequest {

    @SerializedName("first_name")
    public String first_name;

    @SerializedName("mobile")
    public String mobile;

    @SerializedName("email")
    public String email;

    @SerializedName("password")
    public String password;

    @SerializedName("login_type")
    public String login_type;

    @SerializedName("uid")
    public String uid;

    @SerializedName("device_id")
    public String device_id;

    @SerializedName("device_type")
    public String device_type;

    @SerializedName("is_registration")
    public String Is_registration;

    public RegistartionRequest(String first_name, String mobile, String email, String password, String login_type, String uid, String device_id, String device_type, String Is_registration) {
        this.first_name = first_name;
        this.mobile = mobile;
        this.email = email;
        this.password = password;
        this.login_type = login_type;
        this.uid = uid;
        this.device_id = device_id;
        this.device_type = device_type;
        this.Is_registration = Is_registration;
    }
}
