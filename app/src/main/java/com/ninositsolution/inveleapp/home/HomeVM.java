package com.ninositsolution.inveleapp.home;

import android.content.Context;

/**
 * Created by Parthasarathy D on 1/17/2019.
 * Ninos IT Solution Pvt Ltd
 * ben@ninositsolution.com
 */
public class HomeVM {

    private HomeModel homeModel;
    private Context context;
    private IHome iHome;

    public HomeVM(Context context, IHome iHome) {
        this.context = context;
        this.iHome = iHome;
        homeModel = new HomeModel();
        loadHomeScreen();
    }

    private void loadHomeScreen() {

    }

    //clickListeners

    public void onWishlistClicked()
    {
        iHome.onWishlistClicked();
    }

    public void onCartClicked()
    {
        //iHome.onCartClicked();
    }

    public void onSearchClicked()
    {
        iHome.onSearchClicked();
    }

    public void onItemClicked()
    {
        iHome.onItemClicked();
    }

    public void onUsernameClicked()
    {
        iHome.onUsernameClicked();
    }

    public void onBrandMoreClicked()
    {
        iHome.onBrandMoreClicked();
    }
}
