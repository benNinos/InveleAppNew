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

public class AddressBookActivity extends AppCompatActivity{

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

                Log.e(TAG, "LIST_SIZE==>" + addressBookVMS.size());
                if (!addressBookVMS.isEmpty()){

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
                            Session.setUserId(user_id,AddressBookActivity.this);

                            Intent intent = new Intent(AddressBookActivity.this,EditAddressActivity.class);
                            intent.putExtra("user_address_id",id);
                            startActivity(intent);
                        }

                        @Override
                        public void setClickEventDelete(int position, String id, String user_id) {
                            Session.setUserId(user_id,AddressBookActivity.this);

                            if(!id.equalsIgnoreCase("")){
                                select_id = id;
                                addressBookVM.addressDelete(id);
                            }
                        }


                    });

                }else {
                    Toast.makeText(getApplicationContext(),"List Empty",Toast.LENGTH_SHORT).show();
                }
                            }
        });
        //address default update response
        addressBookVM.getDefaultUpdateVMMutableLiveData().observe(this, new Observer<AddressBookVM>() {
            @Override
            public void onChanged(@Nullable AddressBookVM addressBookVM1) {
                if(addressBookVM1.status.get().equalsIgnoreCase("success")) {
                    Toast.makeText(AddressBookActivity.this, addressBookVM1.msg.get(), Toast.LENGTH_SHORT).show();

                    //update  the recylcerview
                    //invoke the get address api list
                    addressBookVM.getAddressList(Session.getUserId(AddressBookActivity.this));
                }else if(addressBookVM1.status.get().equalsIgnoreCase("error")){
                    Toast.makeText(AddressBookActivity.this, addressBookVM1.msg.get(), Toast.LENGTH_SHORT).show();
                }

            }
        });

        addressBookVM.addressDeleteMutableLiveData().observe(this, new Observer<AddressBookVM>() {
            @Override
            public void onChanged(@Nullable AddressBookVM addressBookVM1) {

                if(addressBookVM1.status.get().equalsIgnoreCase("success")) {
                    Toast.makeText(AddressBookActivity.this, addressBookVM1.msg.get(), Toast.LENGTH_SHORT).show();

                    //update  the recylcerview
                    //invoke the get address api list
                    addressBookVM.getAddressList(Session.getUserId(AddressBookActivity.this));
                }else if(addressBookVM1.status.get().equalsIgnoreCase("error")){
                    Toast.makeText(AddressBookActivity.this, addressBookVM1.msg.get(), Toast.LENGTH_SHORT).show();
                }

            }
        });

        binding.setIAddressBook(new IAddressBook() {
            @Override
            public void onBackClicked() {
                finish();

            }

            @Override
            public void onAddAddressClicked() {
                Intent intent = new Intent(AddressBookActivity.this,AddAddressActivity.class);
                startActivity(intent);

                //startActivity(new Intent(this,AddAddressActivity.class));

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
        });

    }




}
