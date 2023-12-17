package com.dotcipher.gift_discovery;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.dotcipher.gift_discovery.db.GiftDatabaseHelper;
import com.dotcipher.gift_discovery.db.occasionsDB;
import com.dotcipher.gift_discovery.helpers.HomeAdapter.LovedGiftHelper;
import com.dotcipher.gift_discovery.helpers.HomeAdapter.OccasionHelper;
import com.dotcipher.gift_discovery.model.GiftClass;
import com.dotcipher.gift_discovery.utils.ImageUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AddGiftActivity extends AppCompatActivity {

    private EditText gift_title_et,gift_description_et,gift_occasion_et;
    private Spinner gift_category_spinner,gift_occasion_spinner;
    String SelectedCategory, SelectedOccasion;
    private ImageView giftImageView;

    private Button btnAddGift;
    private static final int PICK_IMAGE_REQUEST=100;
    private Uri imageFilePath;
    private Bitmap imageToStore;

    GiftClass giftClass;
    ArrayList<LovedGiftHelper> topGifts;

    GiftDatabaseHelper db;
    occasionsDB db_occasions;

    String[] giftCategories;
    String[] giftOccasions = {"Birthday" , "Holiday", "Graduation"};

    List<OccasionHelper> occasions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_gift);
        try{
            giftImageView = findViewById(R.id.gift_add_image);
            gift_title_et = findViewById(R.id.gift_title_et);
            gift_description_et = findViewById(R.id.gift_description_et);
            gift_occasion_spinner = findViewById(R.id.gift_occasion_sp);
            gift_category_spinner = findViewById(R.id.gift_category_sp);
            btnAddGift = findViewById(R.id.btnAddGift);

            giftCategories = getResources().getStringArray(R.array.gift_categories);



            ArrayAdapter<String> adapterCategories = new ArrayAdapter<String>(AddGiftActivity.this, R.layout.custom_spinner_item, giftCategories);
            adapterCategories.setDropDownViewResource(R.layout.custom_spinner_dropdown_item);

            gift_category_spinner.setAdapter(adapterCategories);
            gift_category_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    SelectedCategory = parent.getItemAtPosition(position).toString();
                    //Toast.makeText(AddGiftActivity.this, "Selected Item is : " + SelectedCategory, Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {
                }
            });

            /*

            giftOccasions = db_occasions.getAllOccasionsTitles();
            if (giftOccasions.length == 0)
                giftOccasions = new String[]{"Birthday", "Holiday", "Graduation"};

            ArrayAdapter<String> adapterOccasions = new ArrayAdapter<String>(AddGiftActivity.this, R.layout.custom_spinner_item, giftOccasions);
            adapterOccasions.setDropDownViewResource(R.layout.custom_spinner_dropdown_item);

            gift_occasion_spinner.setAdapter(adapterOccasions);
            gift_occasion_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    SelectedOccasion = parent.getItemAtPosition(position).toString();
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });


        */

            new Thread(() -> {
                db_occasions = new occasionsDB(this);
                giftOccasions = db_occasions.getAllOccasionsTitles();
                adapterCategories.setDropDownViewResource(R.layout.custome_spinner_dropdown_item_2);
                ArrayAdapter<String> adapterOccasions = new ArrayAdapter<String>(AddGiftActivity.this, R.layout.custom_spinner_item_2, giftOccasions);

                gift_occasion_spinner.setAdapter(adapterOccasions);

                gift_occasion_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        SelectedOccasion = parent.getItemAtPosition(position).toString();
                        //Toast.makeText(AddGiftActivity.this, "Selected Item is : " + SelectedCategory, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {
                    }
                });
            }).start();




            btnAddGift.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    db = new GiftDatabaseHelper(AddGiftActivity.this);

                    String title = gift_title_et.getText().toString();
                    String description = gift_description_et.getText().toString();



                    // Check if any field is empty before storing the gift
                    if (title.isEmpty() || description.isEmpty() || SelectedCategory.isEmpty() || SelectedOccasion.isEmpty()) {
                        Toast.makeText(AddGiftActivity.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    storeGiftLocally(title,
                                    description,
                                    SelectedCategory,
                                    SelectedOccasion,
                                    imageToStore);
                    // finally adding our gift to DB
                    Toast.makeText(AddGiftActivity.this, "Add gift ", Toast.LENGTH_SHORT).show();
                    db.addGift(giftClass);

                    // Fetching all gifts from db after being successfully inserted
                    topGifts = db.getTopGift();

                    // Creating an intent to send the recently added gift to MainActivity
                    Intent intent = new Intent(AddGiftActivity.this, MainActivity.class);
                    intent.putExtra("topGifts", new ArrayList<>(topGifts));

                    startActivity(intent);
                }
            });

        }catch(Exception exception){
            Log.d("ERROR", "Error : " + exception.getMessage());
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

    public void storeGiftLocally(String title, String description, String category, String occasion, Bitmap image) {
        if (image != null) {
            byte[] imageBytes = ImageUtils.getByteArrayFromBitmap(image);
            this.giftClass = new GiftClass(title, description, category, occasion, imageBytes);
        } else {
            // Handle the case where there is no image
            this.giftClass = new GiftClass(title, description, category, occasion, null);
        }
    }
}