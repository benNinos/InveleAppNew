package com.ninositsolution.inveleapp.account;

import android.content.Context;

/**
 * Created by Parthasarathy D on 1/17/2019.
 * Ninos IT Solution Pvt Ltd
 * ben@ninositsolution.com
 */
public class AccountVM {

    private AccountModel accountModel;
    private Context context;
    private IAccount iAccount;

    public AccountVM(Context context, IAccount iAccount) {
        this.context = context;
        this.iAccount = iAccount;
        accountModel = new AccountModel();
    }

    //ClickListeners

    public void onBackClicked()
    {
        iAccount.onBackClicked();
    }

    public void onUpdateFitmeClicked()
    {
        iAccount.onUpdateFitmeClicked();
    }

    public void onSettingsClicked()
    {
        iAccount.onSettingsClicked();
    }

    public void onOrderHistoryClicked()
    {
        iAccount.onOrderHistoryClicked();
    }

    public void onFitmeEditClicked()
    {
        iAccount.onFitmeEditClicked();
    }

    public void onAddProfileClicked()
    {
        iAccount.onAddProfileClicked();
    }

    public void onWishlistClicked()
    {
        iAccount.onWishlistClicked();
    }

    public void onRecentsClicked()
    {
        iAccount.onRecentsClicked();
    }

    public void onCouponClicked()
    {
        iAccount.onCouponClicked();
    }

    public void onAddressClicked()
    {
        iAccount.onAddressClicked();
    }

    public void onMyAccountClickedClicked()
    {
        iAccount.onMyAccountClickedClicked();
    }

    public void onHelpDeskClicked()
    {
        iAccount.onHelpDeskClicked();
    }

    public void onContactUSClicked()
    {
        iAccount.onContactUSClicked();
    }

    public void onCartClicked()
    {
        iAccount.onCartClicked();
    }

    public void onOrdersClicked()
    {
        iAccount.onOrdersClicked();
    }

    public void onReviewsClicked()
    {
        iAccount.onReviewsClicked();
    }

    public void onCancelClicked()
    {
        iAccount.onCancelClicked();
    }
}
