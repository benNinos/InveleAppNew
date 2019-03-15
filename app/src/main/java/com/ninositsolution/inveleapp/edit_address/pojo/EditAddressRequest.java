package com.ninositsolution.inveleapp.edit_address.pojo;

import com.google.gson.annotations.SerializedName;

public class EditAddressRequest {

    @SerializedName("user_id")
    public String user_id;

    @SerializedName("address_type")
    public String address_type;

    @SerializedName("name")
    public String name;

    @SerializedName("address")
    public String address;

    @SerializedName("address1")
    public String address1;

    @SerializedName("postal_code")
    public String postal_code;

    @SerializedName("city")
    public String city;

    @SerializedName("contact_no")
    public String contact_no;

    @SerializedName("is_billing")
    public String is_billing;


    @SerializedName("is_shipping")
    public String is_shipping;


    public EditAddressRequest(String user_id, String address_type, String name, String address, String address1, String postal_code, String city, String contact_no, String is_billing, String is_shipping) {
        this.user_id = user_id;
        this.address_type = address_type;
        this.name = name;
        this.address = address;
        this.address1 = address1;
        this.postal_code = postal_code;
        this.city = city;
        this.contact_no = contact_no;
        this.is_billing = is_billing;
        this.is_shipping = is_shipping;
    }
    public EditAddressRequest(String postal_code){
        this.postal_code = postal_code;
    }
}
