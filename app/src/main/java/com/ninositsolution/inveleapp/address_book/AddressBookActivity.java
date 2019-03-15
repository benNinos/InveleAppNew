package com.ninositsolution.inveleapp.address_book;

import android.app.Activity;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;

import com.ninositsolution.inveleapp.R;
import com.ninositsolution.inveleapp.add_address.AddAddressActivity;
import com.ninositsolution.inveleapp.databinding.ActivityAddressBookBinding;
import com.ninositsolution.inveleapp.edit_address.EditAddressActivity;
import com.ninositsolution.inveleapp.utils.Session;

import java.util.ArrayList;
import java.util.List;

public class AddressBookActivity extends AppCompatActivity implements IAddressBook{

    ActivityAddressBookBinding binding;
    private AddressBookAdapter addressBookAdapter;
    AddressBookVM addressBookVM;
    Activity activity;
    public static final String TAG = AddressBookActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_address_book);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_address_book);

        activity = AddressBookActivity.this;
        binding.setAddressBook(addressBookVM);
        //binding.setLifecycleOwner(this);

        binding.addressBookRecyclerview.setHasFixedSize(true);
        binding.addressBookRecyclerview.setLayoutManager(new LinearLayoutManager(this));

        addressBookVM = ViewModelProviders.of(this).get(AddressBookVM.class);



        addressBookVM.getAddressList(Session.getUserId(AddressBookActivity.this));

        addressBookVM.getAddressBookVMMutableLiveData().observe(this, new Observer<List<AddressBookVM>>() {
            @Override
            public void onChanged(@Nullable List<AddressBookVM> addressBookVMS) {

                Log.e(TAG,"LIST_SIZE==>"+addressBookVMS.size());

                addressBookAdapter = new AddressBookAdapter(AddressBookActivity.this, addressBookVMS);
                binding.addressBookRecyclerview.setAdapter(addressBookAdapter);
            }


        });




    }


    @Override
    public void radioButtonClicked() {

    }

    @Override
    public void onEditClicked() {

    }

    @Override
    public void onDeleteClicked() {

    }

    @Override
    public void onBackClicked() {
        finish();

    }

    @Override
    public void onAddAddressClicked() {

        startActivity(new Intent(this,AddAddressActivity.class));

    }
}
