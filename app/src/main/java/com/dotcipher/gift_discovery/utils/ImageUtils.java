package com.dotcipher.gift_discovery.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class ImageUtils {
    public static byte[] getImageByteArray(Context context, int drawableResourceId) {
        Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), drawableResourceId);
        if (bitmap == null) {
            // Handle the case where the bitmap is null
            Log.e("ImageUtils", "Bitmap is null for resource ID: " + drawableResourceId);
            return null; // or handle it some other way
        }
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream);
        byte[] byteArray = outputStream.toByteArray();

        try {
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return byteArray;
    }
    public static byte[] getByteArrayFromBitmap(Bitmap bitmap) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        return stream.toByteArray();
    }

}
