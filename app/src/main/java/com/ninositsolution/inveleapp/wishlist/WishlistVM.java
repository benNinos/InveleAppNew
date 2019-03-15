package com.ninositsolution.inveleapp.wishlist;

import android.content.Context;

/**
 * Created by Parthasarathy D on 1/22/2019.
 * Ninos IT Solution Pvt Ltd
 * ben@ninositsolution.com
 */
public class WishlistVM {

    private WishlistModel wishlistModel;
    private Context context;
    private IWishlist iWishlist;

    public WishlistVM(Context context, IWishlist iWishlist) {
        this.context = context;
        this.iWishlist = iWishlist;
        wishlistModel = new WishlistModel();
        loadWishlist();
    }

    private void loadWishlist() {

        WishlistAdapter wishlistAdapter = new WishlistAdapter(context);
        iWishlist.setRecyclerAdapter(wishlistAdapter);

    }

    //clickListeners

    public void onBackClicked()
    {
        iWishlist.onBackClicked();
    }
}
