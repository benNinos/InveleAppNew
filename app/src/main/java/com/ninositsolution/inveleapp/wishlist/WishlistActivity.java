package com.ninositsolution.inveleapp.wishlist;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;

import com.ninositsolution.inveleapp.R;
import com.ninositsolution.inveleapp.databinding.ActivityWishlistBinding;
import com.ninositsolution.inveleapp.utils.Session;

public class WishlistActivity extends AppCompatActivity {

    ActivityWishlistBinding binding;
    WishlistVM wishlistVM;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_wishlist);

        wishlistVM = ViewModelProviders.of(this).get(WishlistVM.class);
        binding.setLifecycleOwner(this);

        binding.wishlistRecyclerview.setHasFixedSize(true);
        binding.wishlistRecyclerview.setLayoutManager(new GridLayoutManager(this, 2));

        wishlistVM.getWishists(Session.getUserId(this));

        binding.setIWishlist(new IWishlist() {
            @Override
            public void onBackClicked() {
                WishlistActivity.super.onBackPressed();
            }
        });

        wishlistVM.getWishlistVMMutableLiveData().observe(this, new Observer<WishlistVM>() {
            @Override
            public void onChanged(@Nullable WishlistVM wishlistVM) {


            }
        });

    }
}
