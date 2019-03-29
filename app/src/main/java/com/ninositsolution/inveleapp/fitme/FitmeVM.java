package com.ninositsolution.inveleapp.fitme;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.databinding.ObservableField;

import com.ninositsolution.inveleapp.pojo.HomeArrayLists;
import com.ninositsolution.inveleapp.pojo.POJOClass;

import java.util.ArrayList;

/**
 * Created by Parthasarathy D on 1/25/2019.
 * Ninos IT Solution Pvt Ltd
 * ben@ninositsolution.com
 */
public class FitmeVM extends ViewModel {
    public ObservableField<String> label = new ObservableField<>();

    public MutableLiveData<FitmeVM> fitmeVMMutableLiveData = new MutableLiveData<>();


    public FitmeVM() {

        FitmeRepo fitmeRepo = new FitmeRepo();
        fitmeVMMutableLiveData = fitmeRepo.getFitmeVMMutableLiveData();
    }

    public ObservableField<String> status = new ObservableField<>("");
    public ObservableField<String> msg = new ObservableField<>("");
    public ObservableField<ArrayList<HomeArrayLists>> men = new ObservableField<>();
    public ObservableField<ArrayList<HomeArrayLists>> women = new ObservableField<>();

    public FitmeVM(POJOClass pojoClass)
    {
        this.status.set(pojoClass.status);
        this.msg.set(pojoClass.msg);
        this.men.set(pojoClass.men);
        this.women.set(pojoClass.women);
    }


    public FitmeVM(HomeArrayLists homeArrayLists1) {

        this.label.set(homeArrayLists1.getLabel());
    }

    public MutableLiveData<FitmeVM> getFitmeVMMutableLiveData() {
        return fitmeVMMutableLiveData;
    }
}
