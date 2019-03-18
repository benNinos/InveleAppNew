package com.ninositsolution.inveleapp.edit_address;

import android.arch.lifecycle.MutableLiveData;
import android.util.Log;

import com.ninositsolution.inveleapp.add_address.AddAddressVM;
import com.ninositsolution.inveleapp.add_address.pojo.AddAddressRequest;
import com.ninositsolution.inveleapp.address_book.AddressBookVM;
import com.ninositsolution.inveleapp.address_book.pojo.AddressUpdateRequest;
import com.ninositsolution.inveleapp.api.ApiService;
import com.ninositsolution.inveleapp.api.RetrofitClient;
import com.ninositsolution.inveleapp.edit_address.pojo.EditAddressRequest;
import com.ninositsolution.inveleapp.pojo.AddressList;
import com.ninositsolution.inveleapp.pojo.POJOClass;
import com.ninositsolution.inveleapp.utils.Constants;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class EditAddressRepo {

    private static final String TAG = "EditAddressRepo";

    private AddressBookVM arrayList;
    AddressList addressList;


    private MutableLiveData<EditAddressVM> editAddressVMMutableLiveData = new MutableLiveData<>();
    private MutableLiveData<EditAddressVM> showAddressVMMutableLiveData = new MutableLiveData<>();
    private MutableLiveData<EditAddressVM> locateAddessVMMutableLiveData = new MutableLiveData<>();
    public MutableLiveData<EditAddressVM> getEditAddressVMMutableLiveData(EditAddressRequest editAddressRequest) {

        ApiService apiService = RetrofitClient.getApiService();

        apiService.UpdateAddressApi(editAddressRequest)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<POJOClass>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(POJOClass pojoClass) {

                        Log.i(TAG, "onNext - > "+pojoClass.msg);

                        //pojoClassMutableLiveData.setValue(pojoClass);

                        EditAddressVM editAddressVM = new EditAddressVM(pojoClass);

                        editAddressVMMutableLiveData.setValue(editAddressVM);
                    }

                    @Override
                    public void onError(Throwable e) {

                        Log.e(TAG, "onError - > "+e.getMessage());

                    }

                    @Override
                    public void onComplete() {

                    }
                });


        return editAddressVMMutableLiveData;
    }


      //Address save validation
    public int addressValidation(String name, String contact_no, String postal,String address,String address1,String city,String address_type)
    {
        if (name.isEmpty())
        {
            return Constants.NAME_EMPTY;
        }
        if (contact_no.isEmpty())
        {
            return Constants.CONTACT_NUMBER_EMPTY;
        }
        if (postal.isEmpty())
        {
            return Constants.POSTAL_CODE;
        }
        if(address.isEmpty()){
            return  Constants.ADDRESS;
        }
        if(address1.isEmpty()){
            return  Constants.ADDRESS1;
        }
        if(city.isEmpty()){
            return  Constants.CITY;
        }
        if(address_type.isEmpty()){
            return  Constants.ADDRESS_TYPE;
        }

        return Constants.SUCCESS;
    }
    //search address validation

    public int postalCodeValidation(String postal_code){
        if(postal_code.isEmpty()){
            return Constants.POSTAL_CODE;
        }
        return Constants.SUCCESS;
    }

    public MutableLiveData<EditAddressVM> getLocateAddessVMMutableLiveData(AddAddressRequest addAddressRequest) {

        ApiService apiService = RetrofitClient.getApiService();

        apiService.locateAddressApi(addAddressRequest)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<POJOClass>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(POJOClass pojoClass) {

                        Log.e(TAG, "onNext - > "+pojoClass.msg);

                        //pojoClassMutableLiveData.setValue(pojoClass);

                        EditAddressVM editAddressVM = new EditAddressVM(pojoClass);

                        locateAddessVMMutableLiveData.setValue(editAddressVM);
                    }

                    @Override
                    public void onError(Throwable e) {

                        Log.e(TAG, "onError - > "+e.getMessage());

                    }

                    @Override
                    public void onComplete() {

                    }
                });


        return locateAddessVMMutableLiveData;
    }

    public MutableLiveData<EditAddressVM> getshowAddessVMMutableLiveData(AddressUpdateRequest addressUpdateRequest) {

        ApiService apiService = RetrofitClient.getApiService();

        apiService.showAddress(addressUpdateRequest)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<POJOClass>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(POJOClass pojoClass) {

                        Log.e(TAG, "onNext - > "+pojoClass.msg);

                        //pojoClassMutableLiveData.setValue(pojoClass);

                        //pojoClassMutableLiveData.setValue(pojoClass);
                      /*  EditAddressVM editAddressVM;
                        Log.e(TAG,"list_size==>"+pojoClass.user_address);

                        if(pojoClass.user_address!=null){


                            addressList = new AddressList(pojoClass.user_address.id,pojoClass.user_address.user_id,
                                    pojoClass.user_address.address_type,pojoClass.user_address.address,pojoClass.user_address.address1,
                                    pojoClass.user_address.name,pojoClass.user_address.postal_code,pojoClass.user_address.city,
                                    pojoClass.user_address.contact_no,pojoClass.user_address.is_billing_address,pojoClass.user_address.is_shipping_address,
                                    pojoClass.user_address.user_default);

                            editAddressVM = new EditAddressVM(addressList);

                            arrayList.add(addressBookVM);

                        }

                        addressBookVMMutableLiveData.setValue(arrayList);*/

                        EditAddressVM editAddressVM = new EditAddressVM(pojoClass);

                        showAddressVMMutableLiveData.setValue(editAddressVM);
                    }

                    @Override
                    public void onError(Throwable e) {

                        Log.e(TAG, "onError - > "+e.getMessage());

                    }

                    @Override
                    public void onComplete() {

                    }
                });


        return showAddressVMMutableLiveData;
    }



}
