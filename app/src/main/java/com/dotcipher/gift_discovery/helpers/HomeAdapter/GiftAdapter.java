package com.dotcipher.gift_discovery.helpers.HomeAdapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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
import com.dotcipher.gift_discovery.model.GiftClass;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GiftAdapter extends RecyclerView.Adapter<GiftAdapter.GiftViewHolder> {
    List<GiftClass> Gifts;

    public GiftAdapter(List<GiftClass> gifts) {
        Gifts = gifts;
    }

    @NonNull
    @Override
    public GiftAdapter.GiftViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.loved_gift_card_design,parent,false);
        GiftAdapter.GiftViewHolder GiftViewHolder = new GiftAdapter.GiftViewHolder(view);

        return GiftViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull GiftAdapter.GiftViewHolder holder, int position) {
        GiftClass Gift = Gifts.get(position);
        if (Gift.getImage() != null) {
            byte[] imageBytes = Gift.getImage();
            Bitmap bitmap = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);
            holder.image.setImageBitmap(bitmap);
        } else {
            holder.image.setImageResource(R.drawable.gift_icon);
        }
        holder.title.setText(Gift.getTitle());
        holder.description.setText(Gift.getDescription());
    }

    @Override
    public int getItemCount() {
        return Gifts.size();
    }
    public static class GiftViewHolder extends RecyclerView.ViewHolder {

        ImageView image;
        TextView title;
        TextView description;
        public GiftViewHolder(@NonNull View itemView) {
            super(itemView);
            // Hooks
            image = itemView.findViewById(R.id.gift_card_image);
            title = itemView.findViewById(R.id.gift_card_title);
            description = itemView.findViewById(R.id.gift_card_description);

        }
    }
}