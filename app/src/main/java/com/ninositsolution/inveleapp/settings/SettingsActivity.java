package com.ninositsolution.inveleapp.settings;

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
import com.ninositsolution.inveleapp.personal_information.PersonalInformationActivity;
import com.ninositsolution.inveleapp.social_media.SocialMediaActivity;

public class SettingsActivity extends AppCompatActivity implements ISettings{

    ActivitySettingsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_settings);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_settings);
        binding.setSettings(new SettingsVM(getApplicationContext(), this));
    }

    @Override
    public void onBackClicked() {
        startActivity(new Intent(this, AccountActivity.class));
    }

    @Override
    public void AccountInfoClicked() {
        startActivity(new Intent(this, AccountInformationActivity.class));
    }

    @Override
    public void AddressBookClicked() {
        startActivity(new Intent(this, AddressBookActivity.class));
    }

    @Override
    public void PersonalInfoClicked() {
        startActivity(new Intent(this, PersonalInformationActivity.class));
    }

    @Override
    public void onSocialMediaClicked() {
        /*Toast.makeText(this, "Under Production", Toast.LENGTH_SHORT).show();*/
        startActivity(new Intent(this, SocialMediaActivity.class));
    }

    @Override
    public void onPopupsClicked() {
        Toast.makeText(this, "Under Production", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRequestAccountDeletionClicked() {
        Toast.makeText(this, "Under Production", Toast.LENGTH_SHORT).show();
    }
}
