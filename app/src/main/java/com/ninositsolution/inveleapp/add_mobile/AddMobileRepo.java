package com.ninositsolution.inveleapp.add_mobile;

import android.arch.lifecycle.MutableLiveData;
import android.util.Log;

import com.ninositsolution.inveleapp.add_mobile.pojo.MobileOTPRequest;
import com.ninositsolution.inveleapp.api.ApiService;
import com.ninositsolution.inveleapp.api.RetrofitClient;
import com.ninositsolution.inveleapp.pojo.POJOClass;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Parthasarathy D on 1/25/2019.
 * Ninos IT Solution Pvt Ltd
 * ben@ninositsolution.com
 */
public class AddMobileRepo {

    private static final String TAG = "AddMobileRepo";


    private MutableLiveData<AddMobileVM> otpMobileMutableLiveData = new MutableLiveData<>();



    public MutableLiveData<AddMobileVM> getOtpMobileMutableLiveData(MobileOTPRequest mobileOTPRequest)
    {
        ApiService apiService = RetrofitClient.getApiService();
        apiService.mobileChangeApi(mobileOTPRequest).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<POJOClass>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(POJOClass pojoClass) {
                        Log.i(TAG, "onNext : "+pojoClass.status);

                        AddMobileVM addMobileVM = new AddMobileVM(pojoClass);
                        otpMobileMutableLiveData.setValue(addMobileVM);

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i(TAG, "onError : "+e.getMessage());

                    }

                    @Override
                    public void onComplete() {

                    }
                });

        return otpMobileMutableLiveData;
    }


}
