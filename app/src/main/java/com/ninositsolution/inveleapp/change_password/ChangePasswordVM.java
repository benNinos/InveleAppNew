package com.ninositsolution.inveleapp.change_password;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.ObservableField;
import com.ninositsolution.inveleapp.change_password.pojo.ChangePassowrdRequest;
import com.ninositsolution.inveleapp.pojo.POJOClass;
import com.ninositsolution.inveleapp.pojo.Users;


/**
 * Created by Parthasarathy D on 1/25/2019.
 * Ninos IT Solution Pvt Ltd
 * ben@ninositsolution.com
 */
public class ChangePasswordVM extends ViewModel {

    private ChangePasswordRepo changePasswordRepo;

    public ObservableField<String> currentPassword = new ObservableField<>("");
    public ObservableField<String> newPassword = new ObservableField<>("");
    public ObservableField<String> confirmNewPassword = new ObservableField<>("");


    private MutableLiveData<ChangePasswordVM> changePasswordMutableLiveData = new MutableLiveData<>();

    public ObservableField<String> status = new ObservableField<>();
    public ObservableField<String> msg = new ObservableField<>();

    public ChangePasswordVM(POJOClass pojoClass)
    {
        this.status.set(pojoClass.status);
        this.msg.set(pojoClass.msg);
    }

    public ChangePasswordVM()
    {
        changePasswordRepo = new ChangePasswordRepo();
    }

    public int changePasswordEmptyValidation()
    {
        return changePasswordRepo.changePasswordEmptyValidation(currentPassword.get(),newPassword.get(),confirmNewPassword.get());
    }

    public int changePasswordSamevaliation()
    {
        return changePasswordRepo.changePasswordValidation(newPassword.get(),confirmNewPassword.get());
    }


    public MutableLiveData<ChangePasswordVM> getChangePasswordMutableLiveData()
    {
        return changePasswordMutableLiveData;
    }


    public void updatePasswordApi(Integer user_id)
    {
        ChangePassowrdRequest changePassowrdRequest = new ChangePassowrdRequest(user_id,currentPassword.get(),newPassword.get(),confirmNewPassword.get());
        changePasswordMutableLiveData = changePasswordRepo.getChangePasswordMutableLiveData(changePassowrdRequest);
    }





}
