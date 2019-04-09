package com.ninositsolution.inveleapp.search_everywhere;
import android.annotation.SuppressLint;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.ninositsolution.inveleapp.R;
import com.ninositsolution.inveleapp.databinding.FragmentFilterBinding;
import com.ninositsolution.inveleapp.pojo.HomeArrayLists;
import com.ninositsolution.inveleapp.utils.Constants;
import com.ninositsolution.inveleapp.utils.OnSwipeTouchListener;
import com.ninositsolution.inveleapp.utils.Session;

import java.util.ArrayList;
import java.util.Objects;

import static com.ninositsolution.inveleapp.utils.Constants.SEARCH_EVERYWHERE_ATTRIBUTES_CHILD;
import static com.ninositsolution.inveleapp.utils.Constants.SEARCH_EVERYWHERE_BRANDS;
import static com.ninositsolution.inveleapp.utils.Constants.SEARCH_EVERYWHERE_CATEGORIES;
import static com.ninositsolution.inveleapp.utils.Constants.SEARCH_EVERYWHERE_LOCATIONS;
import static com.ninositsolution.inveleapp.utils.Constants.SEARCH_EVERYWHERE_SHIPPING;

/**
 * A simple {@link Fragment} subclass.
 */
public class FilterFragment extends Fragment implements IFilter{

    private static final String TAG = "FilterFragment";
    private FragmentFilterBinding binding;
    private SearchEverywhereVM searchEverywhereVM, vm;
    private static final String SEARCHEVERYWHEREVM_KEY = "searcheverywherevm_key";
    private IFilter iFilter;

    private ArrayList<Integer> brand_ids = new ArrayList<>();
    private ArrayList<Integer> store_location_ids = new ArrayList<>();
    private ArrayList<Integer> attribute_value_ids = new ArrayList<>();
    private ArrayList<Integer> shipping_ids = new ArrayList<>();
    private ArrayList<String> sizes = new ArrayList<>();


    public FilterFragment() {
        // Required empty public constructor
    }

