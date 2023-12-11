package com.dotcipher.gift_discovery.helpers.HomeAdapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dotcipher.gift_discovery.R;

import java.util.ArrayList;

public class LovedGiftAdapter extends RecyclerView.Adapter<LovedGiftAdapter.LovedGiftViewHolder> {

    ArrayList<LovedGiftHelper> lovedGiftHelpers;

    public LovedGiftAdapter(ArrayList<LovedGiftHelper> lovedGiftHelpers) {
        this.lovedGiftHelpers = lovedGiftHelpers;
    }

    @NonNull
    @Override
    public LovedGiftViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.loved_gift_card_design,parent,false);
        LovedGiftViewHolder lovedGiftViewHolder = new LovedGiftViewHolder(view);

        return lovedGiftViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull LovedGiftViewHolder holder, int position) {
        LovedGiftHelper lovedGiftHelper = lovedGiftHelpers.get(position);
        holder.image.setImageResource(lovedGiftHelper.getImage());
        holder.title.setText(lovedGiftHelper.getTitle());
        holder.description.setText(lovedGiftHelper.getDescription());
    }

    @Override
    public int getItemCount() {
        return lovedGiftHelpers.size();
    }

    public static class LovedGiftViewHolder extends RecyclerView.ViewHolder {

        ImageView image;
        TextView title;
        TextView description;
        public LovedGiftViewHolder(@NonNull View itemView) {
            super(itemView);
            // Hooks
            image = itemView.findViewById(R.id.gift_card_image);
            title = itemView.findViewById(R.id.gift_card_title);
            description = itemView.findViewById(R.id.gift_card_description);

        }
    }



}
