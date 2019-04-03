package com.ninositsolution.inveleapp.cart;

import android.content.Context;

/**
 * Created by Parthasarathy D on 1/17/2019.
 * Ninos IT Solution Pvt Ltd
 * ben@ninositsolution.com
 */
public class CartVM {

    private CartModel cartModel;
    private Context context;
    private ICart iCart;

    public CartVM(Context context, ICart iCart) {
        this.context = context;
        this.iCart = iCart;
        cartModel = new CartModel();
        loadCartRecycler();
    }

    private void loadCartRecycler() {

        CartAdapter cartAdapter = new CartAdapter(context, iCart);
        iCart.loadCartRecyclerView(cartAdapter);
    }

    //ClickListeners

    public void onBackClicked()
    {
        iCart.onBackClicked();
    }

    public void onContinueClicked(){
        iCart.onContinueClicked();

    }

    public void onCheckoutClicked(){
        iCart.onCheckoutClicked();
    }
}
