package com.ninositsolution.inveleapp.cart;

import android.arch.lifecycle.MutableLiveData;
import android.util.Log;

import com.ninositsolution.inveleapp.api.RetrofitClient;
import com.ninositsolution.inveleapp.pojo.POJOClass;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Parthasarathy D on 1/17/2019.
 * Ninos IT Solution Pvt Ltd
 * ben@ninositsolution.com
 */
public class CartRepo {

    private MutableLiveData<CartVM> cartListsLiveData = new MutableLiveData<>();
    private MutableLiveData<CartVM> quantityLiveData = new MutableLiveData<>();
    private static final String TAG = CartRepo.class.getSimpleName();

    public MutableLiveData<CartVM> getCartListsLiveData(final String userId) {

        RetrofitClient.getApiService().getCartLists(userId)
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
                        Log.i(TAG, "onNext -> "+pojoClass.getCartDetailsList().get(0).getStoreName());
                        Log.i(TAG, "onNext -> "+pojoClass.getCartDetailsList().size());

                        cartListsLiveData.setValue(new CartVM(pojoClass));

                    }

                    @Override
                    public void onError(Throwable e) {

                        Log.e(TAG, "onNext -> "+e);
                        getCartListsLiveData(userId);

                    }

                    @Override
                    public void onComplete() {

                    }
                });

        return cartListsLiveData;
    }

    public MutableLiveData<CartVM> getQuantityLiveData(final String cartId, final String quantity) {

        RetrofitClient.getApiService().updateCartQuantity(cartId, quantity)
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

                        cartListsLiveData.setValue(new CartVM(pojoClass));

                    }

                    @Override
                    public void onError(Throwable e) {

                        Log.e(TAG, "onNext -> "+e);
                        getQuantityLiveData(cartId, quantity);

                    }

                    @Override
                    public void onComplete() {

                    }
                });


        return quantityLiveData;
    }
}
