package com.dotcipher.gift_discovery;


import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.dotcipher.gift_discovery.db.occasionsDB;
import com.dotcipher.gift_discovery.helpers.HomeAdapter.OccasionHelper;
import com.dotcipher.gift_discovery.utils.ImageUtils;

public class AddOccasion extends AppCompatActivity {

    private EditText titleEditText;
    private EditText descriptionEditText;
    private ImageView occasionImageView;
    private Button addOccasionButton;

    private static final int PICK_IMAGE_REQUEST = 100;
    private Uri imageFilePath;
    private Bitmap imageToStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_occation); // Replace with your actual layout name

        // Initialize views
        occasionImageView = findViewById(R.id.occasion_add_image);
        titleEditText = findViewById(R.id.occasion_title_et);
        descriptionEditText = findViewById(R.id.occasion_description_et);
        addOccasionButton = findViewById(R.id.btnAddOccasion);

        // ImageView click listener for adding an image
        occasionImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooseImage(); // Opens gallery to pick an image
            }
        });

        // Button click listener for adding an occasion
        addOccasionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                storeOccasionLocally(titleEditText.getText().toString(),
                        descriptionEditText.getText().toString(),
                        imageToStore);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null) {
            imageFilePath = data.getData();
            try {
                imageToStore = MediaStore.Images.Media.getBitmap(getContentResolver(), imageFilePath);
                occasionImageView.setImageBitmap(imageToStore);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void chooseImage() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }

    public void storeOccasionLocally(String title, String description, Bitmap image) {
        // Convert Bitmap to byte array using ImageUtils
        byte[] imageBytes = ImageUtils.getByteArrayFromBitmap(image);

        // Create an OccasionHelper object
        OccasionHelper newOccasion = new OccasionHelper();
        newOccasion.setName(title);
        newOccasion.setDescription(description);
        newOccasion.setImage(imageBytes);

        // Add the occasion to the database
        occasionsDB databaseHelper = new occasionsDB(this);
        databaseHelper.addOccasion(newOccasion);

        Toast.makeText(this, "Occasion added successfully", Toast.LENGTH_SHORT).show();

        // Optionally, clear the fields or navigate away from this activity
        // Reset fields here if needed
    }
}
