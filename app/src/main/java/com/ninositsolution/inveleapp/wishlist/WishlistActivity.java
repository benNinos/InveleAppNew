package com.ninositsolution.inveleapp.wishlist;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;

import com.ninositsolution.inveleapp.R;
import com.ninositsolution.inveleapp.databinding.ActivityWishlistBinding;
import com.ninositsolution.inveleapp.home.HomeActivity;

public class WishlistActivity extends AppCompatActivity implements IWishlist{

    ActivityWishlistBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_wishlist);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_wishlist);
        binding.setWishlist(new WishlistVM(getApplicationContext(), this));
    }

    @Override
    public void setRecyclerAdapter(WishlistAdapter wishlistAdapter) {

        binding.wishlistRecyclerview.setHasFixedSize(true);
        binding.wishlistRecyclerview.setLayoutManager(new LinearLayoutManager(this));
        binding.wishlistRecyclerview.setAdapter(wishlistAdapter);

    }

    @Override
    public void onBackClicked() {

        startActivity(new Intent(this, HomeActivity.class));
    }
}
