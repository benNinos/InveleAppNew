package com.ninositsolution.inveleapp.review;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;

import com.ninositsolution.inveleapp.R;
import com.ninositsolution.inveleapp.databinding.ActivityReviewBinding;

public class ReviewActivity extends AppCompatActivity implements IReview{

    ActivityReviewBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_review);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_review);

        binding.setReview(new ReviewVM(getApplicationContext(), this));
    }

    @Override
    public void onBackClicked() {

        super.onBackPressed();

    }

    @Override
    public void setRecyclerView(ReviewAdapter recyclerView) {
        binding.reviewRecyclerView.setHasFixedSize(true);
        binding.reviewRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        binding.reviewRecyclerView.setAdapter(recyclerView);
    }
}
