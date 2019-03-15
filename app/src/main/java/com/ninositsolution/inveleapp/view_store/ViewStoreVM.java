package com.ninositsolution.inveleapp.view_store;

import android.content.Context;

public class ViewStoreVM {

    private ViewStoreModel viewStoreModel;
    private Context context;
    private IViewStore iViewStore;

    public ViewStoreVM(Context context, IViewStore iViewStore) {
        this.context = context;
        this.iViewStore = iViewStore;
        viewStoreModel = new ViewStoreModel();
    }

    public void onBackClicked()
    {
        iViewStore.onBackClicked();
    }
}
