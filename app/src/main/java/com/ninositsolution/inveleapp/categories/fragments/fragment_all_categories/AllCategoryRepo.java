package com.ninositsolution.inveleapp.categories.fragments.fragment_all_categories;

import android.arch.lifecycle.MutableLiveData;
import android.content.Context;
import android.util.Log;

import com.ninositsolution.inveleapp.api.ApiService;
import com.ninositsolution.inveleapp.api.RetrofitClient;
import com.ninositsolution.inveleapp.categories.CategoryModel;
import com.ninositsolution.inveleapp.categories.CategoryVM;
import com.ninositsolution.inveleapp.pojo.POJOClass;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class AllCategoryRepo {

    private static final String TAG = "AllCategoryRepo";

    private List<AllCategoryFragmentVM> arrayList;
    private CategoryModel categoryModel;
    Context context;

    private MutableLiveData<List<AllCategoryFragmentVM>> allcategoryVMMutableLiveData = new MutableLiveData<>();
    private MutableLiveData<AllCategoryFragmentVM>allCategoryVMMutableLiveData = new MutableLiveData<>();

    public MutableLiveData <List<AllCategoryFragmentVM>> getAllCategoryVMMutableLiveData() {

        ApiService apiService = RetrofitClient.getApiService();

        apiService.Categories()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<POJOClass>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(POJOClass pojoClass) {

                        //pojoClassMutableLiveData.setValue(pojoClass);
                        AllCategoryFragmentVM allCategoryFragmentVM;

                        if(pojoClass.status.equalsIgnoreCase("success")) {

                            Log.e(TAG, "onNext - > " + pojoClass.msg);

                            arrayList = new ArrayList<>();
                            if (pojoClass.parent_categories != null) {
                                if (!pojoClass.parent_categories.isEmpty()) {

                                    Log.e(TAG, "list_size==>" + pojoClass.parent_categories.size());

                                    for (int i = 0; i < pojoClass.parent_categories.size(); i++) {

                                        categoryModel = new CategoryModel(pojoClass.parent_categories.get(i).menu_id, pojoClass.parent_categories.get(i).name, pojoClass.parent_categories.get(i).image_path,
                                                pojoClass.parent_categories.get(i).slug);

                                        allCategoryFragmentVM = new AllCategoryFragmentVM(pojoClass,categoryModel);

                                        arrayList.add(allCategoryFragmentVM);

                                    }
                                    allcategoryVMMutableLiveData.setValue(arrayList);

                                }
                            }
                            pojoClass.status="";
                           // arrayList.clear();

                        }else if(pojoClass.status.equalsIgnoreCase("error")){
                            Log.e(TAG, "onNext - > " + pojoClass.msg);
                            // Toast.makeText(context,pojoClass.msg,Toast.LENGTH_SHORT).show();
                        }

                    }


                    @Override
                    public void onError(Throwable e) {

                        Log.e(TAG, "onError - > "+e.getMessage());
                        // Toast.makeText(context,e.getMessage(),Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onComplete() {

                    }
                });


        return allcategoryVMMutableLiveData;
    }
}
