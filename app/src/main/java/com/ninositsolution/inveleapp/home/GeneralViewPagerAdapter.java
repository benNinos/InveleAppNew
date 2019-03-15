package com.ninositsolution.inveleapp.home;

import android.content.Context;
import android.graphics.Paint;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ninositsolution.inveleapp.R;

/**
 * Created by Parthasarathy D on 1/18/2019.
 * Ninos IT Solution Pvt Ltd
 * ben@ninositsolution.com
 */
public class GeneralViewPagerAdapter extends PagerAdapter {

    private Context context;
    private LayoutInflater layoutInflater;
    private int [] images = {R.drawable.category1, R.drawable.category2,R.drawable.category3, R.drawable.category4,R.drawable.category1, R.drawable.category2};

    public GeneralViewPagerAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view == o;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.viewpager_home, null);
        int i = 0;

        if (position<4)
        {
            if (position == 0)
            {
                i = position;

                ImageView general_image_1 = view.findViewById(R.id.image_general_1);
                ImageView general_image_2 = view.findViewById(R.id.image_general_2);

                TextView delete_rate_1 = view.findViewById(R.id.home_viewpager_delete_text1);
                TextView delete_rate_2 = view.findViewById(R.id.home_viewpager_delete_text2);

                delete_rate_1.setPaintFlags(delete_rate_1.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                delete_rate_2.setPaintFlags(delete_rate_2.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);

                general_image_1.setImageResource(images[i]);
                general_image_2.setImageResource(images[i+1]);
            }
            else
            {
                i = position*2;

                ImageView general_image_1 = view.findViewById(R.id.image_general_1);
                ImageView general_image_2 = view.findViewById(R.id.image_general_2);

                TextView delete_rate_1 = view.findViewById(R.id.home_viewpager_delete_text1);
                TextView delete_rate_2 = view.findViewById(R.id.home_viewpager_delete_text2);

                delete_rate_1.setPaintFlags(delete_rate_1.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                delete_rate_2.setPaintFlags(delete_rate_2.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);

                general_image_1.setImageResource(images[i]);
                general_image_2.setImageResource(images[i+1]);
            }
        }


        ViewPager viewPager = (ViewPager) container;
        viewPager.addView(view, 0);

        return view;

    }


    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {

        ViewPager viewPager = (ViewPager) container;
        View view = (View) object;
        viewPager.removeView(view);

    }

}
