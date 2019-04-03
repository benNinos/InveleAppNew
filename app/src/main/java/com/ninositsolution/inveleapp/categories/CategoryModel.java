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

    @SerializedName("id")
    public String id;

    @SerializedName("seller_id")
    public String seller_id;

    @SerializedName("sku")
    public String sku;

    @SerializedName("status")
    public String status;

    @SerializedName("approde_status")
    public String approde_status;

    public String category_position;

  //  @SerializedName("child_categories")
    public List<CategoryModel> child_categories;

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

    public CategoryModel(String menu_id, String name) {
        this.menu_id = menu_id;
        this.name = name;
    }

    public CategoryModel(String position, String id, String seller_id, String name, String status, String approde_status, String image_path) {
        this.category_position = position;
        this.id = id;
        this.seller_id=seller_id;
        this.name = name;
        this.status = status;
        this.approde_status = approde_status;
        this.image_path = image_path;
    }

    public CategoryModel(){
        this.menu_id = null;
        this.name = null;
        this.image_path = null;
        this.slug = null;
        this.banner_image = null;
        this.id = null;
        this.seller_id = null;
        this.status = null;
        this.approde_status = null;
    }

    public String getCategory_position() {
        return category_position;
    }

    public void setCategory_position(String category_position) {
        this.category_position = category_position;
    }

    public String getMenu_id() {
        return menu_id;
    }

    public void setMenu_id(String menu_id) {
        this.menu_id = menu_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage_path() {
        return image_path;
    }

    public void setImage_path(String image_path) {
        this.image_path = image_path;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getBanner_image() {
        return banner_image;
    }

    public void setBanner_image(String banner_image) {
        this.banner_image = banner_image;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getBrand_id() {
        return brand_id;
    }

    public void setBrand_id(String brand_id) {
        this.brand_id = brand_id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSeller_id() {
        return seller_id;
    }

    public void setSeller_id(String seller_id) {
        this.seller_id = seller_id;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getApprode_status() {
        return approde_status;
    }

    public void setApprode_status(String approde_status) {
        this.approde_status = approde_status;
    }

    public List<CategoryModel> getChild_categories() {
        return child_categories;
    }

    public void setChild_categories(List<CategoryModel> child_categories) {
        this.child_categories = child_categories;
    }

    public List<CategoryModel> getBrands() {
        return brands;
    }

    public void setBrands(List<CategoryModel> brands) {
        this.brands = brands;
    }
}
