package com.ninositsolution.inveleapp.home;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.databinding.BindingAdapter;
import android.databinding.ObservableField;
import android.view.Menu;
import android.widget.ImageView;
import android.widget.RatingBar;

import com.ninositsolution.inveleapp.R;
import com.ninositsolution.inveleapp.pojo.HomeArrayLists;
import com.ninositsolution.inveleapp.pojo.POJOClass;
import com.ninositsolution.inveleapp.utils.Constants;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Parthasarathy D on 1/17/2019.
 * Ninos IT Solution Pvt Ltd
 * ben@ninositsolution.com
 */
public class HomeVM extends ViewModel {

    private HomeRepo homeRepo;

    public MutableLiveData<HomeVM> homeVMMutableLiveData = new MutableLiveData<>();
    private MutableLiveData<HomeVM> updateWishlistLiveData = new MutableLiveData<>();
    public ObservableField<String> dealTimer = new ObservableField<>("");
    public ObservableField<String> dealHeading = new ObservableField<>("");

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

    public HomeVM(String image)
    {
        imageUrl.set(image);

    }

    //Sub banners

    public ObservableField<String> subBanner1 = new ObservableField<>();
    public ObservableField<String> subBanner2 = new ObservableField<>();
    public ObservableField<String> subBanner3 = new ObservableField<>();

    public ObservableField<String> subBanner1() { return subBanner1; }
    public ObservableField<String> subBanner2() { return subBanner2; }
    public ObservableField<String> subBanner3() { return subBanner3; }

    @BindingAdapter({"android:src"})
    public static void loadSubBanner1(ImageView imageView, String subBanner1)
    {
        Picasso.get().load(subBanner1).placeholder(R.drawable.sub_banner_placeholder).into(imageView);
    }

    @BindingAdapter({"android:src"})
    public static void loadSubBanner2(ImageView imageView, String subBanner2)
    {
        Picasso.get().load(subBanner2).placeholder(R.drawable.sub_banner_placeholder).into(imageView);
    }

    @BindingAdapter({"android:src"})
    public static void loadSubBanner3(ImageView imageView, String subBanner3)
    {
        Picasso.get().load(subBanner3).placeholder(R.drawable.sub_banner_placeholder).into(imageView);
    }

    public HomeVM(String image1, String image2, String image3)
    {
        subBanner1.set(image1);
        subBanner2.set(image2);
        subBanner3.set(image3);
    }

    // categories

    public ObservableField<String> categoryImage = new ObservableField<>();
    public ObservableField<String> categoryName = new ObservableField<>("");

    public ObservableField<String> getCategoryImage() { return categoryImage; }

    @BindingAdapter({"android:src"})
    public static void loadCategoryImage(ImageView imageView, String image)
    {
        Picasso.get().load(image).placeholder(R.drawable.product_detail_placeholder).into(imageView);
    }

    public HomeVM(HomeArrayLists homeArrayLists, int flag)
    {
        categoryImage.set(homeArrayLists.image_path);
        categoryName.set(homeArrayLists.name);
    }

    // Deal Products

    public ObservableField<String> dealProductImage = new ObservableField<>();
    public ObservableField<String> dealItemName = new ObservableField<>("");
    public ObservableField<String> dealItemRate = new ObservableField<>("");
    public ObservableField<String> dealItemDeletedRate = new ObservableField<>("");

    public ObservableField<String> dealProductImage()
    {
        return dealProductImage;
    }

    @BindingAdapter({"android:src"})
    public static void loadDealImage(ImageView imageView, String dealProductImage)
    {
        try {
            Picasso.get().load(dealProductImage).placeholder(R.drawable.product_detail_placeholder).into(imageView);

        } catch (Exception e)
        {

        }
    }


    public HomeVM(HomeArrayLists homeArrayLists)
    {
        dealProductImage.set(homeArrayLists.image_path);
        dealItemName.set(homeArrayLists.name);
        dealItemRate.set(Constants.CURRENCY+String.valueOf(homeArrayLists.invele_price));
        dealItemDeletedRate.set(Constants.CURRENCY+homeArrayLists.usual_price);
    }

    //Product trendings

    public ObservableField<String> trendingProductName = new ObservableField<>("");
    public ObservableField<String> trendingProductImage = new ObservableField<>("");
    public ObservableField<String> trendingProductRate = new ObservableField<>("");
    public ObservableField<String> trendingProductDeletedRate = new ObservableField<>("");
    public ObservableField<String> trendingProductRating = new ObservableField<>("");
    public ObservableField<Float> trendingProductRatingFloat = new ObservableField<>();

    public HomeVM(HomeVM homeVM, int position)
    {
        trendingProductImage.set(homeVM.product_trendings.get().get(position).image_path);
        trendingProductName.set(homeVM.product_trendings.get().get(position).name);
        trendingProductRate.set(Constants.CURRENCY+String.valueOf(homeVM.product_trendings.get().get(position).invele_price));
        trendingProductDeletedRate.set(Constants.CURRENCY+homeVM.product_trendings.get().get(position).usual_price);
        trendingProductRating.set(String.valueOf(homeVM.product_trendings.get().get(position).average_rating));
        trendingProductRatingFloat.set(Float.valueOf(homeVM.product_trendings.get().get(position).average_rating));
    }

    public ObservableField<String> trendingProductImage()
    {
        return trendingProductImage;
    }

    public ObservableField<Float> trendingProductRatingFloat()
    {
        return trendingProductRatingFloat;
    }

