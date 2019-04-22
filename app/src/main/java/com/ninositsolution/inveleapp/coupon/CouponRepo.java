package com.ninositsolution.inveleapp.coupon;

import android.arch.lifecycle.MutableLiveData;
import android.util.Log;

import com.ninositsolution.inveleapp.api.RetrofitClient;
import com.ninositsolution.inveleapp.pojo.POJOClass;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class CouponRepo {

    private static final String TAG = CouponRepo.class.getSimpleName();
    private MutableLiveData<CouponVM> couponVMMutableLiveData = new MutableLiveData<>();

    public MutableLiveData<CouponVM> getCouponVMMutableLiveData(final String userId) {

        RetrofitClient.getApiService().getCoupon(userId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<POJOClass>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(POJOClass pojoClass) {
                        Log.i(TAG, "onNext -> "+pojoClass.status);
                        Log.i(TAG, "onNext -> "+pojoClass.msg);
                        couponVMMutableLiveData.setValue(new CouponVM(pojoClass));
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i(TAG, "onError -> "+e);
                        getCouponVMMutableLiveData(userId);
                    }

                    @Override
                    public void onComplete() {

                    }
                });


        return couponVMMutableLiveData;
    }

}