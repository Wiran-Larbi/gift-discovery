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
import android.util.Log;

import com.dotcipher.gift_discovery.R;
import com.dotcipher.gift_discovery.helpers.HomeAdapter.OccasionHelper;
import com.dotcipher.gift_discovery.utils.ImageUtils;

import java.util.ArrayList;
import java.util.Arrays;
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
        String CREATE_OCCASIONS_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_OCCASIONS + "("
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
        this.onCreate(db);
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
        byte[] imageBytes2 = ImageUtils.getImageByteArray(context, R.drawable.gift_icon);

        // Use the byte arrays when creating OccasionHelper objects
        addOccasion(new OccasionHelper("Birthday", "Birthday celebration", imageBytes1));
        addOccasion(new OccasionHelper("Anniversary", "Wedding Anniversary", imageBytes2));


    }

    public String[] getAllOccasionsTitles(){
        ArrayList<String> occasionTitles = new ArrayList<String>();
        String selectQuery = "SELECT "+ COLUMN_NAME +" FROM " + TABLE_OCCASIONS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        int index = cursor.getColumnIndex(COLUMN_NAME);

        if (cursor != null && cursor.moveToFirst()) {
            do {
                String occasionTitle = cursor.getString(0);
                occasionTitles.add(occasionTitle);
            } while (cursor.moveToNext());
        }
        cursor.close();
        String[] titles = occasionTitles.toArray(new String[0]);
        return Arrays.stream(titles).distinct().toArray(String[]::new);
    }

    public void deleteDuplicateOccasions() {
            SQLiteDatabase db = this.getWritableDatabase();

            try {
                // SQL query to find and delete duplicate occasions
                String deleteQuery = "DELETE FROM " + TABLE_OCCASIONS +
                        " WHERE " + COLUMN_ID + " NOT IN (" +
                        "SELECT MIN(" + COLUMN_ID + ") FROM " + TABLE_OCCASIONS +
                        " GROUP BY " + COLUMN_NAME + ", " + COLUMN_DESCRIPTION + ")";

                db.execSQL(deleteQuery);
                Log.d("occasionsDB", "Duplicate occasions deleted successfully.");
            } catch (Exception e) {
                Log.e("occasionsDB", "Error while deleting duplicate occasions: " + e.getMessage());
            } finally {
                if (db != null && db.isOpen()) {
                    db.close();
                }
            }
        }



}
