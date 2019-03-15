package com.ninositsolution.inveleapp.cancel_order;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;

import com.ninositsolution.inveleapp.R;
import com.ninositsolution.inveleapp.databinding.ActivityCancelOrderBinding;

public class CancelOrderActivity extends AppCompatActivity implements ICancelOrder{

    ActivityCancelOrderBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_cancel_order);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_cancel_order);
        binding.setCancel(new CancelOrderVM(getApplicationContext(), this));
    }

    @Override
    public void onBackClicked() {
        super.onBackPressed();
    }

    @Override
    public void setRecyclerAdapter(CancelAdapter cancelAdapter) {
        binding.cancelRecyclerView.setHasFixedSize(true);
        binding.cancelRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        binding.cancelRecyclerView.setAdapter(cancelAdapter);
    }
}
