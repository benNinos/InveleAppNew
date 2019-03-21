package com.ninositsolution.inveleapp.categories;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.nhaarman.supertooltips.ToolTip;
import com.nhaarman.supertooltips.ToolTipRelativeLayout;
import com.nhaarman.supertooltips.ToolTipView;
import com.ninositsolution.inveleapp.R;
import com.ninositsolution.inveleapp.account.AccountActivity;
import com.ninositsolution.inveleapp.address_book.AddressBookActivity;
import com.ninositsolution.inveleapp.address_book.AddressBookAdapter;
import com.ninositsolution.inveleapp.address_book.AddressBookVM;
import com.ninositsolution.inveleapp.cart.CartActivity;
import com.ninositsolution.inveleapp.categories.fragments.fragment_all_categories.AllCategoriesFragment;
import com.ninositsolution.inveleapp.categories.fragments.fragment_other_categories.OtherCategoriesFragment;
import com.ninositsolution.inveleapp.databinding.ActivityCategoryBinding;
import com.ninositsolution.inveleapp.home.HomeActivity;
import com.ninositsolution.inveleapp.login.LoginActivity;
import com.ninositsolution.inveleapp.my_order.MyOrderActivity;
import com.ninositsolution.inveleapp.utils.Constants;
import com.ninositsolution.inveleapp.utils.Session;
import com.ninositsolution.inveleapp.wishlist.WishlistActivity;

import java.util.List;

public class CategoryActivity extends AppCompatActivity implements ICategory{

    private ToolTipView toolTipView;
    private ToolTip toolTip;
    private int count;
    public ICategory iCategory;
    public CategoryVM categoryVM;
    CategoryAdapter categoryAdapter;
    List<CategoryVM>objCategoryVM;
    public static final String TAG = CategoryActivity.class.getSimpleName();

