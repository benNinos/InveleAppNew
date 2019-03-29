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
    private List<ChildCategoriesPOJO>childCategoriesPOJOS=new ArrayList<>();


    public ExpandableCategoriesPOJO(String header,List<ChildCategoriesPOJO>childCategoriesPOJOS){
        this.header = header;
       this.childCategoriesPOJOS = childCategoriesPOJOS;
    }
    public ExpandableCategoriesPOJO(){
        this.header = null;

    }

    public List<ChildCategoriesPOJO> getChildCategoriesPOJOS() {
        return childCategoriesPOJOS;
    }

    public void setChildCategoriesPOJOS(List<ChildCategoriesPOJO> childCategoriesPOJOS) {
        this.childCategoriesPOJOS = childCategoriesPOJOS;
    }

    public String getHeader() {
        return header;
    }


}
