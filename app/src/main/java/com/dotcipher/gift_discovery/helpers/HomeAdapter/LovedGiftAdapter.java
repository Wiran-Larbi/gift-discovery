package com.dotcipher.gift_discovery.helpers.HomeAdapter;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dotcipher.gift_discovery.R;
import com.dotcipher.gift_discovery.utils.ImageUtils;

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

        if (lovedGiftHelper.getImage() != null){
            // Converting Byte array to Bitmap
            byte[] imageBytes = lovedGiftHelper.getImage();
            Bitmap imageBitmap = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);

            // Set Bitmap to ImageView
            if (imageBitmap != null){
                holder.image.setImageBitmap(imageBitmap);
            } else {
                holder.image.setImageResource(R.drawable.anniversary_foreground);
            }
            //Log.d("BITMAP", imageBitmap.toString());
        }
        //ImageUtils imageUtils = new ImageUtils();
        //holder.image.setImageResource(imageUtils.bitmapToInt(imageUtils.getBitmapFromByteArray(lovedGiftHelper.getImage())));
        holder.title.setText(lovedGiftHelper.getTitle());
        holder.description.setText(lovedGiftHelper.getDescription());

        /*
          OccasionHelper occasionHelper = occasionHelpers.get(position);
        if (occasionHelper.getImage() != null) {
            // Convert byte array to Bitmap
            byte[] imageBytes = occasionHelper.getImage();
            Bitmap bitmap = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);

        // Set Bitmap to ImageView
        holder.image.setImageBitmap(bitmap);
        } else {
            holder.image.setImageResource(R.drawable.holidays);
        }
        holder.title.setText(occasionHelper.getName());
        int randomColor = holder.randomCardBackgroundColor(context);
        holder.cardLayout.setCardBackgroundColor(randomColor);
         */
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
