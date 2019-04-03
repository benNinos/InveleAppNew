package com.ninositsolution.inveleapp.address_book;

import android.arch.lifecycle.MutableLiveData;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.ninositsolution.inveleapp.add_address.AddAddressVM;
import com.ninositsolution.inveleapp.add_address.pojo.AddAddressRequest;
import com.ninositsolution.inveleapp.address_book.pojo.AddressBookRequest;
import com.ninositsolution.inveleapp.address_book.pojo.AddressUpdateRequest;
import com.ninositsolution.inveleapp.api.ApiService;
import com.ninositsolution.inveleapp.api.RetrofitClient;
import com.ninositsolution.inveleapp.pojo.AddressList;
import com.ninositsolution.inveleapp.pojo.POJOClass;
import com.ninositsolution.inveleapp.utils.Constants;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
public class AddressBookRepo {
    public static final String TAG = "AddressBookRepo";

    private List<AddressBookVM> arrayList;
    AddressList addressList;
    Context context;

    private MutableLiveData<List<AddressBookVM>> addressBookVMMutableLiveData = new MutableLiveData<>();
    private MutableLiveData<AddressBookVM> defaultUpdateVMMutableLiveData = new MutableLiveData<>();
    private MutableLiveData<AddressBookVM> addressDeleteVMMutableLiveData = new MutableLiveData<>();



    public MutableLiveData<List<AddressBookVM>> getAddressListVMMutableLiveData(AddressBookRequest addressBookRequest) {

        ApiService apiService = RetrofitClient.getApiService();

        apiService.addressList(addressBookRequest)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<POJOClass>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(POJOClass pojoClass) {

                        if(pojoClass.status.equalsIgnoreCase("success")) {

                            Log.e(TAG, "onNext - > " + pojoClass.msg);


                            //pojoClassMutableLiveData.setValue(pojoClass);
                            AddressBookVM addressBookVM;
                            Log.e(TAG, "list_size==>" + pojoClass.addresses.size());

                            arrayList = new ArrayList<>();
                            for (int i = 0; i < pojoClass.addresses.size(); i++) {

                                addressList = new AddressList(pojoClass.addresses.get(i).id, pojoClass.addresses.get(i).user_id,
                                        pojoClass.addresses.get(i).address_type, pojoClass.addresses.get(i).address, pojoClass.addresses.get(i).address1,
                                        pojoClass.addresses.get(i).name, pojoClass.addresses.get(i).postal_code, pojoClass.addresses.get(i).city,
                                        pojoClass.addresses.get(i).contact_no, pojoClass.addresses.get(i).is_billing_address, pojoClass.addresses.get(i).is_shipping_address,
                                        pojoClass.addresses.get(i).user_default);

                                addressBookVM = new AddressBookVM(addressList);

                                arrayList.add(addressBookVM);

                            }

                            addressBookVMMutableLiveData.setValue(arrayList);
                        }else if(pojoClass.status.equalsIgnoreCase("error")){
                            Log.e(TAG, "onNext - > " + pojoClass.msg);
                           // Toast.makeText(context,pojoClass.msg,Toast.LENGTH_SHORT).show();
                        }

                    }

                    /*   @Override
                       public void onNext(ArrayList<POJOClass> pojoClasses) {
                           Log.i(TAG, "onNext - > "+pojoClasses.toString());

                           //pojoClassMutableLiveData.setValue(pojoClass);

                           AddressBookVM addressBookVM= new AddressBookVM(pojoClasses);

                           addressBookVMMutableLiveData.setValue(addressBookVM);
                       }*/
                    @Override
                    public void onError(Throwable e) {

                        Log.e(TAG, "onError - > "+e.getMessage());

                    }

                    @Override
                    public void onComplete() {

                    }
                });


        return addressBookVMMutableLiveData;
    }
    public MutableLiveData<AddressBookVM> getUpdateDefaultVMMutableLiveData(AddressUpdateRequest addressUpdateRequest) {

        ApiService apiService = RetrofitClient.getApiService();

        apiService.defaultAddressUpdate(addressUpdateRequest)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<POJOClass>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }
                    @Override
                    public void onNext(POJOClass pojoClass) {

                        if(pojoClass.status.equalsIgnoreCase("success")) {

                           // Toast.makeText(context,pojoClass.msg,Toast.LENGTH_SHORT).show();

                            Log.e(TAG, "onNext - > " + pojoClass.msg);

                            //pojoClassMutableLiveData.setValue(pojoClass);

                            AddressBookVM addressBookVM = new AddressBookVM(pojoClass);

                            defaultUpdateVMMutableLiveData.setValue(addressBookVM);
                        }else if(pojoClass.status.equalsIgnoreCase("error")){
                            Log.e(TAG, "onNext - > " + pojoClass.msg);
                           // Toast.makeText(context,pojoClass.msg,Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                        Log.e(TAG, "onError - > "+e.getMessage());

                    }

                    @Override
                    public void onComplete() {

                    }
                });


        return defaultUpdateVMMutableLiveData;
    }

    public MutableLiveData<AddressBookVM> getAddressDeleteVMMutableLiveData(AddressUpdateRequest addressUpdateRequest) {

        ApiService apiService = RetrofitClient.getApiService();

        apiService.addressDelete(addressUpdateRequest)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<POJOClass>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }
                    @Override
                    public void onNext(POJOClass pojoClass) {

                        if(pojoClass.status.equalsIgnoreCase("success")) {

                            // Toast.makeText(context,pojoClass.msg,Toast.LENGTH_SHORT).show();
                            Log.e(TAG, "onNext - > " + pojoClass.msg);

                            //pojoClassMutableLiveData.setValue(pojoClass);

                            AddressBookVM addressBookVM = new AddressBookVM(pojoClass);

                            addressDeleteVMMutableLiveData.setValue(addressBookVM);

                        }else if(pojoClass.status.equalsIgnoreCase("error")){
                            Log.e(TAG, "onNext - > " + pojoClass.msg);
                            // Toast.makeText(context,pojoClass.msg,Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                        Log.e(TAG, "onError - > "+e.getMessage());

                    }

                    @Override
                    public void onComplete() {

                    }
                });


        return addressDeleteVMMutableLiveData;
    }

}

