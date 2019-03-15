package com.ninositsolution.inveleapp.categories.fragments.fragment_other_categories;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ninositsolution.inveleapp.R;

import java.util.List;

/**
 * Created by Parthasarathy D on 1/21/2019.
 * Ninos IT Solution Pvt Ltd
 * ben@ninositsolution.com
 */
public class ExpandableCategoriesAdapter extends RecyclerView.Adapter<ExpandableCategoriesAdapter.ExpandableCategoriesViewHolder> {

    private List<ExpandableCategoriesPOJO> expandableCategoriesPOJOList;
    private Context context;
    private static int currentPosition = -1;
    private static int previousPosition = -1;

    public ExpandableCategoriesAdapter(List<ExpandableCategoriesPOJO> expandableCategoriesPOJOList, Context context) {
        this.expandableCategoriesPOJOList = expandableCategoriesPOJOList;
        this.context = context;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ExpandableCategoriesViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.adapter_category_recycler, viewGroup, false);
        return new ExpandableCategoriesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ExpandableCategoriesViewHolder expandableCategoriesViewHolder, final int i) {


        if (expandableCategoriesViewHolder.childlayout.getVisibility() == View.VISIBLE)
        {
            Animation slideUp = AnimationUtils.loadAnimation(context, R.anim.slide_up);
            expandableCategoriesViewHolder.childlayout.startAnimation(slideUp);
            expandableCategoriesViewHolder.downArrow.setVisibility(View.VISIBLE);
            expandableCategoriesViewHolder.upArrow.setVisibility(View.GONE);
            expandableCategoriesViewHolder.childlayout.setVisibility(View.GONE);
        }

        expandableCategoriesViewHolder.categoriesHeader.setText("Women's Fashion");

        expandableCategoriesViewHolder.categoriesImg1.setImageResource(R.drawable.img1);
        expandableCategoriesViewHolder.categoriesImg2.setImageResource(R.drawable.img2);
        expandableCategoriesViewHolder.categoriesImg3.setImageResource(R.drawable.img3);
        expandableCategoriesViewHolder.categoriesImg4.setImageResource(R.drawable.img4);
        expandableCategoriesViewHolder.categoriesImg5.setImageResource(R.drawable.img1);
        expandableCategoriesViewHolder.categoriesImg6.setImageResource(R.drawable.img2);

        if (currentPosition == i)
        {
            if (expandableCategoriesViewHolder.childlayout.getVisibility() == View.VISIBLE)
            {
                Animation slideUp = AnimationUtils.loadAnimation(context, R.anim.slide_up);
                expandableCategoriesViewHolder.childlayout.startAnimation(slideUp);
                expandableCategoriesViewHolder.downArrow.setVisibility(View.VISIBLE);
                expandableCategoriesViewHolder.upArrow.setVisibility(View.GONE);
                expandableCategoriesViewHolder.childlayout.setVisibility(View.GONE);
            }

            else {
                Animation slideDown = AnimationUtils.loadAnimation(context, R.anim.slide_down);
                expandableCategoriesViewHolder.childlayout.setVisibility(View.VISIBLE);
                expandableCategoriesViewHolder.childlayout.startAnimation(slideDown);
                expandableCategoriesViewHolder.downArrow.setVisibility(View.GONE);
                expandableCategoriesViewHolder.upArrow.setVisibility(View.VISIBLE);
                expandableCategoriesViewHolder.childlayout.requestFocus();

            }

        }

        expandableCategoriesViewHolder.categoriesHeader.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentPosition = i;
                notifyDataSetChanged();
            }
        });

    }

    @Override
    public int getItemCount() {
        return 5;
    }

    public class ExpandableCategoriesViewHolder extends RecyclerView.ViewHolder {

            public TextView categoriesHeader;
            public ImageView categoriesImg1, categoriesImg2, categoriesImg3, categoriesImg4, categoriesImg5, categoriesImg6;
            public LinearLayout childlayout;
            public ImageView downArrow, upArrow;


        public ExpandableCategoriesViewHolder(@NonNull View itemView) {
            super(itemView);

            categoriesHeader = itemView.findViewById(R.id.categories_header);

            categoriesImg1 = itemView.findViewById(R.id.categories_img1);
            categoriesImg2 = itemView.findViewById(R.id.categories_img2);
            categoriesImg3 = itemView.findViewById(R.id.categories_img3);
            categoriesImg4 = itemView.findViewById(R.id.categories_img4);
            categoriesImg5 = itemView.findViewById(R.id.categories_img5);
            categoriesImg6 = itemView.findViewById(R.id.categories_img6);

            childlayout = itemView.findViewById(R.id.categories_childlayout);

            downArrow = itemView.findViewById(R.id.down_arrow);
            upArrow = itemView.findViewById(R.id.up_arrow);
        }
    }
}
