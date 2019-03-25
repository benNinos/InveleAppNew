package com.ninositsolution.inveleapp.home;

import android.annotation.SuppressLint;
import android.arch.lifecycle.MutableLiveData;
import android.os.CountDownTimer;
import android.util.Log;

import com.ninositsolution.inveleapp.api.ApiService;
import com.ninositsolution.inveleapp.api.RetrofitClient;
import com.ninositsolution.inveleapp.pojo.POJOClass;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class HomeRepo {

    private static final String TAG = "HomeRepo";

    private MutableLiveData<HomeVM> homeVMMutableLiveData = new MutableLiveData<>();


    public MutableLiveData<HomeVM> getHomeVMMutableLiveData(String userIid) {

        ApiService apiService = RetrofitClient.getApiService();

        apiService.homePageApi(userIid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<POJOClass>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(POJOClass pojoClass) {

                        Log.i(TAG, "onNext : "+pojoClass.status);
                        HomeVM homeVM = new HomeVM(pojoClass);

                        homeVMMutableLiveData.setValue(homeVM);
                    }

                    @Override
                    public void onError(Throwable e) {

                        Log.e(TAG, "onError : "+e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });

        return homeVMMutableLiveData;
    }
}