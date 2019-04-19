package com.ninositsolution.inveleapp.wishlist;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

/**
 * Created by Parthasarathy D on 1/22/2019.
 * Ninos IT Solution Pvt Ltd
 * ben@ninositsolution.com
 */
public class WishlistVM extends ViewModel {

    private WishlistRepo wishlistRepo;
    private MutableLiveData<WishlistVM> wishlistVMMutableLiveData = new MutableLiveData<>();

    public WishlistVM() {
        wishlistRepo = new WishlistRepo();
    }

    public void getWishists(String userId)
    {
        wishlistVMMutableLiveData = wishlistRepo.getWishlistVMMutableLiveData(userId);
    }

    public MutableLiveData<WishlistVM> getWishlistVMMutableLiveData() {
        return wishlistVMMutableLiveData;
    }
}
