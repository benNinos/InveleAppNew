package com.ninositsolution.inveleapp.account;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
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
import com.ninositsolution.inveleapp.utils.Session;
import com.ninositsolution.inveleapp.wishlist.WishlistActivity;

public class AccountActivity extends AppCompatActivity {

    ActivityAccountBinding binding;
    Context context;
    AccountVM accountVM;

    @Override
    protected void onStart() {

        accountVM.username.set(Session.getUserFirstName(context)+" "+Session.getUserLastName(context));
        accountVM.fitmeName.set(Session.getUserFirstName(context)+" "+Session.getUserLastName(context));
        accountVM.userPhoto.set(Session.getUserPhoto(context));

        super.onStart();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        context = this;

        binding = DataBindingUtil.setContentView(this, R.layout.activity_account);
        accountVM = ViewModelProviders.of(this).get(AccountVM.class);
        binding.setAccount(accountVM);
        binding.setLifecycleOwner(this);

        binding.setIAccount(new IAccount() {
            @Override
            public void onBackClicked() {
                startActivity(new Intent(context, HomeActivity.class));
            }

            @Override
            public void onUpdateFitmeClicked() {
                startActivity(new Intent(context, FitmeActivity.class));
            }

            @Override
            public void onSettingsClicked() {
                startActivity(new Intent(context, SettingsActivity.class));
            }

            @Override
            public void onOrderHistoryClicked() {
                Toast.makeText(context, "Under Production", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFitmeEditClicked() {
                Toast.makeText(context, "Under Production", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAddProfileClicked() {
                Toast.makeText(context, "Under Production", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onWishlistClicked() {
                startActivity(new Intent(context, WishlistActivity.class));
            }

            @Override
            public void onRecentsClicked() {
                /*Toast.makeText(this, "Under Production", Toast.LENGTH_SHORT).show();*/
                startActivity(new Intent(context, RecentlyViewedActivity.class));
            }

            @Override
            public void onCouponClicked() {
                startActivity(new Intent(context, CouponActivity.class));
            }

            @Override
            public void onAddressClicked() {
                Toast.makeText(context, "Under Production", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onMyAccountClickedClicked() {
                Toast.makeText(context, "Under Production", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onHelpDeskClicked() {
                startActivity(new Intent(context, HelpDeskActivity.class));
            }

            @Override
            public void onContactUSClicked() {
                Toast.makeText(context, "Under Production", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCartClicked() {
                startActivity(new Intent(context, CartActivity.class));
            }

            @Override
            public void onOrdersClicked() {
                startActivity(new Intent(context, MyOrderActivity.class));
            }

            @Override
            public void onReviewsClicked() {
                startActivity(new Intent(context, ReviewActivity.class));
            }

            @Override
            public void onCancelClicked() {
                startActivity(new Intent(context, CancelOrderActivity.class));
            }
        });

    }
}