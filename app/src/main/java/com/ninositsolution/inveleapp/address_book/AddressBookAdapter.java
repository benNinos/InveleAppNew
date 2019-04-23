package com.ninositsolution.inveleapp.address_book;

import android.arch.lifecycle.Observer;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.ninositsolution.inveleapp.R;
import com.ninositsolution.inveleapp.databinding.MainAdapterBinding;
import com.ninositsolution.inveleapp.edit_address.EditAddressActivity;

import java.util.ArrayList;
import java.util.List;

public class AddressBookAdapter extends RecyclerView.Adapter<AddressBookAdapter.MainViewHolder>{

    public Context context;
    private List<AddressBookVM> arrayList;
    private LayoutInflater layoutInflater;

    IAddressBook iAddressBook;
    private RadioButton lastCheckedRadioGroup = null;
    boolean radio_checked=false;
    public static final String TAG = AddressBookAdapter.class.getSimpleName();

    public interface ClickEvent{
        void setClickEventItem(int position,String id,String user_id);
        void setClickEventEdit(int position,String id,String user_id);
        void setClickEventDelete(int position,String id,String user_id);
    }
    ClickEvent clickEvent;

    public void setClikEvent(ClickEvent clikEvent){
        this.clickEvent = clikEvent;
    }

    public AddressBookAdapter(Context context, List<AddressBookVM> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }
    @NonNull
    @Override
    public AddressBookAdapter.MainViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        if (layoutInflater == null)
        {
            layoutInflater = LayoutInflater.from(viewGroup.getContext());
        }

        MainAdapterBinding binding = DataBindingUtil.inflate(layoutInflater, R.layout.address_book_adapter, viewGroup, false);

        return new MainViewHolder(binding,iAddressBook);
    }

    @Override
    public void onBindViewHolder(@NonNull final AddressBookAdapter.MainViewHolder mainViewHolder, final int position) {

        final AddressBookVM addressBookVM = arrayList.get(position);
        mainViewHolder.binding.setAdapterBookVM(addressBookVM);

        Log.e(TAG,"LIST_SIZE==>"+arrayList.size());

        mainViewHolder.bind(addressBookVM,iAddressBook);

        if(!addressBookVM.ship_billAddress.get().equalsIgnoreCase("")) {


            if (addressBookVM.ship_billAddress.get().equalsIgnoreCase("1")) {
                mainViewHolder.binding.shippingButton.setVisibility(View.VISIBLE);
                mainViewHolder.binding.shippingButton.setText("Shipping Address");
            } else {
                mainViewHolder.binding.shippingButton.setVisibility(View.GONE);

            }
        }

        if(!addressBookVM.billAddress.get().equalsIgnoreCase("")) {

            if (addressBookVM.billAddress.get().equalsIgnoreCase("1")) {

                mainViewHolder.binding.billingButton.setVisibility(View.VISIBLE);
                mainViewHolder.binding.billingButton.setText("Billing Address");
            } else {
                mainViewHolder.binding.billingButton.setVisibility(View.GONE);
            }
        }

        if(!addressBookVM.ship_billAddress.get().equalsIgnoreCase("")&& !addressBookVM.billAddress.get().equalsIgnoreCase("")) {

            if (addressBookVM.ship_billAddress.get().equalsIgnoreCase("0") && addressBookVM.billAddress.get().equalsIgnoreCase("0")) {

                mainViewHolder.binding.addressDelete.setVisibility(View.VISIBLE);
            } else {
                mainViewHolder.binding.addressDelete.setVisibility(View.GONE);
            }
        }
        //address default set
        if(addressBookVM.user_default.get().equalsIgnoreCase("1")){
            mainViewHolder.binding.addressRadio.setChecked(true);
            lastCheckedRadioGroup = mainViewHolder.binding.addressRadio;
        }

        mainViewHolder.binding.radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                RadioButton checked_rb = (RadioButton) group.findViewById(checkedId);
                if (lastCheckedRadioGroup != null) {
                    lastCheckedRadioGroup.setChecked(false);
                }else {
                    lastCheckedRadioGroup.setChecked(true);
                }
                //store the clicked radiobutton
                lastCheckedRadioGroup = checked_rb;

                Log.e(TAG,"clciked_position==>"+"\nid-==>"+addressBookVM.id.get()+"position==>"+position);
                clickEvent.setClickEventItem(position,addressBookVM.id.get(),addressBookVM.user_id.get());


              /*  Toast.makeText(context,
                        "Radio button clicked " +  mainViewHolder.binding.radioGroup.getCheckedRadioButtonId(),
                        Toast.LENGTH_SHORT).show();*/
            }
        });

        mainViewHolder.binding.addressEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                clickEvent.setClickEventEdit(position,addressBookVM.id.get(),addressBookVM.user_id.get());

               // context.startActivity(new Intent(context, EditAddressActivity.class));

            }
        });

        mainViewHolder.binding.addressDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickEvent.setClickEventDelete(position,addressBookVM.id.get(),addressBookVM.user_id.get());

            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }



    public class MainViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private MainAdapterBinding binding;
        public IAddressBook iAddressBook;


        public MainViewHolder(@NonNull final MainAdapterBinding binding, final IAddressBook iAddressBook) {
            super(binding.getRoot());
            this.binding = binding;
            this.iAddressBook = iAddressBook;
        /*   binding.editButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Log.e(TAG,"edit_clicked==>"+"\nselect_id==>"+arrayList.get(getAdapterPosition()).id.get());


                }
            });*/
        }

        public void bind(final AddressBookVM  addressBookVM,IAddressBook iAddressBook)
        {
            this.binding.setAdapterBookVM(addressBookVM);
            this.binding.setIAddressBook(iAddressBook);
           // this.binding.setIAddressBook(iAddressBook);
            binding.executePendingBindings();

        }

        public MainAdapterBinding getBinding()
        {
            return binding;
        }

        @Override
        public void onClick(View v) {

            Log.e(TAG, "onClick: " + getAdapterPosition()+"\nId==>"+arrayList.get(getAdapterPosition()).id);

        }
    }
    public interface OnClickListener{
        void onNoteClick(int position);
    }
}
