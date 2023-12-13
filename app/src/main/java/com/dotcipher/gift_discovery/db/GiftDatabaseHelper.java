package com.dotcipher.gift_discovery.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.dotcipher.gift_discovery.model.GiftClass;

import java.io.ByteArrayOutputStream;

public class GiftDatabaseHelper extends SQLiteOpenHelper {
    private Context context;
    private static final String DATABASE_NAME = "gift_discovery.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "gift";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_TITLE = "gift_title";
    private static final String COLUMN_DESCRIPTION = "gift_description";
    private static final String COLUMN_CATEGORY = "gift_category";
    private static final String COLUMN_IMAGE = "gift_image";

    private ByteArrayOutputStream imageOutputStream;
    private byte[] imageInBytes;

    public GiftDatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String create_table_query = "CREATE TABLE " + TABLE_NAME + " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_TITLE + " TEXT, " + COLUMN_DESCRIPTION + " TEXT," + COLUMN_CATEGORY+ " TEXT," + COLUMN_IMAGE + " BLOB);";

        db.execSQL(create_table_query);
        Log.d("DB TABLE CREATED", "Table " + TABLE_NAME + " has been created successfuly.");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    /*
    public void addGift(GiftClass giftToAdd){
            try{
                SQLiteDatabase db = this.getWritableDatabase();
                ContentValues contentValues = new ContentValues();

                // Extracting Image from Bitmap to Bytes
                Bitmap imageBitmap = giftToAdd.getImage();
                imageOutputStream = new ByteArrayOutputStream();
                imageBitmap.compress(Bitmap.CompressFormat.JPEG,100,imageOutputStream);
                imageInBytes = imageOutputStream.toByteArray();


                contentValues.put(COLUMN_TITLE, giftToAdd.getTitle());
                contentValues.put(COLUMN_DESCRIPTION, giftToAdd.getDescription());
                contentValues.put(COLUMN_CATEGORY, giftToAdd.getCategory());
                contentValues.put(COLUMN_IMAGE, imageInBytes);

                long result = db.insert(TABLE_NAME, null, contentValues);
                if (result == -1){
                    Toast.makeText(context, "Failed To insert Gift ..", Toast.LENGTH_SHORT).show();
                    Log.d("INSERT FAILED", "Failed to insert Gift to Table Db");
                }else {
                    Toast.makeText(context, "Successfully inserted Gift ..", Toast.LENGTH_SHORT).show();
                    Log.d("INSERT SUCCESSFULLY", "Successfully inserted Gift");
                    db.close();
                }

            }catch(Exception exception){
                Toast.makeText(context, "Problem With Inserting in DB", Toast.LENGTH_SHORT).show();
                Log.d("PROBLEM TABLE INSERT", "PROBLEM : " + exception.getMessage());
            }
    }
    */
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
}
