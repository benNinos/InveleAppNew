package com.ninositsolution.inveleapp.cart;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.databinding.ObservableField;
import android.util.Log;

import com.ninositsolution.inveleapp.pojo.CartDetails;
import com.ninositsolution.inveleapp.pojo.POJOClass;
import com.ninositsolution.inveleapp.utils.Constants;

import java.util.List;

/**
 * Created by Parthasarathy D on 1/17/2019.
 * Ninos IT Solution Pvt Ltd
 * ben@ninositsolution.com
 */

public class CartVM extends ViewModel {

    private CartRepo cartRepo;
    private static final String TAG = CartVM.class.getSimpleName();

    public CartVM() {

        cartRepo = new CartRepo();
    }

    private MutableLiveData<CartVM> cartListsLiveData = new MutableLiveData<>();

    private String status, message;
    private List<CartDetails> cartDetailsList;

    public ObservableField<String> storeName = new ObservableField<>();
    public ObservableField<String> productName = new ObservableField<>();
    public ObservableField<String> invelePrice = new ObservableField<>();
    public ObservableField<String> usualPrice = new ObservableField<>();
    public ObservableField<String> count = new ObservableField<>();
    public ObservableField<String> imagePath = new ObservableField<>();

   public CartVM(POJOClass pojoClass)
   {
       status = pojoClass.status;
       message = pojoClass.msg;
       cartDetailsList = pojoClass.getCartDetailsList();

       Log.i(TAG, "size -> "+cartDetailsList.size());
   }

   public CartVM(String storeName)
   {
        this.storeName.set(storeName);
   }

   public CartVM(CartDetails cartDetails)
   {
       imagePath.set(cartDetails.getImagepath());
       productName.set(cartDetails.getName());
       invelePrice.set(Constants.CURRENCY+cartDetails.getInvelePrice());
       usualPrice.set(Constants.CURRENCY+cartDetails.getUsualPrice());
       count.set("1");
   }

   public void getCartDetails(String userId)
   {
       cartListsLiveData = cartRepo.getCartListsLiveData(userId);
   }

    public MutableLiveData<CartVM> getCartListsLiveData() {
        return cartListsLiveData;
    }

    public String getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public List<CartDetails> getCartDetailsList() {
        return cartDetailsList;
    }
}
