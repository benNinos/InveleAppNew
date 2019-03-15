package com.ninositsolution.inveleapp.account_information;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.ninositsolution.inveleapp.R;
import com.ninositsolution.inveleapp.add_mobile.AddMobileActivity;
import com.ninositsolution.inveleapp.add_mobile.AddMobileVM;
import com.ninositsolution.inveleapp.change_email.ChangeEmailActivity;
import com.ninositsolution.inveleapp.change_password.ChangePasswordActivity;
import com.ninositsolution.inveleapp.databinding.ActivityAccountInformationBinding;
import com.ninositsolution.inveleapp.settings.SettingsActivity;

public class AccountInformationActivity extends AppCompatActivity implements IAccountInformation{

    ActivityAccountInformationBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_account_information);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_account_information);
        binding.setAccountInfo(new AccountInformationVM(getApplicationContext(), this));
    }

    @Override
    public void onBackClicked() {
        startActivity(new Intent(this, SettingsActivity.class));
    }

    @Override
    public void onAddMobileClicked() {
        startActivity(new Intent(this, AddMobileActivity.class));
    }

    @Override
    public void onChangeEmailClicked() {
        startActivity(new Intent(this, ChangeEmailActivity.class));
    }

    @Override
    public void onChangePasswordClicked() {
        startActivity(new Intent(this, ChangePasswordActivity.class));
    }
}
