package com.ninositsolution.inveleapp.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class POJOClass {

    public String status;
    public String msg;
    public String city;
    public String login_status;
    public Integer otp;
    public Integer otp_verify;
    public Float filter_min_price;
    public Float filter_max_price;
    public Users user;
    //public Users user_id;
    public List<AddressList>address_list;
    public List<AddressList>addresses;
    public AddressList user_address;
    public ArrayList<HomeArrayLists> menus;
    public ArrayList<HomeArrayLists> main_banners;
    public ArrayList<HomeArrayLists> sub_banners;
    public ArrayList<HomeArrayLists> deal_products;
    public ArrayList<HomeArrayLists> home_managements;
    public ArrayList<HomeArrayLists> product_trendings;
    public ArrayList<HomeArrayLists> brands;
    public ArrayList<HomeArrayLists> products;
    public ArrayList<HomeArrayLists> categories;
    public ArrayList<HomeArrayLists> locations;
    public ArrayList<HomeArrayLists> attributes;
    public ArrayList<HomeArrayLists> shippings;
    public ArrayList<HomeArrayLists> men;
    public ArrayList<HomeArrayLists> women;
    public ArrayList<HomeArrayLists> wishlists;
    public String start_date_time;
    public String end_date_time;
    public String caption;
    public List<HomeArrayLists> active_coupons;
    public List<HomeArrayLists> used_coupons;
    public List<HomeArrayLists> all_brands;
    public ArrayList<String> fitme_sizes;
    public List<String> search_keys;
    public List<FitmeLists> user_measurements;
    public FitmeLists user_measurement;


    @SerializedName("cart_details")
    @Expose
    private List<CartDetails> cartDetailsList;

    public List<CartDetails> getCartDetailsList() {
        return cartDetailsList;
    }
}
