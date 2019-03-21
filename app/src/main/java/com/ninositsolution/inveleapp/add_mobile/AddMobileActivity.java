package com.ninositsolution.inveleapp.add_mobile;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.ninositsolution.inveleapp.R;
import com.ninositsolution.inveleapp.databinding.ActivityAddMobileBinding;
import com.ninositsolution.inveleapp.utils.Session;

public class AddMobileActivity extends AppCompatActivity {

    ActivityAddMobileBinding binding;
    public static final String TAG = "AddMobile";
    AddMobileVM addMobileVM;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_add_mobile);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_add_mobile);
        addMobileVM = ViewModelProviders.of(this).get(AddMobileVM.class);
        binding.setAddMobile(addMobileVM);
        binding.setLifecycleOwner(this);
        context = AddMobileActivity.this;

        if (Session.getUserPhone(context) != null)
        {
            if (Session.getUserPhone(context).isEmpty())
            {


                Log.e(TAG, "Mobile number is : -->" + Session.getUserPhone(context));
            }
            else
                {

                addMobileVM.mobileNumber.set(Session.getUserPhone(context));

                Log.e(TAG, "Mobile number is : -->" + Session.getUserPhone(context));

                }

        }

        binding.setIAddMobile(new IAddMobile() {
            @Override
            public void onBackClicked() {
                onBackPressed();
            }

            @Override
            public void onVerifyOTPClicked() {


                showProgressBar();
                addMobileVM.mobileVerifyOTP(Session.getUserId(context), "mobile");
                addMobileVM.getMobileOTPMutableLiveData().observe(AddMobileActivity.this, new Observer<AddMobileVM>() {
                    @Override
                    public void onChanged(@Nullable AddMobileVM addMobileVM) {
                        if (!addMobileVM.status.get().isEmpty())
                        {
                            if (addMobileVM.status.get().equalsIgnoreCase("success"))
                            {
                                hideProgressBar();


                                Toast.makeText(getApplicationContext(), "" + addMobileVM.msg.get(), Toast.LENGTH_LONG).show();
                                Log.d(TAG, "Message is:-->" + addMobileVM.msg.get());
                                addMobileVM.status.get();
                                addMobileVM.otpCode.set(addMobileVM.otp.toString());
                            }
                            else
                                if (addMobileVM.status.get().equalsIgnoreCase("error"))
                                {
                                    hideProgressBar();
                                    addMobileVM.status.set("");
                                    Toast.makeText(getApplicationContext(), "" + addMobileVM.msg.get(), Toast.LENGTH_LONG).show();
                                }
                        }
                    }
                });



            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }


    private void showProgressBar()
    {
        if (binding.addMobileOtpProgress.getVisibility() == View.GONE)
            binding.addMobileOtpProgress.setVisibility(View.VISIBLE);
    }

    private void hideProgressBar()
    {
        if (binding.addMobileOtpProgress.getVisibility() == View.VISIBLE)
            binding.addMobileOtpProgress.setVisibility(View.GONE);
    }



}
