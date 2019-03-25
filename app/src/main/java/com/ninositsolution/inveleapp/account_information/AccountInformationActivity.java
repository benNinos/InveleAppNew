package com.ninositsolution.inveleapp.account_information;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.ninositsolution.inveleapp.R;
import com.ninositsolution.inveleapp.add_mobile.AddMobileActivity;
import com.ninositsolution.inveleapp.add_mobile.AddMobileVM;
import com.ninositsolution.inveleapp.change_email.ChangeEmailActivity;
import com.ninositsolution.inveleapp.change_password.ChangePasswordActivity;
import com.ninositsolution.inveleapp.databinding.ActivityAccountInformationBinding;
import com.ninositsolution.inveleapp.settings.SettingsActivity;
import com.ninositsolution.inveleapp.utils.Session;

public class AccountInformationActivity extends AppCompatActivity {

    public static final String TAG = "AccountActivity";

    ActivityAccountInformationBinding binding;
    AccountInformationVM accountInformationVM;
    Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_account_information);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_account_information);
        accountInformationVM = ViewModelProviders.of(this).get(AccountInformationVM.class);
        binding.setAccountInfo(accountInformationVM);
        binding.setLifecycleOwner(this);

        context = AccountInformationActivity.this;

        if (Session.getUserEmail(context) != null)
        {
            if (Session.getUserEmail(context).isEmpty())
            {


                Log.e(TAG, "Stored Email is : -->" + Session.getUserEmail(context));
            }
            else
            {
                accountInformationVM.emailAddress.set(Session.getUserEmail(context));
                Log.e(TAG, "Stored Email is : -->" + Session.getUserEmail(context));
            }
        }

        if (Session.getUserPhone(context) != null)
        {
            if (Session.getUserPhone(context).isEmpty())
            {
                accountInformationVM.mobileNumber.set("Not Set");

                Log.e(TAG, "Stored Mobile is : -->" + Session.getUserPhone(context));
            }
            else
            {
                accountInformationVM.mobileNumber.set(Session.getUserPhone(context));
                Log.e(TAG, "Stored Mobile is : -->" + Session.getUserPhone(context));
            }
        }




        binding.setIaccountInfo(new IAccountInformation() {
            @Override
            public void onBackClicked() {

            }

            @Override
            public void onAddMobileClicked() {

                Intent intent = new Intent(AccountInformationActivity.this, AddMobileActivity.class);
                startActivity(intent);

            }

            @Override
            public void onChangeEmailClicked() {
                Intent intent = new Intent(AccountInformationActivity.this, ChangeEmailActivity.class);
                startActivity(intent);


            }

            @Override
            public void onChangePasswordClicked() {

                Intent intent = new Intent(AccountInformationActivity.this, ChangePasswordActivity.class);
                startActivity(intent);


            }
        });


    }


}
