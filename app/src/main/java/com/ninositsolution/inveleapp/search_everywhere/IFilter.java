package com.ninositsolution.inveleapp.search_everywhere;

public interface IFilter {

    void onCloseClicked();
    void onCategoriesShowClicked();
    void onBrandsShowClicked();
    void onShippingShowClicked();
    void onLocationsShowClicked();
    void onResetClicked();
    void ApplyClicked();
    void onFilterTwoViewClicked(int mode, int id);
    void onCategoriesClicked(String slug);
}
