package com.ninositsolution.inveleapp.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CartDetails {

    @SerializedName("store_name")
    @Expose
    private String storeName;

    @SerializedName("products")
    @Expose
    private List<CartDetails> productlists;

    @SerializedName("cart_id")
    @Expose
    private int cartId;

    @SerializedName("order_selected")
    @Expose
    private int orderSelected;

    @SerializedName("product_id")
    @Expose
    private int productId;

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("image_path")
    @Expose
    private String imagepath;

    @SerializedName("slug")
    @Expose
    private String slug;

    @SerializedName("usual_price")
    @Expose
    private String usualPrice;

    @SerializedName("invele_price")
    @Expose
    private String invelePrice;

    @SerializedName("discount")
    @Expose
    private String discount;

    @SerializedName("attributes")
    @Expose
    private List<CartDetails> attributeLists;

    @SerializedName("attribute_id")
    @Expose
    private int attributeId;

    @SerializedName("attribute_name")
    @Expose
    private String attributeName;

    @SerializedName("attribute_values")
    @Expose
    private String attributeValue;


    public String getStoreName() {
        return storeName;
    }

    public List<CartDetails> getProductlists() {
        return productlists;
    }

    public int getCartId() {
        return cartId;
    }

    public int getOrderSelected() {
        return orderSelected;
    }

    public int getProductId() {
        return productId;
    }

    public String getName() {
        return name;
    }

    public String getImagepath() {
        return imagepath;
    }

    public String getSlug() {
        return slug;
    }

    public String getUsualPrice() {
        return usualPrice;
    }

    public String getInvelePrice() {
        return invelePrice;
    }

    public String getDiscount() {
        return discount;
    }

    public List<CartDetails> getAttributeLists() {
        return attributeLists;
    }

    public int getAttributeId() {
        return attributeId;
    }

    public String getAttributeName() {
        return attributeName;
    }

    public String getAttributeValue() {
        return attributeValue;
    }
}
