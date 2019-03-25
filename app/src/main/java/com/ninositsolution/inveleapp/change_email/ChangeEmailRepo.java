package com.ninositsolution.inveleapp.change_email;

import android.arch.lifecycle.MutableLiveData;
import android.util.Log;

import com.ninositsolution.inveleapp.api.ApiService;
import com.ninositsolution.inveleapp.api.RetrofitClient;
import com.ninositsolution.inveleapp.change_email.pojo.EmailOTPRequest;
import com.ninositsolution.inveleapp.change_email.pojo.VerifyemailOTPRequest;
import com.ninositsolution.inveleapp.pojo.POJOClass;
import com.ninositsolution.inveleapp.utils.Constants;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Parthasarathy D on 1/25/2019.
 * Ninos IT Solution Pvt Ltd
 * ben@ninositsolution.com
 */
public class ChangeEmailRepo {

    private static final String TAG = "ChangeEmailRepo";


    private MutableLiveData<ChangeEmailVM> otpEmailMutableLiveData = new MutableLiveData<>();

    private MutableLiveData<ChangeEmailVM> otpVerifyEmailMutableLiveData = new MutableLiveData<>();


    public MutableLiveData<ChangeEmailVM> getOtpEmailMutableLiveData(EmailOTPRequest emailOTPRequest)
    {
        ApiService apiService = RetrofitClient.getApiService();
         apiService.emailChangeAPi(emailOTPRequest).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                 .subscribe(new Observer<POJOClass>() {
                     @Override
                     public void onSubscribe(Disposable d) {

                     }

                     @Override
                     public void onNext(POJOClass pojoClass) {
                         Log.i(TAG, "onNext : "+pojoClass.status);
                         Log.i(TAG, "onNext : "+pojoClass.msg);
                         Log.i(TAG, "onNext : "+pojoClass.otp);


                         ChangeEmailVM changeEmailVM = new ChangeEmailVM(pojoClass);
                         otpEmailMutableLiveData.setValue(changeEmailVM);

                     }

                     @Override
                     public void onError(Throwable e) {
                         Log.i(TAG, "onError : "+e.getMessage());

                     }

                     @Override
                     public void onComplete() {

                     }
                 });

         return otpEmailMutableLiveData;
    }

    public MutableLiveData<ChangeEmailVM> getOtpVerifyEmailMutableLiveData(VerifyemailOTPRequest verifyemailOTPRequest)
    {
        ApiService apiService = RetrofitClient.getApiService();
        apiService.verifyOtpEmailApi(verifyemailOTPRequest).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<POJOClass>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(POJOClass pojoClass) {
                        Log.i(TAG, "onNext : "+pojoClass.status);

                        ChangeEmailVM changeEmailVM = new ChangeEmailVM(pojoClass);
                        otpVerifyEmailMutableLiveData.setValue(changeEmailVM);

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i(TAG, "onError : "+e.getMessage());


                    }

                    @Override
                    public void onComplete() {

                    }
                });

        return otpVerifyEmailMutableLiveData;
    }




}
