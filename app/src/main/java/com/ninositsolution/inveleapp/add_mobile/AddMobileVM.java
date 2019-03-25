package com.ninositsolution.inveleapp.add_mobile;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.databinding.ObservableField;

import com.ninositsolution.inveleapp.add_mobile.pojo.MobileOTPRequest;
import com.ninositsolution.inveleapp.add_mobile.pojo.VerifyOTPRequest;
import com.ninositsolution.inveleapp.pojo.POJOClass;
import com.ninositsolution.inveleapp.pojo.Users;

/**
 * Created by Parthasarathy D on 1/25/2019.
 * Ninos IT Solution Pvt Ltd
 * ben@ninositsolution.com
 */
public class AddMobileVM extends ViewModel {

        private AddMobileRepo addMobileRepo;

    public ObservableField<String> mobileNumber = new ObservableField<>("");
    public ObservableField<String> otpCode = new ObservableField<>("");
    public ObservableField<String> newMobileNumber = new ObservableField<>("");
    public ObservableField<String> newOtpCode = new ObservableField<>("");

    private MutableLiveData<AddMobileVM> mobileOTPMutableLiveData = new MutableLiveData<>();
    private MutableLiveData<AddMobileVM> newMobileOTPMutableLiveData = new MutableLiveData<>();

    private MutableLiveData<AddMobileVM> verifyMobileOTPMutableLiveData = new MutableLiveData<>();
    private MutableLiveData<AddMobileVM> newVerifyMobileOTPMutableLiveData = new MutableLiveData<>();


    public ObservableField<String> status = new ObservableField<>();
    public ObservableField<String> msg = new ObservableField<>();
    public ObservableField<Integer> otp = new ObservableField<>();
    public ObservableField<Integer> otp_verify = new ObservableField<>();
    public ObservableField<Users> user = new ObservableField<>();

    public AddMobileVM(POJOClass pojoClass) {

        this.status.set(pojoClass.status);
        this.msg.set(pojoClass.msg);
        this.otp.set(pojoClass.otp);
        this.otp_verify.set(pojoClass.otp_verify);
        this.user.set(pojoClass.user);

    }

    public AddMobileVM()
    {
        addMobileRepo = new AddMobileRepo();
    }



    public void mobileVerifyOTP(String user_id, String type)
    {
        MobileOTPRequest mobileOTPRequest = new MobileOTPRequest(user_id,"mobile",mobileNumber.get());
        mobileOTPMutableLiveData = addMobileRepo.getOtpMobileMutableLiveData(mobileOTPRequest);
    }

    public void newmobileVerifyOTP(String user_id, String type)
    {
        MobileOTPRequest mobileOTPRequest = new MobileOTPRequest(user_id,type,newMobileNumber.get());
        newMobileOTPMutableLiveData = addMobileRepo.getOtpMobileMutableLiveData(mobileOTPRequest);
    }

    public void changeMobileOTP(String user_id, String type)
    {
        VerifyOTPRequest verifyOTPRequest = new VerifyOTPRequest(user_id,"mobile",mobileNumber.get(),otpCode.get());
        verifyMobileOTPMutableLiveData = addMobileRepo.getOtpVerifyMobileMutableLiveData(verifyOTPRequest);

    }
    public void newMobileOTP(String user_id, String type)
    {
        VerifyOTPRequest verifyOTPRequest = new VerifyOTPRequest(user_id,"mobile",newMobileNumber.get(),newOtpCode.get());
        newVerifyMobileOTPMutableLiveData = addMobileRepo.getOtpVerifyMobileMutableLiveData(verifyOTPRequest);

    }

    public int newMobileNumberValidation()

    {
       return addMobileRepo.newMobileValidaiton(newMobileNumber.get());
    }

    public int currentOTPValidation()
    {
        return addMobileRepo.verifyCurrentMobileOTPValidation(otpCode.get());
    }

    public int submitValidation()
    {
        return addMobileRepo.updateMobileNumerValidation(newMobileNumber.get(),newOtpCode.get());
    }

    public MutableLiveData<AddMobileVM> getMobileOTPMutableLiveData()
    {
        return mobileOTPMutableLiveData;
    }

    public MutableLiveData<AddMobileVM> getVerifyMobileOTPMutableLiveData()
    {
        return verifyMobileOTPMutableLiveData;
    }

    public MutableLiveData<AddMobileVM> getNewMobileOTPMutableLiveData() {
        return newMobileOTPMutableLiveData;
    }

    public MutableLiveData<AddMobileVM> getNewVerifyMobileOTPMutableLiveData() {
        return newVerifyMobileOTPMutableLiveData;
    }
}