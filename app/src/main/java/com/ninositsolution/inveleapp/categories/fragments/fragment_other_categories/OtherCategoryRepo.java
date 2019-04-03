package com.ninositsolution.inveleapp.categories.fragments.fragment_other_categories;

import android.arch.lifecycle.MutableLiveData;
import android.content.Context;
import android.util.Log;

import com.ninositsolution.inveleapp.api.ApiService;
import com.ninositsolution.inveleapp.api.RetrofitClient;
import com.ninositsolution.inveleapp.categories.CategoryModel;
import com.ninositsolution.inveleapp.categories.fragments.fragment_all_categories.AllCategoryFragmentVM;
import com.ninositsolution.inveleapp.pojo.CategoryPojoClass;
import com.ninositsolution.inveleapp.pojo.POJOClass;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class OtherCategoryRepo {

    private static final String TAG = "OtherCategoryRepo";

    private List<OtherFragmentVM> arrayList,arraylist1;
    private CategoryModel categoryModel,categoryModelChild;
    private  List<CategoryModel>categoryModelList;
    Context context;

    private MutableLiveData<OtherFragmentVM> brandVMMutableLiveData = new MutableLiveData<>();
    private MutableLiveData<List<OtherFragmentVM>> childLevel1VMMutableLiveData = new MutableLiveData<>();

    public MutableLiveData<OtherFragmentVM> getBrandVMMutableLiveData() {

        ApiService apiService = RetrofitClient.getApiService();

        apiService.Categories()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<CategoryPojoClass>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(CategoryPojoClass pojoClass) {

                        //pojoClassMutableLiveData.setValue(pojoClass);
                        OtherFragmentVM otherFragmentVM,otherFragmentVM1;
                        categoryModel = new CategoryModel();
                        categoryModelChild=new CategoryModel();

                        if(pojoClass.status.equalsIgnoreCase("success")) {

                            Log.e(TAG, "onNext - > " + pojoClass.msg);

                            otherFragmentVM = new OtherFragmentVM(pojoClass);
                            brandVMMutableLiveData.setValue(otherFragmentVM);


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


        return brandVMMutableLiveData ;
    }
}
