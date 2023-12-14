package com.dotcipher.gift_discovery.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.dotcipher.gift_discovery.helpers.HomeAdapter.LovedGiftHelper;
import com.dotcipher.gift_discovery.model.GiftClass;
import com.dotcipher.gift_discovery.utils.ImageUtils;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

public class GiftDatabaseHelper extends SQLiteOpenHelper {
    private Context context;
    private static final String DATABASE_NAME = "gift_discovery.db";
    private static final int DATABASE_VERSION = 2;
    private static final String TABLE_NAME = "gift";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_TITLE = "gift_title";
    private static final String COLUMN_DESCRIPTION = "gift_description";
    private static final String COLUMN_CATEGORY = "gift_category";
    private static final String COLUMN_OCCASION = "gift_occasion";
    private static final String COLUMN_IMAGE = "gift_image";

    private ByteArrayOutputStream imageOutputStream;
    private byte[] imageInBytes;

    public GiftDatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String create_table_query = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_TITLE + " TEXT, " + COLUMN_DESCRIPTION + " TEXT," + COLUMN_CATEGORY + " TEXT," + COLUMN_OCCASION+ " TEXT," + COLUMN_IMAGE + " BLOB);";

        db.execSQL(create_table_query);
        Log.d("DB TABLE CREATED", "Table " + TABLE_NAME + " has been created successfuly.");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }


    public void addGift(GiftClass giftToAdd) {
        try {
            SQLiteDatabase db = this.getWritableDatabase();

            if (db == null) {
                Log.e("DB_NULL", "Database is null. Could not obtain writable database.");
                return;
            }

            this.onCreate(db);
            ContentValues contentValues = new ContentValues();

            // Extracting Image from Bitmap to Bytes
            Bitmap imageBitmap = giftToAdd.getImage();
            if (imageBitmap != null) {
                imageOutputStream = new ByteArrayOutputStream();
                imageBitmap.compress(Bitmap.CompressFormat.JPEG, 100, imageOutputStream);
                imageInBytes = imageOutputStream.toByteArray();
                contentValues.put(COLUMN_IMAGE, imageInBytes);
            } else {
                Log.e("IMAGE_NULL", "Image bitmap is null.");
                // You might want to handle this case appropriately
            }

            contentValues.put(COLUMN_TITLE, giftToAdd.getTitle());
            contentValues.put(COLUMN_DESCRIPTION, giftToAdd.getDescription());
            contentValues.put(COLUMN_CATEGORY, giftToAdd.getCategory());
            contentValues.put(COLUMN_OCCASION, giftToAdd.getOccasion());

            long result = db.insert(TABLE_NAME, null, contentValues);
            if (result == -1) {
                Toast.makeText(context, "Failed To insert Gift ..", Toast.LENGTH_SHORT).show();
                Log.d("INSERT_FAILED", "Failed to insert Gift to Table Db");
            } else {
                Toast.makeText(context, "Successfully inserted Gift ..", Toast.LENGTH_SHORT).show();
                Log.d("INSERT_SUCCESSFUL", "Successfully inserted Gift");
            }
            db.close();
        } catch (Exception exception) {
            Toast.makeText(context, "Problem With Inserting in DB", Toast.LENGTH_SHORT).show();
            Log.e("PROBLEM_TABLE_INSERT", "PROBLEM : " + exception.getMessage());
        }
    }

    public ArrayList<LovedGiftHelper> getTopGift(){

            ArrayList<LovedGiftHelper> gifts = new ArrayList<>();
        try{
            SQLiteDatabase db = this.getReadableDatabase();
            Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);


            if (cursor != null && cursor.moveToFirst()) {
                do {
                    LovedGiftHelper gift = new LovedGiftHelper();
                    gift.setTitle(cursor.getString(1));
                    gift.setDescription(cursor.getString(2));
                    gift.setImage(cursor.getBlob(3));

                    gifts.add(gift);
                } while (cursor.moveToNext());

                ArrayList<LovedGiftHelper> topGifts = new ArrayList<>();
                if (gifts != null && gifts.size() >= 5) {
                    for (int i = 0; i < 5; i++){
                        topGifts.add(gifts.get(i));
                    }
                }
                gifts = topGifts;
                return topGifts;


            }
                cursor.close();

        }catch(Exception exception) {
                Log.d("ERROR DISPLAY","Error Displaying Gifts");

        }
        return gifts;
    }
}