    private ActivityCategoryBinding binding;
    FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    private AllCategoriesFragment allCategoriesFragment = new AllCategoriesFragment();
    private OtherCategoriesFragment otherCategoriesFragment = new OtherCategoriesFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_category);

        binding.setCategory(new CategoryVM(getApplicationContext(), this));
        binding.setCategory(categoryVM);
        binding.setICategory(iCategory);
        binding.setLifecycleOwner(this);

        binding.categoriesRecyclerview.setHasFixedSize(true);
        binding.categoriesRecyclerview.setLayoutManager(new LinearLayoutManager(this));

        categoryVM = ViewModelProviders.of(this).get(CategoryVM.class);

        //invoke the api service for category

        categoryVM.getAllCategory();

        categoryVM.getAllcategoryVMMutableLiveData().observe(this, new Observer<CategoryVM>() {
            @Override
            public void onChanged(@Nullable CategoryVM categoryVM1) {
                try {
                    if (categoryVM1.status.get().equalsIgnoreCase("success")) {
                        if (categoryVM1.all_categories.get() != null) {
                            Log.e(TAG, "name==>" + categoryVM1.all_categories.get().name);
                            // categoryVM.allCategories.set(categoryVM1.all_categories.get().name);
                            if (categoryVM1.all_categories.get().image != null) {
                                if (!categoryVM1.all_categories.get().image.equalsIgnoreCase("")) {
                                    Log.e(TAG, "image==>" + categoryVM1.all_categories.get().image);
                                    //categoryVM.image.set(categoryVM1.all_categories.get().image);
                                    //  categoryVM.image().set(categoryVM1.all_categories.get().image);
                                }
                            }
                        }
                        categoryVM1.status.set("");
                    } else if (categoryVM1.status.get().equalsIgnoreCase("error")) {
                        Toast.makeText(getApplicationContext(), categoryVM1.status.get(), Toast.LENGTH_SHORT).show();
                    }
                }catch (NullPointerException e){
                    e.printStackTrace();
                }
            }
        });
        categoryVM.getCategoryList();

        categoryVM.getCategoryVMMutableLiveData().observe(this, new Observer<List<CategoryVM>>() {
            @Override
            public void onChanged(@Nullable final List<CategoryVM> categoryVMS) {

                Log.e(TAG,"LIST_SIZE==>"+categoryVMS.size());
                if(!categoryVMS.isEmpty()) {

                    objCategoryVM = categoryVMS;

                    categoryAdapter = new CategoryAdapter(CategoryActivity.this, categoryVMS, Constants.select_menu_id);
                    binding.categoriesRecyclerview.setAdapter(categoryAdapter);
                    categoryAdapter.setClikEvent(new CategoryAdapter.ClickEvent() {
                        @Override
                        public void setClickEventItem(int position, String menu_id, String name, String banner) {
                            Constants.select_menu_id = menu_id;
                            Constants.select_banner = banner;
                            Log.e(TAG,"menu_id_selected==>"+menu_id+"\nbanner_image==>"+banner);

                            binding.allCategoriesLayout.setBackgroundColor(getResources().getColor(R.color.grayWhite));
                            binding.allCategoriesText.setTextColor(getResources().getColor(R.color.textColor));

                            changeFragment();

                        }
                    });
                    categoryAdapter.notifyDataSetChanged();

                   // categoryVMS.clear();
                }else {
                    Toast.makeText(getApplicationContext(),"List Empty",Toast.LENGTH_SHORT).show();
                }

            }
        });




        count = 0;

        /*Toolbar toolbar = findViewById(R.id.category);
        setSupportActionBar(toolbar);*/

        //All Categories selected as default
        binding.allCategoriesLayout.setBackgroundColor(getResources().getColor(R.color.white));
        binding.allCategoriesText.setTextColor(getResources().getColor(R.color.colorPrimary));

        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        allCategoriesFragment = new AllCategoriesFragment();
        fragmentTransaction.add(R.id.container_category, allCategoriesFragment).commit();

        binding.setICategory(new ICategory() {
            @Override
            public void AllCategoriesClicked() {

                Log.e(TAG,"objCategoryVM.size==>"+objCategoryVM.size());

                Constants.select_menu_id="";

                binding.allCategoriesLayout.setBackgroundColor(getResources().getColor(R.color.white));
                binding.allCategoriesText.setTextColor(getResources().getColor(R.color.colorPrimary));

                categoryAdapter = new CategoryAdapter(CategoryActivity.this, objCategoryVM, Constants.select_menu_id);
                binding.categoriesRecyclerview.setAdapter(categoryAdapter);
                categoryAdapter.setClikEvent(new CategoryAdapter.ClickEvent() {
                    @Override
                    public void setClickEventItem(int position, String menu_id, String name, String banner) {
                        Constants.select_menu_id = menu_id;
                        Constants.select_banner = banner;
                        Log.e(TAG,"menu_id_selected==>"+menu_id+"\nbanner==>"+banner);

                        binding.allCategoriesLayout.setBackgroundColor(getResources().getColor(R.color.grayWhite));
                        binding.allCategoriesText.setTextColor(getResources().getColor(R.color.textColor));

                    }
                });
                changeFragment();
            }

            @Override
            public void MensCategoriesClicked() {

            }

            @Override
            public void onBackClicked() {

                startActivity(new Intent(CategoryActivity.this, HomeActivity.class));

            }

            @Override
            public void onSearchClicked() {

                startActivity(new Intent(CategoryActivity.this, CartActivity.class));


            }

            @Override
            public void onCartClicked() {

            }

            @Override
            public void onMenuClicked() {

                if (count %2 == 0)
                {
                    createTooltip();
                    count++;
                }
                else
                {
                    toolTipView.remove();
                    count++;
                }

            }

            @Override
            public void ChangePreviousCategoryView() {

                Session session = new Session(CategoryActivity.this);

                int position = session.getCategoryPosition();

                switch (position)
                {
                    case 1:
                        binding.allCategoriesLayout.setBackgroundColor(getResources().getColor(R.color.grayWhite));
                        binding.allCategoriesText.setTextColor(getResources().getColor(R.color.textColor));
                        break;


                }
            }
        });



    }

    private void changeFragment() {
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        otherCategoriesFragment = new OtherCategoriesFragment();
        fragmentTransaction.replace(R.id.container_category, otherCategoriesFragment).addToBackStack(Constants.select_banner);
        fragmentTransaction.commitAllowingStateLoss();
    }

    @Override
    public void AllCategoriesClicked() {
        Log.e(TAG,"AllCategoriesClicked==>");

        binding.allCategoriesLayout.setBackgroundColor(getResources().getColor(R.color.white));
        binding.allCategoriesText.setTextColor(getResources().getColor(R.color.colorPrimary));
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        allCategoriesFragment = new AllCategoriesFragment();
        fragmentTransaction.replace(R.id.container_category, allCategoriesFragment).commit();

    }

    @Override
    public void MensCategoriesClicked() {

        Log.e(TAG,"mens_category_clicked==>");

      //  binding.mensCategoriesLayout.setBackgroundColor(getResources().getColor(R.color.white));
       // binding.mensCategoriesText.setTextColor(getResources().getColor(R.color.colorPrimary));
        changeFragment();

    }

