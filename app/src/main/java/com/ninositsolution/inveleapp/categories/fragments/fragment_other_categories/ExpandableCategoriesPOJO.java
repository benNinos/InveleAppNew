package com.ninositsolution.inveleapp.categories.fragments.fragment_other_categories;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Parthasarathy D on 1/21/2019.
 * Ninos IT Solution Pvt Ltd
 * ben@ninositsolution.com
 */
public class ExpandableCategoriesPOJO {

    private String header;
    private String images ;
    private String category_name;
    private List<ChildCategoriesPOJO> otherFragmentVM;


    public ExpandableCategoriesPOJO(String header,  List<ChildCategoriesPOJO>otherFragmentVM){
        this.header = header;
       this.otherFragmentVM = otherFragmentVM;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public  List<ChildCategoriesPOJO> getOtherFragmentVM() {
        return otherFragmentVM;
    }

    public void setOtherFragmentVM( List<ChildCategoriesPOJO> otherFragmentVM) {
        this.otherFragmentVM = otherFragmentVM;
    }

    public ExpandableCategoriesPOJO(){
        this.header = null;

    }



    public String getHeader() {
        return header;
    }


}
