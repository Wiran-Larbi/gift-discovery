package com.dotcipher.gift_discovery;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.dotcipher.gift_discovery.db.GiftDatabaseHelper;
import com.dotcipher.gift_discovery.helpers.HomeAdapter.GiftAdapter;
import com.dotcipher.gift_discovery.model.GiftClass;

import java.util.List;

public class RandomGiftActivity extends AppCompatActivity {

    private RecyclerView giftsRecyclerView;
    private GiftDatabaseHelper giftDbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_random_gift);

        giftsRecyclerView = findViewById(R.id.giftsRecyclerView); // Replace with your actual RecyclerView ID
        giftsRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        giftDbHelper = new GiftDatabaseHelper(this);
        displayRandomGifts();
    }

    private void displayRandomGifts() {
        List<GiftClass> randomGifts = giftDbHelper.getRandomThreeGifts();
        GiftAdapter adapter = new GiftAdapter(randomGifts);
        giftsRecyclerView.setAdapter(adapter);
    }
}
