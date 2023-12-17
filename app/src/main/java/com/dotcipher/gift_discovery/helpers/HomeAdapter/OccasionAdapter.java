package com.dotcipher.gift_discovery.helpers.HomeAdapter;

import android.content.Context;
import android.content.Intent;
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

import com.dotcipher.gift_discovery.OccasionActivity;

import java.util.List;
import java.util.Random;

public class OccasionAdapter extends RecyclerView.Adapter<OccasionAdapter.OccasionViewHolder> {
    private final Context context;
    private final List<OccasionHelper> occasionHelpers;
    private OccasionClickListener listener;

    public OccasionAdapter(Context context, List<OccasionHelper> occasionHelpers, OccasionClickListener listener) {
        this.context = context;
        this.occasionHelpers = occasionHelpers;
        this.listener = listener;
    }

    public interface OccasionClickListener {
        void onOccasionClick(OccasionHelper occasion);
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
            byte[] imageBytes = occasionHelper.getImage();

            Bitmap bitmap = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);
            holder.image.setImageBitmap(bitmap);
        } else {
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

    public class OccasionViewHolder extends RecyclerView.ViewHolder {
        CardView cardLayout;
        ImageView image;
        TextView title;
        Random randomInt;

        public OccasionViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.holidayImage);
            title = itemView.findViewById(R.id.holidayTitle);
            cardLayout = itemView.findViewById(R.id.holidayCardLayout);
            randomInt = new Random();

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        listener.onOccasionClick(occasionHelpers.get(position));
                    }
                }
            });
        }

        public int randomCardBackgroundColor(Context context) {
            int value = randomInt.nextInt(5) + 1;
            int colorId = R.color.card_5;
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
