package com.ninositsolution.inveleapp.personal_information;

import android.arch.lifecycle.MutableLiveData;
import android.util.Log;

import com.ninositsolution.inveleapp.api.ApiService;
import com.ninositsolution.inveleapp.api.RetrofitClient;
import com.ninositsolution.inveleapp.pojo.POJOClass;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MultipartBody;


/**
 * Created by Parthasarathy D on 1/25/2019.
 * Ninos IT Solution Pvt Ltd
 * ben@ninositsolution.com
 */
public class PersonalInformationRepo {
    private static final String TAG = "PersonalInformationRepo";

    private MutableLiveData<PersonalInformationVM> personalInformationMutableLiveData = new MutableLiveData<>();
    public MutableLiveData<PersonalInformationVM> getPersonalInformationMutableLiveData(final String user_id, String first_name, String last_name, String gender, String mobile, String email, String dob, MultipartBody.Part body)
    {
        Log.d(TAG, "user ID is: " +user_id);
        ApiService apiService  = RetrofitClient.getApiService();
        apiService.profileUpdateApi(user_id,first_name,last_name,email,gender,dob,mobile, body ).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<POJOClass>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(POJOClass pojoClass) {
                        Log.d(TAG, "onNext : "+pojoClass.status);
                        Log.d(TAG, "onNext : "+pojoClass.msg);

                        PersonalInformationVM personalInformationVM = new PersonalInformationVM(pojoClass);
                        personalInformationMutableLiveData.setValue(personalInformationVM);

                    }

                    @Override
                    public void onError(Throwable e) {

                        Log.d(TAG, "onError : "+e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
        return personalInformationMutableLiveData;
    }
}
