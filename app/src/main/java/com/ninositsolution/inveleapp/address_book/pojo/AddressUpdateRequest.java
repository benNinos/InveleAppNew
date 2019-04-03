package com.ninositsolution.inveleapp.address_book.pojo;

import com.google.gson.annotations.SerializedName;

public class AddressUpdateRequest {

    @SerializedName("user_id")
    public String user_id;

    @SerializedName("user_address_id")
    public String user_address_id;

    public AddressUpdateRequest(String user_address_id) {
        this.user_address_id = user_address_id;
    }

}
