package com.ninositsolution.inveleapp.change_password;
import android.arch.lifecycle.MutableLiveData;
import android.util.Log;
import com.ninositsolution.inveleapp.api.ApiService;
import com.ninositsolution.inveleapp.api.RetrofitClient;
import com.ninositsolution.inveleapp.change_password.pojo.ChangePassowrdRequest;
import com.ninositsolution.inveleapp.forgot_password.PasswordVM;
import com.ninositsolution.inveleapp.pojo.POJOClass;

import com.ninositsolution.inveleapp.utils.Constants;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;



/**
 * Created by Parthasarathy D on 1/25/2019.
 * Ninos IT Solution Pvt Ltd
 * ben@ninositsolution.com
 */
public class ChangePasswordRepo {
    private static final String TAG = "ChangePasswordRepo";

    private MutableLiveData<ChangePasswordVM> changePasswordMutableLiveData = new MutableLiveData<>();

    public MutableLiveData<ChangePasswordVM> getChangePasswordMutableLiveData(ChangePassowrdRequest changePassowrdRequest)
    {
        ApiService apiService = RetrofitClient.getApiService();
        apiService.updatePasswordApi(changePassowrdRequest).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<POJOClass>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(POJOClass pojoClass) {
                        Log.i(TAG, "onNext : "+pojoClass.status);

                        ChangePasswordVM changePasswordVM = new ChangePasswordVM(pojoClass);
                        changePasswordMutableLiveData.setValue(changePasswordVM);

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i(TAG, "onError : "+e.getMessage());

                    }

                    @Override
                    public void onComplete() {

                    }
                });

        return changePasswordMutableLiveData;
    }

    public int changePasswordValidation(String newPassword,String confirmNewPassword)
    {
        if (newPassword.matches(confirmNewPassword))
        {
            return Constants.PASSWORD_MATCH;
        } else
        {
            return Constants.PASSWORD_MISMATCH;
        }
    }

    public int changePasswordEmptyValidation(String currentPassword, String newPassword,String confirmNewPassword)
    {
        if (currentPassword.isEmpty())
        {
            return Constants.PASSWORD_EMPTY;
        }
        if (newPassword.isEmpty())
        {
            return Constants.NEW_PASSWORD_EMPTY;
        }
        if (confirmNewPassword.isEmpty())
        {
            return  Constants.CONFIRM_NEW_PASSWORD_EMPTY;
        }

         return Constants.SUCCESS;

    }
}
