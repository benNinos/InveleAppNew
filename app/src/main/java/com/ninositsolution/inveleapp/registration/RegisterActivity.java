package com.ninositsolution.inveleapp.registration;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.ninositsolution.inveleapp.R;
import com.ninositsolution.inveleapp.databinding.ActivityRegisterBinding;
import com.ninositsolution.inveleapp.login.LoginActivity;
import com.ninositsolution.inveleapp.pojo.POJOClass;
import com.ninositsolution.inveleapp.utils.Constants;
import com.ninositsolution.inveleapp.utils.Session;

public class RegisterActivity extends AppCompatActivity {

    ActivityRegisterBinding binding;
    RegisterVM registerVM;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_register);


        registerVM = ViewModelProviders.of(this).get(RegisterVM.class);


        binding.setRegister(registerVM);

        binding.setLifecycleOwner(this);


        binding.setIRegister(new IRegister() {
            @Override
            public void onEmailClicked() {
                swapRegistrationVisibility();
            }

            @Override
            public void onMobileClicked() {
                swapRegistrationVisibility();
            }

            @Override
            public void onUserClicked() {
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                finish();
            }

            @Override
            public void onEmailContinueClicked() {

                int status = registerVM.emailValidation();

                if (status == Constants.EMAIL_EMPTY)
                {
                    binding.emailEdit.setError("required");
                    binding.emailEdit.requestFocus();

                } else if (status == Constants.NO_EMAIL_PATTERN)
                {
                    binding.emailEdit.setError("not a valid Email address");
                    binding.emailEdit.requestFocus();

                } else if (status == Constants.EMAIL_NAME_EMPTY)
                {
                    binding.emailNameEdit.setError("required");
                    binding.emailNameEdit.requestFocus();

                } else if (status == Constants.PASSWORD_EMPTY)
                {
                    binding.passwordRegister.setError("required");
                    binding.passwordRegister.requestFocus();

                } else if (status == Constants.SUCCESS)
                {
                    showProgressBar();

                    registerVM.registerViaEmail(Session.getDevice_id(RegisterActivity.this));

                   registerVM.getRegisterVMMutableLiveData().observe(RegisterActivity.this, new Observer<RegisterVM>() {
                       @Override
                       public void onChanged(@Nullable RegisterVM registerVM) {
                           hideProgressBar();

                           Toast.makeText(RegisterActivity.this, ""+registerVM.msg.get(), Toast.LENGTH_SHORT).show();
                           Session.setUserId(String.valueOf(registerVM.otp.get()), RegisterActivity.this);
                       }
                   });

                } else
                {

                    Toast.makeText(RegisterActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onUser2Clicked() {
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                finish();
            }

            @Override
            public void onMobileContinueClicked() {

                int status = registerVM.mobileValidation();

                if (status == Constants.MOBILE_NO_EMPTY)
                {
                    binding.mobileNoEdit.setError("required");
                    binding.mobileNoEdit.requestFocus();
                } else if (status == Constants.MOBILE_NAME_EMPTY)
                {
                    binding.mobileNameEdit.setError("required");
                    binding.mobileNameEdit.requestFocus();
                } else if (status == Constants.SUCCESS)
                {
                    showProgressBar();
                    registerVM.registerViaMobile(Session.getDevice_id(RegisterActivity.this));

                    registerVM.getRegisterVMMutableLiveData().observe(RegisterActivity.this, new Observer<RegisterVM>() {
                        @Override
                        public void onChanged(@Nullable RegisterVM registerVM) {

                            if (!registerVM.status.get().isEmpty())
                            {
                                hideProgressBar();

                                if (registerVM.status.get().equalsIgnoreCase("success"))
                                {
                                    Toast.makeText(RegisterActivity.this, ""+registerVM.msg.get(), Toast.LENGTH_SHORT).show();
                                    Session.setUserId(String.valueOf(registerVM.otp.get()), RegisterActivity.this);
                                    getOtpLayout();
                                } else
                                {
                                    Toast.makeText(RegisterActivity.this, ""+registerVM.msg.get(), Toast.LENGTH_SHORT).show();
                                }
                            }
                        }
                    });

                }

                //getOtpLayout();
            }

            @Override
            public void onResendClicked() {

            }

            @Override
            public void onOtpContinueClicked() {
                getBackRegistrationVisibility();
            }

            @Override
            public void onFacebookClicked() {

            }

            @Override
            public void onGoogleClicked() {

            }
        });
    }


    private void getBackRegistrationVisibility() {

        if (binding.otpLayout.getVisibility() == View.VISIBLE)
        {
            binding.otpLayout.setVisibility(View.GONE);
            binding.registerLayout.setVisibility(View.VISIBLE);


            ViewGroup.MarginLayoutParams layoutParams = (ViewGroup.MarginLayoutParams) binding.cardRegister.getLayoutParams();
            layoutParams.setMargins(36, 250, 36, 0);
            binding.cardRegister.requestLayout();
        }
    }

    private void getOtpLayout() {

        if (binding.registerLayout.getVisibility() == View.VISIBLE)
        {
            binding.registerLayout.setVisibility(View.GONE);
            binding.otpLayout.setVisibility(View.VISIBLE);

            ViewGroup.MarginLayoutParams layoutParams = (ViewGroup.MarginLayoutParams) binding.cardRegister.getLayoutParams();
            layoutParams.setMargins(36, 475, 36, 0);
            binding.cardRegister.requestLayout();
        }

    }

    private void swapRegistrationVisibility() {

        if (binding.emailRegister.getVisibility() == View.VISIBLE)
        {
            binding.emailHead.setTextColor(getResources().getColor(R.color.text_color));
            binding.mobileHead.setTextColor(getResources().getColor(R.color.colorPrimary));
            binding.emailRegister.setVisibility(View.GONE);
            binding.viewEmail.setVisibility(View.INVISIBLE);
            binding.mobileRegister.setVisibility(View.VISIBLE);
            binding.viewMobile.setVisibility(View.VISIBLE);
        } else
        {
            binding.mobileHead.setTextColor(getResources().getColor(R.color.text_color));
            binding.emailHead.setTextColor(getResources().getColor(R.color.colorPrimary));
            binding.mobileRegister.setVisibility(View.GONE);
            binding.viewMobile.setVisibility(View.INVISIBLE);
            binding.emailRegister.setVisibility(View.VISIBLE);
            binding.viewEmail.setVisibility(View.VISIBLE);
        }
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

   /*
    @Override
    public void updateTimer(String time) {
        binding.timer.setText(time);

        if (time.equalsIgnoreCase("try again"))
            binding.resend.setEnabled(true);
    }

    }*/
}
