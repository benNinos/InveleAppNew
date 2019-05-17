package com.ninositsolution.inveleapp.cart;

import android.app.ProgressDialog;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import com.ninositsolution.inveleapp.R;
import com.ninositsolution.inveleapp.databinding.ActivityCartBinding;
import com.ninositsolution.inveleapp.home.HomeActivity;
import com.ninositsolution.inveleapp.payment.PaymentActivity;
import com.ninositsolution.inveleapp.pojo.CartDetails;
import com.ninositsolution.inveleapp.size_chart.SizeChartActivity;
import com.ninositsolution.inveleapp.utils.CartDatabase;
import com.ninositsolution.inveleapp.utils.Constants;

import java.util.List;

public class CartActivity extends AppCompatActivity implements ICart {

    private static final String TAG = CartActivity.class.getSimpleName();
    ActivityCartBinding binding;
    BottomSheetBehavior bottomSheetBehavior;
    TextView size_chart;
    CartVM cartVM, cartVMTemp;
    ICart iCart;

    private Context context;
    private Observer<CartVM> cartDetailsObserver;
    private ProgressDialog progressDialog;
    private CartDatabase cartDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_cart);

        cartVM = ViewModelProviders.of(this).get(CartVM.class);

        binding.setCart(cartVM);

        iCart = this;
        context = this;

        binding.setICart(iCart);

        initViews();
        initObservers();

        cartVM.getCartDetails("75");
        cartVM.getCartListsLiveData().observe(this, cartDetailsObserver);


        bottomSheetBehavior = BottomSheetBehavior.from(binding.bottomSheetCart);

        bottomSheetBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View view, int i) {

                if (bottomSheetBehavior.getState() == BottomSheetBehavior.STATE_EXPANDED)
                {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        binding.cartAppbarLayout.setForeground(getResources().getDrawable(R.drawable.window_dim));
                        binding.cartRelativeLayoutScroll.setForeground(getResources().getDrawable(R.drawable.window_dim));
                        binding.cartRelativeLayoutScroll.getForeground().setAlpha(180);
                        binding.cartAppbarLayout.getForeground().setAlpha(180);
                    }
                }

                else
                {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        binding.cartAppbarLayout.setForeground(null);
                        binding.cartRelativeLayoutScroll.setForeground(null);
                    }

                }

            }

            @Override
            public void onSlide(@NonNull View view, float v) {

            }
        });

        binding.allCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked)
                {
                    cartDatabase.checkAllBoxes();
                    binding.cartRecyclerview.setAdapter(new CartAdapter(context, cartVMTemp.getCartDetailsList(), iCart));
                    cartVM.total.set(Constants.CURRENCY+cartDatabase.getTotalAmount());
                    cartVM.shippingTotal.set(Constants.CURRENCY+cartDatabase.getTotalAmount());

                } else {
                    cartDatabase.unCheckAllBoxes();
                    binding.cartRecyclerview.setAdapter(new CartAdapter(context, cartVMTemp.getCartDetailsList(), iCart));
                    cartVM.total.set(Constants.CURRENCY+cartDatabase.getTotalAmount());
                    cartVM.shippingTotal.set(Constants.CURRENCY+cartDatabase.getTotalAmount());
                }
            }
        });

        size_chart = findViewById(R.id.size_chart);

        size_chart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CartActivity.this, SizeChartActivity.class);
                startActivity(intent);
            }
        });

    }

    private void initViews() {

        progressDialog = new ProgressDialog(context);
        progressDialog.setTitle(R.string.app_name);
        progressDialog.setMessage("Please wait...");
        progressDialog.setCancelable(false);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.show();

        cartDatabase = new CartDatabase(context);
        cartDatabase.deleteValues();
        binding.cartRecyclerview.setHasFixedSize(true);
        binding.cartRecyclerview.setLayoutManager(new LinearLayoutManager(this));
    }

    private void initObservers() {

        cartDetailsObserver = new Observer<CartVM>() {
            @Override
            public void onChanged(@Nullable CartVM cartVM) {

                if (cartVM.getStatus() != null)
                {
                    progressDialog.dismiss();
                    if (cartVM.getStatus().equalsIgnoreCase("success"))
                    {
                        cartVMTemp = cartVM;
                        updateDatabase(cartVM.getCartDetailsList());
                        binding.cartRecyclerview.setAdapter(new CartAdapter(context, cartVM.getCartDetailsList(), iCart));
                        Toast.makeText(CartActivity.this, ""+cartVM.getMessage(), Toast.LENGTH_SHORT).show();
                        cartDatabase.getParentCheckbox(0);
                    } else
                    {
                        Toast.makeText(CartActivity.this, ""+cartVM.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }

                cartVM.getCartListsLiveData().removeObserver(cartDetailsObserver);
            }
        };
    }

    private void updateDatabase(List<CartDetails> cartDetailsList) {

        for (int i = 0; i<cartDetailsList.size(); i++)
        {
            for (int j= 0; j<cartDetailsList.get(i).getProductlists().size(); j++)
            {
                long result = cartDatabase.insertValues(i, j, 0, cartDetailsList.get(i).getProductlists().get(j).getInvelePrice());
                Log.i(TAG, "insert result -> "+result);
            }
        }
    }

    @Override
    public void onBackClicked() {
        startActivity(new Intent(this, HomeActivity.class));
    }

    @Override
    public void onEditClicked(int position) {

        if (bottomSheetBehavior.getState() == BottomSheetBehavior.STATE_EXPANDED)
        {
            bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
        }
        else
        {
            bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
        }

    }

    @Override
    public void onContinueClicked() {
        startActivity(new Intent(this, HomeActivity.class));
    }

    @Override
    public void onCheckoutClicked() {

        startActivity(new Intent(this, PaymentActivity.class));
    }

    @Override
    public void changeTotal(String total) {

    }

    @Override
    public void onParentBoxChecked(int position) {
       cartDatabase.checkAllSubItems(position);
       binding.cartRecyclerview.setAdapter(new CartAdapter(context, cartVMTemp.getCartDetailsList(), iCart));
       cartVM.total.set(Constants.CURRENCY+cartDatabase.getTotalAmount());
       cartVM.shippingTotal.set(Constants.CURRENCY+cartDatabase.getTotalAmount());
    }

    @Override
    public void onParentBoxUnChecked(int position) {
        cartDatabase.unCheckAllSubitems(position);
        binding.cartRecyclerview.setAdapter(new CartAdapter(context, cartVMTemp.getCartDetailsList(), iCart));
        cartVM.total.set(Constants.CURRENCY+cartDatabase.getTotalAmount());
        cartVM.shippingTotal.set(Constants.CURRENCY+cartDatabase.getTotalAmount());
    }

    @Override
    public void onChildBoxChecked(int parentPosition, int childPosition) {

        cartDatabase.checkSubItem(parentPosition, childPosition);
        binding.cartRecyclerview.setAdapter(new CartAdapter(context, cartVMTemp.getCartDetailsList(), iCart));
        cartVM.total.set(Constants.CURRENCY+cartDatabase.getTotalAmount());
        cartVM.shippingTotal.set(Constants.CURRENCY+cartDatabase.getTotalAmount());
    }

    @Override
    public void onChildBoxUnChecked(int parentPosition, int childPosition) {

        cartDatabase.unCheckSubItem(parentPosition, childPosition);
        binding.cartRecyclerview.setAdapter(new CartAdapter(context, cartVMTemp.getCartDetailsList(), iCart));
        cartVM.total.set(Constants.CURRENCY+cartDatabase.getTotalAmount());
        cartVM.shippingTotal.set(Constants.CURRENCY+cartDatabase.getTotalAmount());
    }
}