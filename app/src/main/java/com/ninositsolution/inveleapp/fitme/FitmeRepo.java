package com.ninositsolution.inveleapp.fitme;

import android.arch.lifecycle.MutableLiveData;
import android.nfc.Tag;
import android.util.Log;

import com.ninositsolution.inveleapp.api.ApiService;
import com.ninositsolution.inveleapp.api.RetrofitClient;
import com.ninositsolution.inveleapp.fitme.pojo.FitmeRequest;
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


    private static final String TAG = "FitmeListRepo";
    private MutableLiveData<FitmeVM> fitmeVMMutableLiveData = new MutableLiveData<>();
    private MutableLiveData<FitmeVM> fitmeAddLiveData = new MutableLiveData<>();
    private MutableLiveData<FitmeVM> fitmeUpdateLiveData = new MutableLiveData<>();
    private MutableLiveData<FitmeVM> fitmeEditMutableLiveData = new MutableLiveData<>();

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

    public MutableLiveData<FitmeVM> getFitmeAddLiveData(String jsonRequest) {

        ApiService apiService = RetrofitClient.getApiService();

        apiService.addfitmeApi(jsonRequest).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<POJOClass>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(POJOClass pojoClass) {
                        Log.i(TAG, "onNext - > "+pojoClass.status);
                        Log.i(TAG, "onNext - > "+pojoClass.msg);

                        FitmeVM fitmeVM = new FitmeVM(pojoClass.status, pojoClass.msg);
                        fitmeAddLiveData.setValue(fitmeVM);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, "onError -> "+e);
                    }

                    @Override
                    public void onComplete() {

                    }
                });

        return fitmeAddLiveData;
    }

    public MutableLiveData<FitmeVM> getFitmeEditMutableLiveData(String userId, int userMeasurementId) {

        ApiService apiService = RetrofitClient.getApiService();

        apiService.getFitmeEditLabels(userId, userMeasurementId).subscribeOn(Schedulers.io())
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
                        fitmeEditMutableLiveData.setValue(fitmeVM);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, "onError -> "+e);
                    }

                    @Override
                    public void onComplete() {

                    }
                });


        return fitmeEditMutableLiveData;
    }

    public MutableLiveData<FitmeVM> getFitmeUpdateLiveData(String jsonRequest) {

        ApiService apiService = RetrofitClient.getApiService();

        apiService.updatefitmeApi(jsonRequest).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<POJOClass>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(POJOClass pojoClass) {
                        Log.i(TAG, "onNext - > "+pojoClass.status);
                        Log.i(TAG, "onNext - > "+pojoClass.msg);

                        FitmeVM fitmeVM = new FitmeVM(pojoClass.status, pojoClass.msg);
                        fitmeUpdateLiveData.setValue(fitmeVM);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, "onError -> "+e);
                    }

                    @Override
                    public void onComplete() {

                    }
                });


        return fitmeUpdateLiveData;
    }
}

