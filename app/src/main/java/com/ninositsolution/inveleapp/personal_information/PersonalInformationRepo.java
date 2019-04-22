package com.ninositsolution.inveleapp.personal_information;

import android.arch.lifecycle.MutableLiveData;
import android.util.Log;

import com.ninositsolution.inveleapp.api.ApiService;
import com.ninositsolution.inveleapp.api.RetrofitClient;
import com.ninositsolution.inveleapp.pojo.POJOClass;
import com.ninositsolution.inveleapp.utils.Constants;

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

    private MutableLiveData<PersonalInformationVM> profileDetailsMutableLiveData =new MutableLiveData<>();

    public MutableLiveData<PersonalInformationVM> getPersonalInformationMutableLiveData(final Integer user_id, String first_name, String last_name, String mobile, String email, String gender, String dob, MultipartBody.Part body)

    {
        Log.d(TAG, "user ID is: " + user_id);
        ApiService apiService = RetrofitClient.getApiService();
        apiService.profileUpdateApi(user_id, first_name, last_name, mobile, email, gender, dob, body).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<POJOClass>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(POJOClass pojoClass) {
                        Log.e(TAG, "onNext : " + pojoClass.status);
                        Log.e(TAG, "onNext : " + pojoClass.msg);

                        PersonalInformationVM personalInformationVM = new PersonalInformationVM(pojoClass);
                        personalInformationMutableLiveData.setValue(personalInformationVM);

                    }

                    @Override
                    public void onError(Throwable e) {

                        Log.d(TAG, "onError : " + e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
        return personalInformationMutableLiveData;
    }

    public MutableLiveData<PersonalInformationVM> getProfileDetailsMutableLiveData(String user_id)

    {
        ApiService apiService = RetrofitClient.getApiService();
        apiService.getProfileDetailsApi(user_id).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<POJOClass>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(POJOClass pojoClass) {

                Log.i(TAG, "onNext : "+pojoClass.status);
                Log.i(TAG, "onNext : "+pojoClass.msg);
               // Log.i(TAG, "userName : "+pojoClass.user_id.first_name);
                PersonalInformationVM personalInformationVM = new PersonalInformationVM(pojoClass);
                profileDetailsMutableLiveData.setValue(personalInformationVM);

            }

            @Override
            public void onError(Throwable e) {

                Log.e(TAG, "onError : "+e);

            }

            @Override
            public void onComplete() {

            }
        });

        return profileDetailsMutableLiveData;
    }


    public int personalInfoValidation(String firstName, String mobile, String emailAddress, String dateOfBirth)
    {
        if (firstName.isEmpty())
        {
            return Constants.NAME_EMPTY;
        }
        if (emailAddress.isEmpty()) {
            return Constants.EMAIL_EMPTY;
        }
        if (mobile.isEmpty()) {
            return Constants.MOBILE_NO_EMPTY;
        }
        if (dateOfBirth.isEmpty()) {
            return Constants.DATE_OF_BIRTH_EMPTY;
        }
        return Constants.SUCCESS;

    }
}