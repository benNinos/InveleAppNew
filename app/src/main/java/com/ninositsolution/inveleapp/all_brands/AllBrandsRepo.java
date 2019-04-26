package com.ninositsolution.inveleapp.all_brands;

import android.arch.lifecycle.MutableLiveData;
import android.util.Log;

import com.ninositsolution.inveleapp.api.RetrofitClient;
import com.ninositsolution.inveleapp.pojo.POJOClass;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class AllBrandsRepo {

    private MutableLiveData<AllBrandsVM> allBrandsVMMutableLiveData = new MutableLiveData<>();
    private static final String TAG = AllBrandsRepo.class.getSimpleName();


    public MutableLiveData<AllBrandsVM> getAllBrandsVMMutableLiveData() {

        RetrofitClient.getApiService().allBrands()
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

                        allBrandsVMMutableLiveData.setValue(new AllBrandsVM(pojoClass));

                    }

                    @Override
                    public void onError(Throwable e) {

                        Log.i(TAG, "onError -> "+e);
                    }

                    @Override
                    public void onComplete() {

                    }
                });

        return allBrandsVMMutableLiveData;
    }
}
