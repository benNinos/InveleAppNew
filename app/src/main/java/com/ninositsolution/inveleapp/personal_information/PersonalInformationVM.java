package com.ninositsolution.inveleapp.personal_information;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.databinding.ObservableField;

import com.ninositsolution.inveleapp.change_password.ChangePasswordVM;
import com.ninositsolution.inveleapp.personal_information.pojo.UpdateProfileRequest;
import com.ninositsolution.inveleapp.pojo.POJOClass;

/**
 * Created by Parthasarathy D on 1/25/2019.
 * Ninos IT Solution Pvt Ltd
 * ben@ninositsolution.com
 */
public class PersonalInformationVM extends ViewModel {

    public PersonalInformationRepo personalInformationRepo;


    public ObservableField<String> firstName = new ObservableField<>("");
    public ObservableField<String> lastName = new ObservableField<>("");
    public ObservableField<String> mobileNumber = new ObservableField<>("");
    public ObservableField<String> emailAddress = new ObservableField<>("");

    private MutableLiveData<PersonalInformationVM> personalInformationMutableLiveData = new MutableLiveData<>();

    public ObservableField<String> status = new ObservableField<>();
    public ObservableField<String> msg = new ObservableField<>();


    public PersonalInformationVM (POJOClass pojoClass)
    {
        this.status.set(pojoClass.status);
        this.msg.set(pojoClass.msg);
    }

    public PersonalInformationVM()
    {
        personalInformationRepo = new PersonalInformationRepo();
    }


    public MutableLiveData<PersonalInformationVM> getPersonalInformationMutableLiveData()
    {
        return personalInformationMutableLiveData;
    }

    public void profileUpdateApi(String user_id)
    {
        UpdateProfileRequest updateProfileRequest = new UpdateProfileRequest(user_id,firstName.get(),lastName.get(),mobileNumber.get(),emailAddress.get());
        personalInformationMutableLiveData = personalInformationRepo.getPersonalInformationMutableLiveData(updateProfileRequest);
    }



}

