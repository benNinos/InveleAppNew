package com.ninositsolution.inveleapp.change_email.pojo;

public class EmailOTPRequest {

    public String user_id;
    public String type;
    public String user_change;


    public EmailOTPRequest(String user_id, String type, String user_change) {
        this.user_id = user_id;
        this.type = type;
        this.user_change = user_change;
    }
}
