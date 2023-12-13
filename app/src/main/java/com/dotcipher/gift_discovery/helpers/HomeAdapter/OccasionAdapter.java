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

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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

public class OccasionAdapter extends RecyclerView.Adapter<OccasionAdapter.OccasionViewHolder> {
    private final Context context;
    private final List<OccasionHelper> occasionHelpers;

    public OccasionAdapter(Context context, List<OccasionHelper> occasionHelpers) {
        this.context = context;
        this.occasionHelpers = occasionHelpers;
    }

    @NonNull
    @Override
    public OccasionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.holiday_card_design, parent, false);
        return new OccasionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OccasionViewHolder holder, int position) {
        OccasionHelper occasionHelper = occasionHelpers.get(position);
        if (occasionHelper.getImage() != null) {
            // Convert byte array to Bitmap
            byte[] imageBytes = occasionHelper.getImage();
            Bitmap bitmap = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);

        // Set Bitmap to ImageView
        holder.image.setImageBitmap(bitmap);
        }
        else {
            holder.image.setImageResource(R.drawable.holidays);
        }
        holder.title.setText(occasionHelper.getName());
        int randomColor = holder.randomCardBackgroundColor(context);
        holder.cardLayout.setCardBackgroundColor(randomColor);
    }


    @Override
    public int getItemCount() {
        return occasionHelpers.size();
    }

    public static class OccasionViewHolder extends RecyclerView.ViewHolder {
        CardView cardLayout;
        ImageView image;
        TextView title;
        Random randomInt;

        public OccasionViewHolder(@NonNull View itemView) {
            super(itemView);
            // Hooks
            image = itemView.findViewById(R.id.holidayImage);
            title = itemView.findViewById(R.id.holidayTitle);
            cardLayout = itemView.findViewById(R.id.holidayCardLayout);
            randomInt = new Random();
        }

        public int randomCardBackgroundColor(Context context) {
            int value = randomInt.nextInt(5) + 1; // Simplified to use only 5 colors as an example
            int colorId = R.color.card_5; // Default color

            switch (value) {
                case 1:
                    colorId = R.color.card_1;
                    break;
                case 2:
                    colorId = R.color.card_2;
                    break;
                case 3:
                    colorId = R.color.card_3;
                    break;
                case 4:
                    colorId = R.color.card_4;
                    break;
                case 5:
                    colorId = R.color.card_5;
                    break;
            }
            return ContextCompat.getColor(context, colorId);
        }
    }
}
