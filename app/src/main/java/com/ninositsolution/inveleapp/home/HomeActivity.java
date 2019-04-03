package com.ninositsolution.inveleapp.home;

import android.annotation.SuppressLint;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.Paint;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
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
import android.widget.Toast;

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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class HomeActivity extends AppCompatActivity {

    private static final String TAG = "HomeActivity";

    ActivityHomeBinding binding;

    RelativeLayout itemLayout;

    private int dotsCount;
    private ImageView[] dots;
    private ViewPagerAdapter viewPagerAdapter;
    private Context context;
    private HomeVM homeVMGlobal;

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

        homeVMGlobal = ViewModelProviders.of(this).get(HomeVM.class);

        binding.setHome(homeVMGlobal);

        binding.setLifecycleOwner(this);


        context = HomeActivity.this;

        showProgressbar();

        saveDeviceId();

        binding.navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);


        UnderViewPagerAdapter underViewPagerAdapter = new UnderViewPagerAdapter(this);
        GeneralViewPagerAdapter generalViewPagerAdapter = new GeneralViewPagerAdapter(this);


        binding.viewpagerUnder.setAdapter(underViewPagerAdapter);

        networkUtil = new NetworkUtil();

        homeVMGlobal.performHomePageApi(Session.getUserId(context));

        homeVMGlobal.getHomeVMMutableLiveData().observe(this, new Observer<HomeVM>() {
            @Override
            public void onChanged(@Nullable HomeVM homeVM) {

                hideProgressbar();

                if (!homeVM.status.get().isEmpty())
                {

                    Toast.makeText(context, ""+homeVM.msg.get(), Toast.LENGTH_SHORT).show();

                    if (homeVM.status.get().equalsIgnoreCase("success"))
                    {
                       // success process

                        //main banner
                        viewPagerAdapter = new ViewPagerAdapter(context, homeVM);
                        binding.viewPager.setAdapter(viewPagerAdapter);
                        loadBaseBanner();
                        setAutolooping();

                        //sub banner
                        HomeThreeImageViewPagerAdapter homeThreeImageViewPagerAdapter = new HomeThreeImageViewPagerAdapter(context, homeVM);
                        binding.homeThreeImageViewPager.setAdapter(homeThreeImageViewPagerAdapter);

                        //categories

                        binding.categoryLayout.categoryRecyclerView.setHasFixedSize(true);
                        binding.categoryLayout.categoryRecyclerView.setLayoutManager(new GridLayoutManager(context, 2, GridLayoutManager.HORIZONTAL, false));
                        binding.categoryLayout.categoryRecyclerView.setAdapter(new CategoryAdapter(context, homeVM));

                        //deal products
                        binding.deals.dealHead.setText(homeVM.caption.get());

                        setTimer(homeVM.end_date_time.get());

                        binding.deals.dealRecyclerView.setHasFixedSize(true);
                        binding.deals.dealRecyclerView.setLayoutManager(new GridLayoutManager(context, 2));
                        binding.deals.dealRecyclerView.setAdapter(new DealAdapter(context, homeVM));

                        //Trending Products
                        binding.trending.trendingRecyclerView.setHasFixedSize(true);
                        binding.trending.trendingRecyclerView.setLayoutManager(new GridLayoutManager(context, 2));
                        binding.trending.trendingRecyclerView.setAdapter(new TrendingProductsAdapter(context, homeVM));

                        //Brands
                        BrandViewPagerAdapter brandViewPagerAdapter = new BrandViewPagerAdapter(context, homeVM);
                        binding.viewpagerBrands.setAdapter(brandViewPagerAdapter);

                        //Home Management
                        binding.homeManagementRecyclerView.setHasFixedSize(true);
                        binding.homeManagementRecyclerView.setLayoutManager(new LinearLayoutManager(context));
                        binding.homeManagementRecyclerView.setAdapter(new HomeManagementAdapter(context, homeVM));

                    } else
                    {
                       // error process

                    }

                    homeVM.status.set("");

                } else
                {
                   // Toast.makeText(context, "Api Error", Toast.LENGTH_SHORT).show();
                }
            }
        });

        binding.setPresenter(new IHomeClick() {
            @Override
            public void onWishlistClicked() {

                if (networkUtil.getNetworkStatus(context))
                {
                    startActivity(new Intent(context, WishlistActivity.class));
                }

                else
                {
                    startActivity(new Intent(context, InternetConnectionActivity.class));
                }

            }

            @Override
            public void onCartClicked() {
                if (networkUtil.getNetworkStatus(context))
                {
                    startActivity(new Intent(context, CartActivity.class));
                }

                else
                {
                    startActivity(new Intent(context, InternetConnectionActivity.class));
                }
            }

            @Override
            public void onSearchClicked() {

                if (networkUtil.getNetworkStatus(context))
                {
                    startActivity(new Intent(context, SearchActivity.class));
                }

                else
                {
                    startActivity(new Intent(context, InternetConnectionActivity.class));
                }

            }

            @Override
            public void onItemClicked() {

                if (networkUtil.getNetworkStatus(context))
                {
                    startActivity(new Intent(context, ProductDetailActivity.class));
                }

                else
                {
                    startActivity(new Intent(context, InternetConnectionActivity.class));
                }

            }

            @Override
            public void onUsernameClicked() {

                if (networkUtil.getNetworkStatus(context))
                {
                    startActivity(new Intent(context, AccountActivity.class));
                }

                else
                {
                    startActivity(new Intent(context, InternetConnectionActivity.class));
                }


            }

            @Override
            public void onBrandMoreClicked() {

                if (networkUtil.getNetworkStatus(context))
                {
                    startActivity(new Intent(context, AllBrandsActivity.class));
                }

                else
                {
                    startActivity(new Intent(context, InternetConnectionActivity.class));
                }

            }
        });

        /*binding.clicks.onClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, ProductDetailActivity.class));
            }
        });*/

      /*  *//*After setting the adapter use the timer *//*
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
        }, DELAY_MS, PERIOD_MS);*/

        //loadBaseBanner();

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

    private void setAutolooping() {

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
    }

    private void setTimer(String s) {

        @SuppressLint("SimpleDateFormat")
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        try {
            Date endDate = format.parse(s);
            Date startDate = Calendar.getInstance().getTime();

            long milliSeconds = endDate.getTime() - startDate.getTime();

            new CountDownTimer(milliSeconds, 1000) {


                @Override
                public void onTick(long millisUntilFinished) {

                    long seconds = millisUntilFinished/1000;

                    @SuppressLint("DefaultLocale")
                    String result = String.format("%02d : %02d : %02d", seconds / 3600, (seconds % 3600) / 60, (seconds % 60));

                    binding.deals.dealTimer.setText(result);

                }

                @Override
                public void onFinish() {

                    binding.deals.dealTimer.setText("Deal Ends");

                }
            }.start();


        } catch (ParseException e) {
            e.printStackTrace();
            binding.deals.dealTimer.setText("Error");
        }

    }

    private void saveDeviceId() {

        TelephonyManager telephonyManager = (TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);

        String android_id = Settings.Secure.getString(getContentResolver(), Settings.Secure.ANDROID_ID);

        Session.setDevice_id(android_id, getApplicationContext());

        Log.i(TAG, "Device_id -> "+android_id);
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

    private void showProgressbar()
    {
        if (binding.homeProgressBar.getVisibility() == View.GONE)
            binding.homeProgressBar.setVisibility(View.VISIBLE);
    }

    private void hideProgressbar()
    {
        if (binding.homeProgressBar.getVisibility() == View.VISIBLE)
            binding.homeProgressBar.setVisibility(View.GONE);
    }
}