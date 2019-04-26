package com.ninositsolution.inveleapp.all_brands;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.databinding.ObservableField;

import com.ninositsolution.inveleapp.pojo.HomeArrayLists;
import com.ninositsolution.inveleapp.pojo.POJOClass;

import java.util.List;


public class AllBrandsVM extends ViewModel {

    private AllBrandsRepo allBrandsRepo;

    private MutableLiveData<AllBrandsVM> allBrandsVMMutableLiveData = new MutableLiveData<>();

    public ObservableField<String> brandTitle = new ObservableField<>();
    public ObservableField<String> childImage = new ObservableField<>();
    public ObservableField<String> childname = new ObservableField<>();

    public AllBrandsVM() {
        allBrandsRepo = new AllBrandsRepo();
        allBrandsVMMutableLiveData = allBrandsRepo.getAllBrandsVMMutableLiveData();
    }

    private String status, message;
    private List<HomeArrayLists> allBrands;

    public AllBrandsVM(POJOClass pojoClass)
    {
        status = pojoClass.status;
        message = pojoClass.msg;
        allBrands = pojoClass.all_brands;
    }

    public AllBrandsVM(String title)
    {
        brandTitle.set(title);
    }

    public AllBrandsVM(String name, String imageUrl)
    {
        childname.set(name);
        childImage.set(imageUrl);
    }

    public MutableLiveData<AllBrandsVM> getAllBrandsVMMutableLiveData() {
        return allBrandsVMMutableLiveData;
    }

    public String getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public List<HomeArrayLists> getAllBrands() {
        return allBrands;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
