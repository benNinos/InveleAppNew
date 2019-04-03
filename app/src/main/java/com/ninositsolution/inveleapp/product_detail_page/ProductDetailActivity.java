package com.ninositsolution.inveleapp.product_detail_page;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.ninositsolution.inveleapp.R;
import com.ninositsolution.inveleapp.databinding.ActivityProductDetailBinding;
import com.ninositsolution.inveleapp.review_all.ReviewAllActivity;
import com.ninositsolution.inveleapp.view_store.ViewStoreActivity;

public class ProductDetailActivity extends AppCompatActivity implements IProductDetail{

    ActivityProductDetailBinding binding;
    BottomSheetBehavior bottomSheetBehavior;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_product_detail);
        binding.setProduct(new ProductDetailVM(getApplicationContext(), this));

        bottomSheetBehavior = BottomSheetBehavior.from(binding.bottomSheetProduct);
        bottomSheetBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View view, int i) {

                if (bottomSheetBehavior.getState() == BottomSheetBehavior.STATE_EXPANDED)
                {
                    binding.productDetailLayout.setAlpha(0.1f);
                    binding.productDetailLayout.setClickable(false);
                }
                else
                {
                    binding.productDetailLayout.setAlpha(1);
                    binding.productDetailLayout.setClickable(true);
                }

            }

            @Override
            public void onSlide(@NonNull View view, float v) {

            }
        });

    }

    @Override
    public void onBackClicked() {
            super.onBackPressed();
    }

    @Override
    public void onViewAllClicked() {
            startActivity(new Intent(this, ReviewAllActivity.class));
    }

    @Override
    public void onViewStoreClicked() {
            startActivity(new Intent(this, ViewStoreActivity.class));
    }

    @Override
    public void onShippingClicked() {

        if (bottomSheetBehavior.getState() == BottomSheetBehavior.STATE_EXPANDED)
            bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);

        else
            bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);

    }

    @Override
    public void onVoucherClicked() {

        if (bottomSheetBehavior.getState() == BottomSheetBehavior.STATE_EXPANDED)
            bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);

        else
            bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);

    }

    @Override
    public void onStorePickUpClicked() {

            StoreLocationPopup storeLocationPopup = new StoreLocationPopup(this);
            storeLocationPopup.show();

    }
}
