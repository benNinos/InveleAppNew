package com.ninositsolution.inveleapp.search;

import java.util.ArrayList;

/**
 * Created by Parthasarathy D on 1/24/2019.
 * Ninos IT Solution Pvt Ltd
 * ben@ninositsolution.com
 */
public class SearchModel {

    private String postalCode;
    private ArrayList<String> arrayList = new ArrayList<>();

    public SearchModel() {
    }

    public SearchModel(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public ArrayList<String> populateSearchList ()
    {
        arrayList.add("iphone 6S");
        arrayList.add("iphone 6S Plus");
        arrayList.add("iphone 6S Case");
        arrayList.add("iphone 6S Plus Case");
        arrayList.add("iphone 6S Cover");
        arrayList.add("iphone 6S Screen protector");
        arrayList.add("iphone 6S Battery");
        arrayList.add("iphone 6S Flip Cover");
        arrayList.add("iphone 6S Plus Screen protector");
        arrayList.add("iphone 6S");
        arrayList.add("iphone 6S Plus");
        arrayList.add("iphone 6S Case");
        arrayList.add("iphone 6S Plus Case");
        arrayList.add("iphone 6S Cover");
        arrayList.add("iphone 6S Screen protector");
        arrayList.add("iphone 6S Battery");
        arrayList.add("iphone 6S Flip Cover");
        arrayList.add("iphone 6S Plus Screen protector");

        return arrayList;
    }
}
