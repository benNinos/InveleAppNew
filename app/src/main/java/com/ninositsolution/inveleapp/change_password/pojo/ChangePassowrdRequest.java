package com.ninositsolution.inveleapp.change_password.pojo;

public class ChangePassowrdRequest {
    public Integer user_id;
    public String current_password;
    public String new_password;
    public String password_confirmation;

    public ChangePassowrdRequest(Integer user_id, String current_password, String new_password, String password_confirmation) {
        this.user_id = user_id;
        this.current_password = current_password;
        this.new_password = new_password;
        this.password_confirmation = password_confirmation;
    }
}
