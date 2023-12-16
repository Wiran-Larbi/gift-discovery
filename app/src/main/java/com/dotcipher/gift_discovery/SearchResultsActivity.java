package com.dotcipher.gift_discovery;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

public class SearchResultsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_results);

        // Inside SearchResultsActivity
        Intent intent = getIntent();
        if (intent != null) {
            String searchQuery = intent.getStringExtra("searchQuery");
            Log.d("SEARCH", searchQuery);

        }

    }
}