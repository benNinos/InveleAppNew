package com.ninositsolution.inveleapp.settings;

import android.content.Context;

/**
 * Created by Parthasarathy D on 1/24/2019.
 * Ninos IT Solution Pvt Ltd
 * ben@ninositsolution.com
 */
public class SettingsVM {

    private SettingsModel settingsModel;
    private Context context;
    private ISettings iSettings;

    public SettingsVM(Context context, ISettings iSettings) {
        this.context = context;
        this.iSettings = iSettings;
        settingsModel = new SettingsModel();
    }

    //ClickListeners

    public void onBackClicked()
    {
        iSettings.onBackClicked();
    }

    public void AccountInfoClicked()
    {
        iSettings.AccountInfoClicked();
    }

    public void AddressBookClicked()
    {
        iSettings.AddressBookClicked();
    }

    public void PersonalInfoClicked()
    {
        iSettings.PersonalInfoClicked();
    }

    public void onSocialMediaClicked()
    {
        iSettings.onSocialMediaClicked();
    }

    public void onPopupsClicked()
    {
        iSettings.onPopupsClicked();
    }

    public void onRequestAccountDeletionClicked()
    {
        iSettings.onRequestAccountDeletionClicked();
    }

}
