package com.ninositsolution.inveleapp.address_book;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.ninositsolution.inveleapp.R;
import com.ninositsolution.inveleapp.databinding.MainAdapterBinding;

import java.util.ArrayList;
import java.util.List;

public class AddressBookAdapter extends RecyclerView.Adapter<AddressBookAdapter.MainViewHolder>{

    public Context context;
    private List<AddressBookVM> arrayList;
    private LayoutInflater layoutInflater;
    public static final String TAG = AddressBookAdapter.class.getSimpleName();

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

        return new MainViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull AddressBookAdapter.MainViewHolder mainViewHolder, int position) {

        AddressBookVM addressBookVM = arrayList.get(position);
        Log.e(TAG,"LIST_SIZE==>"+arrayList.get(position));
        mainViewHolder.bind(addressBookVM);

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class MainViewHolder extends RecyclerView.ViewHolder{

        private MainAdapterBinding binding;


        public MainViewHolder(@NonNull MainAdapterBinding binding) {
            super(binding.getRoot());
            this.binding = binding;

        }

        public void bind(AddressBookVM  addressBookVM)
        {
            this.binding.setAdapterBookVM(addressBookVM);
            binding.executePendingBindings();
        }

        public MainAdapterBinding getBinding()
        {
            return binding;
        }
    }
}
