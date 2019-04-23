package com.ninositsolution.inveleapp.fitme_list;

import android.arch.lifecycle.MutableLiveData;

import com.ninositsolution.inveleapp.api.RetrofitClient;
import com.ninositsolution.inveleapp.pojo.POJOClass;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class FitmeListRepo {

    private MutableLiveData<FitmeListVM> fitmeListVMMutableLiveData = new MutableLiveData<>();

    public MutableLiveData<FitmeListVM> getFitmeListVMMutableLiveData(String userId) {

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
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });


        return fitmeListVMMutableLiveData;
    }
}
