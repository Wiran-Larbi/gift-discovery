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
import androidx.recyclerview.widget.RecyclerView;

import com.dotcipher.gift_discovery.R;
import com.dotcipher.gift_discovery.model.GiftClass;

import java.util.List;

public class SearchedGiftsAdapter extends RecyclerView.Adapter<SearchedGiftsAdapter.SearchedGiftsViewHolder> {

    private final List<GiftClass> gifts;
    private final Context context;

    public SearchedGiftsAdapter(Context context, List<GiftClass> gifts) {
        this.context = context;
        this.gifts = gifts;
    }

    @NonNull
    @Override
    public SearchedGiftsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.gift_card_design, parent, false);
        return new SearchedGiftsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchedGiftsViewHolder holder, int position) {
        GiftClass gift = gifts.get(position);

        if (gift.getImage() != null) {
            Bitmap bitmap = BitmapFactory.decodeByteArray(gift.getImage(), 0, gift.getImage().length);
            holder.giftImage.setImageBitmap(bitmap);
        } else {
            holder.giftImage.setImageResource(R.drawable.gift_icon); // Default image if no image is provided
        }

        holder.giftTitle.setText(gift.getTitle());
        holder.giftDescription.setText(gift.getDescription());
    }

    @Override
    public int getItemCount() {
        return gifts.size();
    }

    public static class SearchedGiftsViewHolder extends RecyclerView.ViewHolder {

        ImageView giftImage;
        TextView giftTitle;
        TextView giftDescription;

        public SearchedGiftsViewHolder(@NonNull View itemView) {
            super(itemView);

            giftImage = itemView.findViewById(R.id.gift_card_image);
            giftTitle = itemView.findViewById(R.id.gift_card_title);
            giftDescription = itemView.findViewById(R.id.gift_card_description);
        }
    }
}
