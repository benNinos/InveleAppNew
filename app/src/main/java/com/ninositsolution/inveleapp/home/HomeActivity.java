package com.ninositsolution.inveleapp.home;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.Paint;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.telecom.TelecomManager;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ninositsolution.inveleapp.R;
import com.ninositsolution.inveleapp.account.AccountActivity;
import com.ninositsolution.inveleapp.all_brands.AllBrandsActivity;
import com.ninositsolution.inveleapp.cart.CartActivity;
import com.ninositsolution.inveleapp.categories.CategoryActivity;
import com.ninositsolution.inveleapp.databinding.ActivityHomeBinding;
import com.ninositsolution.inveleapp.login.LoginActivity;
import com.ninositsolution.inveleapp.no_internet_connection.InternetConnectionActivity;
import com.ninositsolution.inveleapp.product_detail_page.ProductDetailActivity;
import com.ninositsolution.inveleapp.search.SearchActivity;
import com.ninositsolution.inveleapp.utils.NetworkUtil;
import com.ninositsolution.inveleapp.utils.Session;
import com.ninositsolution.inveleapp.wishlist.WishlistActivity;

import java.util.Timer;
import java.util.TimerTask;

public class HomeActivity extends AppCompatActivity implements IHome{

    private static final String TAG = "HomeActivity";

    ActivityHomeBinding binding;

    RelativeLayout itemLayout;

    private int dotsCount;
    private ImageView[] dots;
    private ViewPagerAdapter viewPagerAdapter;

