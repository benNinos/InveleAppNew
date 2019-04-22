package com.ninositsolution.inveleapp.account;

import android.arch.lifecycle.ViewModel;
import android.databinding.BindingAdapter;
import android.databinding.ObservableField;
import android.widget.ImageView;

import com.ninositsolution.inveleapp.R;
import com.squareup.picasso.Picasso;

/**
 * Created by Parthasarathy D on 1/17/2019.
 * Ninos IT Solution Pvt Ltd
 * ben@ninositsolution.com
 */
public class AccountVM extends ViewModel {

    public ObservableField<String> username = new ObservableField<>();
    public ObservableField<String> userPhoto = new ObservableField<>();
    public ObservableField<String> fitmeName = new ObservableField<>();

    public ObservableField<String> getUserPhoto() {
        return userPhoto;
    }

    @BindingAdapter("{android:src}")
    public static void loadImage(ImageView imageView, String imageUrl)
    {
        Picasso.get().load(imageUrl).placeholder(R.drawable.user).into(imageView);
    }

    public AccountVM() {
    }
}
