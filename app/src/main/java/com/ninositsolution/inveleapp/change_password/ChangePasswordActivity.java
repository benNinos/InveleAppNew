package com.ninositsolution.inveleapp.change_password;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.ninositsolution.inveleapp.R;
import com.ninositsolution.inveleapp.databinding.ActivityChangePasswordBinding;

public class ChangePasswordActivity extends AppCompatActivity {

    ActivityChangePasswordBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_change_password);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_change_password);

        binding.setIChangePassword(new IChangePassword() {
            @Override
            public void onBackClicked() {
                onBackPressed();
            }
        });
    }
}
