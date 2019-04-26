package com.ninositsolution.inveleapp.all_brands;

import android.app.ProgressDialog;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSmoothScroller;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TableLayout;
import android.widget.Toast;

import com.ninositsolution.inveleapp.R;
import com.ninositsolution.inveleapp.databinding.ActivityAllBrandsBinding;

public class AllBrandsActivity extends AppCompatActivity implements IAllBrands{

    ActivityAllBrandsBinding binding;
    AllBrandsVM allBrandsVM;
    ProgressDialog progressDialog;
    Context context;
    AllBrandsAdapter allBrandsAdapter;
    LinearLayoutManager layoutManager;
    static final String TAG = AllBrandsActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_all_brands);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_all_brands);

        context = this;

        allBrandsVM = ViewModelProviders.of(this).get(AllBrandsVM.class);
        binding.setAllBrand(allBrandsVM);

        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle(R.string.app_name);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.setMessage("Please wait...");
        progressDialog.show();

        layoutManager = new LinearLayoutManager(this);

        binding.allBrandsRecyclerView.setHasFixedSize(true);
        binding.allBrandsRecyclerView.setLayoutManager(layoutManager);


        allBrandsVM.getAllBrandsVMMutableLiveData().observe(this, new Observer<AllBrandsVM>() {
            @Override
            public void onChanged(@Nullable AllBrandsVM allBrandsVM) {
                if (allBrandsVM.getStatus() != null)
                {
                    hideProgress();

                    if (allBrandsVM.getStatus().equalsIgnoreCase("success"))
                    {
                        Toast.makeText(AllBrandsActivity.this, ""+allBrandsVM.getMessage(), Toast.LENGTH_SHORT).show();


                        for (int i = 0; i < allBrandsVM.getAllBrands().size(); i++)
                        {
                            binding.tabLayout.addTab(binding.tabLayout.newTab().setText(allBrandsVM.getAllBrands().get(i).name));
                        }

                        allBrandsAdapter = new AllBrandsAdapter(context, allBrandsVM.getAllBrands());
                        binding.allBrandsRecyclerView.setAdapter(allBrandsAdapter);


                    } else
                    {
                        Toast.makeText(AllBrandsActivity.this, ""+allBrandsVM.getMessage(), Toast.LENGTH_SHORT).show();
                    }

                    allBrandsVM.setStatus(null);
                }
            }
        });

        binding.setIAllBrand(this);

        binding.tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

            try {
                RecyclerView.SmoothScroller smoothScroller = new LinearSmoothScroller(context) {
                    @Override protected int getVerticalSnapPreference() {
                        return LinearSmoothScroller.SNAP_TO_START;
                    }
                };

                smoothScroller.setTargetPosition(tab.getPosition());

                layoutManager.startSmoothScroll(smoothScroller);

            } catch (NullPointerException e)
            {
                Log.e(TAG, "Tab Error -> "+e);
            }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        binding.allBrandsRecyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {

                Log.i(TAG, "visible position -> "+layoutManager.findFirstVisibleItemPosition());


                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        TabLayout.Tab tab =  binding.tabLayout.getTabAt(layoutManager.findFirstVisibleItemPosition());
                        tab.select();
                    }
                },1000);

                super.onScrollStateChanged(recyclerView, newState);
            }

        });


    }

    @Override
    public void onBackClick() {
        super.onBackPressed();
    }

    private void showProgress(String message)
    {
        progressDialog.setMessage(message);
        progressDialog.show();
    }

    private void hideProgress()
    {
        if (progressDialog.isShowing())
            progressDialog.dismiss();
    }

}