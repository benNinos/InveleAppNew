package com.ninositsolution.inveleapp.address_book;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.databinding.BindingAdapter;
import android.databinding.ObservableField;
import android.content.Context;
import android.util.Log;

import com.ninositsolution.inveleapp.add_address.AddAddressRepo;
import com.ninositsolution.inveleapp.add_address.AddAddressVM;
import com.ninositsolution.inveleapp.add_address.pojo.AddAddressRequest;
import com.ninositsolution.inveleapp.address_book.pojo.AddressBookRequest;
import com.ninositsolution.inveleapp.address_book.pojo.AddressUpdateRequest;
import com.ninositsolution.inveleapp.pojo.AddressList;
import com.ninositsolution.inveleapp.pojo.POJOClass;

import java.util.List;

/**
 * Created by Parthasarathy D on 1/25/2019.
 * Ninos IT Solution Pvt Ltd
 * ben@ninositsolution.com
 */
public class AddressBookVM extends ViewModel {



    private AddressBookRepo addressBookRepo;
    private MutableLiveData<List<AddressBookVM>> addressBookVMMutableLiveData = new MutableLiveData<>();
    private MutableLiveData<AddressBookVM> defaultUpdateVMMutableLiveData = new MutableLiveData<>();
    private MutableLiveData<AddressBookVM>addressDeleteVMMutableLiveData = new MutableLiveData<>();
    //UI fields

    public ObservableField<String> name = new ObservableField<>("");
    public ObservableField<String> contact_no = new ObservableField<>("");
    public ObservableField<String> address = new ObservableField<>("");
    public ObservableField<String> city_pincode = new ObservableField<>("");
    public ObservableField<String> ship_billAddress = new ObservableField<>("");
    public ObservableField<String> billAddress = new ObservableField<>("");
    public ObservableField<String> id = new ObservableField<>("");
    public ObservableField<String> user_id = new ObservableField<>("");
    public ObservableField<String> user_default = new ObservableField<>("");
    public ObservableField<String>floor_unit = new ObservableField<>("");

    //custom field
    public ObservableField<String>address_type = new ObservableField<>("");
    public ObservableField<String>postal_code = new ObservableField<>("");
    //pojo fields
    public ObservableField<String> status = new ObservableField<>();
    public ObservableField<String> msg = new ObservableField<>();
    public ObservableField  <List<AddressList>> address_list = new ObservableField<>();

    public AddressBookVM(POJOClass pojoClass)
    {
        this.status.set(pojoClass.status);
        this.msg.set(pojoClass.msg);
    }
    public AddressBookVM(AddressList addressLists){
        this.id.set(addressLists.getId());
        this.user_id.set(addressLists.getUser_id());
        this.user_default.set(addressLists.getUser_default());
        this.address_type.set(addressLists.getAddress_type());
        //for edit screen
        this.floor_unit.set(addressLists.getAddress());
        this.postal_code.set(addressLists.getPostal_code());

        this.name.set(addressLists.getName());
        this.contact_no.set(addressLists.getContact_no());
        this.address.set(addressLists.getAddress1());
        this.city_pincode.set(addressLists.getCity()+","+addressLists.getPostal_code());
        this.ship_billAddress.set(addressLists.getIs_shipping_address());
        this.billAddress.set(addressLists.getIs_billing_address());

    }
    public AddressBookVM() {
        addressBookRepo = new AddressBookRepo();

    }

    public void getAddressList(String user_id)
    {
        AddressBookRequest addressBookRequest = new AddressBookRequest(user_id);

        addressBookRepo = new AddressBookRepo();

        // pojoClassMutableLiveData = registerRepo.getRegisterVMMutableLiveData(registartionRequest);
        addressBookVMMutableLiveData = addressBookRepo.getAddressListVMMutableLiveData(addressBookRequest);

        //String message = registerVMMutableLiveData.getValue().status.get();

        //  stringMutableLiveData.setValue(message);
    }

    public void updateAddressDefault(String user_address_id)
    {
        Log.e("user_address_id","user_address_id==>"+user_address_id);
        AddressUpdateRequest addressUpdateRequest = new AddressUpdateRequest(user_address_id);

        addressBookRepo = new AddressBookRepo();

        // pojoClassMutableLiveData = registerRepo.getRegisterVMMutableLiveData(registartionRequest);
        defaultUpdateVMMutableLiveData = addressBookRepo.getUpdateDefaultVMMutableLiveData(addressUpdateRequest);

        //String message = registerVMMutableLiveData.getValue().status.get();

        //  stringMutableLiveData.setValue(message);

    }
    public void addressDelete(String user_address_id)
    {
        Log.e("user_address_id","user_address_id==>"+user_address_id);
        AddressUpdateRequest addressUpdateRequest = new AddressUpdateRequest(user_address_id);

        addressBookRepo = new AddressBookRepo();

        // pojoClassMutableLiveData = registerRepo.getRegisterVMMutableLiveData(registartionRequest);
        addressDeleteVMMutableLiveData = addressBookRepo.getAddressDeleteVMMutableLiveData(addressUpdateRequest);

        //String message = registerVMMutableLiveData.getValue().status.get();

        //  stringMutableLiveData.setValue(message);

    }



    public MutableLiveData <List<AddressBookVM>> getAddressBookVMMutableLiveData() {
        return addressBookVMMutableLiveData;
    }

    public MutableLiveData<AddressBookVM>getDefaultUpdateVMMutableLiveData(){
        return  defaultUpdateVMMutableLiveData;
    }
    public MutableLiveData<AddressBookVM>addressDeleteMutableLiveData(){
        return  addressDeleteVMMutableLiveData;
    }





}
