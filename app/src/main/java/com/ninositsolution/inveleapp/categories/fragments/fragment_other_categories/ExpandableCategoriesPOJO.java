package com.ninositsolution.inveleapp.categories.fragments.fragment_other_categories;

/**
 * Created by Parthasarathy D on 1/21/2019.
 * Ninos IT Solution Pvt Ltd
 * ben@ninositsolution.com
 */
public class ExpandableCategoriesPOJO {

    private String header;
    private int [] images ;

    public ExpandableCategoriesPOJO(String header, int[] images) {
        this.header = header;
        this.images = images;
    }

    public String getHeader() {
        return header;
    }

    public int[] getImages() {
        return images;
    }
}
