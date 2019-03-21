package com.ninositsolution.inveleapp.categories.fragments.fragment_other_categories;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.databinding.BindingAdapter;
import android.databinding.ObservableField;
import android.widget.ImageView;

import com.ninositsolution.inveleapp.R;
import com.ninositsolution.inveleapp.categories.CategoryModel;
import com.ninositsolution.inveleapp.categories.fragments.fragment_all_categories.AllCategoryFragmentVM;
import com.ninositsolution.inveleapp.categories.fragments.fragment_all_categories.AllCategoryRepo;
import com.ninositsolution.inveleapp.pojo.POJOClass;
import com.ninositsolution.inveleapp.utils.Constants;
import com.squareup.picasso.Picasso;

import java.util.List;

public class OtherFragmentVM extends ViewModel {

    private OtherCategoryRepo otherCategoryRepo;
    private MutableLiveData<List<OtherFragmentVM>> brandVMMutableLiveData = new MutableLiveData<>();

    //UI fields
    public ObservableField<String> bannerImage = new ObservableField<>();
    public ObservableField<String>brandUrl = new ObservableField<>();
    public ObservableField<String>category_name = new ObservableField<>("");
    //pojo fields
    public ObservableField<String> status = new ObservableField<>();
    public ObservableField<String> msg = new ObservableField<>();
    public ObservableField <List<CategoryModel>>brands = new ObservableField<>();

    public OtherFragmentVM(POJOClass pojoClass, CategoryModel categoryModel){
        this.status.set(pojoClass.status);
        this.msg.set(pojoClass.msg);
        this.brands.set(categoryModel.brands);
        this.category_name.set(categoryModel.name);
        this.bannerImage.set(categoryModel.image_path);
        this.brandUrl.set(Constants.select_banner);

    }

    public OtherFragmentVM(String image){
        this.brandUrl.set(image);

    }
    public OtherFragmentVM(){
        otherCategoryRepo = new OtherCategoryRepo();
    }

    public ObservableField<String> brandUrl()
    {
        return brandUrl;
    }


    @BindingAdapter({"bind:brandUrl"})
    public static void loadImageBrand(ImageView imageView, String brandUrl)
    {
        Picasso.get().load(brandUrl).placeholder(R.drawable.img1).into(imageView);
    }
    public ObservableField<String> bannerImage()
    {
        return bannerImage;
    }


    @BindingAdapter({"bind:bannerImage"})
    public static void loadImage(ImageView imageView, String bannerImage)
    {
        Picasso.get().load(bannerImage).placeholder(R.drawable.img1).into(imageView);
    }

    public void getBrandCategoryList()
    {

        otherCategoryRepo = new OtherCategoryRepo();

        // pojoClassMutableLiveData = registerRepo.getRegisterVMMutableLiveData(registartionRequest);
        brandVMMutableLiveData = otherCategoryRepo.getBrandVMMutableLiveData();

        //String message = registerVMMutableLiveData.getValue().status.get();

        //  stringMutableLiveData.setValue(message);
    }

    public MutableLiveData <List<OtherFragmentVM>> getBrandVMMutableLiveData() {
        return brandVMMutableLiveData;
    }



}
