package com.ninositsolution.inveleapp.no_internet_connection;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.ninositsolution.inveleapp.R;
import com.ninositsolution.inveleapp.databinding.ActivityInternetConnectionBinding;

public class InternetConnectionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // setContentView(R.layout.activity_internet_connection);
        ActivityInternetConnectionBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_internet_connection);

        final InternetConnectionVM internetConnectionVM = ViewModelProviders.of(this).get(InternetConnectionVM.class);
        binding.setInternet(internetConnectionVM);

        internetConnectionVM.getConnected().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(@Nullable Boolean aBoolean) {
                if (!aBoolean)
                {
                    Toast.makeText(InternetConnectionActivity.this, "No Internet Connection", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(InternetConnectionActivity.this, InternetConnectionActivity.class));
                }
            }
        });

        binding.setIInternet(new IInternetConnection() {
            @Override
            public void onTryAgainClicked() {
                internetConnectionVM.checkInternetConnection(InternetConnectionActivity.this);
            }

            @Override
            public void onBackClicked() {
                onBackPressed();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
