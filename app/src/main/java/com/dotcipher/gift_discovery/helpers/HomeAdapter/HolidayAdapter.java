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

public class HolidayAdapter extends RecyclerView.Adapter<HolidayAdapter.HolidayViewHolder>{
    private Context context;
    ArrayList<HolidayHelper> holidayHelpers;

    public HolidayAdapter(Context context,ArrayList<HolidayHelper> holidayHelpers){
        this.context = context;
        this.holidayHelpers = holidayHelpers;
    }

    @NonNull
    @Override
    public HolidayAdapter.HolidayViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.holiday_card_design,parent,false);
        HolidayAdapter.HolidayViewHolder holidayViewHolder = new HolidayAdapter.HolidayViewHolder(view);

        return holidayViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull HolidayAdapter.HolidayViewHolder holder, int position) {
        HolidayHelper holidayHelperClass = holidayHelpers.get(position);

        int randomColor = holder.randomCardBackgroundColor(this.context);
        holder.image.setImageResource(holidayHelperClass.getImage());
        holder.title.setText(holidayHelperClass.getTitle());
        holder.cardLayout.setCardBackgroundColor(randomColor);
    }

    @Override
    public int getItemCount() {
        return holidayHelpers.size();
    }

    public static class HolidayViewHolder extends RecyclerView.ViewHolder {

        CardView cardLayout;
        ImageView image;
        TextView title;

        Random randomInt;
        public HolidayViewHolder(@NonNull View itemView) {
            super(itemView);
            // Hooks
            image = itemView.findViewById(R.id.holidayImage);
            title = itemView.findViewById(R.id.holidayTitle);
            cardLayout = itemView.findViewById(R.id.holidayCardLayout);

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
