package com.ninositsolution.inveleapp.address_book;

import android.app.Activity;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.widget.Toast;

import com.ninositsolution.inveleapp.R;
import com.ninositsolution.inveleapp.add_address.AddAddressActivity;
import com.ninositsolution.inveleapp.address_book.pojo.AddressUpdateRequest;
import com.ninositsolution.inveleapp.databinding.ActivityAddressBookBinding;
import com.ninositsolution.inveleapp.edit_address.EditAddressActivity;
import com.ninositsolution.inveleapp.edit_address.EditAddressModel;
import com.ninositsolution.inveleapp.edit_address.EditAddressVM;
import com.ninositsolution.inveleapp.utils.Session;

import java.util.ArrayList;
import java.util.List;

public class AddressBookActivity extends AppCompatActivity implements IAddressBook{

    ActivityAddressBookBinding binding;
    private AddressBookAdapter addressBookAdapter;
    AddressBookVM addressBookVM;
    IAddressBook iAddressBook;
    Activity activity;
    String select_id="";
    EditAddressVM editAddressVM;
   //ArrayList<EditAddressModel> editAddressModel;
    EditAddressModel editAddressModel;
    public static final String TAG = AddressBookActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_address_book);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_address_book);

        activity = AddressBookActivity.this;
        binding.setAddressBook(addressBookVM);
        binding.setLifecycleOwner(this);


        binding.addressBookRecyclerview.setHasFixedSize(true);
        binding.addressBookRecyclerview.setLayoutManager(new LinearLayoutManager(this));

        addressBookVM = ViewModelProviders.of(this).get(AddressBookVM.class);


        //invoke the get address api list
        addressBookVM.getAddressList(Session.getUserId(AddressBookActivity.this));
        //address list api service response

        addressBookVM.getAddressBookVMMutableLiveData().observe(this, new Observer<List<AddressBookVM>>() {
            @Override
            public void onChanged(@Nullable List<AddressBookVM> addressBookVMS) {

                Log.e(TAG,"LIST_SIZE==>"+addressBookVMS.size());

                addressBookAdapter = new AddressBookAdapter(AddressBookActivity.this, addressBookVMS);
                binding.addressBookRecyclerview.setAdapter(addressBookAdapter);
                addressBookAdapter.setClikEvent(new AddressBookAdapter.ClickEvent() {
                    @Override
                    public void setClickEventItem(int position, String id, String user_id) {
                        Log.e(TAG,"selected_id==>"+id);
                        if(!id.equalsIgnoreCase("")){
                            select_id = id;
                            addressBookVM.updateAddressDefault(id);
                        }
                    }


                    @Override
                    public void setClickEventEdit(int position, String id, String user_id) {
                        Log.e(TAG,"edit_clicked==>");

                        Intent intent = new Intent(AddressBookActivity.this,EditAddressActivity.class);
                        intent.putExtra("user_address_id",id);
                        startActivity(intent);
                    }
                });
            }
        });
        //address default update response
        addressBookVM.getDefaultUpdateVMMutableLiveData().observe(this, new Observer<AddressBookVM>() {
            @Override
            public void onChanged(@Nullable AddressBookVM addressBookVM) {
                Toast.makeText(AddressBookActivity.this,addressBookVM.msg.get(),Toast.LENGTH_SHORT).show();

                //update  the recylcerview
                //invoke the get address api list
                addressBookVM.getAddressList(Session.getUserId(AddressBookActivity.this));

            }
        });

    }

    @Override
    public void onBackClicked() {
        finish();

    }

    @Override
    public void onAddAddressClicked() {

        startActivity(new Intent(this,AddAddressActivity.class));

    }

    @Override
    public void radioButtonClicked() {

    }

    @Override
    public void onEditClicked() {
        Log.e(TAG,"onEditClicked==>");


    }

    @Override
    public void onDeleteClicked() {

    }



}
