package com.ninositsolution.inveleapp.change_email;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.ninositsolution.inveleapp.R;
import com.ninositsolution.inveleapp.account_information.AccountInformationActivity;
import com.ninositsolution.inveleapp.databinding.ActivityChangeEmailBinding;
import com.ninositsolution.inveleapp.utils.Constants;
import com.ninositsolution.inveleapp.utils.Session;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

public class ChangeEmailActivity extends AppCompatActivity {

    ActivityChangeEmailBinding binding;

    public static final String TAG = "ChangeEmail";
    ChangeEmailVM changeEmailVMGlobal;
    Context context;


    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_change_email);
        changeEmailVMGlobal = ViewModelProviders.of(this).get(ChangeEmailVM.class);
        binding.setEmail(changeEmailVMGlobal);
        binding.setLifecycleOwner(this);
        context = ChangeEmailActivity.this;


        if (Session.getUserEmail(context) != null)
        {
            if (Session.getUserEmail(context).isEmpty())
            {
                Log.e(TAG,"Stored Email is :->" + Session.getUserEmail(context));
                Log.e(TAG,"Your user_ID is: ->:" +Session.getUserId(context));

                if (binding.currentEmailLayout.getVisibility()==View.VISIBLE)
                {
                    binding.currentEmailLayout.setVisibility(GONE);
                }
                if (binding.newEmailLayout.getVisibility()== GONE)
                {
                    binding.newEmailLayout.setVisibility(View.VISIBLE);
                }
            }

            else
            {
                changeEmailVMGlobal.currentEmailAddress.set(Session.getUserEmail(context));
                binding.currentEmail.setClickable(false);
                Log.e(TAG, "Email is : -->" + Session.getUserEmail(context));
            }
        }




        binding.setIEmail(new IChangeEmail() {
            @Override
            public void onBackClicked() {

                ChangeEmailActivity.super.onBackPressed();

            }

            @Override
            public void onVerifyCurrentOTPClicked()

            {

                if (binding.countDownTimerTextView1.getVisibility()== GONE)
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
                        if (binding.currentEmailDisabledResend.getVisibility() == View.VISIBLE)
                        {
                            binding.currentEmailDisabledResend.setVisibility(GONE);
                        }
                        if (binding.currentEmailEnabledResend.getVisibility() == GONE)
                        {
                            binding.currentEmailEnabledResend.setVisibility(View.VISIBLE);
                        }

                        if (binding.countDownTimerTextView1.getVisibility() ==View.VISIBLE)
                        {
                            binding.countDownTimerTextView1.setVisibility(GONE);
                        }
                    }


                }.start();


                showProgressBar();
                changeEmailVMGlobal.emailVerifyOTP(Session.getUserId(context), "email");
                changeEmailVMGlobal.getEmailOTPMutableLiveData().observe(ChangeEmailActivity.this, new Observer<ChangeEmailVM>() {
                    @Override
                    public void onChanged(@Nullable ChangeEmailVM changeEmailVM) {
                        if (!changeEmailVM.status.get().isEmpty()) {
                            if (changeEmailVM.status.get().equalsIgnoreCase("success")) {
                                hideProgressBar();

                                Toast.makeText(getApplicationContext(), "" + changeEmailVM.msg.get(), Toast.LENGTH_LONG).show();
                                Log.e(TAG, "onCurrentEmailVerifyOTPClicked:-->" + changeEmailVM.msg.get());
                                changeEmailVMGlobal.currentOtpCode.set(changeEmailVM.otp.get().toString());
                            } else if (changeEmailVM.status.get().equalsIgnoreCase("error")) {
                                hideProgressBar();
                                Toast.makeText(getApplicationContext(), "" + changeEmailVM.msg.get(), Toast.LENGTH_LONG).show();
                            }
                            changeEmailVM.status.set("");
                        }
                        changeEmailVMGlobal.getEmailOTPMutableLiveData().removeObservers(ChangeEmailActivity.this);
                    }
                });

            }

            @Override
            public void onCurrentEnabledResendClicked()
            {
                onVerifyCurrentOTPClicked();

            }

            @Override
            public void onChangeEmailClicked() {

                int currentOTPValidation = changeEmailVMGlobal.currentEmailOTPValidation();
                if (currentOTPValidation == Constants.OTP_EMPTY) {
                    binding.currentEmailOTP.setError("Required");
                    binding.currentEmailOTP.requestFocus();
                } else if (currentOTPValidation == Constants.SUCCESS) {
                    showProgressBar();
                    changeEmailVMGlobal.changeEmailOTP(Session.getUserId(context), "email");
                    changeEmailVMGlobal.getVerifyEmailOTPMutableLiveData().observe(ChangeEmailActivity.this, new Observer<ChangeEmailVM>() {
                        @Override
                        public void onChanged(@Nullable ChangeEmailVM changeEmailVM) {
                            if (!changeEmailVM.status.get().isEmpty()) {
                                if (changeEmailVM.status.get().equalsIgnoreCase("success")) {
                                    hideProgressBar();
                                    Toast.makeText(getApplicationContext(), "" + changeEmailVM.msg.get(), Toast.LENGTH_LONG).show();
                                    Log.e(TAG, "onChangeEmailClicked:->" + changeEmailVM.msg.get());
                                    Log.e(TAG, "Current Email has been verified successfully.Please proceed and update with your new email address in the next screen");

                                    if (binding.currentEmailLayout.getVisibility() == View.VISIBLE) {
                                        binding.currentEmailLayout.setVisibility(GONE);
                                    }

                                    if (binding.newEmailLayout.getVisibility() == GONE) {
                                        binding.newEmailLayout.setVisibility(View.VISIBLE);
                                    }
                                } else if (changeEmailVM.status.get().equalsIgnoreCase("error")) {
                                    hideProgressBar();

                                    Toast.makeText(getApplicationContext(), "" + changeEmailVM.status.get(), Toast.LENGTH_LONG).show();
                                }
                                changeEmailVM.status.set("");

                            }

                            changeEmailVMGlobal.getVerifyEmailOTPMutableLiveData().removeObservers(ChangeEmailActivity.this);
                        }
                    });
                }

            }


            @Override
            public void onVerifyNewOTPClicked() {

                int newOTPValidation = changeEmailVMGlobal.newEmailvalidation();
                if (newOTPValidation == Constants.EMAIL_EMPTY) {
                    binding.newEmail.setError("Required");
                    binding.newEmail.requestFocus();
                } else if (newOTPValidation == Constants.SUCCESS)

                {
                    if (binding.countDownTimerTextView2.getVisibility()== GONE)
                    {
                        binding.countDownTimerTextView2.setVisibility(View.VISIBLE);
                    }

                    new CountDownTimer(45000,1000)
                    {
                       public void onTick(long millisUntilFinished)
                       {
                           binding.countDownTimerTextView2.setText(millisUntilFinished/1000 +""+ "Sec");
                       }

                       public void onFinish()
                       {
                           if (binding.newEmailDisabledResend.getVisibility()==View.VISIBLE)
                           {
                               binding.newEmailDisabledResend.setVisibility(View.GONE);
                           }

                           if (binding.newEmailEnabledResend.getVisibility() == View.GONE)
                           {
                               binding.newEmailEnabledResend.setVisibility(View.VISIBLE);
                           }

                           if (binding.countDownTimerTextView2.getVisibility() ==View.VISIBLE)
                           {
                               binding.countDownTimerTextView2.setVisibility(View.GONE);
                           }
                       }
                    }.start();

                    showProgressBar();
                    changeEmailVMGlobal.newEmailVerifyOTP(Session.getUserId(context), "email");
                    changeEmailVMGlobal.getNewEmailOTPMutableLiveData().observe(ChangeEmailActivity.this, new Observer<ChangeEmailVM>() {
                        @Override
                        public void onChanged(@Nullable ChangeEmailVM changeEmailVM) {
                            if (!changeEmailVM.status.get().isEmpty()) {
                                if (changeEmailVM.status.get().equalsIgnoreCase("success")) {
                                    hideProgressBar();
                                    changeEmailVMGlobal.newOtpCode.set(String.valueOf(changeEmailVM.otp.get()));
                                    Log.e(TAG, "onVerifyNewOTPClicked()->:" + changeEmailVM.otp.get());
                                } else {
                                    Toast.makeText(getApplicationContext(), "" + changeEmailVM.msg.get(), Toast.LENGTH_LONG).show();
                                }
                                changeEmailVM.status.set("");
                            }

                        }
                    });
                }


            }

            @Override
            public void onNewEnabledResendClicked() {
                onVerifyNewOTPClicked();

            }

            @Override
            public void onSubmitEmailButtonClicked() {
                int submitValidation = changeEmailVMGlobal.submitEmailValidation();
                if (submitValidation == Constants.EMAIL_EMPTY) {
                    binding.newEmail.setError("Required");
                    binding.newEmail.requestFocus();
                } else if (submitValidation == Constants.OTP_EMPTY) {
                    binding.newEmailOtp.setError("Required");
                    binding.newEmailOtp.requestFocus();
                } else if (submitValidation == Constants.SUCCESS) {


                    showProgressBar();
                    changeEmailVMGlobal.newEmailOTP(Session.getUserId(context), "email");
                    changeEmailVMGlobal.getNewVerifyEmailOTPMutableLiveData().observe(ChangeEmailActivity.this, new Observer<ChangeEmailVM>() {
                        @Override
                        public void onChanged(@Nullable ChangeEmailVM changeEmailVM) {
                            if (!changeEmailVM.status.get().isEmpty()) {
                                if (changeEmailVM.status.get().equalsIgnoreCase("success")) {
                                    hideProgressBar();
                                    Toast.makeText(getApplicationContext(), "" + changeEmailVM.msg.get(), Toast.LENGTH_LONG).show();
                                    Log.e(TAG, "onSubmitButtonClicked()->:" + changeEmailVM.msg.get());
                                    Session.setUserEmail(changeEmailVMGlobal.newEmail.get(), context);
                                    Intent intent = new Intent(ChangeEmailActivity.this, AccountInformationActivity.class);
                                    startActivity(intent);

                                } else if (changeEmailVM.status.get().equalsIgnoreCase("error"))
                                {
                                    hideProgressBar();
                                    Toast.makeText(getApplicationContext(), "" + changeEmailVM.msg.get(), Toast.LENGTH_LONG).show();
                                    Toast.makeText(getApplicationContext(), "Email updating failed.", Toast.LENGTH_LONG).show();
                                }
                            }

                            changeEmailVM.status.set("");
                        }

                    });
                }


            }
        });
    }


    private void showProgressBar()
    {
        if (binding.addEmailOtpProgress.getVisibility() == GONE)
            binding.addEmailOtpProgress.setVisibility(View.VISIBLE);
    }

    private void hideProgressBar()
    {
        if (binding.addEmailOtpProgress.getVisibility() == View.VISIBLE)
            binding.addEmailOtpProgress.setVisibility(GONE);
    }
}
