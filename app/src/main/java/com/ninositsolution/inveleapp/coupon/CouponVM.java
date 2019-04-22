package com.ninositsolution.inveleapp.coupon;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.databinding.ObservableField;

import com.ninositsolution.inveleapp.pojo.HomeArrayLists;
import com.ninositsolution.inveleapp.pojo.POJOClass;

import java.util.List;

public class CouponVM extends ViewModel {

    private CouponRepo couponRepo;

  private MutableLiveData<CouponVM> couponVMMutableLiveData = new MutableLiveData<>();

  private String status, message;
  private List<HomeArrayLists> activeCoupons;

  public ObservableField<String> couponImage = new ObservableField<>();
  public ObservableField<String> couponCode = new ObservableField<>();
  public ObservableField<String> couponEntitle = new ObservableField<>();
  public ObservableField<String> couponName = new ObservableField<>();
  public ObservableField<String> couponDate = new ObservableField<>();
  public ObservableField<String> couponDesc = new ObservableField<>();

    public CouponVM() {

        couponRepo = new CouponRepo();

    }

    public CouponVM(POJOClass pojoClass)
    {
        status = pojoClass.status;
        message = pojoClass.msg;
        activeCoupons = pojoClass.active_coupons;
    }

    public CouponVM(HomeArrayLists homeArrayLists)
    {
        couponImage.set(homeArrayLists.image);
        couponCode.set(homeArrayLists.promo_code);
        couponEntitle.set(homeArrayLists.entitle);
        couponName.set(homeArrayLists.promo_name);
        couponDate.set("Valid till : "+homeArrayLists.valid_date);
        couponDesc.set(homeArrayLists.description);
    }

    public void getCouponsApi(String userId)
    {
       couponVMMutableLiveData = couponRepo.getCouponVMMutableLiveData(userId);
    }

    public MutableLiveData<CouponVM> getCouponVMMutableLiveData() {
        return couponVMMutableLiveData;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<HomeArrayLists> getActiveCoupons() {
        return activeCoupons;
    }

    public void setActiveCoupons(List<HomeArrayLists> activeCoupons) {
        this.activeCoupons = activeCoupons;
    }
}
