package com.ninositsolution.inveleapp.add_address;

import android.arch.lifecycle.MutableLiveData;
import android.content.Context;
import android.util.Log;
import android.util.Patterns;
import android.widget.Toast;

import com.ninositsolution.inveleapp.add_address.pojo.AddAddressRequest;
import com.ninositsolution.inveleapp.api.ApiService;
import com.ninositsolution.inveleapp.api.RetrofitClient;
import com.ninositsolution.inveleapp.pojo.POJOClass;
import com.ninositsolution.inveleapp.utils.Constants;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class AddAddressRepo {

    private static final String TAG = "EditAddressRepo";
    Context context;

    private MutableLiveData<AddAddressVM> addAddressVMMutableLiveData = new MutableLiveData<>();
    private MutableLiveData<AddAddressVM> locateAddessVMMutableLiveData = new MutableLiveData<>();
    public MutableLiveData<AddAddressVM> getAddAddressVMMutableLiveData(AddAddressRequest addAddressRequest) {

        ApiService apiService = RetrofitClient.getApiService();

        apiService.addAddressApi(addAddressRequest)
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

                            AddAddressVM addAddressVM = new AddAddressVM(pojoClass);

                            addAddressVMMutableLiveData.setValue(addAddressVM);
                        }else if(pojoClass.status.equalsIgnoreCase("error")){
                            Log.e(TAG, "onNext - > " + pojoClass.msg);
                           // Toast.makeText(context,pojoClass.msg,Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                        Log.e(TAG, "onError - > "+e.getMessage());
                       // Toast.makeText(context,e.getMessage(),Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onComplete() {

                    }
                });


        return addAddressVMMutableLiveData;
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

    public MutableLiveData<AddAddressVM> getLocateAddessVMMutableLiveData(AddAddressRequest addAddressRequest) {

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

                        if(pojoClass.status.equalsIgnoreCase("success")) {

                           // Toast.makeText(context,pojoClass.msg,Toast.LENGTH_SHORT).show();


                            Log.e(TAG, "onNext - > " + pojoClass.msg);

                            //pojoClassMutableLiveData.setValue(pojoClass);

                            AddAddressVM addAddressVM = new AddAddressVM(pojoClass);

                            locateAddessVMMutableLiveData.setValue(addAddressVM);
                        }else if(pojoClass.status.equalsIgnoreCase("error")){
                            Log.e(TAG, "onNext - > " + pojoClass.msg);

                           // Toast.makeText(context,pojoClass.msg,Toast.LENGTH_SHORT).show();

                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                        Log.e(TAG, "onError - > "+e.getMessage());
                       // Toast.makeText(context,e.getMessage(),Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onComplete() {

                    }
                });


        return locateAddessVMMutableLiveData;
    }



}
