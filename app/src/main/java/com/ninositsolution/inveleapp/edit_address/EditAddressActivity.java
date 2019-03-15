package com.ninositsolution.inveleapp.edit_address;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.ninositsolution.inveleapp.R;
import com.ninositsolution.inveleapp.databinding.ActivityEditAddressBinding;

public class EditAddressActivity extends AppCompatActivity implements IEditAddress{

    ActivityEditAddressBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_edit_address);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_edit_address);
        binding.setEditAddress(new EditAddressVM(getApplicationContext(),this));
    }

    @Override
    public void onBackClicked() {
        super.onBackPressed();
    }

    @Override
    public void onUpdateClicked() {

    }
}
