package com.ninositsolution.inveleapp.product_detail_page;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.Window;

import com.ninositsolution.inveleapp.R;
import com.ninositsolution.inveleapp.databinding.PopupProductDetailBinding;

public class StoreLocationPopup extends Dialog {

    private PopupProductDetailBinding binding;
    private Activity activity;


    public StoreLocationPopup(@NonNull Activity activity) {
        super(activity);
        this.activity = activity;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //setContentView(R.layout.popup_product_detail);
        binding = DataBindingUtil.inflate(LayoutInflater.from(getContext()), R.layout.popup_product_detail, null,false);
        setContentView(binding.getRoot());


    }
}
