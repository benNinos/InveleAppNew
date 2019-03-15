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
    private  OnClickListener onClickListener;
    AddressBookClick addressBookClick;
    private RadioButton lastCheckedRadioGroup = null;
    boolean radio_checked=false;
    public static final String TAG = AddressBookAdapter.class.getSimpleName();

    public interface ClickEvent{
        void setClickEventItem(int position,String id,String user_id);
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

        return new MainViewHolder(binding,onClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull final AddressBookAdapter.MainViewHolder mainViewHolder, final int position) {

        final AddressBookVM addressBookVM = arrayList.get(position);
        mainViewHolder.binding.setAdapterBookVM(addressBookVM);

        Log.e(TAG,"LIST_SIZE==>"+arrayList.size());

        mainViewHolder.bind(addressBookVM,addressBookClick);


        if(addressBookVM.ship_billAddress.get().equalsIgnoreCase("1")){
            mainViewHolder.binding.shippingButton.setVisibility(View.VISIBLE);
            mainViewHolder.binding.shippingButton.setText("Shipping Address");
        }else if(addressBookVM.billingAddress.get().equalsIgnoreCase("1")){

            mainViewHolder.binding.shippingButton.setVisibility(View.VISIBLE);
            mainViewHolder.binding.shippingButton.setText("Billing Address");

        }else {
            mainViewHolder.binding.shippingButton.setVisibility(View.GONE);
            mainViewHolder.binding.addressDelete.setVisibility(View.VISIBLE);
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

                context.startActivity(new Intent(context, EditAddressActivity.class));

            }
        });



    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }




    public class MainViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private MainAdapterBinding binding;
        public OnClickListener onClickListener;


        public MainViewHolder(@NonNull final MainAdapterBinding binding,OnClickListener onClickListener) {
            super(binding.getRoot());
            this.binding = binding;
            this.onClickListener = onClickListener;
           /* binding.radioGroup.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AddressBookVM addressBookVM = binding.getAdapterBookVM();
                    Log.e(TAG,"clciked_position==>"+addressBookVM.id+"\nid-==>"+arrayList.get(getAdapterPosition()).id);
                }
            });*/



        }

        public void bind(final AddressBookVM  addressBookVM,AddressBookClick addressBookClick)
        {
            this.binding.setAdapterBookVM(addressBookVM);
           // this.binding.setIAddressBook(addressBookVM);
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
            onClickListener.onNoteClick(getAdapterPosition());

        }
    }
    public interface OnClickListener{
        void onNoteClick(int position);
    }
}
