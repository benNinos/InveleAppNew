package com.ninositsolution.inveleapp.review_all;

import android.databinding.DataBindingUtil;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.ninositsolution.inveleapp.R;
import com.ninositsolution.inveleapp.databinding.ActivityReviewAllBinding;

public class ReviewAllActivity extends AppCompatActivity implements IReviewAll{

    ActivityReviewAllBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_review_all);
        binding.setReviewAll(new ReviewAllVM(getApplicationContext(), this));
        binding.starCheck1.setVisibility(View.GONE);
        binding.starCheck2.setVisibility(View.GONE);
        binding.starCheck3.setVisibility(View.GONE);
        binding.starCheck4.setVisibility(View.GONE);
        binding.starCheck5.setVisibility(View.GONE);
        binding.popUpMenu.setVisibility(View.GONE);

    }

    @Override
    public void onBackClicked() {

        super.onBackPressed();

    }

    @Override
    public void setRecyclerAdapter(ReviewAllAdapter recyclerAdapter) {

        binding.reviewAllRecyclerView.setHasFixedSize(true);
        binding.reviewAllRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        binding.reviewAllRecyclerView.setAdapter(recyclerAdapter);

    }

    @Override
    public void onTextViewClicked() {

        if (binding.popUpMenu.getVisibility() == View.GONE)
            binding.popUpMenu.setVisibility(View.VISIBLE);

        else
            binding.popUpMenu.setVisibility(View.GONE);
    }

    @Override
    public void onreviewLayout1Clicked() {
        binding.starCheck1.setVisibility(View.VISIBLE);
        binding.starCheck2.setVisibility(View.GONE);
        binding.starCheck3.setVisibility(View.GONE);
        binding.starCheck4.setVisibility(View.GONE);
        binding.starCheck5.setVisibility(View.GONE);

        binding.popUpMenu.postDelayed(new Runnable() {
    @Override
    public void run() {
        binding.popUpMenu.setVisibility(View.GONE);
    }
},200);

    }

    @Override
    public void onreviewLayout2Clicked() {
        binding.starCheck1.setVisibility(View.GONE);
        binding.starCheck2.setVisibility(View.VISIBLE);
        binding.starCheck3.setVisibility(View.GONE);
        binding.starCheck4.setVisibility(View.GONE);
        binding.starCheck5.setVisibility(View.GONE);

        binding.popUpMenu.postDelayed(new Runnable() {
            @Override
            public void run() {
                binding.popUpMenu.setVisibility(View.GONE);
            }
        },200);

    }

    @Override
    public void onreviewLayout3Clicked() {
        binding.starCheck1.setVisibility(View.GONE);
        binding.starCheck2.setVisibility(View.GONE);
        binding.starCheck3.setVisibility(View.VISIBLE);
        binding.starCheck4.setVisibility(View.GONE);
        binding.starCheck5.setVisibility(View.GONE);

        binding.popUpMenu.postDelayed(new Runnable() {
            @Override
            public void run() {
                binding.popUpMenu.setVisibility(View.GONE);
            }
        },200);

    }

    @Override
    public void onreviewLayout4Clicked() {
        binding.starCheck1.setVisibility(View.GONE);
        binding.starCheck2.setVisibility(View.GONE);
        binding.starCheck3.setVisibility(View.GONE);
        binding.starCheck4.setVisibility(View.VISIBLE);
        binding.starCheck5.setVisibility(View.GONE);

        binding.popUpMenu.postDelayed(new Runnable() {
            @Override
            public void run() {
                binding.popUpMenu.setVisibility(View.GONE);
            }
        },200);

    }

    @Override
    public void onreviewLayout5Clicked() {
        binding.starCheck1.setVisibility(View.GONE);
        binding.starCheck2.setVisibility(View.GONE);
        binding.starCheck3.setVisibility(View.GONE);
        binding.starCheck4.setVisibility(View.GONE);
        binding.starCheck5.setVisibility(View.VISIBLE);

        binding.popUpMenu.postDelayed(new Runnable() {
            @Override
            public void run() {
                binding.popUpMenu.setVisibility(View.GONE);
            }
        },200);

    }
}