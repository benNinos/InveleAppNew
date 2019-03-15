package com.ninositsolution.inveleapp.help_desk;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.ninositsolution.inveleapp.R;
import com.ninositsolution.inveleapp.databinding.ActivityHelpDeskBinding;

public class HelpDeskActivity extends AppCompatActivity implements IHelpDesk{

    ActivityHelpDeskBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_help_desk);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_help_desk);
        binding.setHelpDesk(new HelpDeskVM(getApplicationContext(), this));
    }

    @Override
    public void onBackClicked() {
        super.onBackPressed();
    }
}
