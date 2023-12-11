package com.dotcipher.gift_discovery;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.dotcipher.gift_discovery.helpers.HomeAdapter.CategoryGiftAdapter;
import com.dotcipher.gift_discovery.helpers.HomeAdapter.CategoryGiftHelper;
import com.dotcipher.gift_discovery.helpers.HomeAdapter.HolidayAdapter;
import com.dotcipher.gift_discovery.helpers.HomeAdapter.HolidayHelper;
import com.dotcipher.gift_discovery.helpers.HomeAdapter.LovedGiftAdapter;
import com.dotcipher.gift_discovery.helpers.HomeAdapter.LovedGiftHelper;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView lovedGiftRecycler;
    RecyclerView.Adapter lovedGiftAdapter;
    LinearLayoutManager lovedGiftLinearLayoutManager;

    RecyclerView categoryGiftRecycler;
    RecyclerView.Adapter categoryGiftAdapter;
    LinearLayoutManager categoryGiftLinearLayoutManager;

    RecyclerView holidayRecycler;
    RecyclerView.Adapter holidayAdapter;
    LinearLayoutManager holidayLinearLayoutManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Hooks
        lovedGiftRecycler = findViewById(R.id.lovedGiftRecycler);
        categoryGiftRecycler = findViewById(R.id.categoryGiftRecycler);
        holidayRecycler = findViewById(R.id.holidayRecycler);


        lovedGiftRecyclerFill();
        categoryGiftRecyclerFill();
        holidayRecyclerFill();
    }
    private void holidayRecyclerFill(){
        holidayRecycler.setHasFixedSize(true);
        holidayRecycler.setLayoutManager(new LinearLayoutManager(this));

        ArrayList<HolidayHelper> holidayHelpers = new ArrayList<>();
        holidayHelpers.add(new HolidayHelper(R.drawable.anniversary_foreground, "Anniversary"));
        holidayHelpers.add(new HolidayHelper(R.drawable.anniversary_foreground, "Anniversary"));
        holidayHelpers.add(new HolidayHelper(R.drawable.anniversary_foreground, "Anniversary"));

        holidayLinearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);

        holidayAdapter = new HolidayAdapter(MainActivity.this,holidayHelpers);
        holidayRecycler.setAdapter(holidayAdapter);
        holidayRecycler.setLayoutManager(holidayLinearLayoutManager);
    }
    private void categoryGiftRecyclerFill(){
        categoryGiftRecycler.setHasFixedSize(true);
        categoryGiftRecycler.setLayoutManager(new LinearLayoutManager(this));


        ArrayList<CategoryGiftHelper> categoryGiftHelpers = new ArrayList<>();
        categoryGiftHelpers.add(new CategoryGiftHelper(R.drawable.fragrance_type,"Fragrance"));
        categoryGiftHelpers.add(new CategoryGiftHelper(R.drawable.fragrance_type,"Clothing"));

        categoryGiftLinearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false);

        categoryGiftAdapter = new CategoryGiftAdapter(MainActivity.this,categoryGiftHelpers);
        categoryGiftRecycler.setAdapter(categoryGiftAdapter);
        categoryGiftRecycler.setLayoutManager(categoryGiftLinearLayoutManager);


    }

    private void lovedGiftRecyclerFill(){

        lovedGiftRecycler.setHasFixedSize(true);
        lovedGiftRecycler.setLayoutManager(new LinearLayoutManager(this));

        ArrayList<LovedGiftHelper> lovedGiftHelpers = new ArrayList<>();
        lovedGiftHelpers.add(new LovedGiftHelper(R.mipmap.fragrance_1_foreground,"Fragrance Black","Eau de parfum"));
        lovedGiftHelpers.add(new LovedGiftHelper(R.mipmap.fragrance_1_foreground,"Fragrance Black","Eau de parfum"));


        //LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        //lovedGiftRecycler.setLayoutManager(layoutManager);
        lovedGiftLinearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);

        lovedGiftAdapter = new LovedGiftAdapter(lovedGiftHelpers);
        lovedGiftRecycler.setAdapter(lovedGiftAdapter);
        lovedGiftRecycler.setLayoutManager(lovedGiftLinearLayoutManager);

    }



}