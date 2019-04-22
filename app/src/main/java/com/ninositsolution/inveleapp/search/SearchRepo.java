package com.ninositsolution.inveleapp.search;

import android.arch.lifecycle.MutableLiveData;
import android.util.Log;

import com.ninositsolution.inveleapp.api.RetrofitClient;
import com.ninositsolution.inveleapp.pojo.POJOClass;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Parthasarathy D on 1/24/2019.
 * Ninos IT Solution Pvt Ltd
 * ben@ninositsolution.com
 */
public class SearchRepo {

    private static final String TAG = "SearchRepo";

    private MutableLiveData<SearchVM> searchVMMutableLiveData = new MutableLiveData<>();

    public MutableLiveData<SearchVM> getSearchVMMutableLiveData(final String keyword) {

        RetrofitClient.getApiService().getSearchByKeyword(keyword)
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
                        SearchVM searchVM = new SearchVM(pojoClass);
                        searchVMMutableLiveData.setValue(searchVM);

                    }

                    @Override
                    public void onError(Throwable e) {

                        Log.i(TAG, "onError -> "+e);
                        getSearchVMMutableLiveData(keyword);

                    }

                    @Override
                    public void onComplete() {

                    }
                });


        return searchVMMutableLiveData;
    }
}
