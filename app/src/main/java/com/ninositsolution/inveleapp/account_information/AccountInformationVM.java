package com.ninositsolution.inveleapp.account_information;

import android.content.Context;

/**
 * Created by Parthasarathy D on 1/25/2019.
 * Ninos IT Solution Pvt Ltd
 * ben@ninositsolution.com
 */
public class AccountInformationVM {

    private AccountInformationModel accountInformationModel;
    private Context context;
    private IAccountInformation iAccountInformation;

    public AccountInformationVM(Context context, IAccountInformation iAccountInformation) {
        this.context = context;
        this.iAccountInformation = iAccountInformation;
        accountInformationModel = new AccountInformationModel();
    }

    //ClickListeners

    public void onBackClicked()
    {
        iAccountInformation.onBackClicked();
    }
    public void onAddMobileClicked()
    {
        iAccountInformation.onAddMobileClicked();
    }
    public void onChangeEmailClicked()
    {
        iAccountInformation.onChangeEmailClicked();
    }

    public void onChangePasswordClicked()
    {
        iAccountInformation.onChangePasswordClicked();
    }


}
