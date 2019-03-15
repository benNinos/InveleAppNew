package com.ninositsolution.inveleapp.registration;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.databinding.ObservableField;

import com.ninositsolution.inveleapp.pojo.POJOClass;
import com.ninositsolution.inveleapp.pojo.Users;
import com.ninositsolution.inveleapp.registration.pojo.RegistartionRequest;

/**
 * Created by Parthasarathy D on 1/11/2019.
 * Ninos IT Solution
 * parthasarathy.d@ninositsolution.com
 */
public class RegisterVM extends ViewModel {

    private RegisterRepo registerRepo;
    private MutableLiveData<RegisterVM> registerVMMutableLiveData = new MutableLiveData<>();
    private MutableLiveData<POJOClass> pojoClassMutableLiveData = new MutableLiveData<>();

    // UI fields

    public ObservableField<String> email = new ObservableField<>("");
    public ObservableField<String> email_name = new ObservableField<>("");
    public ObservableField<String> password = new ObservableField<>("");
    public ObservableField<String> mobile = new ObservableField<>("");
    public ObservableField<String> mobile_name = new ObservableField<>("");

    // POJO fields

    public ObservableField<String> status = new ObservableField<>();
    public ObservableField<String> msg = new ObservableField<>();
    public ObservableField<Users> users = new ObservableField<>();
    public ObservableField<Integer> otp = new ObservableField<>();

    public RegisterVM(POJOClass pojoClass)
    {
        this.status.set(pojoClass.status);
        this.msg.set(pojoClass.msg);
        this.users.set(pojoClass.users);
        this.otp.set(pojoClass.otp);
    }

    public RegisterVM()
    {
        registerRepo = new RegisterRepo();
    }

    public int emailValidation()
    {
        return registerRepo.emailValidations(email.get(), email_name.get(), password.get());
    }

    public int mobileValidation()
    {
        return registerRepo.mobileValidations(mobile.get(), mobile_name.get());
    }

    public void registerViaEmail(String device_id)
    {
        RegistartionRequest registartionRequest = new RegistartionRequest(email_name.get(), "",email.get(),
                password.get(), "email", "", device_id, "android", "1");

        registerRepo = new RegisterRepo();

       // pojoClassMutableLiveData = registerRepo.getRegisterVMMutableLiveData(registartionRequest);
        registerVMMutableLiveData = registerRepo.getRegisterVMMutableLiveData(registartionRequest);

        //String message = registerVMMutableLiveData.getValue().status.get();

          //  stringMutableLiveData.setValue(message);

    }

    public void registerViaMobile (String device_id)
    {
        RegistartionRequest registartionRequest = new RegistartionRequest(mobile_name.get(), mobile.get(),"",
                "", "mobile", "", device_id, "android", "1");

        registerVMMutableLiveData = registerRepo.getRegisterVMMutableLiveData(registartionRequest);
        //pojoClassMutableLiveData = registerRepo.getRegisterVMMutableLiveData(registartionRequest);

    }
    public MutableLiveData<POJOClass> getPojoClassMutableLiveData() {
        return pojoClassMutableLiveData;
    }

    public MutableLiveData<RegisterVM> getRegisterVMMutableLiveData() {
        return registerVMMutableLiveData;
    }
}