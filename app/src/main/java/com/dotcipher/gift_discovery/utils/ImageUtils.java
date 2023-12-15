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
    private static Bitmap resizeBitmap(Bitmap originalBitmap, int width, int height) {
        return Bitmap.createScaledBitmap(originalBitmap, width, height, false);
    }

    public static byte[] getImageByteArray(Context context, int drawableResourceId) {
        Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), drawableResourceId);
        if (bitmap == null) {
            Log.e("ImageUtils", "Bitmap is null for resource ID: " + drawableResourceId);
            return null;
        }

        // Resize the bitmap
        Bitmap resizedBitmap = resizeBitmap(bitmap, 500, 500); // You can adjust the size

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        resizedBitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream);
        byte[] byteArray = outputStream.toByteArray();

        try {
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return byteArray;
    }

    public static byte[] getByteArrayFromBitmap(Bitmap bitmap) {
        if (bitmap == null) {
            return null;
        }

        // Resize the bitmap
        Bitmap resizedBitmap = resizeBitmap(bitmap, 500, 500); // You can adjust the size

        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        resizedBitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        return stream.toByteArray();
    }

    public static Bitmap getBitmapFromByteArray(byte[] byteArrayImage) {
        if (byteArrayImage != null && byteArrayImage.length > 0) {
            Bitmap bitmap = BitmapFactory.decodeByteArray(byteArrayImage, 0, byteArrayImage.length);

            // Resize the bitmap
            return resizeBitmap(bitmap, 500, 500); // You can adjust the size
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
