package com.ninositsolution.inveleapp.add_address;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.ninositsolution.inveleapp.R;
import com.ninositsolution.inveleapp.databinding.ActivityAddAddressBinding;
import com.ninositsolution.inveleapp.utils.Constants;
import com.ninositsolution.inveleapp.utils.Session;

public class AddAddressActivity extends AppCompatActivity {

    ActivityAddAddressBinding binding;
    AddAddressVM addAddressVM;
    public static final String TAG = AddAddressActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_add_address);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_add_address);

        addAddressVM = ViewModelProviders.of(this).get(AddAddressVM.class);

        binding.setAddAddress(addAddressVM);
        binding.setLifecycleOwner(this);
        Log.e(TAG,"user_id==>"+Session.getUserId(AddAddressActivity.this));

        binding.setIAddAddress(new IAddAddress() {
            @Override
            public void onBackClicked() {

                finish();
            }

            @Override
            public void onLocateClicked() {

                int status = addAddressVM.postalCodeValidation();

                if(status == Constants.POSTAL_CODE){

                    binding.addressInputPostal.setError("Enter Postal Code");
                    binding.addressInputPostal.requestFocus();

                }else if (status == Constants.SUCCESS)
                {
                    showProgressBar();

                    addAddressVM.LocateAddress();

                    addAddressVM.getSearchAddressVMMutableLiveData().observe(AddAddressActivity.this, new Observer<AddAddressVM>() {
                        @Override
                        public void onChanged(@Nullable AddAddressVM addressVM) {
                            hideProgressBar();

                            if(addressVM.status.get().equalsIgnoreCase("success")) {

                              //  addAddressVM = addressVM;

                                Toast.makeText(AddAddressActivity.this, "" + addressVM.msg.get(), Toast.LENGTH_SHORT).show();
                                Log.e(TAG,"address==>"+addressVM.address_list.get().get(0).ROAD_NAME+"\ncity==>"+addressVM.city.get());
                                addAddressVM.address.set(addressVM.address_list.get().get(0).ROAD_NAME);
                                addAddressVM.city_name.set(addressVM.city.get());
                            }else if(addressVM.status.get().equalsIgnoreCase("error")){
                                Toast.makeText(AddAddressActivity.this, "" + addAddressVM.msg.get(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

                } else
                {

                    Toast.makeText(AddAddressActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onShipaddressClicked() {
                binding.shippingCheckbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        if(isChecked){
                            Log.e(TAG,"checked==>");
                            addAddressVM.is_shipping.set("1");

                        }else {
                            Log.e(TAG,"checked==>");
                            addAddressVM.is_shipping.set("0");
                        }
                        Log.e(TAG,"is_shipping==>"+addAddressVM.is_shipping.get());
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
                            addAddressVM.is_billing.set("1");

                        }else {
                            Log.e(TAG,"checked==>");
                            addAddressVM.is_billing.set("0");
                        }
                        Log.e(TAG,"is_billing==>"+addAddressVM.is_billing.get());
                    }
                });

            }

            @Override
            public void onSaveClicked() {

                int status = addAddressVM.addressValidation();
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

                    addAddressVM.saveAddress(Session.getUserId(AddAddressActivity.this));

                    addAddressVM.getAddAddressVMMutableLiveData().observe(AddAddressActivity.this, new Observer<AddAddressVM>() {
                        @Override
                        public void onChanged(@Nullable AddAddressVM addAddressVM) {
                            hideProgressBar();

                            Toast.makeText(AddAddressActivity.this, ""+addAddressVM.msg.get(), Toast.LENGTH_SHORT).show();
                        }
                    });

                } else
                {

                    Toast.makeText(AddAddressActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
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
                            addAddressVM.address_type.set("Home");
                        }else if(id == binding.officeRadioBtn.getId()){
                            addAddressVM.address_type.set("Office");

                        }else if(id == binding.otherRadioBtn.getId()){
                            addAddressVM.address_type.set("Others");

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
