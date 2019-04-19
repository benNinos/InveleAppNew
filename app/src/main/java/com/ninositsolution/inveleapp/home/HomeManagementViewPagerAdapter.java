package com.ninositsolution.inveleapp.home;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.ninositsolution.inveleapp.R;
import com.ninositsolution.inveleapp.databinding.PagerHomeManagementBinding;
import com.ninositsolution.inveleapp.pojo.HomeArrayLists;

public class HomeManagementViewPagerAdapter extends PagerAdapter {

    private Context context;
    private HomeArrayLists homeArrayLists;
    private LayoutInflater layoutInflater;
    private IHomeClick iHomeClick;

    public HomeManagementViewPagerAdapter(Context context, HomeArrayLists homeArrayLists, IHomeClick iHomeClick) {
        layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.context = context;
        this.homeArrayLists = homeArrayLists;
        this.iHomeClick = iHomeClick;
    }

    @Override
    public int getCount() {
        return homeArrayLists.home_management_products.size()/2;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view == o;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

        final PagerHomeManagementBinding binding = DataBindingUtil.inflate(layoutInflater, R.layout.pager_home_management, container, false);
        container.addView(binding.getRoot());

        final int i = 2*position;

        final HomeVM homeVM = new HomeVM(homeArrayLists.home_management_products.get(i), homeArrayLists.home_management_products.get(i+1));
        binding.setPagerHomeManagement(homeVM);

        if (homeArrayLists.home_management_products.get(i).wishlist == 1)
            showLeftWishList(binding);

        if (homeArrayLists.home_management_products.get(i+1).wishlist == 1)
            showRightWishList(binding);

        binding.wishlistLeft0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showLeftWishList(binding);
                iHomeClick.updateWishlist(homeArrayLists.home_management_products.get(i).product_id, 1);
            }
        });

        binding.wishlistLeft1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeLeftWishList(binding);
                iHomeClick.updateWishlist(homeArrayLists.home_management_products.get(i).product_id, 0);
            }
        });

        binding.wishlistRight0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showRightWishList(binding);
                iHomeClick.updateWishlist(homeArrayLists.home_management_products.get(i+1).product_id, 1);
            }
        });

        binding.wishlistRight1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeRightWishList(binding);
                iHomeClick.updateWishlist(homeArrayLists.home_management_products.get(i+1).product_id, 0);
            }
        });

        return binding.getRoot();
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        ViewPager viewPager = (ViewPager) container;
        View view = (View) object;
        viewPager.removeView(view);
    }

    private void showLeftWishList(PagerHomeManagementBinding binding)
    {
        binding.wishlistLeft0.setVisibility(View.GONE);
        binding.wishlistLeft1.setVisibility(View.VISIBLE);
    }

    private void showRightWishList(PagerHomeManagementBinding binding)
    {
        binding.wishlistRight0.setVisibility(View.GONE);
        binding.wishlistRight1.setVisibility(View.VISIBLE);
    }

    private void removeLeftWishList(PagerHomeManagementBinding binding)
    {
        binding.wishlistLeft0.setVisibility(View.VISIBLE);
        binding.wishlistLeft1.setVisibility(View.GONE);
    }

    private void removeRightWishList(PagerHomeManagementBinding binding)
    {
        binding.wishlistRight0.setVisibility(View.VISIBLE);
        binding.wishlistRight1.setVisibility(View.GONE);
    }

}
