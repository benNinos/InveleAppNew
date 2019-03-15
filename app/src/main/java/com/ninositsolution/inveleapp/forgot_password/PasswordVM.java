package com.ninositsolution.inveleapp.forgot_password;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.databinding.ObservableField;

import com.ninositsolution.inveleapp.forgot_password.pojo.OTPRequest;
import com.ninositsolution.inveleapp.forgot_password.pojo.ResetPasswordRequest;
import com.ninositsolution.inveleapp.pojo.POJOClass;
import com.ninositsolution.inveleapp.pojo.Users;

public class PasswordVM extends ViewModel{
    private PasswordRepo passwordRepo;

    public ObservableField<String> email = new ObservableField<>("");
    public ObservableField<String> newPassword = new ObservableField<>("");
    public ObservableField<String> confirmPassword = new ObservableField<>("");
    public ObservableField<String> otpEmail = new ObservableField<>("");
    public ObservableField<String> otpCode = new ObservableField<>("");


    private MutableLiveData<PasswordVM> passwordVMMutableLiveData = new MutableLiveData<>();
    private MutableLiveData<PasswordVM> otpMutableLiveData = new MutableLiveData<>();
    private MutableLiveData<PasswordVM> resetPasswordMutableLiveData = new MutableLiveData<>();

    public ObservableField<String> status = new ObservableField<>();
    public ObservableField<String> msg = new ObservableField<>();
    public ObservableField<Integer> otp = new ObservableField<>();
    public ObservableField<Integer> otp_verify = new ObservableField<>();
    public ObservableField<Users> user = new ObservableField<>();


    public PasswordVM( POJOClass pojoClass){

        this.status.set(pojoClass.status);
        this.msg.set(pojoClass.msg);
        this.otp.set(pojoClass.otp);
        this.otp_verify.set(pojoClass.otp_verify);
        this.user.set(pojoClass.user);

    }


    public PasswordVM()
    {
        passwordRepo = new PasswordRepo();

    }

    public int forgotPasswordEmailValidation()
    {

        return passwordRepo.forgotEmailValidation(email.get());
    }

    public int resetPasswordOtpValidation()
    {

        return passwordRepo.otpValidation(otpCode.get());
    }

    public int emailPatternValidation(){

        return passwordRepo.forgotEmailPatternValidation(email.get());
    }

    public int passwordMatchValidation(){

        return  passwordRepo.passwordValidation(newPassword.get(),confirmPassword.get());
    }



    public int resetPasswordValidation()
    {

        return passwordRepo.resetValidation(newPassword.get(),confirmPassword.get());
    }

    public void forgotPasswordApi()
    {
        passwordVMMutableLiveData =  passwordRepo.getPasswordVMMutableLiveData(email.get());
    }

    public void otpVerifyApi(String user_id)
    {
      OTPRequest otpRequest = new OTPRequest(user_id,otpCode.get());
      otpMutableLiveData = passwordRepo.getOtpMutableLiveData(otpRequest);
    }




    public void resetPasswordApi(Integer user_id){


        ResetPasswordRequest resetPasswordRequest = new ResetPasswordRequest(user_id, newPassword.get(), confirmPassword.get());
        resetPasswordMutableLiveData = passwordRepo.getResetPasswordMutableLiveData(resetPasswordRequest);
    }



    public MutableLiveData<PasswordVM> getPasswordVMMutableLiveData() {
        return passwordVMMutableLiveData;
    }


    public MutableLiveData<PasswordVM> getOtpMutableLiveData(){
        return otpMutableLiveData;
    }

    public MutableLiveData<PasswordVM> getResetPasswordMutableLiveData() {
        return resetPasswordMutableLiveData;
    }
}


