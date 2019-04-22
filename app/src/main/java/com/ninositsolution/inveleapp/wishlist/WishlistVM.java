package com.ninositsolution.inveleapp.wishlist;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.databinding.ObservableField;

import com.ninositsolution.inveleapp.pojo.HomeArrayLists;
import com.ninositsolution.inveleapp.pojo.POJOClass;
import com.ninositsolution.inveleapp.utils.Constants;

import java.util.List;

/**
 * Created by Parthasarathy D on 1/22/2019.
 * Ninos IT Solution Pvt Ltd
 * ben@ninositsolution.com
 */
public class WishlistVM extends ViewModel {

    private String status, message;
    private List<HomeArrayLists> wishlists;

    private WishlistRepo wishlistRepo;
    private MutableLiveData<WishlistVM> wishlistVMMutableLiveData = new MutableLiveData<>();

    public ObservableField<String> product_item_name = new ObservableField<>();
    public ObservableField<String> product_rate = new ObservableField<>();
    public ObservableField<String> product_delete_rate = new ObservableField<>();
    public ObservableField<Float> product_rating_float = new ObservableField<>();
    public ObservableField<String> product_rating = new ObservableField<>();
    public ObservableField<String> product_img = new ObservableField<>();

    public ObservableField<String> toolbarHeader = new ObservableField<>("My Wishlist");

    public WishlistVM() {
        wishlistRepo = new WishlistRepo();
    }

    public WishlistVM(POJOClass pojoClass)
    {
        status = pojoClass.status;
        message = pojoClass.msg;
        wishlists = pojoClass.wishlists;
    }

    public WishlistVM(HomeArrayLists homeArrayLists)
    {
        product_item_name.set(homeArrayLists.name);
        product_rate.set(Constants.CURRENCY+homeArrayLists.invele_price.toString());
        product_delete_rate.set(Constants.CURRENCY+homeArrayLists.usual_price);
        product_rating_float.set(Float.valueOf(homeArrayLists.average_rating));
        product_rating.set(homeArrayLists.average_rating.toString());
        product_img.set(homeArrayLists.image_path);
    }

    public void getWishists(String userId)
    {
        wishlistVMMutableLiveData = wishlistRepo.getWishlistVMMutableLiveData(userId);
    }

    public MutableLiveData<WishlistVM> getWishlistVMMutableLiveData() {
        return wishlistVMMutableLiveData;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<HomeArrayLists> getWishlists() {
        return wishlists;
    }

    public void setWishlists(List<HomeArrayLists> wishlists) {
        this.wishlists = wishlists;
    }
}
