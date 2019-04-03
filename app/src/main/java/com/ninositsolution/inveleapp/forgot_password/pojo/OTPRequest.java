package com.ninositsolution.inveleapp.forgot_password.pojo;

public class OTPRequest {

    public String user_id;

    public String otp;


    public OTPRequest(String user_id, String otp) {
        this.user_id = user_id;
        this.otp = otp;
    }
}
