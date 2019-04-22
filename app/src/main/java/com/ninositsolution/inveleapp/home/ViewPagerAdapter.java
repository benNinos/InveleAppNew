package com.ninositsolution.inveleapp.home;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.ninositsolution.inveleapp.R;
import com.ninositsolution.inveleapp.databinding.AdapterMainBannerBinding;
import com.squareup.picasso.Picasso;

/**
 * Created by Parthasarathy D on 1/17/2019.
 * Ninos IT Solution Pvt Ltd
 * ben@ninositsolution.com
 */
public class ViewPagerAdapter extends PagerAdapter {

    private static final String TAG = "ViewPagerAdapter";

    private Context context;
    private LayoutInflater layoutInflater;
    //private int [] images = {R.drawable.banner1, R.drawable.banner3, R.drawable.banner4, R.drawable.banner8};
    private HomeVM homeVM;

    public ViewPagerAdapter(Context context, HomeVM homeVM) {
        layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.context = context;
        this.homeVM = homeVM;
    }

    @Override
    public int getCount() {
        return homeVM.main_banners.get().size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view == o;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

        AdapterMainBannerBinding binding = DataBindingUtil.inflate(layoutInflater, R.layout.adapter_main_banner, container, false);
        container.addView(binding.getRoot());

        //Log.i(TAG, "Image - > "+homeVM.main_banners.get().get(position).image_path);

        binding.setAdapterMainBanner(new HomeVM(homeVM.main_banners.get().get(position).image_path));

        //Picasso.get().load(homeVM.main_banners.get().get(position).image_path).placeholder(R.drawable.placeholder).into(binding.sliderImage);

        return binding.getRoot();
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {

        ViewPager viewPager = (ViewPager) container;
        View view = (View) object;
        viewPager.removeView(view);

    }
}
