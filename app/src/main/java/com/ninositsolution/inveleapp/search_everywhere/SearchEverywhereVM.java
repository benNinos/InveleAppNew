package com.ninositsolution.inveleapp.search_everywhere;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.content.Context;
import android.databinding.ObservableField;

import com.ninositsolution.inveleapp.pojo.HomeArrayLists;
import com.ninositsolution.inveleapp.pojo.POJOClass;
import com.ninositsolution.inveleapp.recently_viewed.RecentlyViewedAdapter;
import com.ninositsolution.inveleapp.search.SearchModel;
import com.ninositsolution.inveleapp.utils.Constants;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Parthasarathy D on 1/30/2019.
 * Ninos IT Solution Pvt Ltd
 * ben@ninositsolution.com
 */
public class SearchEverywhereVM extends ViewModel implements Serializable {

    private SearchEveryWhereRepo searchEveryWhereRepo;

    private MutableLiveData<SearchEverywhereVM> searchEverywhereVMMutableLiveData = new MutableLiveData<>();

    public SearchEverywhereVM() {

        searchEveryWhereRepo = new SearchEveryWhereRepo();
    }

    public ObservableField<String> status = new ObservableField<>("");
    public ObservableField<String> msg = new ObservableField<>("");
    public ObservableField<ArrayList<HomeArrayLists>> products = new ObservableField<>();
    public ObservableField<ArrayList<HomeArrayLists>> categories = new ObservableField<>();
    public ObservableField<ArrayList<HomeArrayLists>> brands = new ObservableField<>();
    public ObservableField<ArrayList<HomeArrayLists>> locations = new ObservableField<>();
    public ObservableField<ArrayList<HomeArrayLists>> attributes = new ObservableField<>();
    public ObservableField<ArrayList<HomeArrayLists>> shippings = new ObservableField<>();
    public ObservableField<Float> filter_min_price = new ObservableField<>();
    public ObservableField<Float> filter_max_price = new ObservableField<>();

    public SearchEverywhereVM(POJOClass pojoClass)
    {
        status.set(pojoClass.status);
        msg.set(pojoClass.msg);
        products.set(pojoClass.products);
        categories.set(pojoClass.categories);
        locations.set(pojoClass.locations);
        attributes.set(pojoClass.attributes);
        filter_min_price.set(pojoClass.filter_min_price);
        filter_max_price.set(pojoClass.filter_max_price);
        shippings.set(pojoClass.shippings);
    }

    public void getBySearchApi(String user_id, String search_keyword, String order_by)
    {
        SearchEverywhereRequest request = new SearchEverywhereRequest(user_id, search_keyword, order_by);

        searchEverywhereVMMutableLiveData = searchEveryWhereRepo.getSearchEverywhereVMMutableLiveData(request);
    }

    public MutableLiveData<SearchEverywhereVM> getSearchEverywhereVMMutableLiveData() {
        return searchEverywhereVMMutableLiveData;
    }

    // product loading

    public ObservableField<String> product_img = new ObservableField<>();
    public ObservableField<String> product_item_name = new ObservableField<>("");
    public ObservableField<String> product_rate = new ObservableField<>("");
    public ObservableField<String> product_delete_rate = new ObservableField<>("");
    public ObservableField<String> product_rating = new ObservableField<>("");
    public ObservableField<Float> product_rating_float = new ObservableField<>();



    //filter Screen

    public ObservableField<String> dynamic_head = new ObservableField<>("");
    public ObservableField<String> two_view_text = new ObservableField<>("");
    public ObservableField<String> four_view_text = new ObservableField<>("");

    public SearchEverywhereVM(HomeArrayLists homeArrayLists, int flag)
    {
        if (flag == Constants.SEARCH_EVERYWHERE_PRODUCTS)
        {
            product_img.set(homeArrayLists.image_path);
            product_item_name.set(homeArrayLists.name);
            product_rate.set(homeArrayLists.invele_price.toString());
            product_delete_rate.set(homeArrayLists.usual_price);
            product_rating.set(homeArrayLists.average_rating.toString());
            product_rating_float.set(Float.valueOf(homeArrayLists.average_rating));
        }

        if (flag == Constants.SEARCH_EVERYWHERE_CATEGORIES)
        {
            two_view_text.set(homeArrayLists.name);
        }
    }



}