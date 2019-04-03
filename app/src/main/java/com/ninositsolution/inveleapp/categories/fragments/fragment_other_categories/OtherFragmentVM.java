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
import com.ninositsolution.inveleapp.pojo.CategoryPojoClass;
import com.ninositsolution.inveleapp.pojo.POJOClass;
import com.ninositsolution.inveleapp.utils.Constants;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class OtherFragmentVM extends ViewModel {

    private OtherCategoryRepo otherCategoryRepo;
    private MutableLiveData<OtherFragmentVM> brandVMMutableLiveData = new MutableLiveData<>();

    //UI fields
    public ObservableField<String> bannerImage = new ObservableField<>();

    //brand list
    public ObservableField<String>id = new ObservableField<>("");
    public ObservableField<String>category_name = new ObservableField<>("");
    public ObservableField<String>brandUrl = new ObservableField<>();
    public ObservableField<String>category_position = new ObservableField<>("");
    public ObservableField<String>seller_id = new ObservableField<>("");
    public ObservableField<String>approad_status = new ObservableField<>("");

    //child adapter UI fields
    public ObservableField<String>child_header = new ObservableField<>("");
    public ObservableField<String>child_name = new ObservableField<>();
    public ObservableField<String>childImage = new ObservableField<>();
    public ObservableField<ArrayList<String>>child_category_name = new ObservableField<>();

    //pojo fields
    public ObservableField<String> status = new ObservableField<>("");
    public ObservableField<String> msg = new ObservableField<>("");
    public ObservableField <List<CategoryModel>>categories = new ObservableField<>();
    public ObservableField<List<ChildCategoriesPOJO>>child_categories = new ObservableField<>();
    public ObservableField<List<ExpandableCategoriesPOJO>> expandable_list = new ObservableField<>();
    public ObservableField<List<ChildCategoriesPOJO>>child_category_list = new ObservableField<>();

    public OtherFragmentVM(CategoryPojoClass pojoClass){
        this.status.set(pojoClass.status);
        this.msg.set(pojoClass.msg);
        this.categories.set(pojoClass.categories);
       // this.child_header.set(categoryModel.name);
       // this.child_categories.set(pojoClass.child_categories);

    }
    public OtherFragmentVM(ExpandableCategoriesPOJO categoriesPOJO,List<ChildCategoriesPOJO>childCategoriesPOJOS){
        this.child_header.set(categoriesPOJO.getHeader());
        this.child_categories.set(childCategoriesPOJOS);


    }
    public OtherFragmentVM(List<ChildCategoriesPOJO>categoriesPOJOS){
        this.child_categories.set(categoriesPOJOS);
    }
    public OtherFragmentVM(ChildCategoriesPOJO childCategoriesPOJO){
        this.child_name.set(childCategoriesPOJO.getCategory_name());
        this.childImage.set(childCategoriesPOJO.getImages());
    }

    public OtherFragmentVM(CategoryModel categoryModel){
        this.category_position.set(categoryModel.category_position);
        this.id.set(categoryModel.id);
        this.seller_id.set(categoryModel.seller_id);
        this.category_name.set(categoryModel.name);
        this.brandUrl.set(categoryModel.image_path);
        this.status.set(categoryModel.status);
        this.approad_status.set(categoryModel.approde_status);

    }
    public void getBrandCategoryList()
    {

        otherCategoryRepo = new OtherCategoryRepo();

        // pojoClassMutableLiveData = registerRepo.getRegisterVMMutableLiveData(registartionRequest);
        brandVMMutableLiveData = otherCategoryRepo.getBrandVMMutableLiveData();

        //String message = registerVMMutableLiveData.getValue().status.get();

        //  stringMutableLiveData.setValue(message);
    }

    public MutableLiveData <OtherFragmentVM>getBrandVMMutableLiveData() {
        return brandVMMutableLiveData;
    }

    public ObservableField<String> getId() {
        return id;
    }

    public void setId(ObservableField<String> id) {
        this.id = id;
    }

    public ObservableField<String> getCategory_name() {
        return category_name;
    }

    public void setCategory_name(ObservableField<String> category_name) {
        this.category_name = category_name;
    }


    public ObservableField<String> getCategory_position() {
        return category_position;
    }

    public void setCategory_position(ObservableField<String> category_position) {
        this.category_position = category_position;
    }

    public ObservableField<String> getSeller_id() {
        return seller_id;
    }

    public void setSeller_id(ObservableField<String> seller_id) {
        this.seller_id = seller_id;
    }

    public ObservableField<String> getApproad_status() {
        return approad_status;
    }

    public void setApproad_status(ObservableField<String> approad_status) {
        this.approad_status = approad_status;
    }

    public ObservableField<String> getStatus() {
        return status;
    }

    public void setStatus(ObservableField<String> status) {
        this.status = status;
    }

    public OtherFragmentVM(String image){
        this.bannerImage.set(image);

    }
    public OtherFragmentVM(){
        otherCategoryRepo = new OtherCategoryRepo();
    }
    //set brand url

    public ObservableField<String> brandUrl()
    {
        return brandUrl;
    }

    @BindingAdapter({"bind:brandUrl"})
    public static void loadImage(ImageView imageView, String brandUrl)
    {
        Picasso.get().load(brandUrl).placeholder(R.drawable.img1).into(imageView);
    }

    //set banner image
    public ObservableField<String> bannerImage()
    {
        return bannerImage;
    }
    @BindingAdapter({"bind:bannerImage"})
    public static void loadImageBrand(ImageView imageView, String bannerImage)
    {
        Picasso.get().load(bannerImage).placeholder(R.drawable.img1).into(imageView);
    }

    public ObservableField<String> getChild_name() {
        return child_name;
    }

    //set banner image
    public ObservableField<String> childImage()
    {
        return childImage;
    }


    @BindingAdapter({"bind:childImage"})
    public static void loadImageChild(ImageView imageView, String childImage)
    {
        Picasso.get().load(childImage).placeholder(R.drawable.img1).into(imageView);
    }
}
