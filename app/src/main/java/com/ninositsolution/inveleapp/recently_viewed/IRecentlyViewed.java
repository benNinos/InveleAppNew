package com.ninositsolution.inveleapp.recently_viewed;

/**
 * Created by Parthasarathy D on 1/30/2019.
 * Ninos IT Solution Pvt Ltd
 * ben@ninositsolution.com
 */
public interface IRecentlyViewed {

    void onBackClicked();
    void setRecyclerAdapter(RecentlyViewedAdapter recyclerAdapter);
}
