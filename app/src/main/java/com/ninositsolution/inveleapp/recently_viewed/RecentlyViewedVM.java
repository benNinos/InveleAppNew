package com.ninositsolution.inveleapp.recently_viewed;

import android.content.Context;

/**
 * Created by Parthasarathy D on 1/30/2019.
 * Ninos IT Solution Pvt Ltd
 * ben@ninositsolution.com
 */
public class RecentlyViewedVM {

    private RecentlyViewedModel recentlyViewedModel;
    private Context context;
    private IRecentlyViewed iRecentlyViewed;

    public RecentlyViewedVM(Context context, IRecentlyViewed iRecentlyViewed) {
        this.context = context;
        this.iRecentlyViewed = iRecentlyViewed;
        recentlyViewedModel = new RecentlyViewedModel();
        setRecyclerAdapter();
    }

    private void setRecyclerAdapter() {

        RecentlyViewedAdapter recentlyViewedAdapter = new RecentlyViewedAdapter(context);
        iRecentlyViewed.setRecyclerAdapter(recentlyViewedAdapter);
    }

    //ClickListeners

    public void onBackClicked()
    {
        iRecentlyViewed.onBackClicked();
    }
}
