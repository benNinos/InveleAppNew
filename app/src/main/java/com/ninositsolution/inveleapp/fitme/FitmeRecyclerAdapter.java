package com.ninositsolution.inveleapp.fitme;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
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
    private static final String TAG = "FitmeRecyclerAdapter";
    private Context context;
    private FitmeVM fitmeVMGlobal;
    private LayoutInflater layoutInflater;
    private int gender;
    private ToolTip toolTip;
    private ToolTipView toolTipView;
    private FitmeDetailsListener fitmeDetailsListener;


    public FitmeRecyclerAdapter(Context context, FitmeVM fitmeVM, int gender, FitmeDetailsListener fitmeDetailsListener)
    {
        this.fitmeVMGlobal = fitmeVM;
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
            FitmeVM fitmeVM = new FitmeVM(this.fitmeVMGlobal.men.get().get(i));

            myViewHolder.setBinding(fitmeVM);

        }

        if (gender == FITME_WOMEN)
        {
            FitmeVM fitmeVM1 = new FitmeVM(this.fitmeVMGlobal.women.get().get(i));

            myViewHolder.setBinding(fitmeVM1);
        }
    }

    @Override
    public int getItemCount() {

        if (gender == FITME_MEN)
        {
            return fitmeVMGlobal.men.get().size();
        }

        if (gender == FITME_WOMEN)
        {
            return fitmeVMGlobal.women.get().size();
        }

        return 0;
    }


    public class MyViewHolder extends RecyclerView.ViewHolder
    {
        FitmeRecyclerAdapterBinding binding;

        private FitmeDetailsListener fitmeDetailsListener;


        public MyViewHolder(@NonNull FitmeRecyclerAdapterBinding binding, final FitmeDetailsListener fitmeDetailsListener) {
            super(binding.getRoot());
            this.binding = binding;
            this.fitmeDetailsListener = fitmeDetailsListener;

            binding.sizeValueEditText.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }

                @Override
                public void afterTextChanged(Editable s) {

                    if (gender == FITME_MEN)
                    {
                       fitmeDetailsListener.onValueEdited(fitmeVMGlobal.men.get().get(getAdapterPosition()).fitme_label_id, s.toString());
                    }

                    if (gender == FITME_WOMEN)
                    {
                        fitmeDetailsListener.onValueEdited(fitmeVMGlobal.women.get().get(getAdapterPosition()).fitme_label_id, s.toString());
                    }


                }
            });

        }


        public void setBinding(final FitmeVM fitmeVM)
        {
            binding.setAdapterFitme(fitmeVM);
            binding.executePendingBindings();

            binding.setIadapterFitme(new FitmeClickListener() {
                @Override
                public void onIncreseClicked() {

                    try {
                        float i = Float.parseFloat(fitmeVM.currentSize.get());
                        i++;
                        fitmeVM.currentSize.set(String.valueOf(i));

                        if (gender == FITME_MEN)
                        {
                            fitmeDetailsListener.onIncreasedSizeClicked(fitmeVMGlobal.men.get().get(getAdapterPosition()).fitme_label_id, fitmeVM.currentSize.get());
                        }

                        if (gender == FITME_WOMEN)
                        {
                            fitmeDetailsListener.onIncreasedSizeClicked(fitmeVMGlobal.women.get().get(getAdapterPosition()).fitme_label_id, fitmeVM.currentSize.get());
                        }


                    } catch (Exception e) {
                        Log.e(TAG, "Integer exception : "+e);
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
                            float i = Float.parseFloat(fitmeVM.currentSize.get());
                            i--;
                            fitmeVM.currentSize.set(String.valueOf(i));

                            if (gender == FITME_MEN)
                            {
                                fitmeDetailsListener.onDecreasedSizeClicked(fitmeVMGlobal.men.get().get(getAdapterPosition()).fitme_label_id, fitmeVM.currentSize.get());
                            }

                            if (gender == FITME_WOMEN)
                            {
                                fitmeDetailsListener.onDecreasedSizeClicked(fitmeVMGlobal.women.get().get(getAdapterPosition()).fitme_label_id, fitmeVM.currentSize.get());
                            }

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
        void onValueEdited(int key, String value);
    }
}
