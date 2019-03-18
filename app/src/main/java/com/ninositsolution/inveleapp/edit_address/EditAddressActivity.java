package com.ninositsolution.inveleapp.edit_address;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.ninositsolution.inveleapp.R;
import com.ninositsolution.inveleapp.add_address.AddAddressActivity;
import com.ninositsolution.inveleapp.add_address.AddAddressRepo;
import com.ninositsolution.inveleapp.add_address.AddAddressVM;
import com.ninositsolution.inveleapp.add_address.IAddAddress;
import com.ninositsolution.inveleapp.add_address.pojo.AddAddressRequest;
import com.ninositsolution.inveleapp.address_book.AddressBookActivity;
import com.ninositsolution.inveleapp.databinding.ActivityEditAddressBinding;
import com.ninositsolution.inveleapp.edit_address.pojo.EditAddressRequest;
import com.ninositsolution.inveleapp.utils.Constants;
import com.ninositsolution.inveleapp.utils.Session;

public class EditAddressActivity extends AppCompatActivity {

    ActivityEditAddressBinding binding;
    EditAddressVM editAddressVM;
    EditAddressModel editAddressModel;
    String user_address_id="";
    public static final String TAG = EditAddressActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_edit_address);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_edit_address);


        editAddressVM = ViewModelProviders.of(this).get(EditAddressVM.class);

        binding.setEditAddress(editAddressVM);
        binding.setLifecycleOwner(this);
       editAddressModel = new EditAddressModel();
        Log.e(TAG,"user_id==>"+ Session.getUserId(EditAddressActivity.this));
       // binding.setEditAddress(new EditAddressVM(getApplicationContext(),this));

        //set the radio button address_type value

        Bundle extras = getIntent().getExtras();
        if(extras != null){
            user_address_id = extras.getString("user_address_id");
            Constants.user_address_id = user_address_id;
            //Log.e(TAG,"edit_values==>"+editAddressModel.size());
        }

        if(!user_address_id.equalsIgnoreCase("")){
            //invoke the get address api list
            editAddressVM.ShowAddress(user_address_id);


        }
        editAddressVM.getShowAddressVMMutableLiveData().observe(EditAddressActivity.this, new Observer<EditAddressVM>() {
            @Override
            public void onChanged(@Nullable EditAddressVM editAddVM) {

                if(editAddVM.status.get().equalsIgnoreCase("success")) {

                    //  addAddressVM = addressVM;

                    Toast.makeText(EditAddressActivity.this, "" + editAddVM.msg.get(), Toast.LENGTH_SHORT).show();
                    Log.e(TAG,"id==>"+editAddVM.user_address.get().id+"\nuser_id==>"+editAddVM.user_address.get().user_id);
                    editAddressVM.Name.set(editAddVM.user_address.get().name);
                    editAddressVM.contact_number.set(editAddVM.user_address.get().contact_no);
                    editAddressVM.postal_code.set(editAddVM.user_address.get().postal_code);
                    editAddressVM.floor_unit_numer.set(editAddVM.user_address.get().address);
                    editAddressVM.address.set(editAddVM.user_address.get().address1);
                    editAddressVM.city_name.set(editAddVM.user_address.get().city+","+editAddVM.user_address.get().postal_code);

                    if(!editAddVM.user_address.get().address_type.equalsIgnoreCase("")){
                        if(editAddVM.user_address.get().address_type.equalsIgnoreCase("Home")){
                            ((RadioButton)binding.radioGroup.getChildAt(0)).setChecked(true);
                            editAddressVM.address_type.set("Home");
                        }else if(editAddVM.user_address.get().address_type.equalsIgnoreCase("Office")){
                            ((RadioButton)binding.radioGroup.getChildAt(1)).setChecked(true);
                            editAddressVM.address_type.set("Office");
                        }else if(editAddVM.user_address.get().address_type.equalsIgnoreCase("Others")){
                            ((RadioButton)binding.radioGroup.getChildAt(2)).setChecked(true);
                            editAddressVM.address_type.set("Others");
                        }

                    }
                    //set the shipping and billing status

                    if(!editAddVM.user_address.get().is_shipping_address.equalsIgnoreCase("1")){
                        binding.shippingCheckbox.setChecked(true);
                        editAddressVM.is_shipping.set("1");
                    }else {
                        binding.shippingCheckbox.setChecked(false);
                        editAddressVM.is_shipping.set("0");
                    }

                    if(!editAddVM.user_address.get().is_billing_address.equalsIgnoreCase("1")){
                        binding.billingCheckbox.setChecked(true);
                        editAddressVM.is_billing.set("1");
                    }else {
                        binding.billingCheckbox.setChecked(false);
                        editAddressVM.is_billing.set("0");
                    }

                }else if(editAddVM.status.get().equalsIgnoreCase("error")){
                    Toast.makeText(EditAddressActivity.this, "" + editAddVM.msg.get(), Toast.LENGTH_SHORT).show();
                }

            }
        });




        binding.setIEditAddress(new IEditAddress() {
            @Override
            public void onBackClicked() {

                finish();
            }

            @Override
            public void onLocateClicked() {

                int status = editAddressVM.postalCodeValidation();

                if(status == Constants.POSTAL_CODE){

                    binding.addressInputPostal.setError("Enter Postal Code");
                    binding.addressInputPostal.requestFocus();

                }else if (status == Constants.SUCCESS)
                {
                    showProgressBar();

                    editAddressVM.LocateAddress();

                    editAddressVM.getSearchAddressVMMutableLiveData().observe(EditAddressActivity.this, new Observer<EditAddressVM>() {
                        @Override
                        public void onChanged(@Nullable EditAddressVM addressVM) {
                            hideProgressBar();

                            if(addressVM.status.get().equalsIgnoreCase("success")) {

                                //  addAddressVM = addressVM;

                                Toast.makeText(EditAddressActivity.this, "" + addressVM.msg.get(), Toast.LENGTH_SHORT).show();
                                Log.e(TAG,"address==>"+addressVM.address_list.get().get(0).ROAD_NAME+"\ncity==>"+addressVM.city.get());
                                editAddressVM.address.set(addressVM.address_list.get().get(0).ROAD_NAME);
                                editAddressVM.city_name.set(addressVM.city.get());
                            }else if(addressVM.status.get().equalsIgnoreCase("error")){
                                Toast.makeText(EditAddressActivity.this, "" + addressVM.msg.get(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

                } else
                {

                    Toast.makeText(EditAddressActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onShipaddressClicked() {
                binding.shippingCheckbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        if(isChecked){
                            Log.e(TAG,"checked==>");
                            editAddressVM.is_shipping.set("1");

                        }else {
                            Log.e(TAG,"checked==>");
                            editAddressVM.is_shipping.set("0");
                        }
                        Log.e(TAG,"is_shipping==>"+editAddressVM.is_shipping.get());
                    }
                });

            }

            @Override
            public void onBillAddressClicked() {

                binding.billingCheckbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        if(isChecked){
                            Log.e(TAG,"checked==>");
                            editAddressVM.is_billing.set("1");

                        }else {
                            Log.e(TAG,"checked==>");
                            editAddressVM.is_billing.set("0");
                        }
                        Log.e(TAG,"is_billing==>"+editAddressVM.is_billing.get());
                    }
                });

            }

            @Override
            public void onSaveClicked() {

                int status = editAddressVM.addressValidation();
                if (status == Constants.NAME_EMPTY)
                {
                    binding.addressInputName.setError("Enter name");
                    binding.addressInputName.requestFocus();

                } else if (status == Constants.CONTACT_NUMBER_EMPTY)
                {
                    binding.addressInputNumber.setError("Enter Contact Number");
                    binding.addressInputNumber.requestFocus();

                } else if (status == Constants.POSTAL_CODE)
                {
                    binding.addressInputPostal.setError("Enter Postal Code");
                    binding.addressInputPostal.requestFocus();

                } else if (status == Constants.ADDRESS)
                {
                    binding.addressInputUnitNo.setError("Enter Floor Number");
                    binding.addressInputUnitNo.requestFocus();

                }else if (status == Constants.ADDRESS1)
                {
                    binding.addressInputAddress.setError("required");
                    binding.addressInputAddress.requestFocus();

                }else if (status == Constants.CITY)
                {
                    binding.cityInputAddress.setError("Enter City");
                    binding.cityInputAddress.requestFocus();

                } else if (status == Constants.SUCCESS)
                {
                    showProgressBar();

                    editAddressVM.EditAddress(Constants.user_address_id);

                    editAddressVM.getEditAddressVMMutableLiveData().observe(EditAddressActivity.this, new Observer<EditAddressVM>() {
                        @Override
                        public void onChanged(@Nullable EditAddressVM editAddressVM) {
                            hideProgressBar();

                            if(editAddressVM.status.get().equalsIgnoreCase("success")) {

                                Toast.makeText(EditAddressActivity.this, "" + editAddressVM.msg.get(), Toast.LENGTH_SHORT).show();
                            }else if(editAddressVM.status.get().equalsIgnoreCase("error")){
                                Toast.makeText(EditAddressActivity.this, "" + editAddressVM.msg.get(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

                } else
                {

                    Toast.makeText(EditAddressActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
                }


            }

            @Override
            public void onRadioButtonClicked() {

                binding.radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup group, int checkedId) {
                        int id = group.getCheckedRadioButtonId();
                        if(id == binding.homeRadioBtn.getId()) {
                            Log.e(TAG, "selected_id==>" + id);
                            editAddressVM.address_type.set("Home");
                        }else if(id == binding.officeRadioBtn.getId()){
                            editAddressVM.address_type.set("Office");

                        }else if(id == binding.otherRadioBtn.getId()){
                            editAddressVM.address_type.set("Others");

                        }

                    }
                });

            }
        });
    }

    private void showProgressBar()
    {
        if (binding.registerProgress.getVisibility() == View.GONE)
            binding.registerProgress.setVisibility(View.VISIBLE);
    }

    private void hideProgressBar()
    {
        if (binding.registerProgress.getVisibility() == View.VISIBLE)
            binding.registerProgress.setVisibility(View.GONE);
    }





}
