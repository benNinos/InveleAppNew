package com.ninositsolution.inveleapp.search_everywhere;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.databinding.ObservableField;
import android.util.Log;

import com.google.gson.JsonArray;
import com.ninositsolution.inveleapp.pojo.HomeArrayLists;
import com.ninositsolution.inveleapp.pojo.POJOClass;
import com.ninositsolution.inveleapp.utils.Constants;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Parthasarathy D on 1/30/2019.
 * Ninos IT Solution Pvt Ltd
 * ben@ninositsolution.com
 */
public class SearchEverywhereVM extends ViewModel implements Serializable {

    private static final String TAG = "SearchEverywhereVM";
    private SearchEveryWhereRepo searchEveryWhereRepo;

    private MutableLiveData<SearchEverywhereVM> searchEverywhereVMMutableLiveData = new MutableLiveData<>();
    private MutableLiveData<SearchEverywhereVM> searchFilterUpdateLiveData = new MutableLiveData<>();
    private MutableLiveData<SearchEverywhereVM> categorySearchLiveData = new MutableLiveData<>();
    private MutableLiveData<SearchEverywhereVM> categoryFilterUpdateLiveData = new MutableLiveData<>();
    private MutableLiveData<SearchEverywhereVM> trendingLiveData = new MutableLiveData<>();

    public ObservableField<String> start_price = new ObservableField<>("");
    public ObservableField<String> end_price = new ObservableField<>("");
    public ObservableField<String> toolbarHeader = new ObservableField<>("");

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
    public ObservableField<ArrayList<String>> fitme_sizes = new ObservableField<>();

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
        brands.set(pojoClass.brands);
        shippings.set(pojoClass.shippings);
        fitme_sizes.set(pojoClass.fitme_sizes);
    }

    public void getBySearchApi(String user_id, String search_keyword, String order_by)
    {
        SearchEverywhereRequest request = new SearchEverywhereRequest(user_id, search_keyword, order_by);

        searchEverywhereVMMutableLiveData = searchEveryWhereRepo.getSearchEverywhereVMMutableLiveData(request);
    }

    public void updateSearchFilterApi(String keyword_slug, ArrayList<Integer> brand_ids, ArrayList<Integer> store_location_ids,
                                      ArrayList<Integer> attribute_value_ids, ArrayList<Integer> shipping_ids, ArrayList<String> sizes,
                                      String user_id, String order_by, String condition, String type)
    {

        if (type.equalsIgnoreCase("search"))
        {
            JSONObject jsonObject = new JSONObject();

            JSONArray brand_ids_json = new JSONArray(brand_ids);
            JSONArray store_location_ids_json = new JSONArray(brand_ids);
            JSONArray attribute_value_ids_json = new JSONArray(brand_ids);
            JSONArray shipping_ids_json = new JSONArray(brand_ids);
            JSONArray sizes_json = new JSONArray(brand_ids);

            try {
                jsonObject.put("search_keyword", keyword_slug);
                jsonObject.put("brand_ids", brand_ids_json);
                jsonObject.put("store_location_ids", store_location_ids_json);
                jsonObject.put("attribute_value_ids", attribute_value_ids_json);
                jsonObject.put("shipping_ids", shipping_ids_json);
                jsonObject.put("sizes", sizes_json);
                jsonObject.put("user_id", user_id);
                jsonObject.put("start_price", start_price.get());
                jsonObject.put("end_price", end_price.get());
                jsonObject.put("end_price", end_price.get());
                jsonObject.put("order_by", order_by);
                jsonObject.put("condition", condition);

                Log.i(TAG, "JsonStructure -> "+jsonObject);

                searchFilterUpdateLiveData = searchEveryWhereRepo.getSearchFilterUpdateLiveData(jsonObject.toString());


            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        else
        {
            JSONObject jsonObject = new JSONObject();

            JSONArray brand_ids_json = new JSONArray(brand_ids);
            JSONArray store_location_ids_json = new JSONArray(brand_ids);
            JSONArray attribute_value_ids_json = new JSONArray(brand_ids);
            JSONArray shipping_ids_json = new JSONArray(brand_ids);
            JSONArray sizes_json = new JSONArray(brand_ids);

            try {
                jsonObject.put("category_slug", keyword_slug);
                jsonObject.put("brand_ids", brand_ids_json);
                jsonObject.put("store_location_ids", store_location_ids_json);
                jsonObject.put("attribute_value_ids", attribute_value_ids_json);
                jsonObject.put("shipping_ids", shipping_ids_json);
                jsonObject.put("sizes", sizes_json);
                jsonObject.put("user_id", user_id);
                jsonObject.put("start_price", start_price.get());
                jsonObject.put("end_price", end_price.get());
                jsonObject.put("end_price", end_price.get());
                jsonObject.put("order_by", order_by);
                jsonObject.put("condition", condition);

                Log.i(TAG, "JsonStructure -> "+jsonObject);

                //searchFilterUpdateLiveData = searchEveryWhereRepo.getSearchFilterUpdateLiveData(jsonObject.toString());
                categoryFilterUpdateLiveData = searchEveryWhereRepo.getCategoryFilterUpdateLiveData(jsonObject.toString());


            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    public void getByCategoryApi(String user_id, String order_by, String slug)
    {
        categorySearchLiveData = searchEveryWhereRepo.getCategorySearchLiveData(user_id, order_by, slug);
    }

    public void getByTrendingApi(String order_by, String page_no)
    {
        trendingLiveData = searchEveryWhereRepo.getTrendingLiveData(order_by, page_no);
    }

    public MutableLiveData<SearchEverywhereVM> getSearchEverywhereVMMutableLiveData() {
        return searchEverywhereVMMutableLiveData;
    }

    public MutableLiveData<SearchEverywhereVM> getSearchFilterUpdateLiveData() {
        return searchFilterUpdateLiveData;
    }

    public MutableLiveData<SearchEverywhereVM> getCategorySearchLiveData() {
        return categorySearchLiveData;
    }

    public MutableLiveData<SearchEverywhereVM> getCategoryFilterUpdateLiveData() {
        return categoryFilterUpdateLiveData;
    }

    public MutableLiveData<SearchEverywhereVM> getTrendingLiveData() {
        return trendingLiveData;
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

        if (flag == Constants.SEARCH_EVERYWHERE_BRANDS)
        {
            two_view_text.set(homeArrayLists.name);
        }

        if (flag == Constants.SEARCH_EVERYWHERE_SHIPPING)
        {
            two_view_text.set(homeArrayLists.name);
        }

        if (flag == Constants.SEARCH_EVERYWHERE_LOCATIONS)
        {
            two_view_text.set(homeArrayLists.store_location_name);
        }

        if (flag == Constants.SEARCH_EVERYWHERE_ATTRIBUTES)
        {
            dynamic_head.set(homeArrayLists.name);
        }
    }

    // for child attributes only

    public SearchEverywhereVM(HomeArrayLists homeArrayLists, boolean flag)
    {
        if (flag)
            two_view_text.set(homeArrayLists.name);
        else
            two_view_text.set(homeArrayLists.value);
    }

    //for fitme sizes

    public SearchEverywhereVM(String size)
    {
        two_view_text.set(size);
    }
}