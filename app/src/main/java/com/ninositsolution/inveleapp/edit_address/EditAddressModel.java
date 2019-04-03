package com.ninositsolution.inveleapp.edit_address;

import java.util.ArrayList;
import java.util.stream.Stream;

public class EditAddressModel extends ArrayList<CharSequence> {

   public String id;
   public String user_id;
   public String name;
   public String contact_no;
   public String floor_unit;
   public String postal_code;
   public String address;
   public String city;
   public String address_type;
   public String is_shipping;
   public String is_billing;

    public EditAddressModel(String id, String user_id, String name, String contact_no, String floor_unit,String postal_code, String address, String city, String address_type, String is_shipping, String is_billing) {
        this.id = id;
        this.user_id = user_id;
        this.name = name;
        this.contact_no = contact_no;
        this.floor_unit = floor_unit;
        this.postal_code = postal_code;
        this.address = address;
        this.city = city;
        this.address_type = address_type;
        this.is_shipping = is_shipping;
        this.is_billing = is_billing;
    }
   public EditAddressModel(){

       this.id = null;
       this.user_id = null;
       this.name = null;
       this.contact_no = null;
       this.floor_unit = null;
       this.postal_code = null;
       this.address = null;
       this.city = null;
       this.address_type = null;
       this.is_shipping = null;
       this.is_billing = null;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContact_no() {
        return contact_no;
    }

    public void setContact_no(String contact_no) {
        this.contact_no = contact_no;
    }

    public String getFloor_unit() {
        return floor_unit;
    }

    public void setFloor_unit(String floor_unit) {
        this.floor_unit = floor_unit;
    }

    public String getPostal_code() {
        return postal_code;
    }

    public void setPostal_code(String postal_code) {
        this.postal_code = postal_code;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress_type() {
        return address_type;
    }

    public void setAddress_type(String address_type) {
        this.address_type = address_type;
    }

    public String getIs_shipping() {
        return is_shipping;
    }

    public void setIs_shipping(String is_shipping) {
        this.is_shipping = is_shipping;
    }

    public String getIs_billing() {
        return is_billing;
    }

    public void setIs_billing(String is_billing) {
        this.is_billing = is_billing;
    }
}
