package com.ninositsolution.inveleapp.account_information;

import android.arch.lifecycle.ViewModel;
import android.content.Context;
import android.databinding.ObservableField;

/**
 * Created by Parthasarathy D on 1/25/2019.
 * Ninos IT Solution Pvt Ltd
 * ben@ninositsolution.com
 */
public class AccountInformationVM extends ViewModel {

    public ObservableField<String> mobileNumber = new ObservableField<>("");
    public ObservableField<String> emailAddress = new ObservableField<>("");



    private AccountInformationRepo accountInformationRepo;


    public AccountInformationVM(){

    }






}
