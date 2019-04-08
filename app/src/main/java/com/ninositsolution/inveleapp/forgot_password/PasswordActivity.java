package com.ninositsolution.inveleapp.forgot_password;

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
    PasswordVM passwordVMGlobal;
    private Context context;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_password);
        passwordVMGlobal = ViewModelProviders.of(this).get(PasswordVM.class);
        binding.setPassword(passwordVMGlobal);
        binding.setLifecycleOwner(this);

        context = PasswordActivity.this;

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


        binding.confirmResetPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {



            }

            @Override
            public void afterTextChanged(Editable s) {

                int passmatch = passwordVMGlobal.passwordMatchValidation();
                if (passmatch == Constants.PASSWORD_MATCH)
                {
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(binding.confirmResetPassword.getWindowToken(), 0);

                }

            }
        });


        binding.setIPassword(new IPassword() {
            @Override
            public void onResetClicked() {

                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(binding.forgotPasswordEmail.getWindowToken(), 0);


                int status = passwordVMGlobal.forgotPasswordEmailValidation();

                if (status == Constants.EMAIL_EMPTY)
                {
                    binding.forgotPasswordEmail.setError("Required");
                    binding.forgotPasswordEmail.requestFocus();
                }

                int emailpattern = passwordVMGlobal.emailPatternValidation();

                if (emailpattern == Constants.EMAIL_INVALID)
                {
                    binding.forgotPasswordEmail.setError("Invalid Email Address");
                    binding.forgotPasswordEmail.requestFocus();
                }

                else if (status == Constants.SUCCESS)
                {
                    showProgressBar();
                    passwordVMGlobal.forgotPasswordApi();

                    passwordVMGlobal.getPasswordVMMutableLiveData().observe(PasswordActivity.this, new Observer<PasswordVM>() {
                        @Override
                        public void onChanged(@Nullable PasswordVM passwordVM) {

                            if (!passwordVM.status.get().isEmpty())
                            {
                                hideProgressBar();

                                if (passwordVM.status.get().equalsIgnoreCase("success")) {

                                    try {
                                        Session.setUserId(passwordVM.user.get().id.toString(), context);

                                    } catch (Exception e) {
                                        Log.e(TAG, "Error -> "+e);
                                    }

                                    passwordVMGlobal.otpEmail.set(passwordVMGlobal.email.get());

                                    if (binding.passEmailLayout.getVisibility() == View.VISIBLE) {
                                        binding.passEmailLayout.setVisibility(View.GONE);
                                    }

                                    if (binding.resetPasswordOtpLayout.getVisibility() == View.GONE) {
                                        binding.resetPasswordOtpLayout.setVisibility(View.VISIBLE);

                                        new CountDownTimer(45000,1000)
                                        {
                                            public void onTick(long millisUntilFinished) {
                                                binding.otpTimerText.setText(millisUntilFinished / 1000 +" " + "Sec"  );
                                            }

                                            public void onFinish() {
                                                if (binding.disabledResend.getVisibility() == View.VISIBLE)
                                                {
                                                    binding.disabledResend.setVisibility(View.GONE);
                                                    binding.enabledResend.setVisibility(View.VISIBLE);
                                                }
                                            }
                                        }.start();


                                    }

                                    Toast.makeText(PasswordActivity.this, "" + passwordVM.msg.get(), Toast.LENGTH_SHORT).show();
                                    passwordVM.status.set("");
                                    passwordVM.otpEmail.set(passwordVM.email.get());
                                }
                                if (passwordVM.status.get().equalsIgnoreCase("error")) {

                                    if (binding.passEmailLayout.getVisibility() == View.GONE)
                                    {
                                        binding.passEmailLayout.setVisibility(View.VISIBLE);
                                    }
                                }
                            }
                            else
                            {
                                Toast.makeText(getApplicationContext(),"API Empty",Toast.LENGTH_LONG).show();
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

                int status = passwordVMGlobal.resetPasswordValidation();
                if (status == Constants.PASSWORD_EMPTY)
                {
                    binding.resetPassword.setError("required");
                    binding.resetPassword.requestFocus();

                }else if (status == Constants.CONFIRM_PASSWORD_EMPTY)
                {
                    binding.confirmResetPassword.setError("required");
                    binding.confirmResetPassword.requestFocus();
                }

               int passwordMatch = passwordVMGlobal.passwordMatchValidation();
                if (passwordMatch == Constants.PASSWORD_MISMATCH)
                {
                    binding.confirmResetPassword.setError("Passwords do not Match");
                }

                else if (status == Constants.SUCCESS)
                {
                    showProgressBar();
                    passwordVMGlobal.resetPasswordApi(Integer.parseInt(Session.getUserId(PasswordActivity.this)));
                    passwordVMGlobal.getResetPasswordMutableLiveData().observe(PasswordActivity.this, new Observer<PasswordVM>() {
                        @Override
                        public void onChanged(@Nullable PasswordVM passwordVM) {

                            if (passwordVM != null)
                            {
                                if (!passwordVM.status.get().isEmpty())
                                {
                                    hideProgressBar();

                                    if (passwordVM.status.get().equalsIgnoreCase("success"))
                                    {
                                        Intent intent = new Intent(PasswordActivity.this, LoginActivity.class);
                                        startActivity(intent);

                                        Toast.makeText(PasswordActivity.this, ""+passwordVM.msg.get(), Toast.LENGTH_SHORT).show();
                                        Log.i(TAG, "message : "+passwordVM.msg.get());
                                        passwordVM.status.set("");



                                        if (passwordVM.user.get() != null)
                                        {
                                            Constants.setSession(passwordVM.user.get(), context);
                                            startActivity(new Intent(context, LoginActivity.class));
                                        }

                                    } else
                                    if (passwordVM.status.get().equalsIgnoreCase("error"))
                                    {
                                        Toast.makeText(getApplicationContext(),""+passwordVM.msg.get(),Toast.LENGTH_LONG).show();
                                    }

                                }
                                else
                                {
                                    Toast.makeText(getApplicationContext(),"Response Empty",Toast.LENGTH_LONG).show();
                                }
                            }



                        }
                    });
                }
                else
                    {
                    Toast.makeText(PasswordActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();

                    }

            }

            @Override
            public void onBackClicked() {

            }

            @Override
            public void onVerifyOTPClicked() {


                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(binding.forgotPasswordOtpCode.getWindowToken(), 0);


                int status = passwordVMGlobal.resetPasswordOtpValidation();
                if (status == Constants.OTP_EMPTY)
                {
                    binding.forgotPasswordOtpCode.setError("required");
                    binding.forgotPasswordOtpCode.requestFocus();
                }
                else
                    if (status == Constants.SUCCESS)
                {
                    showProgressBar();
                    passwordVMGlobal.otpVerifyApi(Session.getUserId(PasswordActivity.this));
                    passwordVMGlobal.getOtpMutableLiveData().observe(PasswordActivity.this, new Observer<PasswordVM>() {
                        @Override
                        public void onChanged(@Nullable PasswordVM passwordVM) {

                            if (!passwordVM.status.get().isEmpty())
                            {
                                hideProgressBar();

                                if (passwordVM.status.get().equalsIgnoreCase("success"))
                                {

                                    if (binding.resetPasswordOtpLayout.getVisibility() == View.VISIBLE)
                                    {
                                        binding.resetPasswordOtpLayout.setVisibility(View.GONE);
                                    }

                                    if (binding.passPassLayout.getVisibility() == View.GONE)
                                    {
                                        binding.passPassLayout.setVisibility(View.VISIBLE);
                                    }

                                    Toast.makeText(PasswordActivity.this, ""+passwordVM.msg.get(), Toast.LENGTH_SHORT).show();
                                    Log.i(TAG, "message : "+passwordVM.msg.get());
                                    passwordVM.status.set("");
                                }
                                else if (passwordVM.status.get().equalsIgnoreCase("error"))
                                {
                                    Toast.makeText(PasswordActivity.this, ""+passwordVM.msg.get(), Toast.LENGTH_SHORT).show();
                                }
                            }
                            else
                                {
                                    Toast.makeText(getApplicationContext(),"API Empty",Toast.LENGTH_LONG).show();
                                }
                            }


                    });
                }

            }

            @Override
            public void onEnabledResendClicked() {



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
