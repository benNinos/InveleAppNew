package com.ninositsolution.inveleapp.forgot_password.pojo;

public class ResetPasswordRequest {

        public Integer user_id;
    public String password;
    public String password_confirmation;


    public ResetPasswordRequest(Integer user_id, String password,String password_confirmation){
        this.user_id = user_id;
        this.password = password;
        this.password_confirmation = password_confirmation;
    }

}
