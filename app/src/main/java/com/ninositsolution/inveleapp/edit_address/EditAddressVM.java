package com.ninositsolution.inveleapp.edit_address;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.content.Context;
import android.databinding.ObservableField;
import android.util.Log;

import com.ninositsolution.inveleapp.add_address.AddAddressRepo;
import com.ninositsolution.inveleapp.add_address.AddAddressVM;
import com.ninositsolution.inveleapp.add_address.pojo.AddAddressRequest;
import com.ninositsolution.inveleapp.edit_address.pojo.EditAddressRequest;
import com.ninositsolution.inveleapp.pojo.AddressList;
import com.ninositsolution.inveleapp.pojo.POJOClass;

import java.util.List;

public class EditAddressVM extends ViewModel {

    private EditAddressRepo editAddressRepo;
    private MutableLiveData<EditAddressVM> editAddressVMMutableLiveData = new MutableLiveData<>();
    private MutableLiveData<EditAddressVM>searchAddressVMMutableLiveData = new MutableLiveData<>();

    //UI fields
    public ObservableField<String> Name = new ObservableField<>("");
    public ObservableField<String>contact_number = new ObservableField<>("");
    public ObservableField<String>postal_code = new ObservableField<>("");
    public ObservableField<String>floor_unit_numer = new ObservableField<>("");
    public ObservableField<String>address = new ObservableField<>("");
    public ObservableField<String>city_name = new ObservableField<>("");
    public ObservableField<String>address_type = new ObservableField<>();
    public ObservableField<String>is_billing = new ObservableField<>();
    public ObservableField<String>is_shipping = new ObservableField<>();
    public ObservableField<String>Home = new ObservableField<>();
    public ObservableField<String>Office = new ObservableField<>();
    public ObservableField<String>Others = new ObservableField<>();

    //pojo fields
    public ObservableField<String> status = new ObservableField<>();
    public ObservableField<String> msg = new ObservableField<>();
    public ObservableField<String>city = new ObservableField<>();
    public ObservableField  <List<AddressList>> address_list = new ObservableField<>();

    public EditAddressVM(POJOClass pojoClass)
    {
        this.status.set(pojoClass.status);
        this.msg.set(pojoClass.msg);
        this.city.set(pojoClass.city);
        this.address_list.set(pojoClass.address_list);

    }

    public EditAddressVM() {
        editAddressRepo = new EditAddressRepo();

    }

    public int postalCodeValidation(){
        Log.e("AddAddressVM==>","postal_code==>"+postal_code.get());
        return editAddressRepo.postalCodeValidation(postal_code.get());

    }

    public int addressValidation()
    {
        return editAddressRepo.addressValidation(Name.get(), contact_number.get(), postal_code.get(),floor_unit_numer.get(),address.get(),city_name.get(),address_type.get());
    }

    public void EditAddress(String user_id)
    {
        EditAddressRequest editAddressRequest = new EditAddressRequest(user_id, address_type.get(),Name.get(),
                floor_unit_numer.get(), address.get(),postal_code.get(),city_name.get(), contact_number.get(),is_billing.get(),is_shipping.get());

        editAddressRepo = new EditAddressRepo();

        // pojoClassMutableLiveData = registerRepo.getRegisterVMMutableLiveData(registartionRequest);
        editAddressVMMutableLiveData = editAddressRepo.getEditAddressVMMutableLiveData(editAddressRequest);

        //String message = registerVMMutableLiveData.getValue().status.get();

        //  stringMutableLiveData.setValue(message);

    }

    public void LocateAddress()
    {
        Log.e("postal_code","postal_code==>"+postal_code.get());
        AddAddressRequest addAddressRequest = new AddAddressRequest(postal_code.get());

        editAddressRepo = new EditAddressRepo();

        // pojoClassMutableLiveData = registerRepo.getRegisterVMMutableLiveData(registartionRequest);
        searchAddressVMMutableLiveData = editAddressRepo.getLocateAddessVMMutableLiveData(addAddressRequest);

        //String message = registerVMMutableLiveData.getValue().status.get();

        //  stringMutableLiveData.setValue(message);

    }

    public MutableLiveData<EditAddressVM> getEditAddressVMMutableLiveData() {
        return editAddressVMMutableLiveData;
    }

    public MutableLiveData<EditAddressVM>getSearchAddressVMMutableLiveData(){
        return  searchAddressVMMutableLiveData;
    }



}