    @BindingAdapter("bind:image")
    public static void loadTrendingImage(ImageView imageView, String trendingProductImage)
    {
        Picasso.get().load(trendingProductImage).placeholder(R.drawable.product_detail_placeholder).into(imageView);
    }

    @BindingAdapter("{android:rating}")
    public static void loadTrendingRating(RatingBar ratingBar, Float trendingProductRating)
    {
        if (ratingBar.getRating() != trendingProductRating)
        {
            ratingBar.setRating(trendingProductRating);
        }
    }

    //Brands
    public ObservableField<String> brandImage1 = new ObservableField<>();
    public ObservableField<String> brandImage2 = new ObservableField<>();
    public ObservableField<String> brandImage3 = new ObservableField<>();
    public ObservableField<String> brandImage4 = new ObservableField<>();

    public HomeVM(String image1, String image2, String image3, String image4)
    {
        brandImage1.set(image1);
        brandImage2.set(image2);
        brandImage3.set(image3);
        brandImage4.set(image4);
    }

    public ObservableField<String> getBrandImage1() {
        return brandImage1;
    }

    public ObservableField<String> getBrandImage2() {
        return brandImage2;
    }

    public ObservableField<String> getBrandImage3() {
        return brandImage3;
    }

    public ObservableField<String> getBrandImage4() {
        return brandImage4;
    }

    @BindingAdapter("{android:src}")
    public static void loadBrand1(ImageView imageView, String image)
    {
        Picasso.get().load(image).placeholder(R.drawable.product_detail_placeholder).into(imageView);
    }

    @BindingAdapter("{android:src}")
    public static void loadBrand2(ImageView imageView, String image)
    {
        Picasso.get().load(image).placeholder(R.drawable.product_detail_placeholder).into(imageView);
    }

    @BindingAdapter("{android:src}")
    public static void loadBrand3(ImageView imageView, String image)
    {
        Picasso.get().load(image).placeholder(R.drawable.product_detail_placeholder).into(imageView);
    }

    @BindingAdapter("{android:src}")
    public static void loadBrand4(ImageView imageView, String image)
    {
        Picasso.get().load(image).placeholder(R.drawable.product_detail_placeholder).into(imageView);
    }

    // Home Management
    public ObservableField<String> homeImage1 = new ObservableField<>();
    public ObservableField<String> homeImage2 = new ObservableField<>();
    public ObservableField<String> homeItemName1 = new ObservableField<>("");
    public ObservableField<String> homeItemName2 = new ObservableField<>("");
    public ObservableField<String> homeItemRate1 = new ObservableField<>("");
    public ObservableField<String> homeItemRate2 = new ObservableField<>("");
    public ObservableField<String> homeItemDeleteRate1 = new ObservableField<>("");
    public ObservableField<String> homeItemDeleteRate2 = new ObservableField<>("");
    public ObservableField<String> homeProductHead = new ObservableField<>("");
    public ObservableField<String> homeRating1 = new ObservableField<>("");
    public ObservableField<Float> homeRatingFloat1 = new ObservableField<>();
    public ObservableField<Float> homeRatingFloat2 = new ObservableField<>();
    public ObservableField<String> homeRating2 = new ObservableField<>("");

    public ObservableField<String> getHomeImage1() {return homeImage1;}
    public ObservableField<String> getHomeImage2() {return homeImage2;}

    @BindingAdapter("{android:src}")
    public static void loadHomeImage1(ImageView imageView, String image)
    {
        Picasso.get().load(image).placeholder(R.drawable.product_detail_placeholder).into(imageView);
    }

    @BindingAdapter("{android:src}")
    public static void loadHomeImage2(ImageView imageView, String image)
    {
        Picasso.get().load(image).placeholder(R.drawable.product_detail_placeholder).into(imageView);
    }

    public HomeVM(String name, int flag)
    {
        homeProductHead.set(name);
    }

    public HomeVM(HomeArrayLists lists1, HomeArrayLists lists2)
    {
        homeImage1.set(lists1.image_path);
        homeItemName1.set(lists1.name);
        homeItemRate1.set(Constants.CURRENCY+String.valueOf(lists1.invele_price));
        homeItemDeleteRate1.set(Constants.CURRENCY+lists1.usual_price);
        homeRatingFloat1.set(Float.valueOf(lists1.average_rating));
        homeRating1.set(String.valueOf(lists1.average_rating));

        homeImage2.set(lists2.image_path);
        homeItemName2.set(lists2.name);
        homeItemRate2.set(Constants.CURRENCY+String.valueOf(lists2.invele_price));
        homeItemDeleteRate2.set(Constants.CURRENCY+lists2.usual_price);
        homeRatingFloat2.set(Float.valueOf(lists2.average_rating));
        homeRating2.set(String.valueOf(lists2.average_rating));
    }


    //performing Api call
    public void performHomePageApi(String user_id)
    {
        homeVMMutableLiveData = homeRepo.getHomeVMMutableLiveData(user_id);
    }

    //updating wishlists api call
    public void updateWishListApi(String userId, String productId, String status)
    {
       updateWishlistLiveData =  homeRepo.getUpdateWishlistLiveData(userId, productId, status);
    }

    //Live Data getters
    public MutableLiveData<HomeVM> getHomeVMMutableLiveData() {
        return homeVMMutableLiveData;
    }

    public MutableLiveData<HomeVM> getUpdateWishlistLiveData() {
        return updateWishlistLiveData;
    }
}