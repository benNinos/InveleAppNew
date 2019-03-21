package com.ninositsolution.inveleapp.categories.fragments.fragment_all_categories;

import android.app.Activity;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.content.Context;
import android.databinding.BindingAdapter;
import android.databinding.ObservableField;
import android.support.v4.app.FragmentActivity;
import android.widget.ImageView;

import com.ninositsolution.inveleapp.R;
import com.ninositsolution.inveleapp.categories.CategoryModel;
import com.ninositsolution.inveleapp.categories.CategoryRepo;
import com.ninositsolution.inveleapp.categories.CategoryVM;
import com.ninositsolution.inveleapp.categories.fragments.fragment_all_categories.IAllCategories;
import com.ninositsolution.inveleapp.pojo.POJOClass;
import com.ninositsolution.inveleapp.utils.Session;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AllCategoryFragmentVM extends ViewModel {


    private AllCategoryRepo allCategoryRepo;
    private MutableLiveData<List<AllCategoryFragmentVM>> allcategoryVMMutableLiveData = new MutableLiveData<>();

    //UI fields
    public ObservableField<String>imageUrl = new ObservableField<>("");
    public ObservableField<String>category_name = new ObservableField<>("");


    //pojo fields
    public ObservableField<String> status = new ObservableField<>();
    public ObservableField<String> msg = new ObservableField<>();
    public ObservableField <List<CategoryModel>> parent_categories = new ObservableField<>();

    public AllCategoryFragmentVM(POJOClass pojoClass,CategoryModel categoryModel){
        this.status.set(pojoClass.status);
        this.msg.set(pojoClass.msg);
        this.parent_categories.set(pojoClass.parent_categories);
        this.category_name.set(categoryModel.name);
        this.imageUrl.set(categoryModel.image_path);

    }

    public AllCategoryFragmentVM(){
        allCategoryRepo = new AllCategoryRepo();

    }

    public ObservableField<String> imageUrl()
    {
        return imageUrl;
    }


    @BindingAdapter({"bind:imageUrl"})
    public static void loadImage(ImageView imageView, String imageUrl)
    {
        Picasso.get().load(imageUrl).placeholder(R.drawable.img1).into(imageView);
    }

    public void getAllCategoryList()
    {

        allCategoryRepo = new AllCategoryRepo();

        // pojoClassMutableLiveData = registerRepo.getRegisterVMMutableLiveData(registartionRequest);

        allcategoryVMMutableLiveData = allCategoryRepo.getAllCategoryVMMutableLiveData();

        //String message = registerVMMutableLiveData.getValue().status.get();

        //  stringMutableLiveData.setValue(message);
    }

    public MutableLiveData <List<AllCategoryFragmentVM>> getAllcategoryVMMutableLiveData() {
        return allcategoryVMMutableLiveData;
    }


}
