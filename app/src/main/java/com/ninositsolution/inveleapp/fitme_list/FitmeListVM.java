package com.ninositsolution.inveleapp.fitme_list;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.databinding.ObservableField;

import com.ninositsolution.inveleapp.pojo.FitmeLists;
import com.ninositsolution.inveleapp.pojo.POJOClass;

import java.util.List;

public class FitmeListVM extends ViewModel {

    private MutableLiveData<FitmeListVM> fitmeListVMMutableLiveData = new MutableLiveData<>();

    private FitmeListRepo fitmeListRepo;

    public ObservableField<String> name = new ObservableField<>();
    public ObservableField<String> date = new ObservableField<>();
    public ObservableField<String> key1 = new ObservableField<>();
    public ObservableField<String> value1 = new ObservableField<>();
    public ObservableField<String> key2 = new ObservableField<>();
    public ObservableField<String> value2 = new ObservableField<>();

    public ObservableField<String> viewAllName = new ObservableField<>();
    public ObservableField<String> viewAllgender = new ObservableField<>();
    public ObservableField<String> viewAllMeasurement = new ObservableField<>();
    public ObservableField<String> viewAllLabel = new ObservableField<>();
    public ObservableField<String> viewAllValue = new ObservableField<>();


    public ObservableField<String> toolBarHeader = new ObservableField<>("Fitme List");

    private String status, message;
    private List<FitmeLists> userMeasurements;

    public FitmeListVM() {

        fitmeListRepo = new FitmeListRepo();
    }

    public FitmeListVM(POJOClass pojoClass)
    {
        status = pojoClass.status;
        message = pojoClass.msg;
        userMeasurements = pojoClass.user_measurements;
    }

    public FitmeListVM(FitmeLists fitmeLists)
    {
        try {
            name.set(fitmeLists.getName());
            date.set(fitmeLists.getCreatedAt());
            key1.set(fitmeLists.getMeasureDetails().get(0).getLabel()+" : ");
            key2.set(fitmeLists.getMeasureDetails().get(1).getLabel()+" : ");
            value1.set(fitmeLists.getMeasureDetails().get(0).getValue()+" "+fitmeLists.getMeasurement());
            value2.set(fitmeLists.getMeasureDetails().get(1).getValue()+" "+fitmeLists.getMeasurement());
        } catch (Exception e)
        {

        }

    }

    public FitmeListVM(String label, String value, String unit)
    {
        viewAllLabel.set(label);
        viewAllValue.set(value+" "+unit);
    }

    public void getFitmeListsApi(String userId)
    {
        fitmeListVMMutableLiveData = fitmeListRepo.getFitmeListVMMutableLiveData(userId);
    }

    public String getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public List<FitmeLists> getUserMeasurements() {
        return userMeasurements;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public MutableLiveData<FitmeListVM> getFitmeListVMMutableLiveData() {
        return fitmeListVMMutableLiveData;
    }
}
