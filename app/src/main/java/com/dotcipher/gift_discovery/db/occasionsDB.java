package com.dotcipher.gift_discovery.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.dotcipher.gift_discovery.R;
import com.dotcipher.gift_discovery.helpers.HomeAdapter.OccasionHelper;
import com.dotcipher.gift_discovery.utils.ImageUtils;

import java.util.ArrayList;
import java.util.List;

public class occasionsDB extends SQLiteOpenHelper {

    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "occasionDB";

    // Table name
    private static final String TABLE_OCCASIONS = "occasions";

    // Occasions Table Columns names
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_DESCRIPTION = "description";
    private static final String COLUMN_IMAGE = "image";
    private Context context;
    public occasionsDB(Context context) {

        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context=context;
    }

    // Creating the table
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_OCCASIONS_TABLE = "CREATE TABLE " + TABLE_OCCASIONS + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY,"
                + COLUMN_NAME + " TEXT,"
                + COLUMN_DESCRIPTION + " TEXT,"
                + COLUMN_IMAGE + " BLOB" + ")";
        db.execSQL(CREATE_OCCASIONS_TABLE);

    }

    // Upgrading the database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_OCCASIONS);

        // Create tables again
        onCreate(db);
    }

    // Adding new occasion
    public void addOccasion(OccasionHelper occasion) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, occasion.getName());
        values.put(COLUMN_DESCRIPTION, occasion.getDescription());
        values.put(COLUMN_IMAGE, occasion.getImage());

        db.insert(TABLE_OCCASIONS, null, values);
        db.close();
    }

    // Getting all occasions
    public List<OccasionHelper> getAllOccasions() {
        List<OccasionHelper> occasionList = new ArrayList<>();
        String selectQuery = "SELECT  * FROM " + TABLE_OCCASIONS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                OccasionHelper occasion = new OccasionHelper();
                occasion.setId(cursor.getInt(0));
                occasion.setName(cursor.getString(1));
                occasion.setDescription(cursor.getString(2));
                occasion.setImage(cursor.getBlob(3));
                occasionList.add(occasion);
            } while (cursor.moveToNext());
        }

        cursor.close();
        System.out.println(occasionList);
        return occasionList;
    }

    // Updating a single occasion
    public int updateOccasion(OccasionHelper occasion) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, occasion.getName());
        values.put(COLUMN_DESCRIPTION, occasion.getDescription());
        values.put(COLUMN_IMAGE, occasion.getImage());

        return db.update(TABLE_OCCASIONS, values, COLUMN_ID + " = ?",
                new String[]{String.valueOf(occasion.getId())});
    }

    // Deleting a single occasion
    public void deleteOccasion(OccasionHelper occasion) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_OCCASIONS, COLUMN_ID + " = ?",
                new String[]{String.valueOf(occasion.getId())});
        db.close();
    }
    public void addSampleData() {
        byte[] imageBytes1 = ImageUtils.getImageByteArray(context, R.drawable.gift_icon);
        byte[] imageBytes2 = ImageUtils.getImageByteArray(context, R.drawable.gift_icon); // Replace with actual drawable

        // Use the byte arrays when creating OccasionHelper objects
        addOccasion(new OccasionHelper("Birthday", "Birthday celebration", imageBytes1));
        addOccasion(new OccasionHelper("Anniversary", "Wedding Anniversary", imageBytes2));
        // ... add more samples ...

    }
}
