package com.dotcipher.gift_discovery;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.dotcipher.gift_discovery.db.GiftDatabaseHelper;
import com.dotcipher.gift_discovery.helpers.HomeAdapter.SearchedGiftsAdapter;
import com.dotcipher.gift_discovery.model.GiftClass;

import java.util.List;

public class SearchResultsActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private SearchedGiftsAdapter adapter;
    private GiftDatabaseHelper giftDbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_results);

        recyclerView = findViewById(R.id.searchRecyclerView); // Replace with your RecyclerView ID
        giftDbHelper = new GiftDatabaseHelper(this);

        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("searchQuery")) {
            String searchQuery = intent.getStringExtra("searchQuery");
            Log.d("SEARCH", searchQuery);
            displaySearchResults(searchQuery);
        }
    }

    private void displaySearchResults(String searchQuery) {
        List<GiftClass> searchResults = giftDbHelper.searchGiftsByTitle(searchQuery);
        adapter = new SearchedGiftsAdapter(this, searchResults);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }
}
