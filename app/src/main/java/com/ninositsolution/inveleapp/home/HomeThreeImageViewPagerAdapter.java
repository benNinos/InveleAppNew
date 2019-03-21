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
import com.ninositsolution.inveleapp.databinding.AdapterHomeThreeImageBinding;
import com.squareup.picasso.Picasso;

public class HomeThreeImageViewPagerAdapter extends PagerAdapter {

    private static final String TAG = "SubBannerAdapter";

    private Context context;
    private LayoutInflater layoutInflater;
    private HomeVM homeVM;


    public HomeThreeImageViewPagerAdapter(Context context, HomeVM homeVM) {
        layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.context = context;
        this.homeVM = homeVM;
    }

    @Override
    public int getCount() {
        return 1;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view == o;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

        AdapterHomeThreeImageBinding binding = DataBindingUtil.inflate(layoutInflater, R.layout.adapter_home_three_image, container, false);
        container.addView(binding.getRoot());

        HomeVM homeVM = new HomeVM(
                this.homeVM.sub_banners.get().get(position).image_path,
                this.homeVM.sub_banners.get().get(position+1).image_path,
                this.homeVM.sub_banners.get().get(position+2).image_path);

        binding.setAdapterSubBanner(homeVM);


        return binding.getRoot();
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {

        ViewPager viewPager = (ViewPager) container;
        View view = (View) object;
        viewPager.removeView(view);
    }
}
