package com.ninositsolution.inveleapp.categories;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;


import com.ninositsolution.inveleapp.R;
import com.ninositsolution.inveleapp.address_book.AddressBookAdapter;
import com.ninositsolution.inveleapp.address_book.AddressBookVM;
import com.ninositsolution.inveleapp.address_book.IAddressBook;
import com.ninositsolution.inveleapp.databinding.CategoryAdapterBinding;
import com.ninositsolution.inveleapp.databinding.MainAdapterBinding;
import com.ninositsolution.inveleapp.utils.Constants;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.MainViewHolder> {

    public Context context;
    private CategoryVM categoryVM;
    private List<CategoryVM> arrayList;
    private LayoutInflater layoutInflater;
    ICategory iCategory;
    String selected_position="";
    int  selectedPosition=0;

    public static final String TAG = CategoryAdapter.class.getSimpleName();

    public interface ClickEvent{
        void setClickEventItem(int position,String menu_id,String name,String banner);

    }
    ClickEvent clickEvent;

    public void setClikEvent(ClickEvent clikEvent){
        this.clickEvent = clikEvent;
    }


    public CategoryAdapter(Context context, List<CategoryVM> arrayList,String selected_position) {
        this.context = context;
        this.arrayList = arrayList;
        Constants.category_position = selected_position;
    }
    @NonNull
    @Override
    public CategoryAdapter.MainViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        if (layoutInflater == null)
        {
            layoutInflater = LayoutInflater.from(viewGroup.getContext());
        }
        CategoryAdapterBinding binding = DataBindingUtil.inflate(layoutInflater, R.layout.parent_categories_adapter,viewGroup,false);

        return new MainViewHolder(binding,iCategory);
    }

    @Override
    public void onBindViewHolder(@NonNull final CategoryAdapter.MainViewHolder mainViewHolder, final int position) {

        categoryVM = arrayList.get(position);
        mainViewHolder.binding.setCategory(categoryVM);
        mainViewHolder.binding.setICategory(iCategory);

        Log.e(TAG, "LIST_SIZE==>" + arrayList.size());


        mainViewHolder.bind(categoryVM, iCategory);
        if (!Constants.category_position.equalsIgnoreCase("")){


            if (Integer.parseInt(Constants.category_position) == position) {
                mainViewHolder.binding.mensCategoriesLayout.setBackgroundColor(context.getResources().getColor(R.color.white));
                mainViewHolder.binding.mensCategoriesText.setTextColor(context.getResources().getColor(R.color.colorPrimary));
            } else {
                mainViewHolder.binding.mensCategoriesLayout.setBackgroundColor(context.getResources().getColor(R.color.grayWhite));
                mainViewHolder.binding.mensCategoriesText.setTextColor(context.getResources().getColor(R.color.text_color));

            }
    }
        mainViewHolder.binding.mensCategoriesLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedPosition = position;
                Constants.category_position = String.valueOf(position);
                Log.e(TAG,"banner==>"+arrayList.get(position).banner_image.get()+"menu_id==>"+arrayList.get(position).menu_id.get());

                mainViewHolder.binding.mensCategoriesLayout.setBackgroundColor(context.getResources().getColor(R.color.white));
                mainViewHolder.binding.mensCategoriesText.setTextColor(context.getResources().getColor(R.color.colorPrimary));
                Constants.select_menu_id = arrayList.get(position).menu_id.get();
                clickEvent.setClickEventItem(position,arrayList.get(position).menu_id.get(),"mens_fashion", String.valueOf(arrayList.get(position).banner_image.get()));
                notifyDataSetChanged();

            }
        });

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class MainViewHolder extends RecyclerView.ViewHolder {

        private CategoryAdapterBinding binding;
        public ICategory iCategory;


        public MainViewHolder(@NonNull CategoryAdapterBinding binding,ICategory iCategory) {
            super(binding.getRoot());
            this.binding = binding;
            this.iCategory = iCategory;
        }
        public void bind(final CategoryVM categoryVM, ICategory iCategory)
        {
            this.binding.setCategory(categoryVM);
            this.binding.setICategory(iCategory);
            // this.binding.setIAddressBook(iAddressBook);
            binding.executePendingBindings();

        }

        public CategoryAdapterBinding getBinding()
        {
            return binding;
        }

    }
}
