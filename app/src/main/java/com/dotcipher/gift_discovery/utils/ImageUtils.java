package com.dotcipher.gift_discovery.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

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

    public static Bitmap getBitmapFromByteArray(byte[] byteArrayImage) {
        if (byteArrayImage != null && byteArrayImage.length > 0) {
            return BitmapFactory.decodeByteArray(byteArrayImage, 0, byteArrayImage.length);
        }
        return null;
    }

    public static int bitmapToInt(Bitmap bitmap) {
        if (bitmap != null) {
            try {
                // Convert the bitmap to a byte array
                byte[] pixels = getBitmapBytes(bitmap);

                // Generate an MD5 hash from the byte array
                MessageDigest md = MessageDigest.getInstance("MD5");
                byte[] hash = md.digest(pixels);

                // Convert the hash bytes into an integer
                int hashInt = byteArrayToInt(hash);

                return hashInt;
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
        }
        return 0; // Return a default value if bitmap is null or hashing fails
    }

    private static byte[] getBitmapBytes(Bitmap bitmap) {
        int size = bitmap.getRowBytes() * bitmap.getHeight();
        byte[] pixels = new byte[size];
        bitmap.copyPixelsToBuffer(java.nio.ByteBuffer.wrap(pixels));
        return pixels;
    }

    private static int byteArrayToInt(byte[] bytes) {
        int result = 0;
        for (int i = 0; i < bytes.length; i++) {
            result += (bytes[i] & 0xFF) << (8 * i);
        }
        return result;
    }

}
