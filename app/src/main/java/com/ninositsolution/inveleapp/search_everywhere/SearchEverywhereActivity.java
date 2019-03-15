package com.ninositsolution.inveleapp.search_everywhere;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.Toast;

import com.ninositsolution.inveleapp.R;
import com.ninositsolution.inveleapp.databinding.ActivitySearchEverywhereBinding;
import com.ninositsolution.inveleapp.recently_viewed.RecentlyViewedAdapter;

public class SearchEverywhereActivity extends AppCompatActivity {

    ActivitySearchEverywhereBinding binding;
    SearchEverywhereVM searchEverywhereVM;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_search_everywhere);

        binding.setSearchEverywhere(searchEverywhereVM);

        searchEverywhereVM = ViewModelProviders.of(this).get(SearchEverywhereVM.class);

        RecentlyViewedAdapter adapter = new RecentlyViewedAdapter(this);
        binding.recentlyViewedRecyclerview.setHasFixedSize(true);
        binding.recentlyViewedRecyclerview.setLayoutManager(new LinearLayoutManager(this));
        binding.recentlyViewedRecyclerview.setAdapter(adapter);

        binding.priceFilterLayout.setVisibility(View.GONE);

        binding.setISearchEveryWhere(new ISearchEverywhere() {
            @Override
            public void onBackClicked() {
                    onBackPressed();
            }

            @Override
            public void onPriceClicked() {
                if (binding.priceFilterLayout.getVisibility() == View.VISIBLE)
                    binding.priceFilterLayout.setVisibility(View.GONE);

                else
                    binding.priceFilterLayout.setVisibility(View.VISIBLE);
            }

            @Override
            public void onLowClicked() {
                binding.low.setTextColor(getResources().getColor(R.color.colorPrimary));

                binding.high.setTextColor(getResources().getColor(R.color.textColor));
            }

            @Override
            public void onHighClicked() {
                binding.high.setTextColor(getResources().getColor(R.color.colorPrimary));

                binding.low.setTextColor(getResources().getColor(R.color.textColor));
            }

            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onFilterClicked() {
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                FilterFragment filterFragment = new FilterFragment();
                fragmentTransaction.setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_right);
                fragmentTransaction.add(R.id.filter_container, filterFragment);
                fragmentTransaction.commit();

                binding.searchAppbarLayout.setForeground(getResources().getDrawable(R.drawable.window_dim));
                binding.searchBodyLayout.setForeground(getResources().getDrawable(R.drawable.window_dim));
                binding.searchAppbarLayout.getForeground().setAlpha(180);
                binding.searchBodyLayout.getForeground().setAlpha(180);

            }

            @Override
            public void onRelevanceClicked() {
                Toast.makeText(SearchEverywhereActivity.this, "Relevance Under Construction", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onEveryWhereClicked() {
                Toast.makeText(SearchEverywhereActivity.this, "Everywhere Under Construction", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
