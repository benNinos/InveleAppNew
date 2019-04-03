package com.ninositsolution.inveleapp.home;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ninositsolution.inveleapp.R;
import com.ninositsolution.inveleapp.databinding.PagerHomeManagementBinding;
import com.ninositsolution.inveleapp.pojo.HomeArrayLists;

public class HomeManagementViewPagerAdapter extends PagerAdapter {

    private Context context;
    private HomeArrayLists homeArrayLists;
    private LayoutInflater layoutInflater;

    public HomeManagementViewPagerAdapter(Context context, HomeArrayLists homeArrayLists) {
        layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.context = context;
        this.homeArrayLists = homeArrayLists;
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

        PagerHomeManagementBinding binding = DataBindingUtil.inflate(layoutInflater, R.layout.pager_home_management, container, false);
        container.addView(binding.getRoot());

        int i = 2*position;

        HomeVM homeVM = new HomeVM(homeArrayLists.home_management_products.get(i), homeArrayLists.home_management_products.get(i+1));
        binding.setPagerHomeManagement(homeVM);
        return binding.getRoot();
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        ViewPager viewPager = (ViewPager) container;
        View view = (View) object;
        viewPager.removeView(view);
    }
}
