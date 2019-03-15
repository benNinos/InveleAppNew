package com.ninositsolution.inveleapp.coupon;

import android.content.Context;

public class CouponVM {

    private CouponModel couponModel;
    private Context context;
    private ICoupon iCoupon;


    public CouponVM(Context context, ICoupon iCoupon) {
        this.context = context;
        this.iCoupon = iCoupon;
        couponModel = new CouponModel();
        loadAdapter();
    }

    private void loadAdapter() {

        CouponAdapter couponAdapter = new CouponAdapter(context);
        iCoupon.setRecyclerAdapter(couponAdapter);
    }

    public void onBackClicked()
    {
        iCoupon.onBackClick();
    }
}
