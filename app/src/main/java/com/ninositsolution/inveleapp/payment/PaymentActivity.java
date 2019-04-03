package com.ninositsolution.inveleapp.payment;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.ninositsolution.inveleapp.R;
import com.ninositsolution.inveleapp.databinding.ActivityPaymentBinding;
import com.ninositsolution.inveleapp.home.HomeActivity;

public class PaymentActivity extends AppCompatActivity {

    ActivityPaymentBinding binding;
    PaymentVM paymentVM;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_payment);

        paymentVM = ViewModelProviders.of(this).get(PaymentVM.class);

        binding.setPayment(paymentVM);

        binding.setIPayment(new IPayment() {
            @Override
            public void onBackClicked() {
                onBackPressed();
            }

            @Override
            public void onContinueShoppingClicked() {

                startActivity(new Intent(PaymentActivity.this, HomeActivity.class));
            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
