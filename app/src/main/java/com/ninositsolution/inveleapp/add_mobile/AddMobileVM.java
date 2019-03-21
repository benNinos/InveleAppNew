package com.ninositsolution.inveleapp.add_mobile;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.databinding.ObservableField;

import com.ninositsolution.inveleapp.add_mobile.pojo.MobileOTPRequest;
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



    private MutableLiveData<AddMobileVM> mobileOTPMutableLiveData = new MutableLiveData<>();

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


    public MutableLiveData<AddMobileVM> getMobileOTPMutableLiveData() {
        return mobileOTPMutableLiveData;
    }



}