    int currentPage = 0;
    Timer timer;
    final long DELAY_MS = 500; //delay in milliseconds before task is to be executed
    final long PERIOD_MS = 2000; // time in milliseconds between successive task executions.

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {

                case R.id.navigation_home:

                    return false;

                case R.id.navigation_categories:
                    startActivity(new Intent(HomeActivity.this, CategoryActivity.class));
                    return false;

                case R.id.navigation_cart:
                    startActivity(new Intent(HomeActivity.this, LoginActivity.class));
                    return false;

                case R.id.navigation_account:
                    startActivity(new Intent(HomeActivity.this, AccountActivity.class));
                    return false;
            }
            return false;
        }
    };
    private NetworkUtil networkUtil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_home);
        binding.setHome(new HomeVM(getApplicationContext(), this));

        saveDeviceId();

        binding.navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        putStrikeThrough();

        viewPagerAdapter = new ViewPagerAdapter(this);
        BrandViewPagerAdapter brandViewPagerAdapter = new BrandViewPagerAdapter(this);
        UnderViewPagerAdapter underViewPagerAdapter = new UnderViewPagerAdapter(this);
        GeneralViewPagerAdapter generalViewPagerAdapter = new GeneralViewPagerAdapter(this);
        HomeThreeImageViewPagerAdapter homeThreeImageViewPagerAdapter = new HomeThreeImageViewPagerAdapter(this);

        binding.homeThreeImageViewPager.setAdapter(homeThreeImageViewPagerAdapter);
        binding.viewpagerBrands.setAdapter(brandViewPagerAdapter);
        binding.viewpagerMens.setAdapter(generalViewPagerAdapter);
        binding.viewpagerWomens.setAdapter(generalViewPagerAdapter);
        binding.viewpagerElectronics.setAdapter(generalViewPagerAdapter);

        binding.viewPager.setAdapter(viewPagerAdapter);
        binding.viewpagerUnder.setAdapter(underViewPagerAdapter);

        networkUtil = new NetworkUtil();

        binding.setPresenter(new IHomeClick() {
            @Override
            public void onCartClicked() {
                startActivity(new Intent(HomeActivity.this, CartActivity.class));
            }
        });

        binding.clicks.onClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, ProductDetailActivity.class));
            }
        });

        /*After setting the adapter use the timer */
        final Handler handler = new Handler();
        final Runnable Update = new Runnable() {
            public void run() {
                if (currentPage == viewPagerAdapter.getCount()) {
                    currentPage = 0;
                }
                binding.viewPager.setCurrentItem(currentPage++, true);
            }
        };

        timer = new Timer(); // This will create a new Thread
        timer .schedule(new TimerTask() { // task to be scheduled

            @Override
            public void run() {
                handler.post(Update);
            }
        }, DELAY_MS, PERIOD_MS);

        loadBaseBanner();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            binding.homeScrollView.setOnScrollChangeListener(new View.OnScrollChangeListener() {
                @Override
                public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {

                    Animation fadeIn = AnimationUtils.loadAnimation(HomeActivity.this, R.anim.fade_in);
                    Animation fadeOut = AnimationUtils.loadAnimation(HomeActivity.this, R.anim.fade_out);

                   if (scrollY > 3000)
                   {
                       if (binding.logoInvele1.getVisibility() == View.VISIBLE)
                       {
                           binding.logoInvele1.setVisibility(View.GONE);
                           binding.logoInvele2.setAnimation(fadeIn);
                           binding.logoInvele2.setVisibility(View.VISIBLE);
                       }

                   }

                   else
                   {
                       if (binding.logoInvele2.getVisibility() == View.VISIBLE)
                       {
                           binding.logoInvele2.setVisibility(View.GONE);
                           binding.logoInvele1.setAnimation(fadeIn);
                           binding.logoInvele1.setVisibility(View.VISIBLE);
                       }
                   }
                }
            });
        }
        binding.homeScrollView.setSmoothScrollingEnabled(true);


        binding.viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {

                for (int j = 0; j<dotsCount; j++)
                {
                    dots[j].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_dots_24dp));
                }

                dots[i].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_dots2_24dp));

            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });

    }

    private void saveDeviceId() {

        TelephonyManager telephonyManager = (TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);

        String android_id = Settings.Secure.getString(getContentResolver(), Settings.Secure.ANDROID_ID);

        Session.setDevice_id(android_id, getApplicationContext());

        Log.i(TAG, "Device_id -> "+android_id);
    }


    private void putStrikeThrough() {

        TextView flash1 = findViewById(R.id.flash_delete_rate1);
        TextView flash2 = findViewById(R.id.flash_delete_rate2);
        TextView flash3 = findViewById(R.id.flash_delete_rate3);
        TextView flash4 = findViewById(R.id.flash_delete_rate4);

        TextView trending1 = findViewById(R.id.trending_delete_rate1);
        TextView trending2 = findViewById(R.id.trending_delete_rate2);
        TextView trending3 = findViewById(R.id.trending_delete_rate3);
        TextView trending4 = findViewById(R.id.trending_delete_rate4);

        flash1.setPaintFlags(flash1.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        flash2.setPaintFlags(flash2.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        flash3.setPaintFlags(flash3.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        flash4.setPaintFlags(flash4.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);

        trending1.setPaintFlags(trending1.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        trending2.setPaintFlags(trending2.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        trending3.setPaintFlags(trending3.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        trending4.setPaintFlags(trending4.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);

    }


    private void loadBaseBanner() {

        dotsCount = viewPagerAdapter.getCount();
        dots = new ImageView[dotsCount];

        for (int i = 0; i<dotsCount; i++)
        {
            dots[i] = new ImageView(this);
            dots[i].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_dots_24dp));

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);

            params.setMargins(8, 0, 8, 0);

            binding.sliderDots.addView(dots[i], params);
        }

        dots[0].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_dots2_24dp));
    }

    @Override
    public void onWishlistClicked() {

        if (networkUtil.getNetworkStatus(this))
        {
            startActivity(new Intent(this, WishlistActivity.class));
        }

        else
        {
            startActivity(new Intent(this, InternetConnectionActivity.class));
        }
    }

    @Override
    public void onCartClicked() {
        if (networkUtil.getNetworkStatus(this))
        {
            startActivity(new Intent(this, CartActivity.class));
        }

        else
        {
            startActivity(new Intent(this, InternetConnectionActivity.class));
        }
    }

    @Override
    public void onSearchClicked() {

        if (networkUtil.getNetworkStatus(this))
        {
            startActivity(new Intent(this, SearchActivity.class));
        }

        else
        {
            startActivity(new Intent(this, InternetConnectionActivity.class));
        }
    }

    @Override
    public void onItemClicked() {
        if (networkUtil.getNetworkStatus(this))
        {
            startActivity(new Intent(this, ProductDetailActivity.class));
        }

        else
        {
            startActivity(new Intent(this, InternetConnectionActivity.class));
        }
    }

    @Override
    public void onUsernameClicked() {

        if (networkUtil.getNetworkStatus(this))
        {
            startActivity(new Intent(this, AccountActivity.class));
        }

        else
        {
            startActivity(new Intent(this, InternetConnectionActivity.class));
        }

    }

    @Override
    public void onBrandMoreClicked() {
        if (networkUtil.getNetworkStatus(this))
        {
            startActivity(new Intent(this, AllBrandsActivity.class));
        }

        else
        {
            startActivity(new Intent(this, InternetConnectionActivity.class));
        }
    }
}