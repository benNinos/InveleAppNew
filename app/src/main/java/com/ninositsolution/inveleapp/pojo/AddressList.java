package com.ninositsolution.inveleapp.pojo;

public class AddressList {
    public String ADDRESS;
    public String BUILDING;
    public String BLK_NO;
    public String POSTAL;
    public String ROAD_NAME;

    //address book list
    public String id;
    public String user_id;
    public String address_type;
    public String address;
    public String address1;
    public String name;
    public String postal_code;
    public String city;
    public String contact_no;
    public String is_billing_address;
    public String is_shipping_address;
    public String user_default;

    public AddressList(String id, String user_id, String address_type, String address, String address1, String name, String postal_code, String city, String contact_no, String is_billing_address, String is_shipping_address, String user_default) {
        this.id = id;
        this.user_id = user_id;
        this.address_type = address_type;
        this.address = address;
        this.address1 = address1;
        this.name = name;
        this.postal_code = postal_code;
        this.city = city;
        this.contact_no = contact_no;
        this.is_billing_address = is_billing_address;
        this.is_shipping_address = is_shipping_address;
        this.user_default = user_default;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getAddress_type() {
        return address_type;
    }

    public void setAddress_type(String address_type) {
        this.address_type = address_type;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPostal_code() {
        return postal_code;
    }

    public void setPostal_code(String postal_code) {
        this.postal_code = postal_code;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getContact_no() {
        return contact_no;
    }

    public void setContact_no(String contact_no) {
        this.contact_no = contact_no;
    }

    public String getIs_billing_address() {
        return is_billing_address;
    }

    public void setIs_billing_address(String is_billing_address) {
        this.is_billing_address = is_billing_address;
    }

    public String getIs_shipping_address() {
        return is_shipping_address;
    }

    public void setIs_shipping_address(String is_shipping_address) {
        this.is_shipping_address = is_shipping_address;
    }

    public String getUser_default() {
        return user_default;
    }

    public void setUser_default(String user_default) {
        this.user_default = user_default;
    }
}
