package com.ninositsolution.inveleapp.pojo;

import java.util.ArrayList;
import com.ninositsolution.inveleapp.categories.CategoryModel;

import java.util.List;

public class POJOClass {

    public String status;
    public String msg;
    public String city;
    public String login_status;
    public Integer otp;
    public Integer otp_verify;
    //public Users users;
    public Users user;
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
    public String start_date_time;
    public String end_date_time;
    public String caption;
    public List<CategoryModel>categories;
    public CategoryModel all_categories;
    public List<CategoryModel>parent_categories;

}
