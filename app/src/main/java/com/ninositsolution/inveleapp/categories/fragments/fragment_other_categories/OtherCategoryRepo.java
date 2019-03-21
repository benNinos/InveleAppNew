package com.ninositsolution.inveleapp.categories.fragments.fragment_other_categories;

import android.arch.lifecycle.MutableLiveData;
import android.content.Context;
import android.util.Log;

import com.ninositsolution.inveleapp.api.ApiService;
import com.ninositsolution.inveleapp.api.RetrofitClient;
import com.ninositsolution.inveleapp.categories.CategoryModel;
import com.ninositsolution.inveleapp.categories.fragments.fragment_all_categories.AllCategoryFragmentVM;
import com.ninositsolution.inveleapp.pojo.POJOClass;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class OtherCategoryRepo {

    private static final String TAG = "OtherCategoryRepo";

    private List<OtherFragmentVM> arrayList;
    private CategoryModel categoryModel;
    Context context;

    private MutableLiveData<List<OtherFragmentVM>> brandVMMutableLiveData = new MutableLiveData<>();
    private MutableLiveData<OtherFragmentVM>allCategoryVMMutableLiveData = new MutableLiveData<>();

    public MutableLiveData <List<OtherFragmentVM>> getBrandVMMutableLiveData() {

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
                        OtherFragmentVM otherFragmentVM;

                        if(pojoClass.status.equalsIgnoreCase("success")) {

                          /*  if(pojoClass.categories!=null) {
                                if (!pojoClass.categories.isEmpty()) {

                                    for (int i = 0; i < pojoClass.categories.size(); i++) {

                                        Log.e(TAG, "banner_image==>" + pojoClass.categories.get(i).banner_image);
                                    }
                                }
                            }
*/
                            Log.e(TAG, "onNext - > " + pojoClass.msg);

                            arrayList = new ArrayList<>();
                            if (pojoClass.categories!= null) {
                                if (!pojoClass.categories.isEmpty()) {

                                    Log.e(TAG, "list_size==>" + pojoClass.categories.size());
                                    try {

                                        for (int i = 0; i < pojoClass.categories.size(); i++) {

                                            // otherFragmentVM = new OtherFragmentVM(pojoClass, categoryModel);
                                            Log.e(TAG, "brand_size==>" + pojoClass.categories.get(0).brands.size());
                                            for (int j = 0; j < pojoClass.categories.get(0).brands.size(); j++) {

                                                categoryModel = new CategoryModel(pojoClass.categories.get(i).brands.get(j).name, pojoClass.categories.get(i).brands.get(j).image_path, pojoClass.categories.get(i).brands.get(j).menu_id);

                                                otherFragmentVM = new OtherFragmentVM(pojoClass, categoryModel);

                                                arrayList.add(otherFragmentVM);

                                            }
                                        }
                                        brandVMMutableLiveData.setValue(arrayList);
                                    }catch (IndexOutOfBoundsException e){
                                        e.printStackTrace();
                                    }

                                }
                            }
                           /* if(pojoClass.child_categories!= null){
                                if(!pojoClass.child_categories.isEmpty()){
                                    Log.e(TAG, "child_categories==>" + pojoClass.child_categories.size());


                                    for(int i = 0;i<pojoClass.child_categories.size();i++){
                                        Log.e(TAG,"id==>"+pojoClass.child_categories.get(i).menu_id);
                                    }
                                }

                            }*/
                           pojoClass.status="";

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


        return brandVMMutableLiveData;
    }
}
