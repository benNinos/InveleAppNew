package com.ninositsolution.inveleapp.my_order;

import android.content.Context;

public class MyOrderVM {

    private MyOrderModel myOrderModel;
    private Context context;
    private IMyOrder iMyOrder;

    public MyOrderVM(Context context, IMyOrder iMyOrder) {
        this.context = context;
        this.iMyOrder = iMyOrder;
        myOrderModel = new MyOrderModel();
        loadAdapters();
    }

    private void loadAdapters() {

        MyOrderAdapter myOrderAdapter = new MyOrderAdapter(context,iMyOrder);
        iMyOrder.setRecyclerAdapter(myOrderAdapter);
    }

    //ClickListeners

    public void onBackClicked()
    {
        iMyOrder.onBackClicked();
    }




}
