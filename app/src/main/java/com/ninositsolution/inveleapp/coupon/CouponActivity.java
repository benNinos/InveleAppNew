package com.ninositsolution.inveleapp.coupon;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;

import com.ninositsolution.inveleapp.R;
import com.ninositsolution.inveleapp.databinding.ActivityCouponBinding;

public class CouponActivity extends AppCompatActivity implements ICoupon{

    ActivityCouponBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_coupon);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_coupon);

        binding.setCoupon(new CouponVM(getApplicationContext(), this));
    }

    @Override
    public void onBackClick() {
        super.onBackPressed();
    }

    @Override
    public void setRecyclerAdapter(CouponAdapter couponAdapter) {
        binding.couponRecyclerview.setHasFixedSize(true);
        binding.couponRecyclerview.setLayoutManager(new LinearLayoutManager(this));
        binding.couponRecyclerview.setAdapter(couponAdapter);
    }
}
