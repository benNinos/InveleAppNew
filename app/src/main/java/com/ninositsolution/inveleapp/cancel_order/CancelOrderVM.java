package com.ninositsolution.inveleapp.cancel_order;

import android.content.Context;

public class CancelOrderVM {

    private CancelOrderModel cancelOrderModel;
    private Context context;
    private ICancelOrder iCancelOrder;

    public CancelOrderVM(Context context, ICancelOrder iCancelOrder) {
        this.context = context;
        this.iCancelOrder = iCancelOrder;
        cancelOrderModel = new CancelOrderModel();
        loadAdapter();
    }

    private void loadAdapter() {

        CancelAdapter cancelAdapter = new CancelAdapter(context);
        iCancelOrder.setRecyclerAdapter(cancelAdapter);
    }

    //ClickListeners

    public void onBackClicked()
    {
        iCancelOrder.onBackClicked();
    }
}
