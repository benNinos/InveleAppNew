package com.ninositsolution.inveleapp.my_order;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.ninositsolution.inveleapp.R;
import com.ninositsolution.inveleapp.cart.CartActivity;
import com.ninositsolution.inveleapp.databinding.ActivityMyOrderBinding;
import com.ninositsolution.inveleapp.product_review.ProductReviewActivity;

public class MyOrderActivity extends AppCompatActivity implements IMyOrder, OnMapReadyCallback {

    ActivityMyOrderBinding binding;
    BottomSheetBehavior bottomSheetBehavior_delivered, bottomSheetBehavior_storepick;
    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*setContentView(R.layout.activity_my_order);*/
        binding = DataBindingUtil.setContentView(this, R.layout.activity_my_order);
        binding.setMyOrder(new MyOrderVM(getApplicationContext(), this));
        bottomSheetBehavior_delivered = BottomSheetBehavior.from(binding.bottomSheetTrackorder);
        bottomSheetBehavior_storepick = BottomSheetBehavior.from(binding.bottomSheetStore);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onBackClicked() {
        super.onBackPressed();
    }

    @Override
    public void setRecyclerAdapter(MyOrderAdapter myOrderAdapter) {

            binding.orderRecyclerView.setHasFixedSize(true);
            binding.orderRecyclerView.setLayoutManager(new LinearLayoutManager(this));
            binding.orderRecyclerView.setAdapter(myOrderAdapter);
    }

    @Override
    public void reviewClicked(int position) {

        startActivity(new Intent(this, ProductReviewActivity.class));
    }

    @Override
    public void onTrackClicked(int position) {

            if (position%2 == 0)
            {
                if (bottomSheetBehavior_storepick.getState() == BottomSheetBehavior.STATE_HALF_EXPANDED)
                    bottomSheetBehavior_storepick.setState(BottomSheetBehavior.STATE_COLLAPSED);

                else
                    bottomSheetBehavior_storepick.setState(BottomSheetBehavior.STATE_EXPANDED);

               /* startActivity(new Intent(this, MapsActivity.class));*/
            }
               else
            {
                if (bottomSheetBehavior_delivered.getState() == BottomSheetBehavior.STATE_EXPANDED)
                    bottomSheetBehavior_delivered.setState(BottomSheetBehavior.STATE_COLLAPSED);

                else
                    bottomSheetBehavior_delivered.setState(BottomSheetBehavior.STATE_EXPANDED);
            }

    }


    @Override
    public void onPlaceOrderClicked() {

        startActivity(new Intent(MyOrderActivity.this, CartActivity.class));

}

    @Override
    public void onMapReady(GoogleMap googleMap) {

        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng singapore = new LatLng(1.3521, 103.8198);
        mMap.addMarker(new MarkerOptions().position(singapore).title("Singapore"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(singapore));

    }

}




