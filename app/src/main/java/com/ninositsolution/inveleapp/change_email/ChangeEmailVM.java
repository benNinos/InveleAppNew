package com.ninositsolution.inveleapp.change_email;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.databinding.ObservableField;

import com.ninositsolution.inveleapp.change_email.pojo.EmailOTPRequest;
import com.ninositsolution.inveleapp.change_email.pojo.VerifyemailOTPRequest;
import com.ninositsolution.inveleapp.pojo.POJOClass;
import com.ninositsolution.inveleapp.pojo.Users;
import com.ninositsolution.inveleapp.utils.Session;

/**
 * Created by Parthasarathy D on 1/25/2019.
 * Ninos IT Solution Pvt Ltd
 * ben@ninositsolution.com
 */
public class ChangeEmailVM extends ViewModel {

    private ChangeEmailRepo changeEmailRepo;

    public ObservableField<String> currentEmailAddress = new ObservableField<>("");
    public ObservableField<String> currentOtpCode = new ObservableField<>("");
    public ObservableField<String> newEmail = new ObservableField<>("");
    public ObservableField<String> newOtpCode = new ObservableField<>("");

    private MutableLiveData<ChangeEmailVM> emailOTPMutableLiveData = new MutableLiveData<>();
    private MutableLiveData<ChangeEmailVM> newEmailOTPMutableLiveData = new MutableLiveData<>();
    private MutableLiveData<ChangeEmailVM> verifyEmailOTPMutableLiveData = new MutableLiveData<>();
    private MutableLiveData<ChangeEmailVM> newVerifyEmailOTPMutableLiveData = new MutableLiveData<>();


    public ObservableField<String> status = new ObservableField<>();
    public ObservableField<String> msg = new ObservableField<>();
    public ObservableField<Integer> otp = new ObservableField<>();
    public ObservableField<Integer> otp_verify = new ObservableField<>();
    public ObservableField<Users> user = new ObservableField<>();


    public ChangeEmailVM(POJOClass pojoClass)
    {
        this.status.set(pojoClass.status);
        this.msg.set(pojoClass.msg);
        this.otp.set(pojoClass.otp);
        this.otp_verify.set(pojoClass.otp_verify);
        this.user.set(pojoClass.user);

    }

    public ChangeEmailVM()
    {
        changeEmailRepo = new ChangeEmailRepo();
    }


    public void emailVerifyOTP(String user_id, String type)
    {
        EmailOTPRequest emailOTPRequest = new EmailOTPRequest(user_id,"email",currentEmailAddress.get());
        emailOTPMutableLiveData = changeEmailRepo.getOtpEmailMutableLiveData(emailOTPRequest);

    }

    public void newEmailVerifyOTP(String user_id, String type)
    {
        EmailOTPRequest emailOTPRequest = new EmailOTPRequest(user_id,newEmail.get(),type);
        newEmailOTPMutableLiveData = changeEmailRepo.getOtpEmailMutableLiveData(emailOTPRequest);
    }


    public void changeEmailOTP(String user_id,String type)
    {
        VerifyemailOTPRequest verifyemailOTPRequest = new VerifyemailOTPRequest(user_id,"email",currentEmailAddress.get(),currentOtpCode.get());
            verifyEmailOTPMutableLiveData = changeEmailRepo.getOtpVerifyEmailMutableLiveData(verifyemailOTPRequest);

    }

    public void newEmailOTP(String user_id,String type)
    {
        VerifyemailOTPRequest verifyemailOTPRequest = new VerifyemailOTPRequest(user_id,"email",newEmail.get(),newOtpCode.get());
        newVerifyEmailOTPMutableLiveData = changeEmailRepo.getOtpVerifyEmailMutableLiveData(verifyemailOTPRequest);
    }

    public MutableLiveData<ChangeEmailVM> getEmailOTPMutableLiveData()
    {
        return emailOTPMutableLiveData;
    }

    public MutableLiveData<ChangeEmailVM> getVerifyEmailOTPMutableLiveData()
    {
        return verifyEmailOTPMutableLiveData;
    }
    public MutableLiveData<ChangeEmailVM> getNewEmailOTPMutableLiveData()
    {
        return newEmailOTPMutableLiveData;
    }
    public MutableLiveData<ChangeEmailVM> getNewVerifyEmailOTPMutableLiveData()
    {
        return newVerifyEmailOTPMutableLiveData;
    }









}
