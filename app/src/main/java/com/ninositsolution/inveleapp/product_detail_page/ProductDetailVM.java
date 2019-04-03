package com.ninositsolution.inveleapp.product_detail_page;

import android.content.Context;

public class ProductDetailVM {

    private ProductDetailModel productDetailModel;
    private Context context;
    private IProductDetail iProductDetail;

    public ProductDetailVM(Context context, IProductDetail iProductDetail) {
        this.context = context;
        this.iProductDetail = iProductDetail;
        productDetailModel = new ProductDetailModel();
    }

    public void onBackClicked()
    {
        iProductDetail.onBackClicked();
    }

    public void onViewAllClicked()
    {
        iProductDetail.onViewAllClicked();
    }

    public void onViewStoreClicked()
    {
        iProductDetail.onViewStoreClicked();
    }

    public void onShippingClicked()
    {
        iProductDetail.onShippingClicked();
    }

    public void onVoucherClicked()
    {
        iProductDetail.onVoucherClicked();
    }

    public void onStorePickUpClicked()
    {
        iProductDetail.onStorePickUpClicked();
    }
}
