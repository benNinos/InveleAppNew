package com.ninositsolution.inveleapp.coupon;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.Toast;

import com.ninositsolution.inveleapp.R;
import com.ninositsolution.inveleapp.databinding.ActivityCouponBinding;
import com.ninositsolution.inveleapp.utils.Session;

public class CouponActivity extends AppCompatActivity implements ICoupon{

    ActivityCouponBinding binding;
    CouponVM couponVM;
    ICoupon iCoupon;
    Context context;
    BottomSheetBehavior bottomSheetBehavior;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_coupon);

        iCoupon = this;
        context = CouponActivity.this;

        couponVM = ViewModelProviders.of(this).get(CouponVM.class);
        binding.setCoupon(couponVM);
        binding.setLifecycleOwner(this);

        binding.setICoupon(iCoupon);

        bottomSheetBehavior = BottomSheetBehavior.from(binding.couponDescLayout);

        bottomSheetBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View view, int i) {

                if (bottomSheetBehavior.getState() == BottomSheetBehavior.STATE_EXPANDED)
                {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        binding.couponLayout.setForeground(getResources().getDrawable(R.drawable.window_dim));
                        binding.couponLayout.getForeground().setAlpha(180);
                    }
                } else
                {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        binding.couponLayout.setForeground(null);
                    }
                }
            }

            @Override
            public void onSlide(@NonNull View view, float v) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    binding.couponLayout.setForeground(null);
                }
            }
        });

        binding.couponRecyclerview.setHasFixedSize(true);
        binding.couponRecyclerview.setLayoutManager(new LinearLayoutManager(this));

        couponVM.getCouponsApi(Session.getUserId(this));

        couponVM.getCouponVMMutableLiveData().observe(this, new Observer<CouponVM>() {
            @Override
            public void onChanged(@Nullable CouponVM couponVM) {

                if (couponVM.getStatus() != null)
                {
                    if (couponVM.getStatus().equalsIgnoreCase("success"))
                    {
                        binding.couponRecyclerview.setAdapter(new CouponAdapter(context, couponVM.getActiveCoupons(), iCoupon));
                        Toast.makeText(CouponActivity.this, ""+couponVM.getMessage(), Toast.LENGTH_SHORT).show();

                    } else
                    {
                        Toast.makeText(CouponActivity.this, ""+couponVM.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    @Override
    public void onBackClicked() {
        super.onBackPressed();
    }

    @Override
    public void onMoreDetailsClicked(String desc) {

        couponVM.couponDesc.set(desc);

        if (bottomSheetBehavior.getState() == BottomSheetBehavior.STATE_EXPANDED)
        {
            bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
        }
        else
        {
            bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
        }

    }

}