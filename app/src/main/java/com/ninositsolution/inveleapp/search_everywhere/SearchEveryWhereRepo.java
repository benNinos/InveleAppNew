package com.ninositsolution.inveleapp.search_everywhere;

import android.arch.lifecycle.MutableLiveData;
import android.util.Log;

import com.ninositsolution.inveleapp.api.ApiService;
import com.ninositsolution.inveleapp.api.RetrofitClient;
import com.ninositsolution.inveleapp.pojo.POJOClass;

import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class SearchEveryWhereRepo {

    private static final String TAG = "SearchEveryWhereRepo";
    private MutableLiveData<SearchEverywhereVM> searchEverywhereVMMutableLiveData = new MutableLiveData<>();
    private MutableLiveData<SearchEverywhereVM> searchFilterUpdateLiveData = new MutableLiveData<>();
    private MutableLiveData<SearchEverywhereVM> categorySearchLiveData = new MutableLiveData<>();
    private MutableLiveData<SearchEverywhereVM> categoryFilterUpdateLiveData = new MutableLiveData<>();

    public SearchEveryWhereRepo() {
    }

    public MutableLiveData<SearchEverywhereVM> getCategorySearchLiveData(String user_id, String order_by, String slug) {

        ApiService apiService = RetrofitClient.getApiService();

        apiService.categerySearchApi(user_id, order_by, slug).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<POJOClass>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(POJOClass pojoClass) {

                        Log.i(TAG, "onNext : "+pojoClass.status);
                        Log.i(TAG, "onNext : "+pojoClass.msg);

                        SearchEverywhereVM searchEverywhereVM = new SearchEverywhereVM(pojoClass);

                        categorySearchLiveData.setValue(searchEverywhereVM);

                    }

                    @Override
                    public void onError(Throwable e) {

                        Log.e(TAG, "onError : "+e.getMessage());

                    }

                    @Override
                    public void onComplete() {

                    }
                });

        return categorySearchLiveData;
    }

    public MutableLiveData<SearchEverywhereVM> getSearchEverywhereVMMutableLiveData(SearchEverywhereRequest request) {

        ApiService apiService = RetrofitClient.getApiService();

        apiService.searchEverywhereApi(request)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<POJOClass>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(POJOClass pojoClass) {

                        Log.i(TAG, "onNext : "+pojoClass.status);
                        Log.i(TAG, "onNext : "+pojoClass.msg);

                        SearchEverywhereVM searchEverywhereVM = new SearchEverywhereVM(pojoClass);
                        searchEverywhereVMMutableLiveData.setValue(searchEverywhereVM);

                    }

                    @Override
                    public void onError(Throwable e) {

                        Log.e(TAG, "onError : "+e.getMessage());

                    }

                    @Override
                    public void onComplete() {

                    }
                });

        return searchEverywhereVMMutableLiveData;
    }

    public MutableLiveData<SearchEverywhereVM> getSearchFilterUpdateLiveData(String jsonRequest) {

        ApiService apiService = RetrofitClient.getApiService();

        apiService.getSearchFilterUpdate(jsonRequest)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<POJOClass>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(POJOClass pojoClass) {

                        Log.i(TAG, "onNext : "+pojoClass.status);
                        Log.i(TAG, "onNext : "+pojoClass.msg);

                        SearchEverywhereVM searchEverywhereVM = new SearchEverywhereVM(pojoClass);
                        searchFilterUpdateLiveData.setValue(searchEverywhereVM);

                    }

                    @Override
                    public void onError(Throwable e) {

                        Log.e(TAG, "onError : "+e.getMessage());

                    }

                    @Override
                    public void onComplete() {

                    }
                });


        return searchFilterUpdateLiveData;
    }

    public MutableLiveData<SearchEverywhereVM> getCategoryFilterUpdateLiveData(String jsonRequest)
    {
        ApiService apiService = RetrofitClient.getApiService();

        apiService.getCategoryFilterUpdate(jsonRequest).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<POJOClass>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(POJOClass pojoClass) {

                        Log.i(TAG, "onNext : "+pojoClass.status);
                        Log.i(TAG, "onNext : "+pojoClass.msg);

                        SearchEverywhereVM searchEverywhereVM = new SearchEverywhereVM(pojoClass);
                        categoryFilterUpdateLiveData.setValue(searchEverywhereVM);


                    }

                    @Override
                    public void onError(Throwable e) {

                        Log.e(TAG, "onError : "+e.getMessage());

                    }

                    @Override
                    public void onComplete() {

                    }
                });

        return categoryFilterUpdateLiveData;
    }
}