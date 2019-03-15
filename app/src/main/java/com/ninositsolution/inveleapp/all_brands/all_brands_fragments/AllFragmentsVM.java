package com.ninositsolution.inveleapp.all_brands.all_brands_fragments;

import android.content.Context;

import com.ninositsolution.inveleapp.all_brands.AllBrandsAdapter;

public class AllFragmentsVM {

    private Context context;
    private IAllFragment iAllFragment;

    public AllFragmentsVM(Context context, IAllFragment iAllFragment) {
        this.context = context;
        this.iAllFragment = iAllFragment;
        loadAdapter();
    }

    private void loadAdapter() {

        AllBrandsAdapter allBrandsAdapter = new AllBrandsAdapter(context);

        iAllFragment.setRecyclerAdapter(allBrandsAdapter);
    }
}
