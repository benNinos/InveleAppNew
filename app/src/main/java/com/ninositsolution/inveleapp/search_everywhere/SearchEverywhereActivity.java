package com.ninositsolution.inveleapp.search_everywhere;

import android.annotation.SuppressLint;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.ninositsolution.inveleapp.R;
import com.ninositsolution.inveleapp.databinding.ActivitySearchEverywhereBinding;
import com.ninositsolution.inveleapp.recently_viewed.RecentlyViewedAdapter;
import com.ninositsolution.inveleapp.utils.OnSwipeTouchListener;
import com.ninositsolution.inveleapp.utils.Session;

import java.io.Serializable;
import java.util.Objects;

public class SearchEverywhereActivity extends AppCompatActivity {

    ActivitySearchEverywhereBinding binding;
    SearchEverywhereVM searchEverywhereVM, vmInstance;
    Context context;
    private static final String TAG = "SearchEverywherActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        vmInstance = new SearchEverywhereVM();

        binding = DataBindingUtil.setContentView(this, R.layout.activity_search_everywhere);
        searchEverywhereVM = ViewModelProviders.of(this).get(SearchEverywhereVM.class);
        binding.setSearchEverywhere(searchEverywhereVM);
        binding.setLifecycleOwner(this);

        context = SearchEverywhereActivity.this;

        showProgressBar();

        searchEverywhereVM.getBySearchApi(Session.getUserId(context), "shirts", "new");

        searchEverywhereVM.getSearchEverywhereVMMutableLiveData().observe(this, new Observer<SearchEverywhereVM>() {
            @Override
            public void onChanged(@Nullable SearchEverywhereVM searchEverywhereVM) {

                hideProgressBar();
                String status = searchEverywhereVM.status.get();
                String msg = searchEverywhereVM.msg.get();

                if (!status.isEmpty())
                {
                    if (status.equalsIgnoreCase("success"))
                    {

                        Toast.makeText(SearchEverywhereActivity.this, ""+msg, Toast.LENGTH_SHORT).show();
                        // handle success process

                        SearchEveryWhereAdapter adapter = new SearchEveryWhereAdapter(context, searchEverywhereVM);
                        binding.recentlyViewedRecyclerview.setHasFixedSize(true);
                        binding.recentlyViewedRecyclerview.setLayoutManager(new GridLayoutManager(context, 2));
                        binding.recentlyViewedRecyclerview.setAdapter(adapter);

                        vmInstance = searchEverywhereVM;

                    } else  {

                        Toast.makeText(SearchEverywhereActivity.this, ""+msg, Toast.LENGTH_SHORT).show();
                        // handle failure process

                    }
                } else
                {
                    Log.e(TAG, "getBySearchApiError : Api Error");
                }

            }
        });

        searchEverywhereVM.getSearchFilterUpdateLiveData().observe(this, new Observer<SearchEverywhereVM>() {
            @Override
            public void onChanged(@Nullable SearchEverywhereVM searchEverywhereVM) {
                Toast.makeText(SearchEverywhereActivity.this, "Filter updated", Toast.LENGTH_SHORT).show();
            }
        });



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

                showProgressBar();

                searchEverywhereVM.getBySearchApi(Session.getUserId(context), "shirts", "low");

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        binding.priceFilterLayout.setVisibility(View.GONE);
                    }
                },400);
            }

            @Override
            public void onHighClicked() {
                binding.high.setTextColor(getResources().getColor(R.color.colorPrimary));

                binding.low.setTextColor(getResources().getColor(R.color.textColor));

                showProgressBar();

                searchEverywhereVM.getBySearchApi(Session.getUserId(context), "shirts", "high");

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        binding.priceFilterLayout.setVisibility(View.GONE);
                    }
                },400);
            }


            @Override
            public void onFilterClicked() {
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                //FilterFragment filterFragment = new FilterFragment();
                Fragment filterFragment = FilterFragment.fragmentInstance(vmInstance);
                fragmentTransaction.setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_right);
                fragmentTransaction.add(R.id.filter_container, filterFragment);
                fragmentTransaction.commit();

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    binding.searchAppbarLayout.setForeground(getResources().getDrawable(R.drawable.window_dim));
                    binding.searchBodyLayout.setForeground(getResources().getDrawable(R.drawable.window_dim));
                    binding.searchAppbarLayout.getForeground().setAlpha(180);
                    binding.searchBodyLayout.getForeground().setAlpha(180);
                }


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


    public void clearAlpha()
    {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            binding.searchBodyLayout.setForeground(null);
            binding.searchAppbarLayout.setForeground(null);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    public void showProgressBar()
    {
        if (binding.searchEverywhereProgress.getVisibility() == View.GONE)
            binding.searchEverywhereProgress.setVisibility(View.VISIBLE);
    }

    public void hideProgressBar()
    {
        if (binding.searchEverywhereProgress.getVisibility() == View.VISIBLE)
            binding.searchEverywhereProgress.setVisibility(View.GONE);
    }
}
