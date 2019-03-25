package com.ninositsolution.inveleapp.add_mobile;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.InputFilter;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;
import com.ninositsolution.inveleapp.R;
import com.ninositsolution.inveleapp.account_information.AccountInformationActivity;
import com.ninositsolution.inveleapp.databinding.ActivityAddMobileBinding;
import com.ninositsolution.inveleapp.utils.Constants;
import com.ninositsolution.inveleapp.utils.Session;

public class AddMobileActivity extends AppCompatActivity {

    ActivityAddMobileBinding binding;
    public static final String TAG = "AddMobile";
    AddMobileVM addMobileVmGlobal;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_add_mobile);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_add_mobile);
        addMobileVmGlobal = ViewModelProviders.of(this).get(AddMobileVM.class);
        binding.setAddMobile(addMobileVmGlobal);
        binding.setLifecycleOwner(this);
        context = AddMobileActivity.this;
        InputFilter[] filters = new InputFilter[1];
        filters[0] = new InputFilter.LengthFilter(8);
        binding.newMobileNumber.setFilters(filters);
        binding.newMobileNumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if (s.toString().length() == 8) {

                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(binding.newMobileNumber.getWindowToken(), 0);
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        if (Session.getUserPhone(context) != null)
        {
            if (Session.getUserPhone(context).isEmpty())
            {
                Log.e(TAG, "Mobile number is : -->" + Session.getUserPhone(context));

                if (binding.currentMobileLayout.getVisibility() == View.VISIBLE)
                {
                    binding.currentMobileLayout.setVisibility(View.GONE);
                }
                if (binding.newMobileLayout.getVisibility() == View.GONE)
                {
                    binding.newMobileLayout.setVisibility(View.VISIBLE);
                }
            }
            else
                {
                addMobileVmGlobal.mobileNumber.set(Session.getUserPhone(context));
                binding.mobileNumber.setClickable(false);

                    Log.e(TAG, "Mobile number is : -->" + Session.getUserPhone(context));

                }
        }

        binding.setIAddMobile(new IAddMobile() {
            @Override
            public void onBackClicked() {
                onBackPressed();
            }

            @Override
            public void onVerifyOTPClicked()

            {
                if (binding.countDownTimerTextView1.getVisibility()==View.GONE)
                {
                    binding.countDownTimerTextView1.setVisibility(View.VISIBLE);
                }

                new CountDownTimer(45000,1000)
                {
                    public void onTick(long millisUntilFinished)
                    {
                        binding.countDownTimerTextView1.setText(millisUntilFinished / 1000 +" " + "Sec");
                    }

                    public void onFinish()
                    {
                        if (binding.currentDisabledResend.getVisibility() == View.VISIBLE)
                        {
                            binding.currentDisabledResend.setVisibility(View.GONE);
                        }

                        if (binding.currentEnabledResend.getVisibility() == View.GONE)
                        {
                            binding.currentEnabledResend.setVisibility(View.VISIBLE);
                        }

                        if (binding.countDownTimerTextView1.getVisibility()==View.VISIBLE)
                        {
                            binding.countDownTimerTextView1.setVisibility(View.GONE);
                        }
                    }
                }.start();

                showProgressBar();
                addMobileVmGlobal.mobileVerifyOTP(Session.getUserId(context), "mobile");
                addMobileVmGlobal.getMobileOTPMutableLiveData().observe(AddMobileActivity.this, new Observer<AddMobileVM>() {
                    @Override
                    public void onChanged(@Nullable AddMobileVM addMobileVM) {
                        if (!addMobileVM.status.get().isEmpty())
                        {
                            if (addMobileVM.status.get().equalsIgnoreCase("success"))
                            {
                                hideProgressBar();

                                Toast.makeText(getApplicationContext(), "" + addMobileVM.msg.get(), Toast.LENGTH_LONG).show();
                                Log.d(TAG, "onVerifyOTPClicked:-->" + addMobileVM.msg.get());
                                addMobileVmGlobal.otpCode.set(addMobileVM.otp.get().toString());
                            }
                            else
                                if (addMobileVM.status.get().equalsIgnoreCase("error"))
                                {
                                    hideProgressBar();
                                    Toast.makeText(getApplicationContext(), "" + addMobileVM.msg.get(), Toast.LENGTH_LONG).show();
                                }

                                addMobileVM.status.set("");
                        }

                        addMobileVmGlobal.getMobileOTPMutableLiveData().removeObservers(AddMobileActivity.this);
                    }
                });
            }

            @Override
            public void onChangeMobileClicked() {

                int currentOTPValidation = addMobileVmGlobal.currentOTPValidation();

                if (currentOTPValidation == Constants.OTP_EMPTY) {
                    binding.currentOTP.setError("Required");
                    binding.currentOTP.requestFocus();
                }

                else if (currentOTPValidation == Constants.SUCCESS)
                {
                    showProgressBar();
                    addMobileVmGlobal.changeMobileOTP(Session.getUserId(context), "mobile");
                    addMobileVmGlobal.getVerifyMobileOTPMutableLiveData().observe(AddMobileActivity.this, new Observer<AddMobileVM>() {
                        @Override
                        public void onChanged(@Nullable AddMobileVM addMobileVM) {
                            if (!addMobileVM.status.get().isEmpty()) {
                                if (addMobileVM.status.get().equalsIgnoreCase("success")) {
                                    hideProgressBar();
                                    Toast.makeText(getApplicationContext(), "" + addMobileVM.msg.get(), Toast.LENGTH_LONG).show();
                                    Log.d(TAG, "onChangeMobileClickeds:-->" + addMobileVM.msg.get());
                                    if (binding.currentMobileLayout.getVisibility() == View.VISIBLE) {
                                        binding.currentMobileLayout.setVisibility(View.GONE);
                                    }
                                    if (binding.newMobileLayout.getVisibility() == View.GONE) {
                                        binding.newMobileLayout.setVisibility(View.VISIBLE);
                                    }

                                } else if (addMobileVM.status.get().equalsIgnoreCase("error"))
                                {
                                    hideProgressBar();
                                    Toast.makeText(getApplicationContext(), "" + addMobileVM.msg.get(), Toast.LENGTH_LONG).show();
                                    Toast.makeText(getApplicationContext(), "Verification failed.", Toast.LENGTH_LONG).show();

                                }

                                addMobileVM.status.set("");
                            }


                            addMobileVmGlobal.getVerifyMobileOTPMutableLiveData().removeObservers(AddMobileActivity.this);

                        }

                    });
                }
            }

            @Override
            public void onNewVerifyOTPClicked()

            {
                int newMobileValidation = addMobileVmGlobal.newMobileNumberValidation();
                if (newMobileValidation == Constants.MOBILE_NO_EMPTY)
                {
                    binding.newMobileNumber.setError("Required");
                    binding.newMobileNumber.requestFocus();
                }

                else if (newMobileValidation == Constants.SUCCESS)
                {


                    if (binding.countDownTimerTextView2.getVisibility() == View.GONE) {
                        binding.countDownTimerTextView2.setVisibility(View.VISIBLE);
                    }

                    new CountDownTimer(45000, 1000) {
                        public void onTick(long millisUntilFinished) {
                            binding.countDownTimerTextView2.setText(millisUntilFinished / 1000 + " " + "Sec");
                        }

                        public void onFinish() {
                            if (binding.newDisabledResend.getVisibility() == View.VISIBLE) {
                                binding.newDisabledResend.setVisibility(View.GONE);
                            }

                            if (binding.newEnabledResend.getVisibility() == View.GONE) {
                                binding.newEnabledResend.setVisibility(View.VISIBLE);
                            }

                            if (binding.countDownTimerTextView2.getVisibility() == View.VISIBLE) {
                                binding.countDownTimerTextView2.setVisibility(View.GONE);
                            }

                        }
                    }.start();

                    showProgressBar();
                    addMobileVmGlobal.newmobileVerifyOTP(Session.getUserId(context), "mobile");
                    addMobileVmGlobal.getNewMobileOTPMutableLiveData().observe(AddMobileActivity.this, new Observer<AddMobileVM>() {
                        @Override
                        public void onChanged(@Nullable AddMobileVM addMobileVM) {
                            if (!addMobileVM.status.get().isEmpty()) {
                                if (addMobileVM.status.get().equalsIgnoreCase("success"))
                                {
                                    hideProgressBar();

                                    addMobileVmGlobal.newOtpCode.set(String.valueOf(addMobileVM.otp.get()));

                                } else {

                                    Toast.makeText(getApplicationContext(), "" + addMobileVM.msg.get(), Toast.LENGTH_LONG).show();
                                }

                                addMobileVM.status.set("");
                            }
                        }
                    });
                }

            }

            @Override
            public void onSubmitButtonClicked()
            {

                int newMobileNumberValidation = addMobileVmGlobal.submitValidation();
                if (newMobileNumberValidation == Constants.MOBILE_NO_EMPTY)
                {
                    binding.newMobileNumber.setError("Required");
                    binding.newMobileNumber.requestFocus();
                }

                else if (newMobileNumberValidation == Constants.OTP_EMPTY)
                {
                    binding.newOtp.setError("Required");
                    binding.newOtp.requestFocus();
                }

                else if (newMobileNumberValidation == Constants.SUCCESS)
                {
                    showProgressBar();
                    addMobileVmGlobal.newMobileOTP(Session.getUserId(context), "mobile");
                    addMobileVmGlobal.getNewVerifyMobileOTPMutableLiveData().observe(AddMobileActivity.this, new Observer<AddMobileVM>() {
                        @Override
                        public void onChanged(@Nullable AddMobileVM addMobileVM) {
                            if (!addMobileVM.status.get().isEmpty()) {
                                if (addMobileVM.status.get().equalsIgnoreCase("success")) {
                                    hideProgressBar();
                                    Toast.makeText(getApplicationContext(), "" + addMobileVM.msg.get(), Toast.LENGTH_LONG).show();
                                    Log.d(TAG, "onSubmitButtonClicked:-->" + addMobileVM.msg.get());
                                    Toast.makeText(getApplicationContext(), "Mobile number has been successfully updated", Toast.LENGTH_LONG).show();
                                    Session.setUserPhone(addMobileVmGlobal.newMobileNumber.get(), context);
                                    Intent intent = new Intent(AddMobileActivity.this, AccountInformationActivity.class);
                                    startActivity(intent);
                                } else {
                                    if (addMobileVM.status.get().equalsIgnoreCase("error")) {
                                        hideProgressBar();
                                        Toast.makeText(getApplicationContext(), "" + addMobileVM.msg.get(), Toast.LENGTH_LONG).show();
                                        Toast.makeText(getApplicationContext(), "Mobile number updating failed.", Toast.LENGTH_LONG).show();

                                    }

                                }

                                addMobileVM.status.set("");
                            }
                        }
                    });
                }

            }

            @Override
            public void onCurrentEnabledResendClicked() {
                onVerifyOTPClicked();
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
