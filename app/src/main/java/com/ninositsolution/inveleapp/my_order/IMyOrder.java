package com.ninositsolution.inveleapp.my_order;

public interface IMyOrder {

    void onBackClicked();
    void setRecyclerAdapter(MyOrderAdapter myOrderAdapter);
    void reviewClicked(int position);
    void onTrackClicked(int position);
    void onPlaceOrderClicked();
}
