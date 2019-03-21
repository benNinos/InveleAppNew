package com.ninositsolution.inveleapp.categories;

import com.google.gson.annotations.SerializedName;

import java.util.List;

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

    @SerializedName("brand_id")
    public String brand_id;

    @SerializedName("child_categories")
    public List<CategoryModel> child_categories;

    @SerializedName("brands")
    public List<CategoryModel> brands;

    public CategoryModel(List<CategoryModel> brands) {
        this.brands = brands;
    }

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

    public CategoryModel(String name, String image_path, String brand_id) {
        this.name = name;
        this.image_path = image_path;
        this.brand_id = brand_id;
    }

    public CategoryModel(){
        this.menu_id = null;
        this.name = null;
        this.image_path = null;
        this.slug = null;
        this.banner_image = null;
    }

}