    public static FilterFragment fragmentInstance(SearchEverywhereVM vmInstance)
    {
        FilterFragment filterFragment = new FilterFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(SEARCHEVERYWHEREVM_KEY, vmInstance);
        filterFragment.setArguments(bundle);

        return filterFragment;
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_filter, container, false);
        final View view = binding.getRoot();


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getActivity().findViewById(R.id.search_appbar_layout).setForeground(getResources().getDrawable(R.drawable.window_dim));
            getActivity().findViewById(R.id.search_body_layout).setForeground(getResources().getDrawable(R.drawable.window_dim));
            getActivity().findViewById(R.id.search_appbar_layout).getForeground().setAlpha(180);
            getActivity().findViewById(R.id.search_body_layout).getForeground().setAlpha(180);
        }

        searchEverywhereVM = ViewModelProviders.of(this).get(SearchEverywhereVM.class);
        binding.setFilter(searchEverywhereVM);
        binding.setLifecycleOwner(this);
        iFilter = this;
        binding.setIFilter(iFilter);

        brand_ids.clear();
        store_location_ids.clear();
        attribute_value_ids.clear();
        shipping_ids.clear();
        sizes.clear();


        vm = (SearchEverywhereVM) getArguments().getSerializable(SEARCHEVERYWHEREVM_KEY);

        Toast.makeText(getContext(), ""+vm.msg.get(), Toast.LENGTH_SHORT).show();

        binding.filterCategoriesRecyclerView.setHasFixedSize(true);
        binding.filterCategoriesRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        binding.filterCategoriesRecyclerView.setAdapter(new FilterTwoViewAdapter(getContext(), getArraylistLess(vm.categories.get()), SEARCH_EVERYWHERE_CATEGORIES, this));

        if (vm.categories.get().size() > 4)
        {
            binding.filterCategoriesRecyclerViewMore.setHasFixedSize(true);
            binding.filterCategoriesRecyclerViewMore.setLayoutManager(new GridLayoutManager(getContext(), 2));
            binding.filterCategoriesRecyclerViewMore.setAdapter(new FilterTwoViewAdapterMore(getContext(), getArraylistMore(vm.categories.get()), SEARCH_EVERYWHERE_CATEGORIES, this));
        }

        Log.i(TAG, "brandlist size -> "+ vm.brands.get().size());

        binding.filterBrandRecyclerView.setHasFixedSize(true);
        binding.filterBrandRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        binding.filterBrandRecyclerView.setAdapter(new FilterTwoViewAdapter(getContext(), getArraylistLess(vm.brands.get()), Constants.SEARCH_EVERYWHERE_BRANDS, this));

        if (vm.brands.get().size() > 4)
        {
            binding.filterBrandRecyclerViewMore.setHasFixedSize(true);
            binding.filterBrandRecyclerViewMore.setLayoutManager(new GridLayoutManager(getContext(), 2));
            binding.filterBrandRecyclerViewMore.setAdapter(new FilterTwoViewAdapterMore(getContext(), getArraylistMore(vm.brands.get()), Constants.SEARCH_EVERYWHERE_BRANDS, this));
        }

        binding.filterShippingRecyclerView.setHasFixedSize(true);
        binding.filterShippingRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        binding.filterShippingRecyclerView.setAdapter(new FilterTwoViewAdapter(getContext(), getArraylistLess(vm.shippings.get()),Constants.SEARCH_EVERYWHERE_SHIPPING, this));

        if (vm.shippings.get().size() > 4)
        {
            binding.filterShippingRecyclerViewMore.setHasFixedSize(true);
            binding.filterShippingRecyclerViewMore.setLayoutManager(new GridLayoutManager(getContext(), 2));
            binding.filterShippingRecyclerViewMore.setAdapter(new FilterTwoViewAdapterMore(getContext(), getArraylistMore(vm.shippings.get()), Constants.SEARCH_EVERYWHERE_SHIPPING, this));
        }

        binding.filterLocationsRecyclerView.setHasFixedSize(true);
        binding.filterLocationsRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        binding.filterLocationsRecyclerView.setAdapter(new FilterTwoViewAdapter(getContext(), getArraylistLess(vm.locations.get()),Constants.SEARCH_EVERYWHERE_LOCATIONS, this));

        if (vm.locations.get().size() > 4)
        {
            binding.filterLocationsRecyclerViewMore.setHasFixedSize(true);
            binding.filterLocationsRecyclerViewMore.setLayoutManager(new GridLayoutManager(getContext(), 2));
            binding.filterLocationsRecyclerViewMore.setAdapter(new FilterTwoViewAdapterMore(getContext(), getArraylistMore(vm.locations.get()), Constants.SEARCH_EVERYWHERE_LOCATIONS, this));
        }

        binding.filterSizesRecyclerView.setHasFixedSize(true);
        binding.filterSizesRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 4));
        binding.filterSizesRecyclerView.setAdapter(new FilterFourViewAdapter(getContext(), null, false,this, vm.fitme_sizes.get(), Constants.SEARCH_EVERYWHERE_FITME_SIZE));

        binding.filterAttributesRecyclerView.setHasFixedSize(true);
        binding.filterAttributesRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.filterAttributesRecyclerView.setAdapter(new FilterDynamicAdapter(getContext(), vm.attributes.get(), Constants.SEARCH_EVERYWHERE_ATTRIBUTES, this));



        binding.filterContainer.setOnTouchListener(new OnSwipeTouchListener(view.getContext()) {

            @RequiresApi(api = Build.VERSION_CODES.M)
            public void onSwipeRight() {

                assert getFragmentManager() != null;
                getFragmentManager()
                        .beginTransaction()
                        .setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_right)
                        .remove(Objects.requireNonNull(getFragmentManager()
                                .findFragmentById(R.id.filter_container)))
                        .commit();
            }
        });

        binding.filterScrollView.setOnTouchListener(new OnSwipeTouchListener(view.getContext()) {

            @RequiresApi(api = Build.VERSION_CODES.M)
            public void onSwipeRight() {

                assert getFragmentManager() != null;
                getFragmentManager()
                        .beginTransaction()
                        .setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_right)
                        .remove(Objects.requireNonNull(getFragmentManager()
                                .findFragmentById(R.id.filter_container)))
                        .commit();
            }
        });
        return view;
    }

    private ArrayList<HomeArrayLists> getArraylistLess(ArrayList<HomeArrayLists> lists)
    {
        if (lists.size() > 4)
        {
            ArrayList<HomeArrayLists> arrayLists = new ArrayList<>();
            arrayLists.add(lists.get(0));
            arrayLists.add(lists.get(1));
            arrayLists.add(lists.get(2));
            arrayLists.add(lists.get(3));
            return arrayLists;
        }
        else
        {
            return lists;
        }
    }

    private ArrayList<HomeArrayLists> getArraylistMore(ArrayList<HomeArrayLists> lists)
    {
        ArrayList<HomeArrayLists> listsArrayList = new ArrayList<>();


            for (int i = 0; i<lists.size(); i++)
            {
                if (i > 3)
                    listsArrayList.add(lists.get(i));
            }

            return listsArrayList;
    }

    @Override
    public void onDestroy() {

        super.onDestroy();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getActivity().findViewById(R.id.search_appbar_layout).setForeground(null);
            getActivity().findViewById(R.id.search_body_layout).setForeground(null);
        }
    }

    @Override
    public void onCloseClicked() {

        assert getFragmentManager() != null;
        getFragmentManager()
                .beginTransaction()
                .setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_right)
                .remove(Objects.requireNonNull(getFragmentManager()
                        .findFragmentById(R.id.filter_container)))
                .commit();
    }

    @Override
    public void onCategoriesShowClicked() {

        if (binding.categoriesShowText.getText().toString().equalsIgnoreCase("show more"))
        {
            if (binding.filterCategoriesRecyclerViewMore.getVisibility() == View.GONE)
                binding.filterCategoriesRecyclerViewMore.setVisibility(View.VISIBLE);

            binding.categoriesShowText.setText(R.string.show_less);
            binding.categoriesShowText.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0,R.drawable.ic_expand_less_black_24dp,0);

        } else
        {
            if (binding.filterCategoriesRecyclerViewMore.getVisibility() == View.VISIBLE)
                binding.filterCategoriesRecyclerViewMore.setVisibility(View.GONE);

            binding.categoriesShowText.setText(R.string.show_more);
            binding.categoriesShowText.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0,R.drawable.ic_expand_more_black_24dp,0);
        }
    }

    @Override
    public void onBrandsShowClicked() {
        if (binding.brandsShowText.getText().toString().equalsIgnoreCase("show more"))
        {
            if (binding.filterBrandRecyclerViewMore.getVisibility() == View.GONE)
                binding.filterBrandRecyclerViewMore.setVisibility(View.VISIBLE);

            binding.brandsShowText.setText(R.string.show_less);
            binding.brandsShowText.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0,R.drawable.ic_expand_less_black_24dp,0);

        } else
        {
            if (binding.filterBrandRecyclerViewMore.getVisibility() == View.VISIBLE)
                binding.filterBrandRecyclerViewMore.setVisibility(View.GONE);

            binding.brandsShowText.setText(R.string.show_more);
            binding.brandsShowText.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0,R.drawable.ic_expand_more_black_24dp,0);
        }
    }

    @Override
    public void onShippingShowClicked() {

        if (binding.shippingShowText.getText().toString().equalsIgnoreCase("show more"))
        {
            if (binding.filterShippingRecyclerViewMore.getVisibility() == View.GONE)
                binding.filterShippingRecyclerViewMore.setVisibility(View.VISIBLE);

            binding.shippingShowText.setText(R.string.show_less);
            binding.shippingShowText.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0,R.drawable.ic_expand_less_black_24dp,0);

        } else
        {
            if (binding.filterShippingRecyclerViewMore.getVisibility() == View.VISIBLE)
                binding.filterShippingRecyclerViewMore.setVisibility(View.GONE);

            binding.shippingShowText.setText(R.string.show_more);
            binding.shippingShowText.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0,R.drawable.ic_expand_more_black_24dp,0);
        }
    }

    @Override
    public void onLocationsShowClicked() {

        if (binding.locationsShowText.getText().toString().equalsIgnoreCase("show more"))
        {
            if (binding.filterLocationsRecyclerViewMore.getVisibility() == View.GONE)
                binding.filterLocationsRecyclerViewMore.setVisibility(View.VISIBLE);

            binding.locationsShowText.setText(R.string.show_less);
            binding.locationsShowText.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0,R.drawable.ic_expand_less_black_24dp,0);

        } else
        {
            if (binding.filterLocationsRecyclerViewMore.getVisibility() == View.VISIBLE)
                binding.filterLocationsRecyclerViewMore.setVisibility(View.GONE);

            binding.locationsShowText.setText(R.string.show_more);
            binding.locationsShowText.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0,R.drawable.ic_expand_more_black_24dp,0);
        }

    }

    @Override
    public void onResetClicked() {

        ((SearchEverywhereActivity)getActivity()).refreshFragment();
        onDestroy();
    }

    @Override
    public void ApplyClicked() {

        String type = ((SearchEverywhereActivity)getActivity()).getType();
        String slug = ((SearchEverywhereActivity)getActivity()).getSlug();


            ((SearchEverywhereActivity)getActivity()).showProgressBar();

            searchEverywhereVM.updateSearchFilterApi(slug, brand_ids, store_location_ids, attribute_value_ids, shipping_ids, sizes,
                    Session.getUserId(getContext()), "new", "New", type);

            searchEverywhereVM.getSearchFilterUpdateLiveData().observe(this, new Observer<SearchEverywhereVM>() {
                @Override
                public void onChanged(@Nullable SearchEverywhereVM searchEverywhereVM) {

                    ((SearchEverywhereActivity)getActivity()).hideProgressBar();

                    if (!searchEverywhereVM.status.get().isEmpty())
                    {
                        if (searchEverywhereVM.status.get().equalsIgnoreCase("success"))
                        {
                            Toast.makeText(getContext(), ""+searchEverywhereVM.msg.get(), Toast.LENGTH_SHORT).show();
                            ((SearchEverywhereActivity)getActivity()).binding.recentlyViewedRecyclerview.setAdapter(new SearchEveryWhereAdapter(getContext(), searchEverywhereVM));
                            onCloseClicked();

                        } else
                        {
                            Toast.makeText(getContext(), ""+searchEverywhereVM.msg.get(), Toast.LENGTH_SHORT).show();
                        }
                    } else
                    {
                        Toast.makeText(getContext(), "Api Empty", Toast.LENGTH_SHORT).show();
                    }

                }
            });

        searchEverywhereVM.getCategoryFilterUpdateLiveData().observe(this, new Observer<SearchEverywhereVM>() {
            @Override
            public void onChanged(@Nullable SearchEverywhereVM searchEverywhereVM) {

                ((SearchEverywhereActivity)getActivity()).hideProgressBar();

                if (!searchEverywhereVM.status.get().isEmpty())
                {
                    if (searchEverywhereVM.status.get().equalsIgnoreCase("success"))
                    {
                        Toast.makeText(getContext(), ""+searchEverywhereVM.msg.get(), Toast.LENGTH_SHORT).show();
                        ((SearchEverywhereActivity)getActivity()).binding.recentlyViewedRecyclerview.setAdapter(new SearchEveryWhereAdapter(getContext(), searchEverywhereVM));
                        onCloseClicked();

                    } else
                    {
                        Toast.makeText(getContext(), ""+searchEverywhereVM.msg.get(), Toast.LENGTH_SHORT).show();
                    }
                } else
                {
                    Toast.makeText(getContext(), "Api Empty", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    @Override
    public void onFilterTwoViewClicked(int mode, int id) {

        switch(mode)
        {
            case SEARCH_EVERYWHERE_BRANDS:
                Toast.makeText(getContext(), "brands_id : "+ id , Toast.LENGTH_SHORT).show();
                brand_ids.add(id);
                break;

            case SEARCH_EVERYWHERE_SHIPPING:
                Toast.makeText(getContext(), "shippings_id : "+ id , Toast.LENGTH_SHORT).show();
                shipping_ids.add(id);
                break;

            case SEARCH_EVERYWHERE_LOCATIONS:
                Toast.makeText(getContext(), "Locations_id : "+ id , Toast.LENGTH_SHORT).show();
                store_location_ids.add(id);
                break;

            case SEARCH_EVERYWHERE_ATTRIBUTES_CHILD:
                Toast.makeText(getContext(), "attributes_id : "+ id , Toast.LENGTH_SHORT).show();
                attribute_value_ids.add(id);
                break;
        }
    }

    @Override
    public void onFitmeSizeClicked(String size) {

        sizes.add(size);
    }

    @Override
    public void onCategoriesClicked(String slug) {

        ((SearchEverywhereActivity)getActivity()).getProducts("category", slug);
        onCloseClicked();

    }
}