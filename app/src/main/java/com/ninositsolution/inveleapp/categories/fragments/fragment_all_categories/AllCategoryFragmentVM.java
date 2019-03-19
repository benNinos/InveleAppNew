package com.ninositsolution.inveleapp.categories.fragments.fragment_all_categories;

import android.app.Activity;
import android.arch.lifecycle.ViewModel;
import android.content.Context;
import android.databinding.ObservableField;
import android.support.v4.app.FragmentActivity;

import com.ninositsolution.inveleapp.categories.CategoryModel;
import com.ninositsolution.inveleapp.categories.fragments.fragment_all_categories.IAllCategories;
import com.ninositsolution.inveleapp.utils.Session;

public class AllCategoryFragmentVM extends ViewModel {

    private CategoryModel categoryModel;
    private Context context;
    private IAllCategories iAllCategories;
    private Session session;



    public AllCategoryFragmentVM(Context context, IAllCategories iAllCategories) {
        this.context = context;
        this.iAllCategories = iAllCategories;
        categoryModel = new CategoryModel();
        session = new Session(context);
    }


}
