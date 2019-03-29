package com.ninositsolution.inveleapp.categories.fragments.fragment_other_categories;

public class ChildCategoriesPOJO {

    private String images ;
    private String category_name;

    public ChildCategoriesPOJO(String images, String category_name) {
        this.images = images;
        this.category_name = category_name;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }
}