/*

    @Override
    public void WomensCategoriesClicked() {

        binding.womensCategoriesLayout.setBackgroundColor(getResources().getColor(R.color.white));
        binding.womensCategoriesText.setTextColor(getResources().getColor(R.color.colorPrimary));
        changeFragment();

    }

    @Override
    public void BoysCategoriesClicked() {

        binding.boysCategoriesLayout.setBackgroundColor(getResources().getColor(R.color.white));
        binding.boysCategoriesText.setTextColor(getResources().getColor(R.color.colorPrimary));
        changeFragment();

    }

    @Override
    public void MobilesCategoriesClicked() {

        binding.mobilesCategoriesLayout.setBackgroundColor(getResources().getColor(R.color.white));
        binding.mobilesCategoriesText.setTextColor(getResources().getColor(R.color.colorPrimary));
        changeFragment();

    }

    @Override
    public void ElectronicsCategoriesClicked() {

        binding.electronicsCategoriesLayout.setBackgroundColor(getResources().getColor(R.color.white));
        binding.electronicsCategoriesText.setTextColor(getResources().getColor(R.color.colorPrimary));
        changeFragment();
    }

    @Override
    public void HomeCategoriesClicked() {

        binding.homeCategoriesLayout.setBackgroundColor(getResources().getColor(R.color.white));
        binding.homeCategoriesText.setTextColor(getResources().getColor(R.color.colorPrimary));
        changeFragment();

    }

    @Override
    public void BabiesCategoriesClicked() {

        binding.babiesCategoriesLayout.setBackgroundColor(getResources().getColor(R.color.white));
        binding.babiesCategoriesText.setTextColor(getResources().getColor(R.color.colorPrimary));
        changeFragment();

    }

    @Override
    public void BeautyCategoriesClicked() {

        binding.beautyCategoriesLayout.setBackgroundColor(getResources().getColor(R.color.white));
        binding.beautyCategoriesText.setTextColor(getResources().getColor(R.color.colorPrimary));
        changeFragment();

    }

    @Override
    public void HealthCategoriesClicked() {

        binding.healthCategoriesLayout.setBackgroundColor(getResources().getColor(R.color.white));
        binding.healthCategoriesText.setTextColor(getResources().getColor(R.color.colorPrimary));
        changeFragment();

    }

    @Override
    public void BooksCategoriesClicked() {

        binding.booksCategoriesLayout.setBackgroundColor(getResources().getColor(R.color.white));
        binding.booksCategoriesText.setTextColor(getResources().getColor(R.color.colorPrimary));
        changeFragment();

    }
*/

    @Override
    public void onBackClicked() {

        startActivity(new Intent(this, HomeActivity.class));
    }

    @Override
    public void onSearchClicked() {

        startActivity(new Intent(this, CartActivity.class));

    }

    @Override
    public void onCartClicked() {

    }

    @Override
    public void onMenuClicked() {

        if (count %2 == 0)
        {
            createTooltip();
            count++;
        }
        else
        {
            toolTipView.remove();
            count++;
        }

    }

    private void createTooltip() {

        toolTip = new ToolTip()
                //.withText("Hello World!")
                .withContentView(LayoutInflater.from(CategoryActivity.this).inflate(R.layout.popup_category, null))
                .withShadow()
                .withAnimationType(ToolTip.AnimationType.FROM_TOP);

        ToolTipRelativeLayout toolTipRelativeLayout = findViewById(R.id.tooltip_layout);

        toolTipView = toolTipRelativeLayout.showToolTipForView(toolTip, findViewById(R.id.categories_menu));
        //toolTipView.setOnToolTipViewClickedListener(MainActivity.this);

        toolTipView.findViewById(R.id.review_layout_1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CategoryActivity.this, LoginActivity.class));
                toolTipView.remove();
                count++;
            }
        });

        toolTipView.findViewById(R.id.review_layout_2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CategoryActivity.this, HomeActivity.class));
                toolTipView.remove();
                count++;
            }
        });

        toolTipView.findViewById(R.id.review_layout_3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CategoryActivity.this, AccountActivity.class));
                toolTipView.remove();
                count++;
            }
        });

        toolTipView.findViewById(R.id.review_layout_4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CategoryActivity.this, MyOrderActivity.class));
                toolTipView.remove();
                count++;
            }
        });

        toolTipView.findViewById(R.id.review_layout_5).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CategoryActivity.this, WishlistActivity.class));
                toolTipView.remove();
                count++;
            }
        });
    }


    @Override
    public void ChangePreviousCategoryView() {




    }

    @Override
    protected void onStart() {

        Session session = new Session(this);
        session.setCategoryPosition(1);

        super.onStart();
    }
}
