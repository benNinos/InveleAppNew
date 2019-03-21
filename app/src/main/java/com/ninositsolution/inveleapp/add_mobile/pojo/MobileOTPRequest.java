package com.ninositsolution.inveleapp.add_mobile.pojo;

public class MobileOTPRequest {

    public String user_id;
    public String type;
    public String user_change;

    public MobileOTPRequest(String user_id, String type, String user_change) {
        this.user_id = user_id;
        this.type = type;
        this.user_change = user_change;
    }
}
