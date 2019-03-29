package com.ninositsolution.inveleapp.personal_information;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.databinding.BindingAdapter;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.net.Uri;
import android.util.Log;
import android.widget.ImageView;

import com.ninositsolution.inveleapp.R;
import com.ninositsolution.inveleapp.change_password.ChangePasswordVM;
import com.ninositsolution.inveleapp.forgot_password.PasswordVM;
import com.ninositsolution.inveleapp.personal_information.pojo.UpdateProfileRequest;
import com.ninositsolution.inveleapp.pojo.HomeArrayLists;
import com.ninositsolution.inveleapp.pojo.POJOClass;
import com.ninositsolution.inveleapp.pojo.Users;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.InputStream;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * Created by Parthasarathy D on 1/25/2019.
 * Ninos IT Solution Pvt Ltd
 * ben@ninositsolution.com
 */
public class PersonalInformationVM extends ViewModel {

    private static final String TAG = "PersonalInformationVM";
    public PersonalInformationRepo personalInformationRepo;


    public ObservableField<String> firstName = new ObservableField<>("");
    public ObservableField<String> lastName = new ObservableField<>("");
    public ObservableField<String> mobileNumber = new ObservableField<>("");
    public ObservableField<String> emailAddress = new ObservableField<>("");
    public ObservableField<String> dateOfBirth = new ObservableField<>("");
    public ObservableField<String> user_image = new ObservableField<>();

    public final ObservableBoolean optionMale = new ObservableBoolean();
    public final ObservableBoolean optionFemale = new ObservableBoolean();
    public String gender = "";

    private MutableLiveData<PersonalInformationVM> personalInformationMutableLiveData = new MutableLiveData<>();



    private MutableLiveData<PersonalInformationVM> personalInfoDetailsMutableLiveData = new MutableLiveData<>();

    public ObservableField<String> status = new ObservableField<>();
    public ObservableField<String> msg = new ObservableField<>();
    public ObservableField<Users> user = new ObservableField<>();

    public PersonalInformationVM (POJOClass pojoClass)
    {
        this.status.set(pojoClass.status);
        this.msg.set(pojoClass.msg);
        this.user.set(pojoClass.user);
    }

    public PersonalInformationVM()
    {
        personalInformationRepo = new PersonalInformationRepo();
    }

    public ObservableField<String> getUser_image()
    {
        return user_image;
    }

    @BindingAdapter("{android:src}")
    public static void loadProfile(ImageView imageView, String url)
    {
        Picasso.get().load(url).placeholder(R.drawable.profile).into(imageView);
    }


    public int personalValidation()
    {
        return personalInformationRepo.personalInfoValidation(firstName.get(),emailAddress.get(),dateOfBirth.get(),mobileNumber.get());
    }


    public MutableLiveData<PersonalInformationVM> getPersonalInformationMutableLiveData()
    {
        return personalInformationMutableLiveData;
    }

    public void profileUpdateApi(Integer user_id, String image_path, String gender)
    {
            File file = new File(image_path);
            RequestBody requestBody  = RequestBody.create(MediaType.parse("multipart/form-data"), file);
            MultipartBody.Part body = MultipartBody.Part.createFormData("image", file.getName(), requestBody);

            personalInformationMutableLiveData = personalInformationRepo
                    .getPersonalInformationMutableLiveData(user_id,firstName.get(),lastName.get(),
                            mobileNumber.get(),emailAddress.get(),gender,dateOfBirth.get());

        Log.i(TAG, "body -> " + body);
    }

    public void getPersonalInfoApi(String user_id)
    {
        personalInfoDetailsMutableLiveData = personalInformationRepo.getProfileDetailsMutableLiveData(user_id);

    }

    public MutableLiveData<PersonalInformationVM> getPersonalInfoDetailsMutableLiveData() {
        return personalInfoDetailsMutableLiveData;
    }

    public void setPersonalInfo(Users users)
    {
        this.firstName.set(users.first_name);
        this.lastName.set(users.last_name);
        this.emailAddress.set(users.email);
        this.dateOfBirth.set(users.dob);
        this.mobileNumber.set(users.mobile);
        this.user_image.set(users.image_path);
    }




}

