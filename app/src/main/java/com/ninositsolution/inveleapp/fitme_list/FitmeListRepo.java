package com.ninositsolution.inveleapp.fitme_list;

import android.arch.lifecycle.MutableLiveData;
import android.util.Log;

import com.ninositsolution.inveleapp.api.RetrofitClient;
import com.ninositsolution.inveleapp.pojo.POJOClass;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class FitmeListRepo {
    private static final String TAG = FitmeListRepo.class.getSimpleName();

    private MutableLiveData<FitmeListVM> fitmeListVMMutableLiveData = new MutableLiveData<>();
    private MutableLiveData<FitmeListVM> fitmeDeleteLiveData = new MutableLiveData<>();

    public MutableLiveData<FitmeListVM> getFitmeListVMMutableLiveData(final String userId) {

        RetrofitClient.getApiService().getFitmeLists(userId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<POJOClass>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(POJOClass pojoClass) {

                        fitmeListVMMutableLiveData.setValue(new FitmeListVM(pojoClass));
                        Log.i(TAG, "onNext -> "+pojoClass.status);
                        Log.i(TAG, "onNext -> "+pojoClass.msg);
                    }

                    @Override
                    public void onError(Throwable e) {

                        Log.i(TAG, "onError -> "+e);

                        getFitmeListVMMutableLiveData(userId);
                    }

                    @Override
                    public void onComplete() {

                    }
                });


        return fitmeListVMMutableLiveData;
    }

    public MutableLiveData<FitmeListVM> getFitmeDeleteLiveData(int userMeasurementId) {

        RetrofitClient.getApiService().deleteFitme(userMeasurementId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<POJOClass>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(POJOClass pojoClass) {

                        fitmeDeleteLiveData.setValue(new FitmeListVM(pojoClass));
                        Log.i(TAG, "onNext -> "+pojoClass.status);
                        Log.i(TAG, "onNext -> "+pojoClass.msg);
                    }

                    @Override
                    public void onError(Throwable e) {

                        Log.i(TAG, "onError -> "+e);
                    }

                    @Override
                    public void onComplete() {

                    }
                });


        return fitmeDeleteLiveData;
    }
}
