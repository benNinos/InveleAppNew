package com.ninositsolution.inveleapp.personal_information.pojo;

public class UpdateProfileRequest {

    public String user_id,first_name,last_name,mobile,email;


    public UpdateProfileRequest(String user_id, String first_name, String last_name, String mobile,String email )
    {
        this.user_id = user_id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
        this.mobile = mobile;
    }


}
