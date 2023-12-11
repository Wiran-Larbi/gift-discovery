package com.dotcipher.gift_discovery.helpers.HomeAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.dotcipher.gift_discovery.R;

import java.util.ArrayList;
import java.util.Random;

public class CategoryGiftAdapter extends RecyclerView.Adapter<CategoryGiftAdapter.CategoryGiftViewHolder> {
    ArrayList<CategoryGiftHelper> categoryGiftHelpers;
    Context context;

    public CategoryGiftAdapter(Context context,ArrayList<CategoryGiftHelper> categoryGiftHelpers){
        this.categoryGiftHelpers = categoryGiftHelpers;
        this.context = context;
    }

    @NonNull
    @Override
    public CategoryGiftViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.gift_category_card_design,parent,false);
        CategoryGiftViewHolder categoryGiftViewHolder = new CategoryGiftViewHolder(view);

        return categoryGiftViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryGiftViewHolder holder, int position) {
        CategoryGiftHelper categoryGiftHelperClass = categoryGiftHelpers.get(position);
        int randomColor = holder.randomCardBackgroundColor(this.context);

        holder.image.setImageResource(categoryGiftHelperClass.getImage());
        holder.title.setText(categoryGiftHelperClass.getTitle());
        holder.cardLayout.setCardBackgroundColor(randomColor);
    }

    @Override
    public int getItemCount() {
        return categoryGiftHelpers.size();
    }


    public static class CategoryGiftViewHolder extends RecyclerView.ViewHolder {

        CardView cardLayout;
        ImageView image;
        TextView title;
        Random randomInt;
        public CategoryGiftViewHolder(@NonNull View itemView) {
            super(itemView);
            // Hooks
            image = itemView.findViewById(R.id.categoryImage);
            title = itemView.findViewById(R.id.categoryTitle);
            cardLayout = itemView.findViewById(R.id.categoryCardLayout);
            this.randomInt = new Random();
        }

        public int randomCardBackgroundColor(Context context){

            int value = this.randomInt.nextInt(12) + 1;
            switch (value){
                case 1:
                    return ContextCompat.getColor(context, R.color.card_1);
                case 2:
                    return ContextCompat.getColor(context, R.color.card_2);
                case 3:
                    return ContextCompat.getColor(context, R.color.card_3);
                case 4:
                    return ContextCompat.getColor(context, R.color.card_4);
                case 5:
                    return ContextCompat.getColor(context, R.color.card_5);
            }
            return ContextCompat.getColor(context, R.color.card_5);
        }

    }
}
