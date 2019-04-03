package com.ninositsolution.inveleapp.view_store;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.ninositsolution.inveleapp.R;
import com.ninositsolution.inveleapp.databinding.ActivityViewStoreBinding;
import com.ninositsolution.inveleapp.home.GeneralViewPagerAdapter;

public class ViewStoreActivity extends AppCompatActivity implements IViewStore{

    private ActivityViewStoreBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_view_store);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_view_store);
        binding.setViewStore(new ViewStoreVM(getApplicationContext(), this));

        GeneralViewPagerAdapter generalViewPagerAdapter = new GeneralViewPagerAdapter(this);

        binding.viewpagerMens.setAdapter(generalViewPagerAdapter);
        binding.viewpagerWomens.setAdapter(generalViewPagerAdapter);
        binding.viewpagerElectronics.setAdapter(generalViewPagerAdapter);
    }

    @Override
    public void onBackClicked() {
        super.onBackPressed();
    }
}
