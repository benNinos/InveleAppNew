package com.ninositsolution.inveleapp.all_brands;
import android.content.Context;


public class AllBrandsVM {

    private AllBrandsModel allBrandsModel;
    private Context context;
    private IAllBrands iAllBrands;



    public AllBrandsVM(Context context, IAllBrands iAllBrands) {
        this.context = context;
        this.iAllBrands = iAllBrands;
        allBrandsModel = new AllBrandsModel();
    }



    public void onBackClicked()
    {
        iAllBrands.onBackClick();
    }

}
