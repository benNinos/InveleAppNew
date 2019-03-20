package com.ninositsolution.inveleapp.categories;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Parthasarathy D on 1/17/2019.
 * Ninos IT Solution Pvt Ltd
 * ben@ninositsolution.com
 */
public class CategoryModel {

    @SerializedName("menu_id")
    public String menu_id;

    @SerializedName("name")
    public String name;

    @SerializedName("image_path")
    public String image_path;

    @SerializedName("slug")
    public String slug;

    @SerializedName("banner_image")
    public String banner_image;

    @SerializedName("image")
    public String image;

    public CategoryModel(String menu_id, String name, String image_path, String slug, String banner_image) {
        this.menu_id = menu_id;
        this.name = name;
        this.image_path = image_path;
        this.slug = slug;
        this.banner_image = banner_image;
    }

    public CategoryModel(String menu_id, String name, String image_path, String slug) {
        this.menu_id = menu_id;
        this.name = name;
        this.image_path = image_path;
        this.slug = slug;
    }

    public CategoryModel(){
        this.menu_id = null;
        this.name = null;
        this.image_path = null;
        this.slug = null;
        this.banner_image = null;
    }

}
