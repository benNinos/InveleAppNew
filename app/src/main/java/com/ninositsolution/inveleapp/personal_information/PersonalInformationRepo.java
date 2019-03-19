package com.ninositsolution.inveleapp.personal_information;

import android.arch.lifecycle.MutableLiveData;
import android.util.Log;

import com.ninositsolution.inveleapp.api.ApiService;
import com.ninositsolution.inveleapp.api.RetrofitClient;
import com.ninositsolution.inveleapp.personal_information.pojo.UpdateProfileRequest;
import com.ninositsolution.inveleapp.pojo.POJOClass;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Parthasarathy D on 1/25/2019.
 * Ninos IT Solution Pvt Ltd
 * ben@ninositsolution.com
 */
public class PersonalInformationRepo {
    private static final String TAG = "PersonalInformationRepo";

    private MutableLiveData<PersonalInformationVM> personalInformationMutableLiveData = new MutableLiveData<>();
    public MutableLiveData<PersonalInformationVM> getPersonalInformationMutableLiveData(UpdateProfileRequest updateProfileRequest)
    {
        ApiService apiService  = RetrofitClient.getApiService();
        apiService.profileUpdateApi(updateProfileRequest).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<POJOClass>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(POJOClass pojoClass) {
                        Log.i(TAG, "onNext : "+pojoClass.status);

                        PersonalInformationVM personalInformationVM = new PersonalInformationVM(pojoClass);
                        personalInformationMutableLiveData.setValue(personalInformationVM);


                    }

                    @Override
                    public void onError(Throwable e) {

                        Log.i(TAG, "onError : "+e.getMessage());


                    }

                    @Override
                    public void onComplete() {

                    }
                });
        return personalInformationMutableLiveData;
    }
}
