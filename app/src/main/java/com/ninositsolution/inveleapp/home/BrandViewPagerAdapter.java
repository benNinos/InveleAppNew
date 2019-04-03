package com.ninositsolution.inveleapp.home;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.ninositsolution.inveleapp.R;
import com.ninositsolution.inveleapp.databinding.ViewpagerBrandsBinding;

/**
 * Created by Parthasarathy D on 1/18/2019.
 * Ninos IT Solution Pvt Ltd
 * ben@ninositsolution.com
 */
public class BrandViewPagerAdapter extends PagerAdapter {

    private Context context;
    private LayoutInflater layoutInflater;
    private HomeVM homeVM;
    /*private int [] images = {R.drawable.b1, R.drawable.b2, R.drawable.b3,
            R.drawable.b2, R.drawable.b5, R.drawable.b6, R.drawable.b7,
            R.drawable.b8, R.drawable.b9, R.drawable.b10, R.drawable.b11,
            R.drawable.b12,R.drawable.b13, R.drawable.b14, R.drawable.b15,
            R.drawable.b16 };*/

    public BrandViewPagerAdapter(Context context, HomeVM homeVM) {
        layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.context = context;
        this.homeVM = homeVM;
    }


    @Override
    public int getCount() {
        return homeVM.brands.get().size()/4;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view == o;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

        ViewpagerBrandsBinding brandsBinding = DataBindingUtil.inflate(layoutInflater, R.layout.viewpager_brands, container, false);
        container.addView(brandsBinding.getRoot());

        int i = 4*position;

        HomeVM homeVM = new HomeVM(
                                this.homeVM.brands.get().get(i).image_path,
                                this.homeVM.brands.get().get(i+1).image_path,
                                this.homeVM.brands.get().get(i+2).image_path,
                                this.homeVM.brands.get().get(i+3).image_path
        );


        brandsBinding.setBrandViewPager(homeVM);

        return brandsBinding.getRoot();

    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {

        ViewPager viewPager = (ViewPager) container;
        View view = (View) object;
        viewPager.removeView(view);

    }
}
