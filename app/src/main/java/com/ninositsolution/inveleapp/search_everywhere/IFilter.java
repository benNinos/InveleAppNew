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
    void onFitmeSizeClicked(String size);
    void onCategoriesClicked(String slug, String name);
}
