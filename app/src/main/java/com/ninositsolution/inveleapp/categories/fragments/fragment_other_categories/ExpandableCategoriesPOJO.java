package com.ninositsolution.inveleapp.categories.fragments.fragment_other_categories;

/**
 * Created by Parthasarathy D on 1/21/2019.
 * Ninos IT Solution Pvt Ltd
 * ben@ninositsolution.com
 */
public class ExpandableCategoriesPOJO {

    private String header;
    private String images ;
    private String category_name;

    public ExpandableCategoriesPOJO(String header,String images,String category_name) {
        this.header = header;
        this.images = images;
        this.category_name = category_name;
    }

    public String getHeader() {
        return header;
    }

    public String getImages() {
        return images;
    }

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }
}
