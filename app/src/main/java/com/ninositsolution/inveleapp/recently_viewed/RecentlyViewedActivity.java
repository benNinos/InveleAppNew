package com.ninositsolution.inveleapp.recently_viewed;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;

import com.ninositsolution.inveleapp.R;
import com.ninositsolution.inveleapp.databinding.ActivityRecentlyViewedBinding;

public class RecentlyViewedActivity extends AppCompatActivity implements IRecentlyViewed{

    ActivityRecentlyViewedBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setContentView(R.layout.activity_recently_viewed);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_recently_viewed);
        binding.setRecentlyViewed(new RecentlyViewedVM(getApplicationContext(), this));
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public void onBackClicked() {
        super.onBackPressed();
    }

    @Override
    public void setRecyclerAdapter(RecentlyViewedAdapter recyclerAdapter) {

        binding.recentlyViewedRecyclerview.setHasFixedSize(true);
        binding.recentlyViewedRecyclerview.setLayoutManager(new LinearLayoutManager(this));
        binding.recentlyViewedRecyclerview.setAdapter(recyclerAdapter);

    }
}
