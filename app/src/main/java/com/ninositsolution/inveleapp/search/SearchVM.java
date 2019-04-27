package com.ninositsolution.inveleapp.search;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.databinding.ObservableField;
import android.util.Log;
import android.widget.Toast;

import com.ninositsolution.inveleapp.pojo.POJOClass;

import java.util.List;

/**
 * Created by Parthasarathy D on 1/24/2019.
 * Ninos IT Solution Pvt Ltd
 * ben@ninositsolution.com
 */
public class SearchVM extends ViewModel {

  //  public ObservableField<String> keyword = new ObservableField<>("");

    private static final String TAG = "SearchVM";

    private SearchRepo searchRepo;
    private MutableLiveData<SearchVM> searchVMMutableLiveData = new MutableLiveData<>();

    public ObservableField<String> searchText = new ObservableField<>();

    public SearchVM() {

        searchRepo = new SearchRepo();
    }

    private String status, message;
    private List<String> searchKeys;

    public SearchVM(POJOClass pojoClass)
    {
        status = pojoClass.status;
        message = pojoClass.msg;
        searchKeys = pojoClass.search_keys;
    }

    public void searchByKeywordApi(String keyword)
    {
        searchVMMutableLiveData = searchRepo.getSearchVMMutableLiveData(keyword);
    }

    public MutableLiveData<SearchVM> getSearchVMMutableLiveData() {
        return searchVMMutableLiveData;
    }

    public SearchVM(String value)
    {
        searchText.set(value);
        Log.i(TAG, "value -> "+value);
    }


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<String> getSearchKeys() {
        return searchKeys;
    }

    public void setSearchKeys(List<String> searchKeys) {
        this.searchKeys = searchKeys;
    }

}
