package com.ninositsolution.inveleapp.fitme;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Toast;

import com.nhaarman.supertooltips.ToolTip;
import com.nhaarman.supertooltips.ToolTipRelativeLayout;
import com.nhaarman.supertooltips.ToolTipView;
import com.ninositsolution.inveleapp.R;
import com.ninositsolution.inveleapp.databinding.FitmeRecyclerAdapterBinding;

import static com.ninositsolution.inveleapp.utils.Constants.FITME_MEN;
import static com.ninositsolution.inveleapp.utils.Constants.FITME_WOMEN;

public class FitmeRecyclerAdapter extends RecyclerView.Adapter<FitmeRecyclerAdapter.MyViewHolder>{
    private Context context;
    private FitmeVM fitmeVM;
    private LayoutInflater layoutInflater;
    private int gender;
    private ToolTip toolTip;
    private ToolTipView toolTipView;
    private FitmeDetailsListener fitmeDetailsListener;


    public FitmeRecyclerAdapter(Context context, FitmeVM fitmeVM, int gender, FitmeDetailsListener fitmeDetailsListener)
    {
        this.fitmeVM = fitmeVM;
        this.context = context;
        this.gender = gender;
        this.fitmeDetailsListener = fitmeDetailsListener;
    }



    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i)
    {
        if (layoutInflater == null)
        {
            layoutInflater = LayoutInflater.from(viewGroup.getContext());
        }

FitmeRecyclerAdapterBinding binding = DataBindingUtil.inflate(layoutInflater, R.layout.fitme_recycler_adapter,viewGroup,false);


        return new MyViewHolder(binding, fitmeDetailsListener);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {

        if (gender == FITME_MEN)
        {
            FitmeVM fitmeVM = new FitmeVM(this.fitmeVM.men.get().get(i));

            myViewHolder.setBinding(fitmeVM);

        }

        if (gender == FITME_WOMEN)
        {
            FitmeVM fitmeVM1 = new FitmeVM(this.fitmeVM.women.get().get(i));

            myViewHolder.setBinding(fitmeVM1);
        }
    }

    @Override
    public int getItemCount() {

        if (gender == FITME_MEN)
        {
            return fitmeVM.men.get().size();
        }

        if (gender == FITME_WOMEN)
        {
            return fitmeVM.women.get().size();
        }

        return 0;
    }


    public class MyViewHolder extends RecyclerView.ViewHolder
    {
        FitmeRecyclerAdapterBinding binding;

        private FitmeDetailsListener fitmeDetailsListener;


        public MyViewHolder(@NonNull FitmeRecyclerAdapterBinding binding, FitmeDetailsListener fitmeDetailsListener) {
            super(binding.getRoot());
            this.binding = binding;
            this.fitmeDetailsListener = fitmeDetailsListener;

        }


        public void setBinding(final FitmeVM fitmeVM)
        {
            binding.setAdapterFitme(fitmeVM);
            binding.executePendingBindings();

            fitmeVM.currentSize.set("0");

            binding.setIadapterFitme(new FitmeClickListener() {
                @Override
                public void onIncreseClicked() {

                    try {
                        int i = Integer.parseInt(fitmeVM.currentSize.get());
                        i++;
                        fitmeVM.currentSize.set(String.valueOf(i));

                        fitmeDetailsListener.onIncreasedSizeClicked(fitmeVM.men.get().get(getAdapterPosition()).fitme_label_id, fitmeVM.currentSize.get());

                    } catch (Exception e) {
                        //Log.i(TAG, "Integer exception");
                    }
                }

                @Override
                public void onDecreasedClicked() {

                    if (fitmeVM.currentSize.get().equalsIgnoreCase("0"))
                    {
                        Toast.makeText(context, "Cannot be decreased below zero", Toast.LENGTH_SHORT).show();
                    } else

                        {
                        try
                        {
                            int i = Integer.parseInt(fitmeVM.currentSize.get());
                            i--;
                            fitmeVM.currentSize.set(String.valueOf(i));

                            fitmeDetailsListener.onDecreasedSizeClicked(getAdapterPosition(), fitmeVM.currentSize.get());


                        } catch (Exception e) {
                            // Log.i(TAG, "Integer exception");
                        }

                    }

                }

                @Override
                public void onQuestionImageClicked() {

                    try {
                        toolTipView.remove();
                    } catch (NullPointerException e)
                    {
                        e.printStackTrace();
                    }

                    toolTip = new ToolTip().withContentView(LayoutInflater.from(context).inflate(R.layout.fitme_adapter_popup,null ))
                            .withShadow()
                            .withAnimationType(ToolTip.AnimationType.FROM_TOP);

                    ToolTipRelativeLayout toolTipRelativeLayout = itemView.findViewById(R.id.fitme_tooltip_layout);

                    toolTipView = toolTipRelativeLayout.showToolTipForView(toolTip, itemView.findViewById(R.id.questionLayout));
                }
            });
        }

        public FitmeRecyclerAdapterBinding getBinding()
        {
            return binding;
        }
    }

    public interface FitmeDetailsListener
    {
        void onDecreasedSizeClicked(int key, String value);
        void onIncreasedSizeClicked(int key, String value);
        void onQuestionDescClicked();
    }
}
