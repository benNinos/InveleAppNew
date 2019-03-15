package com.ninositsolution.inveleapp.address_book.pojo;

import com.google.gson.annotations.SerializedName;

public class AddressBookRequest {

    @SerializedName("user_id")
    public String user_id;

    @SerializedName("user_address_id")
    public String user_address_id;

    public AddressBookRequest(String user_id) {
        this.user_id = user_id;
    }
}
