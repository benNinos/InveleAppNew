package com.ninositsolution.inveleapp.search_everywhere;

import android.arch.lifecycle.MutableLiveData;
import android.util.Log;

import com.ninositsolution.inveleapp.api.ApiService;
import com.ninositsolution.inveleapp.api.RetrofitClient;
import com.ninositsolution.inveleapp.pojo.POJOClass;

import java.net.SocketTimeoutException;

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
    private MutableLiveData<SearchEverywhereVM> trendingLiveData = new MutableLiveData<>();

    public SearchEveryWhereRepo() {
    }

    public MutableLiveData<SearchEverywhereVM> getCategorySearchLiveData(final String user_id, final String order_by, final String slug) {

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

                        Log.e(TAG, "onError : "+e);


                            getCategorySearchLiveData(user_id, order_by, slug);

                    }

                    @Override
                    public void onComplete() {

                    }
                });

        return categorySearchLiveData;
    }

    public MutableLiveData<SearchEverywhereVM> getSearchEverywhereVMMutableLiveData(final SearchEverywhereRequest request) {

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

                        Log.e(TAG, "onError : "+e);

                            getSearchEverywhereVMMutableLiveData(request);

                    }

                    @Override
                    public void onComplete() {

                    }
                });

        return searchEverywhereVMMutableLiveData;
    }

    public MutableLiveData<SearchEverywhereVM> getSearchFilterUpdateLiveData(final String jsonRequest) {

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

                        Log.e(TAG, "onError : "+e);

                            getSearchFilterUpdateLiveData(jsonRequest);

                    }

                    @Override
                    public void onComplete() {

                    }
                });


        return searchFilterUpdateLiveData;
    }

    public MutableLiveData<SearchEverywhereVM> getCategoryFilterUpdateLiveData(final String jsonRequest)
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

                        Log.e(TAG, "onError : "+e);

                            getCategoryFilterUpdateLiveData(jsonRequest);
                    }

                    @Override
                    public void onComplete() {

                    }
                });

        return categoryFilterUpdateLiveData;
    }

    public MutableLiveData<SearchEverywhereVM> getTrendingLiveData(final String order_by, final String page_no) {

        ApiService apiService = RetrofitClient.getApiService();

        apiService.trendingAllApi(order_by, page_no).subscribeOn(Schedulers.io())
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
                        trendingLiveData.setValue(searchEverywhereVM);


                    }

                    @Override
                    public void onError(Throwable e) {

                        Log.e(TAG, "onError : "+e);
                            getTrendingLiveData(order_by, page_no);

                    }

                    @Override
                    public void onComplete() {

                    }
                });
        return trendingLiveData;
    }
}