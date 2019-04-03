package com.ninositsolution.inveleapp.all_brands;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.ninositsolution.inveleapp.R;
import com.ninositsolution.inveleapp.all_brands.all_brands_fragments.AllFragment;
import com.ninositsolution.inveleapp.databinding.ActivityAllBrandsBinding;

public class AllBrandsActivity extends AppCompatActivity implements IAllBrands{

    ActivityAllBrandsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_all_brands);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_all_brands);

        binding.setAllBrand(new AllBrandsVM(getApplicationContext(), this));

        TabAdapter adapter = new TabAdapter(getSupportFragmentManager());

        adapter.addFragment(new AllFragment(), "All");
        adapter.addFragment(new AllFragment(), "Men");
        adapter.addFragment(new AllFragment(), "Women");

        binding.viewPager.setAdapter(adapter);
        binding.tabLayout.setupWithViewPager(binding.viewPager);


    }

    @Override
    public void onBackClick() {
        super.onBackPressed();
    }

}
