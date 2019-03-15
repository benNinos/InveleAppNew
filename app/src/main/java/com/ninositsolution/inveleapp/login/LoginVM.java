package com.ninositsolution.inveleapp.login;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.databinding.ObservableField;

import com.ninositsolution.inveleapp.forgot_password.pojo.OTPRequest;
import com.ninositsolution.inveleapp.pojo.POJOClass;
import com.ninositsolution.inveleapp.pojo.Users;
import com.ninositsolution.inveleapp.registration.pojo.RegistartionRequest;

public class LoginVM extends ViewModel {

    private LoginRepo loginRepo;

    public ObservableField<String> username = new ObservableField<>("");
    public ObservableField<String> password = new ObservableField<>("");
    public ObservableField<String> mobile = new ObservableField<>("");
    public ObservableField<String> otp_code = new ObservableField<>("");

    private MutableLiveData<LoginVM> loginVMMutableLiveData = new MutableLiveData<>();
    private MutableLiveData<LoginVM> mobileSendOtpLiveData = new MutableLiveData<>();
    private MutableLiveData<LoginVM> googleLoginLiveData = new MutableLiveData<>();
    private MutableLiveData<LoginVM> otpVerifyLiveData = new MutableLiveData<>();

    public ObservableField<String> status = new ObservableField<>();
    public ObservableField<String> msg = new ObservableField<>();
    public ObservableField<Integer> otp = new ObservableField<>();
    public ObservableField<Integer> otp_verify = new ObservableField<>();
    public ObservableField<Users> user = new ObservableField<>();
    public ObservableField<Users> users = new ObservableField<>();

    public LoginVM(POJOClass pojoClass)
    {
        this.status.set(pojoClass.status);
        this.msg.set(pojoClass.msg);
        this.otp.set(pojoClass.otp);
        this.otp_verify.set(pojoClass.otp_verify);
        this.user.set(pojoClass.user);
    }

    public LoginVM()
    {
        loginRepo = new LoginRepo();
    }

    public void loginViaEmail()
    {
        LoginRequest loginRequest = new LoginRequest(username.get(), password.get());
        loginVMMutableLiveData = loginRepo.getLoginVMMutableLiveData(loginRequest);
    }

   public Integer emailValidations()
   {
       return loginRepo.emailValidations(username.get(), password.get());
   }

   public Integer mobileAloneValidations()
   {

       return loginRepo.mobileValidations(mobile.get(), "0000");
   }

   public Integer mobileValidations()
   {
       return loginRepo.mobileValidations(mobile.get(), otp_code.get());
   }

   public void sendOtpApi(String device_id)
   {
       RegistartionRequest registartionRequest = new RegistartionRequest("", mobile.get(), "", "", "mobile", "", device_id, "android", "0");
       mobileSendOtpLiveData = loginRepo.getMobileSendOtpLiveData(registartionRequest);

   }

   public void googleLoginApi(String name, String phone, String email,String uid, String deviceId)
   {
       RegistartionRequest registartionRequest = new RegistartionRequest(name, phone, email, "","google", uid, deviceId, "Android", "0");

       googleLoginLiveData = loginRepo.getMobileSendOtpLiveData(registartionRequest);
   }

   public void mobileOtpVerifyApi(String userId)
   {
       OTPRequest otpRequest = new OTPRequest(userId, otp_code.get());
       otpVerifyLiveData =  loginRepo.getOtpVerifyLivedata(otpRequest);
   }

    public MutableLiveData<LoginVM> getLoginVMMutableLiveData() {
        return loginVMMutableLiveData;
    }

    public MutableLiveData<LoginVM> getMobileSendOtpLiveData() {
        return mobileSendOtpLiveData;
    }

    public MutableLiveData<LoginVM> getGoogleLoginLiveData() {
        return googleLoginLiveData;
    }

    public MutableLiveData<LoginVM> getOtpVerifyLiveData() {
        return otpVerifyLiveData;
    }
}
