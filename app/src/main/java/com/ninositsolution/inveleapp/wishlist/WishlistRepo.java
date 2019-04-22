package com.ninositsolution.inveleapp.wishlist;

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

/**
 * Created by Parthasarathy D on 1/22/2019.
 * Ninos IT Solution Pvt Ltd
 * ben@ninositsolution.com
 */
public class WishlistRepo {

    private static final String TAG = "WishlistRepo";
    private MutableLiveData<WishlistVM> wishlistVMMutableLiveData = new MutableLiveData<>();


    public MutableLiveData<WishlistVM> getWishlistVMMutableLiveData(final String userId) {

        ApiService apiService = RetrofitClient.getApiService();

        apiService.getWishListsApi(userId).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<POJOClass>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(POJOClass pojoClass) {
                        Log.i(TAG, "onNext : "+pojoClass.status);
                        Log.i(TAG, "onNext : "+pojoClass.msg);

                        wishlistVMMutableLiveData.setValue(new WishlistVM(pojoClass));
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, "onError : "+e);
                        getWishlistVMMutableLiveData(userId);
                    }

                    @Override
                    public void onComplete() {

                    }
                });

        return wishlistVMMutableLiveData;
    }
}
