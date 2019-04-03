package com.ninositsolution.inveleapp.change_password;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.ninositsolution.inveleapp.R;
import com.ninositsolution.inveleapp.account_information.AccountInformationActivity;
import com.ninositsolution.inveleapp.databinding.ActivityChangePasswordBinding;

import com.ninositsolution.inveleapp.utils.Constants;
import com.ninositsolution.inveleapp.utils.Session;

public class ChangePasswordActivity extends AppCompatActivity {

    private static final String TAG = "PasswordActivity";


    ActivityChangePasswordBinding binding;
    ChangePasswordVM changePasswordVM;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_change_password);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_change_password);
        changePasswordVM = ViewModelProviders.of(this).get(ChangePasswordVM.class);
        binding.setChangePassword(changePasswordVM);
        binding.setLifecycleOwner(this);

        binding.confirmNewPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                int passmatch = changePasswordVM.changePasswordSamevaliation();
                if (passmatch == Constants.PASSWORD_MATCH)
                {
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(binding.confirmNewPassword.getWindowToken(), 0);

                }

            }
        });

        binding.setIChangePassword(new IChangePassword() {
            @Override
            public void onBackClicked() {
                onBackPressed();
            }

            @Override
            public void onChangePasswordClicked() {

                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(binding.confirmNewPassword.getWindowToken(), 0);

                int status = changePasswordVM.changePasswordEmptyValidation();
                if (status == Constants.PASSWORD_EMPTY)
                {
                    binding.currentPassword.setError("Required");
                    binding.currentPassword.requestFocus();

                }
                 if (status == Constants.NEW_PASSWORD_EMPTY)
                {
                    binding.newPassword.setError("Required");
                    binding.newPassword.requestFocus();
                }
                  if (status == Constants.CONFIRM_NEW_PASSWORD_EMPTY)
                {
                    binding.confirmNewPassword.setError("Required");
                    binding.confirmNewPassword.requestFocus();
                }

                int passwordMatch = changePasswordVM.changePasswordSamevaliation();

                 if (passwordMatch == Constants.PASSWORD_MISMATCH)
                 {
                     binding.confirmNewPassword.setError("Passwords do not match");
                 }

                 else if (status == Constants.SUCCESS)
                 {
                     showProgressBar();
                     changePasswordVM.updatePasswordApi(Integer.parseInt(Session.getUserId(ChangePasswordActivity.this)));
                     changePasswordVM.getChangePasswordMutableLiveData().observe(ChangePasswordActivity.this, new Observer<ChangePasswordVM>() {
                         @Override
                         public void onChanged(@Nullable ChangePasswordVM changePasswordVM) {
                             if (!changePasswordVM.status.get().isEmpty())
                             {
                                 if (changePasswordVM.status.get().equalsIgnoreCase("success"))
                                 {
                                     hideProgressBar();
                                     Intent intent = new Intent(ChangePasswordActivity.this, AccountInformationActivity.class);
                                     startActivity(intent);
                                     Toast.makeText(ChangePasswordActivity.this, ""+changePasswordVM.msg.get(), Toast.LENGTH_SHORT).show();
                                     Log.i(TAG, "message : "+changePasswordVM.msg.get());
                                     changePasswordVM.status.set("");
                                 } else
                                 if (changePasswordVM.status.get().equalsIgnoreCase("error"))
                                 {
                                     Toast.makeText(getApplicationContext(),"API Empty",Toast.LENGTH_LONG).show();
                                 }
                             }
                             else
                             {
                                 Toast.makeText(getApplicationContext(),"Something went Wrong",Toast.LENGTH_LONG).show();
                             }

                         }
                     });
                 }
            }
        });
    }


    private void showProgressBar()
    {
        if (binding.changePasswordProgress.getVisibility() == View.GONE)
            binding.changePasswordProgress.setVisibility(View.VISIBLE);
    }

    private void hideProgressBar()
    {
        if (binding.changePasswordProgress.getVisibility() == View.VISIBLE)
            binding.changePasswordProgress.setVisibility(View.GONE);
    }


}
