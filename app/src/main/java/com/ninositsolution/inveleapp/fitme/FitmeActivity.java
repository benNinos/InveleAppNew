package com.ninositsolution.inveleapp.fitme;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.ninositsolution.inveleapp.R;
import com.ninositsolution.inveleapp.databinding.ActivityFitmeBinding;
import com.ninositsolution.inveleapp.fitme.pojo.FitmeRequest;
import com.ninositsolution.inveleapp.utils.Session;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import static com.ninositsolution.inveleapp.utils.Constants.FITME_MEN;
import static com.ninositsolution.inveleapp.utils.Constants.FITME_WOMEN;

public class FitmeActivity extends AppCompatActivity implements FitmeRecyclerAdapter.FitmeDetailsListener, IFitme {
    ActivityFitmeBinding binding;

    public static final String TAG = "FitmeActivity";
    FitmeVM fitmeVMGlobal, fitmeInstance;
    Context context;
    FitmeRecyclerAdapter adapter;
    FitmeRecyclerAdapter.FitmeDetailsListener fitmeDetailsListener;
    private int userMeasurementId;
    IFitme iFitme;
    BottomSheetBehavior bottomSheetBehavior;


    @Override
    protected void onStart() {
        Session.clearFitmeMap(context);
        super.onStart();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        fitmeDetailsListener = this;

        binding = DataBindingUtil.setContentView(this,R.layout.activity_fitme);
        fitmeVMGlobal = ViewModelProviders.of(this).get(FitmeVM.class);
        binding.setFitme(fitmeVMGlobal);
        binding.setLifecycleOwner(this);
        //fitmeVMGlobal.currentSize.set("0");
        context = FitmeActivity.this;
        iFitme = this;

        bottomSheetBehavior = BottomSheetBehavior.from(binding.description);

        bottomSheetBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View view, int i) {


                if (bottomSheetBehavior.getState() == BottomSheetBehavior.STATE_EXPANDED)
                {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        binding.fitmeBodyLayout.setForeground(getResources().getDrawable(R.drawable.window_dim));
                        binding.fitmeBodyLayout.getForeground().setAlpha(180);
                        binding.fitmeHeaderLayout.setForeground(getResources().getDrawable(R.drawable.window_dim));
                        binding.fitmeHeaderLayout.getForeground().setAlpha(180);
                    }
                } else
                {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        binding.fitmeBodyLayout.setForeground(null);
                        binding.fitmeHeaderLayout.setForeground(null);
                    }
                }

            }

            @Override
            public void onSlide(@NonNull View view, float v) {

            }
        });

        Bundle bundle = getIntent().getExtras();

        userMeasurementId = bundle.getInt("userMeasurementId");

        if (userMeasurementId == 0)
        {
            binding.saveButton.setVisibility(View.VISIBLE);
            binding.updateButton.setVisibility(View.GONE);

            fitmeVMGlobal.getFitmeforAddApi();
        }
        else
        {
            binding.saveButton.setVisibility(View.GONE);
            binding.updateButton.setVisibility(View.VISIBLE);

            fitmeVMGlobal.getFitmeforEdit(Session.getUserId(context), userMeasurementId);
        }

        Log.e(TAG, "Flow will show progress dialog");

        showProgressBar();
        Log.e(TAG, "Flow entered into next line");

        binding.fitMeRecyclerView.setHasFixedSize(true);
        binding.fitMeRecyclerView.setLayoutManager(new LinearLayoutManager(context));


        fitmeVMGlobal.getFitmeVMMutableLiveData().observe(this, new Observer<FitmeVM>() {
            @Override
            public void onChanged(@Nullable FitmeVM fitmeVM) {

                hideProgressBar();

                String status = fitmeVM.status.get();
                String msg = fitmeVM.msg.get();

                if (!status.isEmpty())
                {
                    if (status.equalsIgnoreCase("success"))
                    {
                        Toast.makeText(context, ""+msg, Toast.LENGTH_SHORT).show();

                        fitmeInstance = fitmeVM;

                        Log.i(TAG, "userMeasurementId -> "+userMeasurementId);
                        if (userMeasurementId != 0)
                        {
                            initHashmap();
                            binding.enterNameEdit.setText(fitmeVM.userMeasurement.get().getName());

                            if (fitmeVM.userMeasurement.get().getGender().equalsIgnoreCase("MALE"))
                                onDisabledMenClicked();
                            else
                                onDisabledWomenClicked();

                            if (fitmeVM.userMeasurement.get().getMeasurement().equalsIgnoreCase("CM"))
                                onDisabledCMClicked();
                            else
                                onEnabledInchesClicked();
                        } else
                        {
                            adapter = new FitmeRecyclerAdapter(context,fitmeVM, FITME_MEN, fitmeDetailsListener);
                            binding.fitMeRecyclerView.setAdapter(adapter);
                        }

                    } else
                    {
                        Toast.makeText(context, ""+msg, Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });


        binding.setIFitme(iFitme);
    }

    private void initHashmap() {

        HashMap<Integer, String> fitme_details = new HashMap<>();

        if (fitmeVMGlobal.genderValue.get().equalsIgnoreCase("MALE"))
        {
            for (int i=0; i<fitmeInstance.men.get().size(); i++)
            {
                fitme_details.put(fitmeInstance.men.get().get(i).fitme_label_id, fitmeInstance.men.get().get(i).value);
            }
        } else
        {
            for (int i=0; i<fitmeInstance.women.get().size(); i++)
            {
                fitme_details.put(fitmeInstance.women.get().get(i).fitme_label_id, fitmeInstance.women.get().get(i).value);
            }
        }

        Log.i(TAG, "fitme_details -> "+fitme_details);
        Session.setFitmeMap(context, fitme_details);

    }


    private void showProgressBar()
    {
        if (binding.fitmeProgress.getVisibility() == View.GONE)
            binding.fitmeProgress.setVisibility(View.VISIBLE);
    }

    private void hideProgressBar()
    {
        if (binding.fitmeProgress.getVisibility() == View.VISIBLE)
            binding.fitmeProgress.setVisibility(View.GONE);
    }

    @Override
    public void onDecreasedSizeClicked(int key, String value)
    {
        Log.i(TAG, "key : "+key+" value : "+value);

        Session.updateFitmeMap(context, key, value);

        Log.i(TAG, "updated fitme -> "+Session.getFitmeMap(context));
    }

    @Override
    public void onIncreasedSizeClicked(int key, String value)
    {
        Log.i(TAG, "key : "+key+" value : "+value);

        Session.updateFitmeMap(context, key, value);

        Log.i(TAG, "updated fitme -> "+Session.getFitmeMap(context));
    }

    @Override
    public void onQuestionDescClicked(String imagePath, String desc) {

        fitmeVMGlobal.fitmeImage.set(imagePath);
        fitmeVMGlobal.fitmeDesc.set(desc);

        if (bottomSheetBehavior.getState() == BottomSheetBehavior.STATE_EXPANDED)
        {
            bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
        }
        else
        {
            bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
        }

    }

    @Override
    public void onValueEdited(int key, String value) {

        Log.i(TAG, "key : "+key+" value : "+value);

        Session.updateFitmeMap(context, key, value);

        Log.i(TAG, "updated fitme -> "+Session.getFitmeMap(context));

    }

    private JSONArray getFitmeDetails(HashMap<Integer, String> fitme_details)
    {
        ArrayList<FitmeDetailModel> fitmeDetailModelArrayList = new ArrayList<>();

       for (Integer key : fitme_details.keySet())
       {
           FitmeDetailModel fitmeDetailModel = new FitmeDetailModel();

           fitmeDetailModel.setLabel_id(key);
           fitmeDetailModel.setValue(fitme_details.get(key));

           fitmeDetailModelArrayList.add(fitmeDetailModel);
       }

        Gson gson = new Gson();
        JSONArray jsonArray = null;
        try {
            jsonArray = new JSONArray(gson.toJson(fitmeDetailModelArrayList));
        } catch (JSONException e) {
            e.printStackTrace();
        }


        Log.i(TAG, "fitme_details -> "+ jsonArray);

       return jsonArray;
    }

    @Override
    public void onDisabledMenClicked() {

        binding.disabledWomen.setBackgroundColor(getResources().getColor(R.color.ash_color));
        binding.womenTextView.setTextColor(getResources().getColor(R.color.black));
        binding.enabledMen.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        binding.menTextView.setTextColor(getResources().getColor(R.color.white));

        fitmeVMGlobal.genderValue.set("MALE");

        Session.clearFitmeMap(context);
        initHashmap();

        adapter = new FitmeRecyclerAdapter(context,fitmeInstance, FITME_MEN, fitmeDetailsListener);
        binding.fitMeRecyclerView.setAdapter(adapter);

    }

    @Override
    public void onDisabledWomenClicked() {

        binding.enabledMen.setBackgroundColor(getResources().getColor(R.color.ash_color));
        binding.menTextView.setTextColor(getResources().getColor(R.color.black));
        binding.disabledWomen.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        binding.womenTextView.setTextColor(getResources().getColor(R.color.white));

        fitmeVMGlobal.genderValue.set("FEMALE");

        Session.clearFitmeMap(context);
        initHashmap();

        adapter = new FitmeRecyclerAdapter(context,fitmeInstance, FITME_WOMEN, fitmeDetailsListener);
        binding.fitMeRecyclerView.setAdapter(adapter);

    }

    @Override
    public void onDisabledCMClicked() {
        binding.disabledCM.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        binding.enabledInches.setBackgroundColor(getResources().getColor(R.color.ash_color));
        binding.cmTextView.setTextColor(getResources().getColor(R.color.white));
        binding.cmTextView.setTextColor(getResources().getColor(R.color.white));
        binding.inchesTextView.setTextColor(getResources().getColor(R.color.black));

        fitmeVMGlobal.measurementValue.set("CM");

    }

    @Override
    public void onEnabledInchesClicked()
    {
        binding.disabledCM.setBackgroundColor(getResources().getColor(R.color.ash_color));
        binding.enabledInches.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        binding.cmTextView.setTextColor(getResources().getColor(R.color.black));
        binding.inchesTextView.setTextColor(getResources().getColor(R.color.white));

        fitmeVMGlobal.measurementValue.set("INCHES");
    }

    @Override
    public void onSaveClicked() {


        fitmeVMGlobal.addFitmeApi(Session.getUserId(context), getFitmeDetails(Session.getFitmeMap(context)));

        fitmeVMGlobal.getFitmeAddLiveData().observe(FitmeActivity.this, new Observer<FitmeVM>() {
            @Override
            public void onChanged(@Nullable FitmeVM fitmeVM) {

                if (fitmeVM.status != null)
                {
                    if (fitmeVM.status.get().equalsIgnoreCase("success"))
                    {
                        Toast.makeText(context, ""+fitmeVM.msg.get(), Toast.LENGTH_SHORT).show();
                        FitmeActivity.super.onBackPressed();
                    } else
                    {
                        Toast.makeText(context, ""+fitmeVM.msg.get(), Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

    }

    @Override
    public void onUpdateClicked() {

        Log.i(TAG, "update clicked");

        fitmeVMGlobal.updateFitmeApi(String.valueOf(userMeasurementId), getFitmeDetails(Session.getFitmeMap(context)));

        fitmeVMGlobal.getFitmeUpdateLiveData().observe(FitmeActivity.this, new Observer<FitmeVM>() {
            @Override
            public void onChanged(@Nullable FitmeVM fitmeVM) {

                if (fitmeVM.status != null)
                {
                    if (fitmeVM.status.get().equalsIgnoreCase("success"))
                    {
                        Toast.makeText(context, ""+fitmeVM.msg.get(), Toast.LENGTH_SHORT).show();
                        FitmeActivity.super.onBackPressed();
                    } else
                    {
                        Toast.makeText(context, ""+fitmeVM.msg.get(), Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    public class FitmeDetailModel
    {
        private Integer label_id;
        private String value;

        public Integer getLabel_id() {
            return label_id;
        }

        public void setLabel_id(Integer label_id) {
            this.label_id = label_id;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }
}
