package com.ninositsolution.inveleapp.categories;

import android.arch.lifecycle.MutableLiveData;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.ninositsolution.inveleapp.address_book.AddressBookVM;
import com.ninositsolution.inveleapp.address_book.pojo.AddressBookRequest;
import com.ninositsolution.inveleapp.api.ApiService;
import com.ninositsolution.inveleapp.api.RetrofitClient;
import com.ninositsolution.inveleapp.pojo.AddressList;
import com.ninositsolution.inveleapp.pojo.POJOClass;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class CategoryRepo {

    private static final String TAG = "CategoryRepo";

    private List<CategoryVM> arrayList;
    private CategoryModel categoryModel;
    Context context;

    private MutableLiveData<List<CategoryVM>> categoryVMMutableLiveData = new MutableLiveData<>();
    private MutableLiveData<CategoryVM>allCategoryVMMutableLiveData = new MutableLiveData<>();
    private MutableLiveData<CategoryVM> defaultUpdateVMMutableLiveData = new MutableLiveData<>();

    public MutableLiveData <List<CategoryVM>> getCategoryVMMutableLiveData() {

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
                        CategoryVM categoryVM;

                        if(pojoClass.status.equalsIgnoreCase("success")) {

                            Log.e(TAG, "onNext - > "+pojoClass.msg);
                            Log.e(TAG,"list_size==>"+pojoClass.categories.size());

                            arrayList = new ArrayList<>();
                            for (int i = 0; i < pojoClass.categories.size(); i++) {

                                categoryModel = new CategoryModel(pojoClass.categories.get(i).menu_id, pojoClass.categories.get(i).name, pojoClass.categories.get(i).image_path,
                                        pojoClass.categories.get(i).slug, pojoClass.categories.get(i).banner_image);

                                categoryVM = new CategoryVM(categoryModel);

                                arrayList.add(categoryVM);

                            }

                            categoryVMMutableLiveData.setValue(arrayList);
                            pojoClass.status="";
                           // arrayList.clear();
                        }else if(pojoClass.status.equalsIgnoreCase("error")){
                            Log.e(TAG, "onNext - > " + pojoClass.msg);
                           // Toast.makeText(context,pojoClass.msg,Toast.LENGTH_SHORT).show();
                        }

                    }

                    /*   @Override
                       public void onNext(ArrayList<POJOClass> pojoClasses) {
                           Log.i(TAG, "onNext - > "+pojoClasses.toString());

                           //pojoClassMutableLiveData.setValue(pojoClass);

                           AddressBookVM addressBookVM= new AddressBookVM(pojoClasses);

                           addressBookVMMutableLiveData.setValue(addressBookVM);
                       }*/
                    @Override
                    public void onError(Throwable e) {

                        Log.e(TAG, "onError - > "+e.getMessage());
                       // Toast.makeText(context,e.getMessage(),Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onComplete() {

                    }
                });


        return categoryVMMutableLiveData;
    }

    public MutableLiveData<CategoryVM> getAllCategoryVMMutableLiveData() {

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

                        if(pojoClass.status.equalsIgnoreCase("success")) {

                            Log.e(TAG, "onNext - > " + pojoClass.msg);

                            CategoryVM categoryVM1 = new CategoryVM(pojoClass);
                            allCategoryVMMutableLiveData.setValue(categoryVM1);
                        }else if(pojoClass.status.equalsIgnoreCase("error")){
                            Log.e(TAG, "onNext - > " + pojoClass.msg);
                            //Toast.makeText(context,pojoClass.msg,Toast.LENGTH_SHORT).show();
                        }
                        pojoClass.status="";


                    }

                    /*   @Override
                       public void onNext(ArrayList<POJOClass> pojoClasses) {
                           Log.i(TAG, "onNext - > "+pojoClasses.toString());

                           //pojoClassMutableLiveData.setValue(pojoClass);

                           AddressBookVM addressBookVM= new AddressBookVM(pojoClasses);

                           addressBookVMMutableLiveData.setValue(addressBookVM);
                       }*/
                    @Override
                    public void onError(Throwable e) {

                        Log.e(TAG, "onError - > "+e.getMessage());
                       // Toast.makeText(context,e.getMessage(),Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onComplete() {

                    }
                });


        return allCategoryVMMutableLiveData;
    }
}
