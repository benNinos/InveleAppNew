package com.ninositsolution.inveleapp.home;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.databinding.BindingAdapter;
import android.databinding.ObservableField;
import android.view.Menu;
import android.widget.ImageView;

import com.ninositsolution.inveleapp.R;
import com.ninositsolution.inveleapp.pojo.HomeArrayLists;
import com.ninositsolution.inveleapp.pojo.POJOClass;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Parthasarathy D on 1/17/2019.
 * Ninos IT Solution Pvt Ltd
 * ben@ninositsolution.com
 */
public class HomeVM extends ViewModel {

    private HomeRepo homeRepo;

    private MutableLiveData<HomeVM> homeVMMutableLiveData = new MutableLiveData<>();

    public HomeVM() {

        homeRepo = new HomeRepo();
    }


    public ObservableField<String> status = new ObservableField<>("");
    public ObservableField<String> msg = new ObservableField<>("");
    public ObservableField<String> caption = new ObservableField<>("");
    public ObservableField<String> start_date_time = new ObservableField<>("");
    public ObservableField<String> end_date_time = new ObservableField<>("");
    public ObservableField<ArrayList<HomeArrayLists>> menus = new ObservableField<>();
    public ObservableField<ArrayList<HomeArrayLists>> main_banners = new ObservableField<>();
    public ObservableField<ArrayList<HomeArrayLists>> sub_banners = new ObservableField<>();
    public ObservableField<ArrayList<HomeArrayLists>> deal_products = new ObservableField<>();
    public ObservableField<ArrayList<HomeArrayLists>> home_managements = new ObservableField<>();
    public ObservableField<ArrayList<HomeArrayLists>> product_trendings = new ObservableField<>();
    public ObservableField<ArrayList<HomeArrayLists>> brands = new ObservableField<>();

    public HomeVM(POJOClass pojoClass)
    {
        this.status.set(pojoClass.status);
        this.msg.set(pojoClass.msg);
        this.caption.set(pojoClass.caption);
        this.start_date_time.set(pojoClass.start_date_time);
        this.end_date_time.set(pojoClass.end_date_time);
        this.menus.set(pojoClass.menus);
        this.main_banners.set(pojoClass.main_banners);
        this.sub_banners.set(pojoClass.sub_banners);
        this.deal_products.set(pojoClass.deal_products);
        this.home_managements.set(pojoClass.home_managements);
        this.product_trendings.set(pojoClass.product_trendings);
        this.brands.set(pojoClass.brands);
    }

    //Main banners

        public ObservableField<String> imageUrl = new ObservableField<>();

        public ObservableField<String> imageUrl()
        {
            return imageUrl;
        }

    @BindingAdapter({"android:src"})

    public static void loadImage(ImageView imageView, String imageUrl)
    {
        Picasso.get().load(imageUrl).placeholder(R.drawable.main_banner_placeholder).into(imageView);
    }

    public HomeVM(HomeArrayLists homeArrayLists)
    {
        imageUrl.set(homeArrayLists.image_path);
    }

    //performing Api call
    public void performHomePageApi(String user_id)
    {
        homeVMMutableLiveData = homeRepo.getHomeVMMutableLiveData(user_id);
    }

    //Live Data getters
    public MutableLiveData<HomeVM> getHomeVMMutableLiveData() {
        return homeVMMutableLiveData;
    }
}