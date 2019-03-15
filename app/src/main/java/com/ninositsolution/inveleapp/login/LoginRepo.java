package com.ninositsolution.inveleapp.login;

import android.arch.lifecycle.MutableLiveData;
import android.content.Intent;
import android.util.Log;

import com.ninositsolution.inveleapp.api.ApiService;
import com.ninositsolution.inveleapp.api.RetrofitClient;
import com.ninositsolution.inveleapp.pojo.POJOClass;
import com.ninositsolution.inveleapp.registration.pojo.RegistartionRequest;
import com.ninositsolution.inveleapp.utils.Constants;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class LoginRepo {

    private static final String TAG = "LoginRepo";

    private MutableLiveData<LoginVM> loginVMMutableLiveData = new MutableLiveData<>();

    private MutableLiveData<LoginVM> mobileSendOtpLiveData = new MutableLiveData<>();

    private MutableLiveData<LoginVM> otpVerifyLivedata = new MutableLiveData<>();

    public MutableLiveData<LoginVM> getLoginVMMutableLiveData(LoginRequest loginRequest) {

        ApiService apiService = RetrofitClient.getApiService();

        apiService.loginApi(loginRequest)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<POJOClass>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(POJOClass pojoClass) {

                        LoginVM loginVM = new LoginVM(pojoClass);
                        loginVMMutableLiveData.setValue(loginVM);
                        Log.i(TAG, "onNext : "+pojoClass.msg);

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i(TAG, "onError : "+e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });

        return loginVMMutableLiveData;
    }

    public MutableLiveData<LoginVM> getMobileSendOtpLiveData(RegistartionRequest registartionRequest) {

        ApiService apiService = RetrofitClient.getApiService();

        apiService.registerApi(registartionRequest).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<POJOClass>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(POJOClass pojoClass) {

                        LoginVM loginVM = new LoginVM(pojoClass);
                        mobileSendOtpLiveData.setValue(loginVM);
                        Log.i(TAG, "onNext : "+pojoClass.msg);

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i(TAG, "onError : "+e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });

        return mobileSendOtpLiveData;
    }

    public MutableLiveData<LoginVM> getOtpVerifyLivedata(String userId, String otp) {

        ApiService apiService = RetrofitClient.getApiService();

        apiService.otpVerifyApi(userId,otp).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<POJOClass>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(POJOClass pojoClass) {

                        LoginVM loginVM = new LoginVM(pojoClass);
                        otpVerifyLivedata.setValue(loginVM);
                        Log.i(TAG, "onNext : "+pojoClass.msg);

                    }

                    @Override
                    public void onError(Throwable e) {

                        Log.i(TAG, "onError : "+e.getMessage());

                    }

                    @Override
                    public void onComplete() {

                    }
                });


        return otpVerifyLivedata;
    }

    public Integer emailValidations(String username, String password)
    {
        if (username.isEmpty())
        {
            return Constants.EMAIL_EMPTY;
        }

        if (password.isEmpty())
        {
            return Constants.PASSWORD_EMPTY;
        }

        return Constants.SUCCESS;
    }

    public Integer mobileValidations(String mobile, String otp)
    {
        if (mobile.isEmpty())
        {
            return Constants.MOBILE_NO_EMPTY;
        }

        if (otp.isEmpty())
        {
            return Constants.OTP_EMPTY;
        }

        return Constants.SUCCESS;
    }



}