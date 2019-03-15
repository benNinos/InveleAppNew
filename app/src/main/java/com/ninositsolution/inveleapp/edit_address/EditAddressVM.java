package com.ninositsolution.inveleapp.edit_address;

import android.content.Context;

public class EditAddressVM {

    private EditAddressModel editAddressModel;
    private Context context;
    private IEditAddress iEditAddress;

    public EditAddressVM(Context context, IEditAddress iEditAddress) {
        this.context = context;
        this.iEditAddress = iEditAddress;
    }

    //ClickListeners

    public void onBackClicked()
    {
        iEditAddress.onBackClicked();
    }


}
