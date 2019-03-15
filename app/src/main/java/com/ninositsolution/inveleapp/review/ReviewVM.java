package com.ninositsolution.inveleapp.review;

import android.content.Context;

public class ReviewVM {

    private ReviewModel reviewModel;
    private Context context;
    private IReview iReview;

    public ReviewVM(Context context, IReview iReview) {
        this.context = context;
        this.iReview = iReview;
        reviewModel = new ReviewModel();
        loadAdapter();
    }

    private void loadAdapter() {

        ReviewAdapter reviewAdapter = new ReviewAdapter(context);

        iReview.setRecyclerView(reviewAdapter);
    }

    //ClickListeners

    public void onBackClicked()
    {
        iReview.onBackClicked();
    }
}
