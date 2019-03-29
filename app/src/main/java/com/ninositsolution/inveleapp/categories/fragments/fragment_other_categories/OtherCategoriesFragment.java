package com.ninositsolution.inveleapp.categories.fragments.fragment_other_categories;


import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.ninositsolution.inveleapp.R;
import com.ninositsolution.inveleapp.categories.CategoryModel;
import com.ninositsolution.inveleapp.categories.fragments.fragment_all_categories.AllCategoryFragmentVM;
import com.ninositsolution.inveleapp.databinding.FragmentOtherCategoriesBinding;
import com.ninositsolution.inveleapp.utils.Constants;

import java.lang.invoke.ConstantCallSite;
import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class OtherCategoriesFragment extends Fragment {

    FragmentOtherCategoriesBinding binding;
    OtherFragmentVM otherFragmentVM;
    CategoryModel categoryModel;
    IOtherCategory iOtherCategory;
    List<CategoryModel>all_brands_list,categories_list,childList,innerChildList;
    List<OtherFragmentVM>brand_list_separate;
    BrandCategoryAdapter brandCategoryAdapter;
    ChildCategoryAdapter childCategoryAdapter;
    public static final String TAG = OtherCategoriesFragment.class.getSimpleName();
    int position=0;

    private RecyclerView recyclerView;
    private ExpandableCategoriesAdapter expandableCategoriesAdapter;
    /*private int [] images = {R.drawable.mb1, R.drawable.mb2, R.drawable.mb3,
            R.drawable.mb1, R.drawable.mb1, R.drawable.mb2};
    private String header = "Women's Fashion";*/
    private List<OtherFragmentVM>expandableVM;
    public List<OtherFragmentVM>chilCategoryVM;
    ExpandableCategoriesPOJO expandableCategoriesPOJO;
    ChildCategoriesPOJO childCategoriesPOJO;
    public List<ChildCategoriesPOJO>child_categories;


    public OtherCategoriesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_other_categories,container,false);
        final View view = binding.getRoot();

        binding.setOtherFragment(otherFragmentVM);
        binding.setIOtherCategory(iOtherCategory);

        binding.brandRecyclerview.setHasFixedSize(true);
        binding.brandRecyclerview.setLayoutManager(new GridLayoutManager(view.getContext(),3));

        binding.categoryRecycler.setHasFixedSize(true);
        binding.categoryRecycler.setLayoutManager(new LinearLayoutManager(view.getContext()));



        otherFragmentVM = ViewModelProviders.of(this).get(OtherFragmentVM.class);

       // otherFragmentVM.brandUrl.set(String.valueOf(new OtherFragmentVM(Constants.select_banner)));

        binding.setOtherFragment(new OtherFragmentVM(Constants.select_banner));
       otherFragmentVM.bannerImage().set(Constants.select_banner);

      // if(!Constants.category_position.equalsIgnoreCase("")) {

         //  if (Constants.category_position.equalsIgnoreCase("0")) {

               otherFragmentVM.getBrandCategoryList();
               otherFragmentVM.getBrandVMMutableLiveData().observe(this, new Observer<OtherFragmentVM>() {
                   @Override
                   public void onChanged(@Nullable OtherFragmentVM otherFragmentVMS) {
                       try {

                           if (otherFragmentVMS.status.get().equalsIgnoreCase("success")) {

                               if (!otherFragmentVMS.categories.get().isEmpty()) {
                                   // for(int c = 0;c<otherFragmentVMS.categories.get().size();c++) {
                                   position = Integer.parseInt(Constants.category_position);
                                   categories_list = otherFragmentVMS.categories.get();

                                   all_brands_list = otherFragmentVMS.categories.get().get(position).brands;
                                   Log.e(TAG, "all_brands_list==>" + all_brands_list.size());
                                   brand_list_separate = new ArrayList<>();
                                   categoryModel = new CategoryModel();
                                   for (int i = 0; i < all_brands_list.size(); i++) {
                                       Log.e(TAG, "id==>" + all_brands_list.get(i).id);

                                       categoryModel = new CategoryModel(String.valueOf(position), all_brands_list.get(i).id, all_brands_list.get(i).seller_id,
                                               all_brands_list.get(i).name, all_brands_list.get(i).status, all_brands_list.get(i).approde_status, all_brands_list.get(i).image_path);

                                       otherFragmentVM = new OtherFragmentVM(categoryModel);
                                       brand_list_separate.add(otherFragmentVM);

                                   }
                                   brandCategoryAdapter = new BrandCategoryAdapter(view.getContext(), brand_list_separate);
                                   binding.brandRecyclerview.setAdapter(brandCategoryAdapter);
                                   // }
                                   //  brandCategoryAdapter.notifyDataSetChanged();
                               }
                               //child list loading

                               if(!categories_list.isEmpty()){
                                   Log.e(TAG, "child_list_size==>" + categories_list.get(position).child_categories.size());
                                   childList = new ArrayList<>();
                                   childList = otherFragmentVMS.categories.get().get(position).child_categories;
                                   expandableVM = new ArrayList<>();

                                   innerChildList = new ArrayList<>();
                                   chilCategoryVM = new ArrayList<>();
                                   for(int c = 0;c < childList.size();c++) {
                                       innerChildList = otherFragmentVMS.categories.get().get(position).child_categories.get(c).child_categories;
                                       child_categories = new ArrayList<>();
                                       for (int i = 0; i < innerChildList.size(); i++) {

                                           child_categories.add(new ChildCategoriesPOJO(innerChildList.get(i).image_path,innerChildList.get(i).name));
                                           childCategoriesPOJO = new ChildCategoriesPOJO(innerChildList.get(i).image_path,innerChildList.get(i).name);
                                           otherFragmentVM = new OtherFragmentVM(childCategoriesPOJO);
                                           chilCategoryVM.add(otherFragmentVM);
                                       }
                                      // childCategoryAdapter = new ChildCategoryAdapter(chilCategoryVM,getContext());

                                       expandableCategoriesPOJO = new ExpandableCategoriesPOJO(childList.get(c).name,child_categories);
                                       otherFragmentVM = new OtherFragmentVM(expandableCategoriesPOJO);
                                     //  otherFragmentVM = new OtherFragmentVM(childCategoriesPOJO);
                                       expandableVM.add(otherFragmentVM);
                                   }
                                   expandableCategoriesAdapter = new ExpandableCategoriesAdapter(expandableVM, getContext());
                                   binding.categoryRecycler.setAdapter(expandableCategoriesAdapter);


                               }else {
                                   Log.e(TAG,"child_categories_empty==>");

                               }

                           } else if (otherFragmentVMS.status.get().equalsIgnoreCase("error")) {
                               Toast.makeText(getActivity(), otherFragmentVMS.msg.get(), Toast.LENGTH_SHORT).show();
                           }
                       } catch (NullPointerException e) {
                           e.printStackTrace();
                       }
                   }
               });



        return view;
    }

    private void loadExpandableRecycler() {

      /*  expandableCategoriesPOJO = new ExpandableCategoriesPOJO(header, images);
        expandableCategoriesPOJO = new ExpandableCategoriesPOJO(header, images);
        expandableCategoriesPOJO = new ExpandableCategoriesPOJO(header, images);
        expandableCategoriesPOJO = new ExpandableCategoriesPOJO(header, images);
        expandableCategoriesPOJO = new ExpandableCategoriesPOJO(header, images);
        expandableCategoriesPOJO = new ExpandableCategoriesPOJO(header, images);
        expandableCategoriesPOJO = new ExpandableCategoriesPOJO(header, images);


        expandableCategoriesAdapter = new ExpandableCategoriesAdapter(expandableCategoriesPOJOList, getContext());
       // recyclerView.setAdapter(expandableCategoriesAdapter);*/
    }

}
