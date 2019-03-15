package com.ninositsolution.inveleapp.forgot_password;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.ninositsolution.inveleapp.login.LoginActivity;
import com.ninositsolution.inveleapp.R;
import com.ninositsolution.inveleapp.databinding.ActivityPasswordBinding;
import com.ninositsolution.inveleapp.utils.Constants;
import com.ninositsolution.inveleapp.utils.Session;

public class PasswordActivity extends AppCompatActivity{

    private static final String TAG = "PasswordActivity";
    ActivityPasswordBinding binding;
    PasswordVM passwordVM;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_password);
        passwordVM = ViewModelProviders.of(this).get(PasswordVM.class);
        binding.setPassword(passwordVM);
        binding.setLifecycleOwner(this);

        InputFilter[] filters = new InputFilter[1];
        filters[0] = new InputFilter.LengthFilter(6);
        binding.forgotPasswordOtpCode.setFilters(filters);

        binding.forgotPasswordOtpCode.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if (s.toString().length() == 6) {

                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(binding.forgotPasswordOtpCode.getWindowToken(), 0);
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        binding.setIPassword(new IPassword() {
            @Override
            public void onResetClicked() {



                int status = passwordVM.forgotPasswordEmailValidation();

                if (status == Constants.EMAIL_EMPTY)
                {
                    binding.forgotPasswordEmail.setError("Required");
                    binding.forgotPasswordEmail.requestFocus();
                }

                int emailpattern = passwordVM.emailPatternValidation();

                if (emailpattern == Constants.EMAIL_INVALID)
                {
                    binding.forgotPasswordEmail.setError("Invalid Pattern");
                    binding.forgotPasswordEmail.requestFocus();
                }

                else if (status == Constants.SUCCESS)
                {
                    showProgressBar();
                    passwordVM.forgotPasswordApi();

                    passwordVM.getPasswordVMMutableLiveData().observe(PasswordActivity.this, new Observer<PasswordVM>() {
                        @Override
                        public void onChanged(@Nullable PasswordVM passwordVM) {

                            if (!passwordVM.status.get().isEmpty())
                            {
                                hideProgressBar();
                                Toast.makeText(PasswordActivity.this, ""+passwordVM.msg.get(), Toast.LENGTH_SHORT).show();
                                passwordVM.status.set("");
                                passwordVM.otpEmail.set(passwordVM.email.get());
                            } else
                            {

                            }

                            if (binding.resetPasswordOtpLayout.getVisibility()== View.GONE)
                            {
                                binding.resetPasswordOtpLayout.setVisibility(View.VISIBLE);


                            }

                            if (binding.passEmailLayout.getVisibility() == View.VISIBLE)
                            {
                                binding.passEmailLayout.setVisibility(View.GONE);
                            }

                        }
                    });

                } else
                {
                    Toast.makeText(PasswordActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onSubmitClicked() {

                int status = passwordVM.resetPasswordValidation();
                if (status == Constants.PASSWORD_EMPTY)
                {
                    binding.resetPassword.setError("required");
                    binding.resetPassword.requestFocus();

                }else if (status == Constants.CONFIRM_PASSWORD_EMPTY)
                {
                    binding.confirmResetPassword.setError("required");
                    binding.confirmResetPassword.requestFocus();
                }else if (!binding.resetPassword.equals(binding.confirmResetPassword))
                {
                    binding.confirmResetPassword.setError("passwords do not match");

                }



                else if (status == Constants.SUCCESS)
                {
                    showProgressBar();
                    passwordVM.resetPasswordApi(Integer.parseInt(Session.getUserId(PasswordActivity.this)));
                    passwordVM.getResetPasswordMutableLiveData().observe(PasswordActivity.this, new Observer<PasswordVM>() {
                        @Override
                        public void onChanged(@Nullable PasswordVM passwordVM) {
                            if (!passwordVM.status.get().isEmpty())
                            {
                                hideProgressBar();
                                Toast.makeText(PasswordActivity.this, ""+passwordVM.msg.get(), Toast.LENGTH_SHORT).show();
                                Log.i(TAG, "message : "+passwordVM.msg.get());
                                passwordVM.status.set("");
                                Intent intent = new Intent(PasswordActivity.this,LoginActivity.class);
                                startActivity(intent);
                            }

                        }
                    });
                }
                else{
                    Toast.makeText(PasswordActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();

                }

            }

            @Override
            public void onBackClicked() {

            }

            @Override
            public void onVerifyOTPClicked() {

                int status = passwordVM.resetPasswordOtpValidation();
                if (status == Constants.OTP_EMPTY)
                {
                    binding.forgotPasswordOtpCode.setError("required");
                    binding.forgotPasswordOtpCode.requestFocus();
                }
                else
                    if (status == Constants.SUCCESS)
                {
                    showProgressBar();
                    passwordVM.otpVerifyApi(Integer.parseInt(Session.getUserId(PasswordActivity.this)));
                    passwordVM.getOtpMutableLiveData().observe(PasswordActivity.this, new Observer<PasswordVM>() {
                        @Override
                        public void onChanged(@Nullable PasswordVM passwordVM) {

                            if (!passwordVM.status.get().isEmpty())
                            {
                                hideProgressBar();
                                Toast.makeText(PasswordActivity.this, ""+passwordVM.msg.get(), Toast.LENGTH_SHORT).show();
                                Log.i(TAG, "message : "+passwordVM.msg.get());
                                passwordVM.status.set("");
                            }

                            if (binding.resetPasswordOtpLayout.getVisibility() == View.VISIBLE)
                            {
                                binding.resetPasswordOtpLayout.setVisibility(View.GONE);
                            }

                                if (binding.passPassLayout.getVisibility() == View.GONE)
                                {
                                    binding.passPassLayout.setVisibility(View.VISIBLE);
                                }


                        }
                    });
                }

            }
        });


    }

    private void showProgressBar()
    {
        if (binding.forgotProgress.getVisibility() == View.GONE)
            binding.forgotProgress.setVisibility(View.VISIBLE);
    }

    private void hideProgressBar()
    {
        if (binding.forgotProgress.getVisibility() == View.VISIBLE)
            binding.forgotProgress.setVisibility(View.GONE);
    }


}
