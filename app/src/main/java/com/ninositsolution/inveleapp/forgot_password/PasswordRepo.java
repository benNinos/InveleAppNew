package com.ninositsolution.inveleapp.forgot_password;
import android.arch.lifecycle.MutableLiveData;
import android.util.Log;
import com.ninositsolution.inveleapp.api.ApiService;
import com.ninositsolution.inveleapp.api.RetrofitClient;
import com.ninositsolution.inveleapp.forgot_password.pojo.OTPRequest;
import com.ninositsolution.inveleapp.forgot_password.pojo.ResetPasswordRequest;
import com.ninositsolution.inveleapp.pojo.POJOClass;

import com.ninositsolution.inveleapp.utils.Constants;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class PasswordRepo {

    private static final String TAG = "PasswordRepo";

     final String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

    private MutableLiveData<PasswordVM> passwordVMMutableLiveData = new MutableLiveData<>();



    private MutableLiveData<PasswordVM> otpMutableLiveData = new MutableLiveData<>();


    private MutableLiveData<PasswordVM> resetPasswordMutableLiveData = new MutableLiveData<>();


    public MutableLiveData<PasswordVM> getPasswordVMMutableLiveData(String email) {


        ApiService apiService = RetrofitClient.getApiService();

        apiService.forgotPasswordApi(email).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<POJOClass>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(POJOClass pojoClass) {

                        Log.i(TAG, "onNext : "+pojoClass.status);

                        PasswordVM passwordVM = new PasswordVM(pojoClass);

                        passwordVMMutableLiveData.setValue(passwordVM);

                    }

                    @Override
                    public void onError(Throwable e)
                    {
                        Log.i(TAG, "onError : "+e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });

        return passwordVMMutableLiveData;
    }

    public MutableLiveData<PasswordVM> getOtpMutableLiveData(OTPRequest otpRequest) {
        ApiService apiService = RetrofitClient.getApiService();
        apiService.otpVerifyApi(otpRequest).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<POJOClass>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(POJOClass pojoClass) {
                            Log.i(TAG, "onNext : "+pojoClass.status);

                        PasswordVM passwordVM = new PasswordVM(pojoClass);
                        otpMutableLiveData.setValue(passwordVM);

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });


        return otpMutableLiveData;
    }


    public MutableLiveData<PasswordVM> getResetPasswordMutableLiveData(ResetPasswordRequest resetPasswordRequest) {

        ApiService apiService = RetrofitClient.getApiService();
        apiService.resetPasswordApi(resetPasswordRequest).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<POJOClass>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(POJOClass pojoClass) {
                        Log.i(TAG, "onNext : "+pojoClass.status);

                        PasswordVM passwordVM = new PasswordVM(pojoClass);
                        resetPasswordMutableLiveData.setValue(passwordVM);

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i(TAG, "onError : "+e.getMessage());

                    }

                    @Override
                    public void onComplete() {

                    }
                });



        return resetPasswordMutableLiveData;
    }

    public int forgotEmailValidation(String forgotemail)

    {
        if (forgotemail.isEmpty())
        {
            return Constants.EMAIL_EMPTY;
        } else
            {

            return Constants.SUCCESS;
        }
    }

    public int forgotEmailPatternValidation(String forgotemail){
        if (forgotemail.matches(emailPattern))
        {
            return Constants.EMAIL_VALID;
        } else
        {
            return Constants.EMAIL_INVALID;
        }
    }

    public int passwordValidation (String password, String confirmPassword){
        if (!password.equals(confirmPassword))
        {
            return Constants.PASSWORD_MISMATCH;
        }
        else {
            return Constants.PASSWORD_MATCH;
        }
    }






    public int otpValidation(String otp){

        if (otp.isEmpty())
        {
            return Constants.OTP_EMPTY;
        }
        else
            {
            return Constants.SUCCESS;
        }



    }

    public int resetValidation(String password, String confirmPassword){

        if (password.isEmpty())
        {
            return Constants.PASSWORD_EMPTY;
        }


        if (confirmPassword.isEmpty())
            {
            return Constants.CONFIRM_PASSWORD_EMPTY;
        }

        return Constants.SUCCESS;

    }
}
