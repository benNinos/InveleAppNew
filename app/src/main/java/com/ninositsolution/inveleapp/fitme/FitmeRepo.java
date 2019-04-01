package com.ninositsolution.inveleapp.fitme;

import android.arch.lifecycle.MutableLiveData;
import android.nfc.Tag;
import android.util.Log;

import com.ninositsolution.inveleapp.api.ApiService;
import com.ninositsolution.inveleapp.api.RetrofitClient;
import com.ninositsolution.inveleapp.pojo.HomeArrayLists;
import com.ninositsolution.inveleapp.pojo.POJOClass;

import java.util.ArrayList;
import java.util.Observable;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Parthasarathy D on 1/25/2019.
 * Ninos IT Solution Pvt Ltd
 * ben@ninositsolution.com
 */
public class FitmeRepo {


    private static final String TAG = "FitmeRepo";
    private MutableLiveData<FitmeVM> fitmeVMMutableLiveData = new MutableLiveData<>();
    public ArrayList<FitmeVM> arrayList;

    public FitmeRepo() {

    }

    public MutableLiveData<FitmeVM> getFitmeVMMutableLiveData() {

        ApiService apiService = RetrofitClient.getApiService();

        apiService.getFitMeDetails().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<POJOClass>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(POJOClass pojoClass) {
                        Log.i(TAG, "onNext - > "+pojoClass.status);
                        Log.i(TAG, "onNext - > "+pojoClass.msg);

                        FitmeVM fitmeVM = new FitmeVM(pojoClass);
                        fitmeVMMutableLiveData.setValue(fitmeVM);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, "onError -> "+e);
                    }

                    @Override
                    public void onComplete() {

                    }
                });

        return fitmeVMMutableLiveData;
    }

    }

