package com.ninositsolution.inveleapp.social_media;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.ninositsolution.inveleapp.R;
import com.ninositsolution.inveleapp.databinding.ActivitySocialMediaBinding;

public class SocialMediaActivity extends AppCompatActivity{

    ActivitySocialMediaBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_social_media);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_social_media);

        binding.setISocial(new ISocialMedia() {
            @Override
            public void onBackClicked() {
                onBackPressed();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
