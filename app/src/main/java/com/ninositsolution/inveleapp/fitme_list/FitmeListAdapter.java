package com.ninositsolution.inveleapp.fitme_list;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;

import com.ninositsolution.inveleapp.R;
import com.ninositsolution.inveleapp.databinding.AdapterFitmeListBinding;
import com.ninositsolution.inveleapp.pojo.FitmeLists;

import java.util.List;

public class FitmeListAdapter extends RecyclerView.Adapter<FitmeListAdapter.FitmeListViewHolder> {

    private List<FitmeLists> fitmeLists;
    private Context context;
    private IFitmeList iFitmeList;
    private LayoutInflater layoutInflater;
    private int selectedPosition = -1;

    public FitmeListAdapter(List<FitmeLists> fitmeLists, Context context, IFitmeList iFitmeList) {
        this.fitmeLists = fitmeLists;
        this.context = context;
        this.iFitmeList = iFitmeList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public FitmeListViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        if (layoutInflater == null)
        {
            layoutInflater = LayoutInflater.from(context);
        }

        AdapterFitmeListBinding binding = DataBindingUtil.inflate(layoutInflater, R.layout.adapter_fitme_list, viewGroup, false);

        return new FitmeListViewHolder(binding, iFitmeList);
    }

    @Override
    public void onBindViewHolder(@NonNull FitmeListViewHolder fitmeListViewHolder, int i) {

        if (i == fitmeLists.size())
        {
            fitmeListViewHolder.binding.fitmeListsLayout.setVisibility(View.GONE);
            fitmeListViewHolder.binding.addLayout.setVisibility(View.VISIBLE);
        } else
        {
            fitmeListViewHolder.binding.addLayout.setVisibility(View.GONE);
            fitmeListViewHolder.setBinding(new FitmeListVM(fitmeLists.get(i)));
        }

        fitmeListViewHolder.binding.fitmeRadioBtn.setChecked(selectedPosition == i);

    }

    @Override
    public int getItemCount() {
        return fitmeLists.size()+1;
    }

    public class FitmeListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private AdapterFitmeListBinding binding;
        private IFitmeList iFitmeList;

        public FitmeListViewHolder(@NonNull AdapterFitmeListBinding binding, final IFitmeList iFitmeList) {
            super(binding.getRoot());
            this.binding = binding;
            this.iFitmeList = iFitmeList;

            binding.viewAll.setOnClickListener(this);
            binding.edit.setOnClickListener(this);
            binding.delete.setOnClickListener(this);
            binding.addLayout.setOnClickListener(this);

            binding.fitmeRadioBtn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                    selectedPosition = getAdapterPosition();
                    iFitmeList.onCheckboxClicked(fitmeLists.get(getAdapterPosition()).getMeasureId());
                }
            });

        }

        public void setBinding(FitmeListVM fitmeListVM)
        {
            binding.setAdapterFitmeList(fitmeListVM);
            binding.executePendingBindings();
        }

        @Override
        public void onClick(View v) {

            switch (v.getId())
            {
                case R.id.viewAll:
                    iFitmeList.onViewAllClicked(fitmeLists.get(getAdapterPosition()));
                    break;

                case R.id.edit:
                    iFitmeList.onEditClicked(fitmeLists.get(getAdapterPosition()).getMeasureId());
                    break;

                case R.id.delete:
                    iFitmeList.onDeleteClicked(fitmeLists.get(getAdapterPosition()).getMeasureId());
                    break;

                case R.id.addLayout:
                    iFitmeList.onAddLayoutClicked();
                    break;
            }
        }
    }
}
