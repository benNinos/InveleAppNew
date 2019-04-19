package com.ninositsolution.inveleapp.home;

public interface IHomeClick {

    void onWishlistClicked();
    void onCartClicked();
    void onSearchClicked();
    void onItemClicked();
    void onUsernameClicked();
    void onBrandMoreClicked();
    void onCategoriesClicked(String slug, String name);
    void updateWishlist(int productId, int status);
}
