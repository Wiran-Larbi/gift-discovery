package com.dotcipher.gift_discovery;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.dotcipher.gift_discovery.db.GiftDatabaseHelper;
import com.dotcipher.gift_discovery.model.GiftClass;

public class AddGiftActivity extends AppCompatActivity {

    private EditText gift_title_et,gift_description_et,gift_category_et,gift_occasion_et;
    private ImageView giftImageView;

    private Button btnAddGift;
    private static final int PICK_IMAGE_REQUEST=100;
    private Uri imageFilePath;
    private Bitmap imageToStore;

    GiftClass giftClass;

    GiftDatabaseHelper db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_gift);
        try{
            giftImageView = findViewById(R.id.gift_add_image);
            gift_title_et = findViewById(R.id.gift_title_et);
            gift_description_et = findViewById(R.id.gift_description_et);
            gift_category_et = findViewById(R.id.gift_category_et);
            gift_occasion_et = findViewById(R.id.gift_occasion_et);
            btnAddGift = findViewById(R.id.btnAddGift);
            db = new GiftDatabaseHelper(this);
            btnAddGift.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    storeGiftLocally(gift_title_et.getText().toString(),
                            gift_description_et.getText().toString(),
                            gift_category_et.getText().toString(),
                            gift_occasion_et.getText().toString(),
                            imageToStore);

                    // finally adding our gift to DB
                    db.addGift(giftClass);
                }
            });

        }catch(Exception exception){

        }

    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        try{
            super.onActivityResult(requestCode, resultCode, data);
            if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null){
                imageFilePath = data.getData();
                imageToStore = MediaStore.Images.Media.getBitmap(getContentResolver(), imageFilePath);

                giftImageView.setImageBitmap(imageToStore);
            }

        }catch (Exception exception) {
        }
    }

    public void chooseImage(View imageView){
        try{
            Intent intent = new Intent();
            intent.setType("image/*");

            intent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(intent, PICK_IMAGE_REQUEST);


        }catch(Exception exception){

        }
    }

    public void storeGiftLocally(String title, String description, String category, String occasion, Bitmap image){
        this.giftClass = new GiftClass(title, description, category, occasion, image);


    }
}