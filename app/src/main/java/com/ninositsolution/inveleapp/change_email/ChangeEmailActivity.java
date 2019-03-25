package com.ninositsolution.inveleapp.change_email;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.ninositsolution.inveleapp.R;
import com.ninositsolution.inveleapp.account_information.AccountInformationActivity;
import com.ninositsolution.inveleapp.databinding.ActivityChangeEmailBinding;
import com.ninositsolution.inveleapp.utils.Session;

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
                    binding.currentEmailLayout.setVisibility(View.GONE);
                }
                if (binding.newEmailLayout.getVisibility()==View.GONE)
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

            }

            @Override
            public void onVerifyCurrentOTPClicked()
            {
                showProgressBar();
                changeEmailVMGlobal.emailVerifyOTP(Session.getUserId(context),"email");
                changeEmailVMGlobal.getEmailOTPMutableLiveData().observe(ChangeEmailActivity.this, new Observer<ChangeEmailVM>() {
                    @Override
                    public void onChanged(@Nullable ChangeEmailVM changeEmailVM) {
                        if (!changeEmailVM.status.get().isEmpty())
                        {
                            if (changeEmailVM.status.get().equalsIgnoreCase("success"))
                            {
                                hideProgressBar();

                                Toast.makeText(getApplicationContext(),""+changeEmailVM.msg.get(),Toast.LENGTH_LONG).show();
                                Log.e(TAG,"onCurrentEmailVerifyOTPClicked:-->" + changeEmailVM.msg.get());
                                changeEmailVMGlobal.currentOtpCode.set(changeEmailVM.otp.get().toString());
                            }
                            else
                                if (changeEmailVM.status.get().equalsIgnoreCase("error"))
                                {
                                    hideProgressBar();
                                    Toast.makeText(getApplicationContext(),""+ changeEmailVM.msg.get(),Toast.LENGTH_LONG).show();
                                }
                                changeEmailVM.status.set("");
                        }
                        changeEmailVMGlobal.getEmailOTPMutableLiveData().removeObservers(ChangeEmailActivity.this);
                    }
                });



            }

            @Override
            public void onCurrentEnabledResendClicked() {

            }

            @Override
            public void onChangeEmailClicked()

            {
                showProgressBar();
                changeEmailVMGlobal.changeEmailOTP(Session.getUserId(context),"email");
                changeEmailVMGlobal.getVerifyEmailOTPMutableLiveData().observe(ChangeEmailActivity.this, new Observer<ChangeEmailVM>() {
                    @Override
                    public void onChanged(@Nullable ChangeEmailVM changeEmailVM) {
                        if (!changeEmailVM.status.get().isEmpty())
                        {
                            if (changeEmailVM.status.get().equalsIgnoreCase("success"))
                            {
                                hideProgressBar();
                                Toast.makeText(getApplicationContext(),"" +changeEmailVM.msg.get(),Toast.LENGTH_LONG).show();
                                Log.e(TAG,"onChangeEmailClicked:->" +changeEmailVM.msg.get());
                                Log.e(TAG,"Current Email has been verified successfully.Please proceed and update with your new email address in the next screen");

                                if (binding.currentEmailLayout.getVisibility()==View.VISIBLE)
                                {
                                    binding.currentEmailLayout.setVisibility(View.GONE);
                                }

                                if (binding.newEmailLayout.getVisibility()==View.GONE)
                                {
                                    binding.newEmailLayout.setVisibility(View.VISIBLE);
                                }
                            }
                            else if (changeEmailVM.status.get().equalsIgnoreCase("error"))
                            {
                                hideProgressBar();

                                Toast.makeText(getApplicationContext(),""+changeEmailVM.status.get(),Toast.LENGTH_LONG).show();
                            }
                            changeEmailVM.status.set("");

                        }

                        changeEmailVMGlobal.getVerifyEmailOTPMutableLiveData().removeObservers(ChangeEmailActivity.this);
                    }
                });

            }

            @Override
            public void onVerifyNewOTPClicked()
            {
                showProgressBar();
                changeEmailVMGlobal.newEmailVerifyOTP(Session.getUserId(context),"email");
                changeEmailVMGlobal.getNewEmailOTPMutableLiveData().observe(ChangeEmailActivity.this, new Observer<ChangeEmailVM>() {
                    @Override
                    public void onChanged(@Nullable ChangeEmailVM changeEmailVM)
                    {
                        if (!changeEmailVM.status.get().isEmpty())
                        {
                            if (changeEmailVM.status.get().equalsIgnoreCase("success"))
                            {
                                hideProgressBar();
                                changeEmailVMGlobal.newOtpCode.set(String.valueOf(changeEmailVM.otp.get()));
                                Log.e(TAG,"onVerifyNewOTPClicked()->:" +changeEmailVM.otp.get());
                            }

                            else
                            {
                                Toast.makeText(getApplicationContext(),""+changeEmailVM.msg.get(),Toast.LENGTH_LONG).show();
                            }
                              changeEmailVM.status.set("");
                        }
                    }
                });
            }

            @Override
            public void onNewEnabledResendClicked() {

            }

            @Override
            public void onSubmitEmailButtonClicked()
            {
                showProgressBar();

                changeEmailVMGlobal.newEmailOTP(Session.getUserId(context),"email");
                changeEmailVMGlobal.getNewVerifyEmailOTPMutableLiveData().observe(ChangeEmailActivity.this, new Observer<ChangeEmailVM>() {
                    @Override
                    public void onChanged(@Nullable ChangeEmailVM changeEmailVM) {
                        if (!changeEmailVM.status.get().isEmpty())
                        {
                            if (changeEmailVM.status.get().equalsIgnoreCase("success"))
                            {
                                hideProgressBar();
                                Toast.makeText(getApplicationContext(),""+changeEmailVM.msg.get(),Toast.LENGTH_LONG).show();
                                Log.e(TAG,"onSubmitButtonClicked()->:" +changeEmailVM.msg.get());
                                Session.setUserEmail(changeEmailVMGlobal.newEmail.get(),context);
                                Intent intent = new Intent(ChangeEmailActivity.this, AccountInformationActivity.class);
                                startActivity(intent);

                            }
                            else
                                if (changeEmailVM.status.get().equalsIgnoreCase("error"))
                                {
                                    hideProgressBar();
                                    Toast.makeText(getApplicationContext(),""+changeEmailVM.msg.get(),Toast.LENGTH_LONG).show();
                                    Toast.makeText(getApplicationContext(), "Email updating failed.", Toast.LENGTH_LONG).show();
                                }
                        }

                        changeEmailVM.status.set("");

                    }
                });


            }
        });



    }

    private void showProgressBar()
    {
        if (binding.addEmailOtpProgress.getVisibility() == View.GONE)
            binding.addEmailOtpProgress.setVisibility(View.VISIBLE);
    }

    private void hideProgressBar()
    {
        if (binding.addEmailOtpProgress.getVisibility() == View.VISIBLE)
            binding.addEmailOtpProgress.setVisibility(View.GONE);
    }






}
