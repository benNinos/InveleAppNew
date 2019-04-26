package com.ninositsolution.inveleapp.fitme;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.content.Context;
import android.databinding.ObservableField;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.JsonObject;
import com.ninositsolution.inveleapp.fitme.pojo.FitmeRequest;
import com.ninositsolution.inveleapp.pojo.FitmeLists;
import com.ninositsolution.inveleapp.pojo.HomeArrayLists;
import com.ninositsolution.inveleapp.pojo.POJOClass;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Parthasarathy D on 1/25/2019.
 * Ninos IT Solution Pvt Ltd
 * ben@ninositsolution.com
 */
public class FitmeVM extends ViewModel {

    private static final String TAG = FitmeVM.class.getSimpleName();
    private FitmeRepo fitmeRepo;

    public ObservableField<String> label = new ObservableField<>();
    public ObservableField<String> currentSize = new ObservableField<>();
    public  ObservableField<String> name = new ObservableField<>();
    public ObservableField<String> measurement = new ObservableField<>();
    public ObservableField<String> gender = new ObservableField<>();
    public ObservableField<String> fitme_details = new ObservableField<>();

    public ObservableField<String> fitmeImage = new ObservableField<>();
    public ObservableField<String> fitmeDesc = new ObservableField<>();

    public ObservableField<String> nameEdt = new ObservableField<>("");

    public ObservableField<String> genderValue = new ObservableField<>("MALE");
    public ObservableField<String> measurementValue = new ObservableField<>("INCHES");


    public MutableLiveData<FitmeVM> fitmeVMMutableLiveData = new MutableLiveData<>();

    private MutableLiveData<FitmeVM> fitmeAddLiveData = new MutableLiveData<>();

    private MutableLiveData<FitmeVM> fitmeUpdateLiveData = new MutableLiveData<>();


    public FitmeVM()
    {

        fitmeRepo = new FitmeRepo();
    }

    public void getFitmeforAddApi()
    {
        fitmeVMMutableLiveData = fitmeRepo.getFitmeVMMutableLiveData();
    }

    public void getFitmeforEdit(String userId, int userMeasurementId)
    {
        fitmeVMMutableLiveData = fitmeRepo.getFitmeEditMutableLiveData(userId, userMeasurementId);
    }

    public ObservableField<String> status = new ObservableField<>("");
    public ObservableField<String> msg = new ObservableField<>("");

    public ObservableField<ArrayList<HomeArrayLists>> men = new ObservableField<>();
    public ObservableField<ArrayList<HomeArrayLists>> women = new ObservableField<>();
    public ObservableField<FitmeLists> userMeasurement = new ObservableField<>();

    public FitmeVM(POJOClass pojoClass)
    {
        this.status.set(pojoClass.status);
        this.msg.set(pojoClass.msg);
        this.men.set(pojoClass.men);
        this.women.set(pojoClass.women);

        try {
            userMeasurement.set(pojoClass.user_measurement);
            Log.i(TAG, "user_measurement : "+pojoClass.user_measurement);
        } catch (Exception e) {
            Log.e(TAG, "Error -> "+e);
        }
    }

    public FitmeVM(String status, String message)
    {
        this.status.set(status);
        msg.set(message);
    }

    public void addFitmeApi(String userId, JSONArray fitmeDetails)
    {

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("user_id", userId);
            jsonObject.put("name", nameEdt.get());
            jsonObject.put("measurement", measurementValue.get());
            jsonObject.put("gender", genderValue.get());
            jsonObject.put("fitme_details", fitmeDetails);

            Log.i("JSON", ""+jsonObject);
            fitmeAddLiveData = fitmeRepo.getFitmeAddLiveData(jsonObject.toString());

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void updateFitmeApi(String userMeasurmentId, JSONArray fitmeDetails)
    {

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("user_measurement_id", userMeasurmentId);
            jsonObject.put("name", nameEdt.get());
            jsonObject.put("measurement", measurementValue.get());
            jsonObject.put("gender", genderValue.get());
            jsonObject.put("fitme_details", fitmeDetails);

            Log.i("JSON", ""+jsonObject);
            fitmeUpdateLiveData = fitmeRepo.getFitmeUpdateLiveData(jsonObject.toString());

        } catch (JSONException e) {
            e.printStackTrace();
            Log.e(TAG, "JSONException -> "+e);
        }

    }

    public FitmeVM(HomeArrayLists homeArrayLists1) {

        this.label.set(homeArrayLists1.getLabel());

        try {
            currentSize.set(homeArrayLists1.value);
            if (homeArrayLists1.value == null)
            {
                currentSize.set("0");
            }
        } catch (NullPointerException e)
        {
            Log.e(TAG, "Value Exception -> "+e);
            this.currentSize.set("0");
        }
    }

    public MutableLiveData<FitmeVM> getFitmeVMMutableLiveData() {
        return fitmeVMMutableLiveData;
    }

    public MutableLiveData<FitmeVM> getFitmeAddLiveData() {
        return fitmeAddLiveData;
    }

    public MutableLiveData<FitmeVM> getFitmeUpdateLiveData() {
        return fitmeUpdateLiveData;
    }
}
