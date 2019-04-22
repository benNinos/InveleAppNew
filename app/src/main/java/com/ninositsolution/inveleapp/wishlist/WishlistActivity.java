package com.ninositsolution.inveleapp.wishlist;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.widget.Toast;

import com.ninositsolution.inveleapp.R;
import com.ninositsolution.inveleapp.databinding.ActivityWishlistBinding;
import com.ninositsolution.inveleapp.home.HomeRepo;
import com.ninositsolution.inveleapp.home.HomeVM;
import com.ninositsolution.inveleapp.utils.Session;
import com.ninositsolution.inveleapp.utils.WishListListener;

public class WishlistActivity extends AppCompatActivity implements WishListListener {

    ActivityWishlistBinding binding;
    WishlistVM wishlistVMGlobal;
    Context context;
    WishListListener wishListListener;
    private MutableLiveData<HomeVM> updateWishlistLiveData = new MutableLiveData<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_wishlist);

        context = WishlistActivity.this;
        wishListListener = this;

        wishlistVMGlobal = ViewModelProviders.of(this).get(WishlistVM.class);
        binding.setWishlist(wishlistVMGlobal);
        binding.setLifecycleOwner(this);

        binding.wishlistRecyclerview.setHasFixedSize(true);
        binding.wishlistRecyclerview.setLayoutManager(new GridLayoutManager(this, 2));

        wishlistVMGlobal.getWishists(Session.getUserId(this));

        binding.setIWishlist(new IWishlist() {
            @Override
            public void onBackClicked() {
                WishlistActivity.super.onBackPressed();
            }
        });

        wishlistVMGlobal.getWishlistVMMutableLiveData().observe(this, new Observer<WishlistVM>() {
            @Override
            public void onChanged(@Nullable WishlistVM wishlistVM) {

                if (wishlistVM.getStatus() != null)
                {
                    if (wishlistVM.getStatus().equalsIgnoreCase("success"))
                    {
                        wishlistVMGlobal.toolbarHeader.set("My Wishlist ("+wishlistVM.getWishlists().size()+")");
                        binding.wishlistRecyclerview.setAdapter(new WishlistAdapter(context, wishlistVM.getWishlists(), wishListListener));

                    }
                    if (wishlistVM.getStatus().equalsIgnoreCase("error"))
                    {
                        Toast.makeText(WishlistActivity.this, ""+wishlistVM.getMessage(), Toast.LENGTH_SHORT).show();
                    }

                    wishlistVM.setStatus("");
                }
            }
        });

    }

    @Override
    public void updateWishList(int product_id, int status) {

       updateWishlistLiveData = new HomeRepo().getUpdateWishlistLiveData(Session.getUserId(context), String.valueOf(product_id), String.valueOf(status));

       updateWishlistLiveData.observe(this, new Observer<HomeVM>() {
           @Override
           public void onChanged(@Nullable HomeVM homeVM) {
               if (homeVM.status.get() != null)
               {
                   String status = homeVM.status.get();
                   String message = homeVM.msg.get();

                   if (status.equalsIgnoreCase("success"))
                   {
                       Toast.makeText(context, ""+message, Toast.LENGTH_SHORT).show();
                       wishlistVMGlobal.getWishists(Session.getUserId(context));

                   } else {
                       Toast.makeText(context, ""+message, Toast.LENGTH_SHORT).show();
                   }

                   homeVM.status.set("");

               } else
               {
                   Toast.makeText(context, "Response null", Toast.LENGTH_SHORT).show();
               }
           }
       });
    }
}
