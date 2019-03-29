package com.ninositsolution.inveleapp.fitme;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.ninositsolution.inveleapp.R;
import com.ninositsolution.inveleapp.databinding.ActivityFitmeBinding;

import java.util.ArrayList;

import static com.ninositsolution.inveleapp.utils.Constants.FITME_MEN;
import static com.ninositsolution.inveleapp.utils.Constants.FITME_WOMEN;

public class FitmeActivity extends AppCompatActivity {
    ActivityFitmeBinding binding;

    public static final String TAG = "FitmeActivity";
    FitmeVM fitmeVMGlobal, fitmeInstance;
    Context context;
    FitmeRecyclerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_fitme);
        fitmeVMGlobal = ViewModelProviders.of(this).get(FitmeVM.class);
        binding.setFitme(fitmeVMGlobal);
        binding.setLifecycleOwner(this);


        context = FitmeActivity.this;
        Log.e(TAG, "Flow will show progress dialog");

        showProgressBar();
        Log.e(TAG, "Flow entered into next line");

        binding.fitMeRecyclerView.setHasFixedSize(true);
        binding.fitMeRecyclerView.setLayoutManager(new LinearLayoutManager(context));

        fitmeVMGlobal.getFitmeVMMutableLiveData().observe(this, new Observer<FitmeVM>() {
            @Override
            public void onChanged(@Nullable FitmeVM fitmeVM) {

                hideProgressBar();

                String status = fitmeVM.status.get();
                String msg = fitmeVM.msg.get();

                if (!status.isEmpty())
                {
                    if (status.equalsIgnoreCase("success"))
                    {
                        Toast.makeText(context, ""+msg, Toast.LENGTH_SHORT).show();

                        fitmeInstance = fitmeVM;

                        adapter = new FitmeRecyclerAdapter(context,fitmeVM, FITME_MEN);
                        binding.fitMeRecyclerView.setAdapter(adapter);

                    } else
                    {
                        Toast.makeText(context, ""+msg, Toast.LENGTH_SHORT).show();
                    }
                }


            }
        });


        binding.setIFitme(new IFitme() {
            @Override
            public void onDisabledMenClicked() {

                binding.disabledWomen.setBackgroundColor(getResources().getColor(R.color.ash_color));
                binding.womenTextView.setTextColor(getResources().getColor(R.color.black));
                binding.enabledMen.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                binding.menTextView.setTextColor(getResources().getColor(R.color.white));

                adapter = new FitmeRecyclerAdapter(context,fitmeInstance, FITME_MEN);
                binding.fitMeRecyclerView.setAdapter(adapter);

            }

            @Override
            public void onDisabledWomenClicked() {

                binding.enabledMen.setBackgroundColor(getResources().getColor(R.color.ash_color));
                binding.menTextView.setTextColor(getResources().getColor(R.color.black));
                binding.disabledWomen.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                binding.womenTextView.setTextColor(getResources().getColor(R.color.white));

                adapter = new FitmeRecyclerAdapter(context,fitmeInstance, FITME_WOMEN);
                binding.fitMeRecyclerView.setAdapter(adapter);

            }

            @Override
            public void onDisabledCMClicked() {
                binding.disabledCM.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                binding.enabledInches.setBackgroundColor(getResources().getColor(R.color.ash_color));
                binding.cmTextView.setTextColor(getResources().getColor(R.color.white));
                binding.cmTextView.setTextColor(getResources().getColor(R.color.white));
                binding.inchesTextView.setTextColor(getResources().getColor(R.color.black));

            }

            @Override
            public void onEnabledInchesClicked()
            {

                binding.disabledCM.setBackgroundColor(getResources().getColor(R.color.ash_color));
                binding.enabledInches.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                binding.cmTextView.setTextColor(getResources().getColor(R.color.black));
                binding.inchesTextView.setTextColor(getResources().getColor(R.color.white));

            }
        });


    }


    private void showProgressBar()
    {
        if (binding.fitmeProgress.getVisibility() == View.GONE)
            binding.fitmeProgress.setVisibility(View.VISIBLE);
    }

    private void hideProgressBar()
    {
        if (binding.fitmeProgress.getVisibility() == View.VISIBLE)
            binding.fitmeProgress.setVisibility(View.GONE);
    }

}
