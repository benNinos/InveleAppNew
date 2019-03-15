package com.ninositsolution.inveleapp.categories;

import android.content.Context;

import com.ninositsolution.inveleapp.utils.Session;

/**
 * Created by Parthasarathy D on 1/17/2019.
 * Ninos IT Solution Pvt Ltd
 * ben@ninositsolution.com
 */
public class CategoryVM {

    private CategoryModel categoryModel;
    private Context context;
    private ICategory iCategory;
    private Session session;


    public CategoryVM(Context context, ICategory iCategory) {
        this.context = context;
        this.iCategory = iCategory;
        categoryModel = new CategoryModel();
        session = new Session(context);
    }

    public void AllCategoriesClicked()
    {
        iCategory.AllCategoriesClicked();
        iCategory.ChangePreviousCategoryView();
        session.setCategoryPosition(1);
    }

    public void MensCategoriesClicked()
    {
        iCategory.ChangePreviousCategoryView();
        iCategory.MensCategoriesClicked();
        session.setCategoryPosition(2);
    }

    public void WomensCategoriesClicked()
    {
        iCategory.ChangePreviousCategoryView();
        iCategory.WomensCategoriesClicked();
        session.setCategoryPosition(3);
    }

    public void BoysCategoriesClicked()
    {
        iCategory.ChangePreviousCategoryView();
        iCategory.BoysCategoriesClicked();
        session.setCategoryPosition(4);
    }

    public void MobilesCategoriesClicked()
    {
        iCategory.ChangePreviousCategoryView();
        iCategory.MobilesCategoriesClicked();
        session.setCategoryPosition(5);
    }

    public void ElectronicsCategoriesClicked()
    {
        iCategory.ChangePreviousCategoryView();
        iCategory.ElectronicsCategoriesClicked();
        session.setCategoryPosition(6);
    }

    public void HomeCategoriesClicked()
    {
        iCategory.ChangePreviousCategoryView();
        iCategory.HomeCategoriesClicked();
        session.setCategoryPosition(7);
    }

    public void BabiesCategoriesClicked()
    {
        iCategory.ChangePreviousCategoryView();
        iCategory.BabiesCategoriesClicked();
        session.setCategoryPosition(8);
    }

    public void BeautyCategoriesClicked()
    {
        iCategory.ChangePreviousCategoryView();
        iCategory.BeautyCategoriesClicked();
        session.setCategoryPosition(9);
    }

    public void HealthCategoriesClicked()
    {
        iCategory.ChangePreviousCategoryView();
        iCategory.HealthCategoriesClicked();
        session.setCategoryPosition(10);
    }

    public void BooksCategoriesClicked()
    {
        iCategory.ChangePreviousCategoryView();
        iCategory.BooksCategoriesClicked();
        session.setCategoryPosition(11);
    }

    public void onBackClicked()
    {
        iCategory.onBackClicked();
    }

    public void onSearchClicked()
    {
        iCategory.onSearchClicked();
    }

    public void onCartClicked()
    {
        iCategory.onCartClicked();
    }

    public void onMenuClicked()
    {
        iCategory.onMenuClicked();
    }
}
