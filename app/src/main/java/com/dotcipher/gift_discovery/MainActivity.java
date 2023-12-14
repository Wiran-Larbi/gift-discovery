package com.dotcipher.gift_discovery;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.dotcipher.gift_discovery.db.GiftDatabaseHelper;
import com.dotcipher.gift_discovery.db.occasionsDB;
import com.dotcipher.gift_discovery.helpers.HomeAdapter.CategoryGiftAdapter;
import com.dotcipher.gift_discovery.helpers.HomeAdapter.CategoryGiftHelper;
import com.dotcipher.gift_discovery.helpers.HomeAdapter.OccasionAdapter;
import com.dotcipher.gift_discovery.helpers.HomeAdapter.OccasionHelper;
import com.dotcipher.gift_discovery.helpers.HomeAdapter.LovedGiftAdapter;
import com.dotcipher.gift_discovery.helpers.HomeAdapter.LovedGiftHelper;

import java.util.ArrayList;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {




    RecyclerView lovedGiftRecycler;
    RecyclerView.Adapter lovedGiftAdapter;
    LinearLayoutManager lovedGiftLinearLayoutManager;
    LinearLayout iconAddOccasion;
    RecyclerView categoryGiftRecycler;
    RecyclerView.Adapter categoryGiftAdapter;
    LinearLayoutManager categoryGiftLinearLayoutManager;

    RecyclerView holidayRecycler;
    RecyclerView.Adapter holidayAdapter;
    LinearLayoutManager holidayLinearLayoutManager;

    LinearLayout iconAddGift;

    LinearLayout iconAddPlanning;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        iconAddOccasion = findViewById(R.id.iconAddOccasion);

        // Setup click listener for the LinearLayout

        // Hooks
        lovedGiftRecycler = findViewById(R.id.lovedGiftRecycler);
        categoryGiftRecycler = findViewById(R.id.categoryGiftRecycler);
        holidayRecycler = findViewById(R.id.holidayRecycler);


        iconAddGift = findViewById(R.id.iconAddGift);
        iconAddOccasion = findViewById(R.id.iconAddOccasion);
        iconAddPlanning = findViewById(R.id.iconAddPlanning);

        // Listeners for Click Event
        iconAddGift.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(MainActivity.this, "Adding a Gift", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this, AddGiftActivity.class);
                startActivity(intent);
            }
        });

        iconAddOccasion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Intent to start AddOccasionActivity
                Intent intent = new Intent(MainActivity.this, AddOccasion.class);
                startActivity(intent);
            }
        });

        iconAddPlanning.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Adding a Planning", Toast.LENGTH_SHORT).show();
            }
        });
        occasionsDB db = new occasionsDB(this);



        lovedGiftRecyclerFill();
        categoryGiftRecyclerFill();


        // Initialize RecyclerView

        holidayRecycler.setHasFixedSize(true);
        holidayLinearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        holidayRecycler.setLayoutManager(holidayLinearLayoutManager);

        // Load data from database and set adapter
        loadOccasionsFromDatabase();
    }
    private void loadOccasionsFromDatabase() {
        // Create instance of DatabaseHelper
        occasionsDB db = new occasionsDB(this);

        // Fetch the list of occasions from the database
        List<OccasionHelper> occasionHelpers = db.getAllOccasions();

        // Set the adapter with the fetched data
        holidayAdapter = new OccasionAdapter(MainActivity.this, occasionHelpers);
        holidayRecycler.setAdapter(holidayAdapter);
    }
    private void categoryGiftRecyclerFill(){
        categoryGiftRecycler.setHasFixedSize(true);
        categoryGiftRecycler.setLayoutManager(new LinearLayoutManager(this));


        ArrayList<CategoryGiftHelper> categoryGiftHelpers = new ArrayList<>();

        categoryGiftHelpers.add(new CategoryGiftHelper(R.drawable.electronics,"Electronics"));
        categoryGiftHelpers.add(new CategoryGiftHelper(R.drawable.books,"Books"));
        categoryGiftHelpers.add(new CategoryGiftHelper(R.drawable.fashion_accessories,"Fashion & Accessories"));
        categoryGiftHelpers.add(new CategoryGiftHelper(R.drawable.toys_games,"Toys & Games"));
        categoryGiftHelpers.add(new CategoryGiftHelper(R.drawable.beauty,"Beauty & Personal Care"));
        categoryGiftHelpers.add(new CategoryGiftHelper(R.drawable.sports_outdoor,"Sports & Outdoors"));
        categoryGiftHelpers.add(new CategoryGiftHelper(R.drawable.food_organic,"Food & Beverages"));
        categoryGiftHelpers.add(new CategoryGiftHelper(R.drawable.diy,"Diy & Craft"));
        categoryGiftHelpers.add(new CategoryGiftHelper(R.drawable.subscribtion,"Subscription Boxes"));
        categoryGiftHelpers.add(new CategoryGiftHelper(R.drawable.home_kitchen,"Home & Kitchen"));

        categoryGiftLinearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false);

        categoryGiftAdapter = new CategoryGiftAdapter(MainActivity.this,categoryGiftHelpers);
        categoryGiftRecycler.setAdapter(categoryGiftAdapter);
        categoryGiftRecycler.setLayoutManager(categoryGiftLinearLayoutManager);


    }

    private void lovedGiftRecyclerFill(){

        lovedGiftRecycler.setHasFixedSize(true);
        lovedGiftRecycler.setLayoutManager(new LinearLayoutManager(this));

        GiftDatabaseHelper giftDb = new GiftDatabaseHelper(this);

        ArrayList<LovedGiftHelper> lovedGiftHelpers = giftDb.getTopGift();


        lovedGiftLinearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);

        lovedGiftAdapter = new LovedGiftAdapter(lovedGiftHelpers);
        lovedGiftRecycler.setAdapter(lovedGiftAdapter);
        lovedGiftRecycler.setLayoutManager(lovedGiftLinearLayoutManager);

    }



}