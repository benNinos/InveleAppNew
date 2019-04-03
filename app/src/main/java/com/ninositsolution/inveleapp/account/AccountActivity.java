package com.ninositsolution.inveleapp.account;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.ninositsolution.inveleapp.R;
import com.ninositsolution.inveleapp.cancel_order.CancelOrderActivity;
import com.ninositsolution.inveleapp.cart.CartActivity;
import com.ninositsolution.inveleapp.coupon.CouponActivity;
import com.ninositsolution.inveleapp.databinding.ActivityAccountBinding;
import com.ninositsolution.inveleapp.fitme.FitmeActivity;
import com.ninositsolution.inveleapp.help_desk.HelpDeskActivity;
import com.ninositsolution.inveleapp.home.HomeActivity;
import com.ninositsolution.inveleapp.my_order.MyOrderActivity;
import com.ninositsolution.inveleapp.recently_viewed.RecentlyViewedActivity;
import com.ninositsolution.inveleapp.review.ReviewActivity;
import com.ninositsolution.inveleapp.settings.SettingsActivity;
import com.ninositsolution.inveleapp.wishlist.WishlistActivity;

public class AccountActivity extends AppCompatActivity implements IAccount{

    ActivityAccountBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setContentView(R.layout.activity_account);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_account);
        binding.setAccount(new AccountVM(getApplicationContext(),this));
    }

    @Override
    public void onBackClicked() {
        startActivity(new Intent(this, HomeActivity.class));
    }

    @Override
    public void onUpdateFitmeClicked() {
        startActivity(new Intent(this, FitmeActivity.class));
    }

    @Override
    public void onSettingsClicked() {
        startActivity(new Intent(this, SettingsActivity.class));
    }

    @Override
    public void onOrderHistoryClicked() {
        Toast.makeText(this, "Under Production", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onFitmeEditClicked() {
        Toast.makeText(this, "Under Production", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onAddProfileClicked() {
        Toast.makeText(this, "Under Production", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onWishlistClicked() {
        startActivity(new Intent(this, WishlistActivity.class));
    }

    @Override
    public void onRecentsClicked() {
        /*Toast.makeText(this, "Under Production", Toast.LENGTH_SHORT).show();*/
        startActivity(new Intent(this, RecentlyViewedActivity.class));
    }

    @Override
    public void onCouponClicked() {
        startActivity(new Intent(this, CouponActivity.class));
    }

    @Override
    public void onAddressClicked() {
        Toast.makeText(this, "Under Production", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onMyAccountClickedClicked() {
        Toast.makeText(this, "Under Production", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onHelpDeskClicked() {
        startActivity(new Intent(this, HelpDeskActivity.class));
    }

    @Override
    public void onContactUSClicked() {
        Toast.makeText(this, "Under Production", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onCartClicked() {
        startActivity(new Intent(this, CartActivity.class));
    }

    @Override
    public void onOrdersClicked() {
        startActivity(new Intent(this, MyOrderActivity.class));
    }

    @Override
    public void onReviewsClicked() {
        startActivity(new Intent(this, ReviewActivity.class));
    }

    @Override
    public void onCancelClicked() {
        startActivity(new Intent(this, CancelOrderActivity.class));
    }
}
