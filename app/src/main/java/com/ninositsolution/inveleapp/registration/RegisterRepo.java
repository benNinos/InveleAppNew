package com.ninositsolution.inveleapp.registration;

import android.arch.lifecycle.MutableLiveData;
import android.util.Log;
import android.util.Patterns;

import com.ninositsolution.inveleapp.api.ApiService;
import com.ninositsolution.inveleapp.api.RetrofitClient;
import com.ninositsolution.inveleapp.pojo.POJOClass;
import com.ninositsolution.inveleapp.registration.pojo.RegistartionRequest;
import com.ninositsolution.inveleapp.utils.Constants;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class RegisterRepo {

    private static final String TAG = "RegisterRepo";

   // private MutableLiveData<POJOClass> pojoClassMutableLiveData = new MutableLiveData<>();

    private MutableLiveData<RegisterVM> registerVMMutableLiveData = new MutableLiveData<>();

    public MutableLiveData<RegisterVM> getRegisterVMMutableLiveData(RegistartionRequest registartionRequest) {

        ApiService apiService = RetrofitClient.getApiService();

        apiService.registerApi(registartionRequest)
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

                RegisterVM registerVM = new RegisterVM(pojoClass);

                registerVMMutableLiveData.setValue(registerVM);
            }

            @Override
            public void onError(Throwable e) {

                Log.e(TAG, "onError - > "+e.getMessage());

            }

            @Override
            public void onComplete() {

            }
        });


        return registerVMMutableLiveData;
    }


    public int emailValidations(String email, String name, String password)
    {
        if (email.isEmpty())
        {
            return Constants.EMAIL_EMPTY;
        }

        if (name.isEmpty())
        {
            return Constants.EMAIL_NAME_EMPTY;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches())
        {
            return Constants.NO_EMAIL_PATTERN;
        }

        if (password.isEmpty())
        {
            return Constants.PASSWORD_EMPTY;
        }

        return Constants.SUCCESS;
    }

    public int mobileValidations(String mobile, String name)
    {
        if (mobile.isEmpty())
        {
            return Constants.MOBILE_NO_EMPTY;
        }

        if (name.isEmpty())
        {
            return Constants.MOBILE_NAME_EMPTY;
        }

        return Constants.SUCCESS;
    }


}
