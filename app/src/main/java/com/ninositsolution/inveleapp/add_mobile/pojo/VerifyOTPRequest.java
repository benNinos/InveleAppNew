package com.ninositsolution.inveleapp.add_mobile.pojo;

public class VerifyOTPRequest {

    public String user_id;
    public String type;
    public String user_change;
    public String otp;

    public VerifyOTPRequest(String user_id, String type, String user_change, String otp) {
        this.user_id = user_id;
        this.type = type;
        this.user_change = user_change;
        this.otp = otp;
    }
}
