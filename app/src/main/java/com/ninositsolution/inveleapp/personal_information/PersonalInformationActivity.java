package com.ninositsolution.inveleapp.personal_information;

import android.app.DatePickerDialog;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.Toast;

import com.ninositsolution.inveleapp.R;
import com.ninositsolution.inveleapp.databinding.ActivityPersonalInformationBinding;
import com.ninositsolution.inveleapp.utils.Session;

import java.util.ArrayList;
import java.util.Calendar;

public class PersonalInformationActivity extends AppCompatActivity {
    private static final String TAG = "PersonalInformationActivity";

    ActivityPersonalInformationBinding binding;
    PersonalInformationVM personalInformationVM;
    DatePickerDialog picker;





    @Override

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_personal_information);
        personalInformationVM = ViewModelProviders.of(this).get(PersonalInformationVM.class);
        binding.setPersonalInfo(personalInformationVM);
        binding.setLifecycleOwner(this);
        binding.dateEdittext.setInputType(InputType.TYPE_NULL);







        binding.setIpersonalInfo(new IPersonalInformation() {
            @Override
            public void onUpdateProfileClicked() {
                showProgressBar();
                personalInformationVM.profileUpdateApi(Session.getUserId(PersonalInformationActivity.this));
                personalInformationVM.getPersonalInformationMutableLiveData().observe(PersonalInformationActivity.this, new Observer<PersonalInformationVM>() {
                    @Override
                    public void onChanged(@Nullable PersonalInformationVM personalInformationVM) {
                        if (!personalInformationVM.status.get().isEmpty())
                        {
                            if (personalInformationVM.status.get().equalsIgnoreCase("success"))
                            {
                                hideProgressBar();
                                Toast.makeText(PersonalInformationActivity.this,""+personalInformationVM.msg.get(),Toast.LENGTH_LONG).show();
                                personalInformationVM.status.get();
                                finish();
                            } else
                            {
                                if (personalInformationVM.status.get().equalsIgnoreCase("error"))
                                {
                                    Toast.makeText(getApplicationContext(),"API Error",Toast.LENGTH_LONG).show();

                                }
                            }
                        }
                        else
                        {
                            Toast.makeText(getApplicationContext(),"Something went Wrong",Toast.LENGTH_LONG).show();
                        }

                    }
                });

            }

            @Override
            public void omImageViewClicked() {

            }

            @Override
            public void onDateEditTextClicked() {

                showDatePickerDialog();




            }
        });


    }

    public void showDatePickerDialog() {

        DatePickerFragment newFragment = new DatePickerFragment();
        newFragment.show(getSupportFragmentManager(), "datePicker");

        newFragment.setOnDateClickListener(new DatePickerFragment.onDateClickListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {

                binding.dateEdittext.setText(datePicker.getDayOfMonth()+"-"+(datePicker.getMonth() + 1) +"-" +datePicker.getYear());

            }
        });





    }


    private void showProgressBar()
    {
        if (binding.updateInfoProgress.getVisibility() == View.GONE)
            binding.updateInfoProgress.setVisibility(View.VISIBLE);
    }

    private void hideProgressBar()
    {
        if (binding.updateInfoProgress.getVisibility() == View.VISIBLE)
            binding.updateInfoProgress.setVisibility(View.GONE);
    }




}
