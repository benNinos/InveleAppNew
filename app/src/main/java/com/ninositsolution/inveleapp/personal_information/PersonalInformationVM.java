package com.ninositsolution.inveleapp.personal_information;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;

import com.ninositsolution.inveleapp.change_password.ChangePasswordVM;
import com.ninositsolution.inveleapp.personal_information.pojo.UpdateProfileRequest;
import com.ninositsolution.inveleapp.pojo.POJOClass;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

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
    public ObservableField<String> dateOfBirth = new ObservableField<>("");
    public final ObservableBoolean optionMale = new ObservableBoolean();
    public final ObservableBoolean optionFemale = new ObservableBoolean();
    public String gender = "";

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

    public void profileUpdateApi(String user_id, String image_path)
    {
        File file = new File(image_path);
        RequestBody requestBody  = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        MultipartBody.Part body = MultipartBody.Part.createFormData("image", file.getName(), requestBody);



//        personalInformationMutableLiveData = personalInformationRepo.getPersonalInformationMutableLiveData("25", "Arun", "prasad",
//                "male", "7402191727", "arunprasadh.s@gmail.com", "1993-12-11", body );

        personalInformationMutableLiveData = personalInformationRepo.getPersonalInformationMutableLiveData(user_id,firstName.get(),lastName.get(),mobileNumber.get(),gender,emailAddress.get(),dateOfBirth.get(),body);
    }






}

