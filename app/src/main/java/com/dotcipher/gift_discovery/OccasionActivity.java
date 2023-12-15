package com.dotcipher.gift_discovery;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dotcipher.gift_discovery.db.GiftDatabaseHelper;
import com.dotcipher.gift_discovery.helpers.HomeAdapter.GiftAdapter;
import com.dotcipher.gift_discovery.helpers.HomeAdapter.LovedGiftAdapter;
import com.dotcipher.gift_discovery.model.GiftClass;

import java.util.ArrayList;
import java.util.List;

public class OccasionActivity extends AppCompatActivity {

    private ImageView occasionImageView;
    private TextView occasionNameTextView;
    private TextView occasionDescriptionTextView;
    private RecyclerView relatedGiftsRecyclerView;
    private GiftDatabaseHelper giftDatabaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_occasions);
        System.out.println("OccasionsActivity onCreate executed");
        // Initialize views
        occasionImageView = findViewById(R.id.occasionImageView);
        occasionNameTextView = findViewById(R.id.occasionNameTextView);
        occasionDescriptionTextView = findViewById(R.id.occasionDescriptionTextView);
        relatedGiftsRecyclerView = findViewById(R.id.relatedGiftsRecyclerView);

        // Initialize the database helper
        giftDatabaseHelper = new GiftDatabaseHelper(this);

        // Get data passed to this activity
        Intent intent = getIntent();
        String occasionName = intent.getStringExtra("OCCASION_NAME");
        String occasionDescription = intent.getStringExtra("OCCASION_DESCRIPTION");
        byte[] occasionImageBytes = intent.getByteArrayExtra("OCCASION_IMAGE");

        // Set occasion data to views
        if (occasionImageBytes != null) {
            occasionImageView.setImageBitmap(BitmapFactory.decodeByteArray(occasionImageBytes, 0, occasionImageBytes.length));
        }
        occasionNameTextView.setText(occasionName);
        occasionDescriptionTextView.setText(occasionDescription);

        // Fetch and display related gifts
        displayRelatedGifts(occasionName);
    }

    private void displayRelatedGifts(String occasionName) {
        Log.d("OccasionsActivity", "Number of related gifts hanaaaaaaaaaaaaa: ");
        List<GiftClass> relatedGifts = giftDatabaseHelper.getGiftsByOccasion(occasionName);
        Log.d("OccasionsActivity", "Number of related gifts: " + relatedGifts.size());
        relatedGiftsRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Assuming you have a GiftAdapter for the RecyclerView
        GiftAdapter adapter = new GiftAdapter(relatedGifts);
        relatedGiftsRecyclerView.setAdapter(adapter);
    }
}
