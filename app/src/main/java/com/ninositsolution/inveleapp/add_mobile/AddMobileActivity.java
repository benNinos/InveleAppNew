package com.ninositsolution.inveleapp.add_mobile;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.ninositsolution.inveleapp.R;
import com.ninositsolution.inveleapp.databinding.ActivityAddMobileBinding;

public class AddMobileActivity extends AppCompatActivity {

    ActivityAddMobileBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_add_mobile);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_add_mobile);

        binding.setIAddMobile(new IAddMobile() {
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
