package com.ninositsolution.inveleapp.pojo;

import java.util.ArrayList;

public class HomeArrayLists {

    public Integer menu_id;
    public String name;
    public String image_path;
    public String slug;
    public ArrayList<HomeArrayLists> child_menus;
    public Integer id;
    public Integer home_management_id;
    public String image;
    public String banner_image;
    public String url;
    public String color;
    public String starting_date;
    public String ending_date;
    public String position;
    public String seller_id;
    public String sku;
    public String store_location_name;
    //public String attribute_id;
    public String is_color;
    public Integer status;
    public Integer seller_store_location_id;
    public Integer is_fitme;
    public Integer brand_id;
    public Integer approde_status;
    public Float min_price;
    public Float max_price;
    public String created_at;
    public String updated_at;
    public Integer product_id;
    public Integer category_id;
    public Integer attribute_id;
    public String usual_price;
    public String value;
    public Float invele_price;
    public String discount;
    public Integer average_rating;
    public Integer wishlist;
    public String category_name;
    public String theme_type;
    public String theme_color;
    public String caption;
    public String advertisement;
    public String banner_type;
    public String start_date_time;
    public String end_date_time;
    public String label;
    public String description;
    public Integer fitme_label_id;
    public ArrayList<HomeArrayLists> home_management_products;
    public ArrayList<HomeArrayLists> home_management_images;
    public ArrayList<HomeArrayLists> attribute_values;

    public Integer wishlist_id;

    public HomeArrayLists  (Integer fitme_label_id,String label)
    {
        this.label = label;
        this.fitme_label_id = fitme_label_id;
    }

    public HomeArrayLists(String label) {
        this.label = label;
    }


    public String getLabel()
    {
        return label;
    }

}
