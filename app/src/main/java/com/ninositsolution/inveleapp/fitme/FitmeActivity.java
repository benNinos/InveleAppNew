package com.ninositsolution.inveleapp.fitme;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.ninositsolution.inveleapp.R;
import com.ninositsolution.inveleapp.databinding.ActivityFitmeBinding;

public class FitmeActivity extends AppCompatActivity {
    ActivityFitmeBinding binding;
    FitmeVM fitmeVMGlobal;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_fitme);
        fitmeVMGlobal = ViewModelProviders.of(this).get(FitmeVM.class);
        binding.setFitme(fitmeVMGlobal);
        binding.setLifecycleOwner(this);
        context = FitmeActivity.this;




        binding.setIFitme(new IFitme() {
            @Override
            public void onDisabledMenClicked() {

                binding.disabledWomen.setBackgroundColor(getResources().getColor(R.color.ash_color));
                binding.womenTextView.setTextColor(getResources().getColor(R.color.black));
                binding.enabledMen.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                binding.menTextView.setTextColor(getResources().getColor(R.color.white));

            }

            @Override
            public void onDisabledWomenClicked() {

                binding.enabledMen.setBackgroundColor(getResources().getColor(R.color.ash_color));
                binding.menTextView.setTextColor(getResources().getColor(R.color.black));
                binding.disabledWomen.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                binding.womenTextView.setTextColor(getResources().getColor(R.color.white));

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
            public void onEnabledInchesClicked() {

                binding.disabledCM.setBackgroundColor(getResources().getColor(R.color.ash_color));
                binding.enabledInches.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                binding.cmTextView.setTextColor(getResources().getColor(R.color.black));
                binding.inchesTextView.setTextColor(getResources().getColor(R.color.white));

            }
        });


    }
}
