package com.dotcipher.gift_discovery.helpers.HomeAdapter;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class SearchedGiftsAdapter extends RecyclerView.Adapter<SearchedGiftsAdapter.SearchedGiftsViewHolder> {



    public SearchedGiftsAdapter() {
    }

    @NonNull
    @Override
    public SearchedGiftsAdapter.SearchedGiftsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull SearchedGiftsAdapter.SearchedGiftsViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public static class SearchedGiftsViewHolder extends RecyclerView.ViewHolder {

        public SearchedGiftsViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

}
