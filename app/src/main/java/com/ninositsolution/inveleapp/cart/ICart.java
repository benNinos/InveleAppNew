package com.ninositsolution.inveleapp.cart;

/**
 * Created by Parthasarathy D on 1/17/2019.
 * Ninos IT Solution Pvt Ltd
 * ben@ninositsolution.com
 */
public interface ICart {
    void onBackClicked();
    void onEditClicked(int position);
    void onContinueClicked();
    void onCheckoutClicked();
    void changeTotal(String total);
    void onParentBoxChecked(int position);
    void onParentBoxUnChecked(int position);
    void onChildBoxChecked(int parentPosition, int childPosition);
    void onChildBoxUnChecked(int parentPosition, int childPosition);
}
