package com.ninositsolution.inveleapp.categories;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.content.Context;
import android.databinding.BindingAdapter;
import android.databinding.ObservableField;
import android.util.Log;
import android.widget.ImageView;

import com.ninositsolution.inveleapp.R;
import com.ninositsolution.inveleapp.address_book.AddressBookRepo;
import com.ninositsolution.inveleapp.address_book.AddressBookVM;
import com.ninositsolution.inveleapp.address_book.pojo.AddressBookRequest;
import com.ninositsolution.inveleapp.pojo.AddressList;
import com.ninositsolution.inveleapp.pojo.CategoryPojoClass;
import com.ninositsolution.inveleapp.pojo.POJOClass;
import com.ninositsolution.inveleapp.utils.Session;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Parthasarathy D on 1/17/2019.
 * Ninos IT Solution Pvt Ltd
 * ben@ninositsolution.com
 */
public class CategoryVM extends ViewModel {

    private CategoryModel categoryModel;
    private Context context;
    private ICategory iCategory;
    private Session session;

    private CategoryRepo categoryRepo;
    private MutableLiveData<List<CategoryVM>> categoryVMMutableLiveData = new MutableLiveData<>();
    private MutableLiveData<CategoryVM> allcategoryVMMutableLiveData = new MutableLiveData<>();
   //activity ui fields
    public ObservableField<String>allCategories = new ObservableField<>("");
    public ObservableField<String>imageUrl = new ObservableField<>();
    public ObservableField<String>image = new ObservableField<>();



    //adapter ui fields
    public ObservableField<String>category_name = new ObservableField<>("");
    public ObservableField<String>menu_id = new ObservableField<>("");
    public ObservableField<String>banner_image = new ObservableField<>("");
    public ObservableField<String>slug = new ObservableField<>("");
  //  public ObservableField<String>menscategory_layout = new ObservableField<>();

    //pojo fields
    public ObservableField<String> status = new ObservableField<>();
    public ObservableField<String> msg = new ObservableField<>();
    public ObservableField<CategoryModel>all_categories = new ObservableField<>();
    public ObservableField <List<CategoryModel>> categories = new ObservableField<>();

    public CategoryVM(Context context, ICategory iCategory) {
        this.context = context;
        this.iCategory = iCategory;
        categoryModel = new CategoryModel();
        session = new Session(context);
    }

    public CategoryVM(CategoryPojoClass pojoClass)
    {
        this.status.set(pojoClass.status);
        this.msg.set(pojoClass.msg);
        this.all_categories.set(pojoClass.all_categories);
        this.categories.set(pojoClass.categories);
      //  this.allCategories.set(pojoClass.all_categories.name);
      //  this.image.set(pojoClass.all_categories.image);
      //  this.categories.set(pojoClass.categories);
    }
    public CategoryVM(CategoryModel categoryModel){
        this.menu_id.set(categoryModel.menu_id);
        this.category_name.set(categoryModel.name);
        this.imageUrl.set(categoryModel.image_path);
        this.banner_image.set(categoryModel.banner_image);
        this.slug.set(categoryModel.slug);

    }
    /*public ObservableField<String> image(){
        return image;
    }

    @BindingAdapter({"bind:image"})
    public static void loadCategoryImage(ImageView imageView, String image)
    {
        Picasso.get().load(image).placeholder(R.drawable.shirt).into(imageView);
    }*/


    public ObservableField<String> imageUrl()
    {
        return imageUrl;
    }


    @BindingAdapter({"bind:imageUrl"})
    public static void loadImage(ImageView imageView, String imageUrl)
    {
        Picasso.get().load(imageUrl).placeholder(R.drawable.shirt).into(imageView);
    }

    public CategoryVM(){
        categoryRepo  =new CategoryRepo();
    }

    public void getCategoryList()
    {

        categoryRepo = new CategoryRepo();

        // pojoClassMutableLiveData = registerRepo.getRegisterVMMutableLiveData(registartionRequest);

        categoryVMMutableLiveData = categoryRepo.getCategoryVMMutableLiveData();
        allcategoryVMMutableLiveData = categoryRepo.getAllCategoryVMMutableLiveData();

        //String message = registerVMMutableLiveData.getValue().status.get();

        //  stringMutableLiveData.setValue(message);
    }
    public void getAllCategory(){

        categoryRepo = new CategoryRepo();

        // pojoClassMutableLiveData = registerRepo.getRegisterVMMutableLiveData(registartionRequest);

        allcategoryVMMutableLiveData = categoryRepo.getAllCategoryVMMutableLiveData();


    }

    public MutableLiveData <List<CategoryVM>> getCategoryVMMutableLiveData() {
        return categoryVMMutableLiveData;
    }
    public MutableLiveData <CategoryVM> getAllcategoryVMMutableLiveData() {
        return allcategoryVMMutableLiveData;
    }


    public void AllCategoriesClicked()
    {
        iCategory.AllCategoriesClicked();
        iCategory.ChangePreviousCategoryView();
        session.setCategoryPosition(1);
    }

    public void MensCategoriesClicked()
    {
        Log.e("categoryVM","categoryVM_menu_clicked==>");
        iCategory.ChangePreviousCategoryView();
        iCategory.MensCategoriesClicked();
        session.setCategoryPosition(2);
    }

  /*  public void WomensCategoriesClicked()
    {
        iCategory.ChangePreviousCategoryView();
        iCategory.WomensCategoriesClicked();
        session.setCategoryPosition(3);
    }

    public void BoysCategoriesClicked()
    {
        iCategory.ChangePreviousCategoryView();
        iCategory.BoysCategoriesClicked();
        session.setCategoryPosition(4);
    }

    public void MobilesCategoriesClicked()
    {
        iCategory.ChangePreviousCategoryView();
        iCategory.MobilesCategoriesClicked();
        session.setCategoryPosition(5);
    }

    public void ElectronicsCategoriesClicked()
    {
        iCategory.ChangePreviousCategoryView();
        iCategory.ElectronicsCategoriesClicked();
        session.setCategoryPosition(6);
    }

    public void HomeCategoriesClicked()
    {
        iCategory.ChangePreviousCategoryView();
        iCategory.HomeCategoriesClicked();
        session.setCategoryPosition(7);
    }

    public void BabiesCategoriesClicked()
    {
        iCategory.ChangePreviousCategoryView();
        iCategory.BabiesCategoriesClicked();
        session.setCategoryPosition(8);
    }

    public void BeautyCategoriesClicked()
    {
        iCategory.ChangePreviousCategoryView();
        iCategory.BeautyCategoriesClicked();
        session.setCategoryPosition(9);
    }

    public void HealthCategoriesClicked()
    {
        iCategory.ChangePreviousCategoryView();
        iCategory.HealthCategoriesClicked();
        session.setCategoryPosition(10);
    }

    public void BooksCategoriesClicked()
    {
        iCategory.ChangePreviousCategoryView();
        iCategory.BooksCategoriesClicked();
        session.setCategoryPosition(11);
    }*/

    public void onBackClicked()
    {
        iCategory.onBackClicked();
    }

    public void onSearchClicked()
    {
        iCategory.onSearchClicked();
    }

    public void onCartClicked()
    {
        iCategory.onCartClicked();
    }

    public void onMenuClicked()
    {
        iCategory.onMenuClicked();
    }
}
