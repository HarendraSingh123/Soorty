package com.camellia.soorty.utills;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Bundle;
import android.os.ResultReceiver;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by abid on 20/12/17.
 */

public class IntentServiceForImage extends IntentService {
    protected ResultReceiver mReceiver;
    Uri capturedImageUri = null;
    private String fileName;
    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     *
     */
    public IntentServiceForImage() {
        super("intentService");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        Log.e("file name", "-----" );
        mReceiver=intent.getParcelableExtra(AppConstant.EXTRA_DATA_IMAGE);
        capturedImageUri=intent.getParcelableExtra("capturedImageUri");
        fileName=intent.getStringExtra("fileName");
        Log.e("file name", "" + fileName);
        Bitmap bitmapItem = null;
        try {
            bitmapItem = MediaStore.Images.Media.getBitmap(getContentResolver(), capturedImageUri);
        } catch (IOException e) {
            e.printStackTrace();
            Log.e("error", "" + capturedImageUri);
        }
       // bitmapItem = new BitmapDrawable(IntentServiceForImage.this.getResources(), bitmapItem).getBitmap();
        try {
            bitmapItem= rotateImageIfRequired(bitmapItem,getApplicationContext(),capturedImageUri);
        } catch (IOException e) {
            e.printStackTrace();
        }

        File f = new File(IntentServiceForImage.this.getCacheDir(), fileName);
        try {
            f.createNewFile();
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            bitmapItem.compress(Bitmap.CompressFormat.PNG, 0 /*ignored for PNG*/, bos);
            byte[] bitmapdata = bos.toByteArray();
            //write the bytes in file
            FileOutputStream fos = new FileOutputStream(f);
            fos.write(bitmapdata);
            fos.flush();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        deliverResultToReceiver(AppConstant.SUCCESS_RESULT, Uri.fromFile(f));
    }

    public static Bitmap rotateImageIfRequired(Bitmap img, Context context, Uri selectedImage) throws IOException {

        if (selectedImage.getScheme().equals("content")) {
            String[] projection = { MediaStore.Images.ImageColumns.ORIENTATION };
            Cursor c = context.getContentResolver().query(selectedImage, projection, null, null, null);
            if(c.getColumnCount()>0)
                if (c.moveToFirst()) {
                Log.e("hello mr","DJ");
                    final int rotation = c.getInt(0);
                    c.close();
                    return rotateImage(img, rotation);
                }
            return img;
        } else {
            ExifInterface ei = new ExifInterface(selectedImage.getPath());
            int orientation = ei.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);
            Log.d("orientation: %s", orientation+"");

            switch (orientation) {
                case ExifInterface.ORIENTATION_ROTATE_90:
                    return rotateImage(img, 90);
                case ExifInterface.ORIENTATION_ROTATE_180:
                    return rotateImage(img, 180);
                case ExifInterface.ORIENTATION_ROTATE_270:
                    return rotateImage(img, 270);
                default:
                    return img;
            }
        }
    }

    private static Bitmap rotateImage(Bitmap img, int degree) {
        Matrix matrix = new Matrix();
        matrix.postRotate(degree);
        Bitmap rotatedImg = Bitmap.createBitmap(img, 0, 0, img.getWidth(), img.getHeight(), matrix, true);
        return rotatedImg;
    }

    private void deliverResultToReceiver(int resultCode, Uri uri) {
        Log.e("result receiver", "-----" );
        Bundle bundle = new Bundle();
        bundle.putParcelable(AppConstant.EXTRA_DATA_IMAGE, uri);
        mReceiver.send(resultCode, bundle);
    }
}
