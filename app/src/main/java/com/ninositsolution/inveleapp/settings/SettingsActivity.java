package com.ninositsolution.inveleapp.settings;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;
import com.ninositsolution.inveleapp.R;
import com.ninositsolution.inveleapp.account.AccountActivity;
import com.ninositsolution.inveleapp.account_information.AccountInformationActivity;
import com.ninositsolution.inveleapp.address_book.AddressBookActivity;
import com.ninositsolution.inveleapp.databinding.ActivitySettingsBinding;
import com.ninositsolution.inveleapp.login.LoginActivity;
import com.ninositsolution.inveleapp.personal_information.PersonalInformationActivity;
import com.ninositsolution.inveleapp.social_media.SocialMediaActivity;
import com.ninositsolution.inveleapp.utils.Session;

public class SettingsActivity extends AppCompatActivity{

    private static final String TAG = "SettingsActivity";
    ActivitySettingsBinding binding;
    SettingsVM settingsVMGlobal;
    Context context;



    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_settings);
        settingsVMGlobal = ViewModelProviders.of(this).get(SettingsVM.class);
        binding.setSettings(settingsVMGlobal);
        binding.setLifecycleOwner(this);

        context = SettingsActivity.this;

        binding.setISettings(new ISettings() {
            @Override
            public void onBackClicked() {
                SettingsActivity.super.onBackPressed();

            }

            @Override
            public void AccountInfoClicked() {

                Intent intent = new Intent(SettingsActivity.this,AccountInformationActivity.class);
                startActivity(intent);

            }

            @Override
            public void AddressBookClicked() {

                Intent intent = new Intent(SettingsActivity.this, AddressBookActivity.class);
                startActivity(intent);

            }

            @Override
            public void PersonalInfoClicked() {

                Intent intent = new Intent(SettingsActivity.this,PersonalInformationActivity.class);
                startActivity(intent);

            }

            @Override
            public void onSocialMediaClicked() {

                Intent intent = new Intent(SettingsActivity.this,SocialMediaActivity.class);
                startActivity(intent);

            }

            @Override
            public void onPopupsClicked() {

            }

            @Override
            public void onRequestAccountDeletionClicked() {

            }

            @Override
            public void onLogoutButtonClicked() {

                Session.clear(context);

                Intent intent  =new Intent(SettingsActivity.this, LoginActivity.class);
                startActivity(intent);

            }
        });

    }

}


