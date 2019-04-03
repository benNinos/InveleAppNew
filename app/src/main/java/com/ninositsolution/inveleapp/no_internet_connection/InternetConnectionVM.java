package com.ninositsolution.inveleapp.no_internet_connection;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.content.Context;


import com.ninositsolution.inveleapp.utils.NetworkUtil;

public class InternetConnectionVM extends ViewModel {

    private MutableLiveData<Boolean> connected = new MutableLiveData<>();

    public InternetConnectionVM() {

    }

    public void checkInternetConnection(Context context)
    {
        NetworkUtil networkUtil = new NetworkUtil();
        connected.setValue(networkUtil.getNetworkStatus(context));
    }

    public MutableLiveData<Boolean> getConnected() {
        return connected;
    }
}
