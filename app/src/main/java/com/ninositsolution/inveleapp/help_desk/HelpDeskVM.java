package com.ninositsolution.inveleapp.help_desk;

import android.content.Context;

public class HelpDeskVM {

    private HelpDeskModel helpDeskModel;
    private Context context;
    private IHelpDesk iHelpDesk;

    public HelpDeskVM(Context context, IHelpDesk iHelpDesk) {
        this.context = context;
        this.iHelpDesk = iHelpDesk;
        helpDeskModel = new HelpDeskModel();
    }

    public void onBackClicked()
    {
        iHelpDesk.onBackClicked();
    }
}
