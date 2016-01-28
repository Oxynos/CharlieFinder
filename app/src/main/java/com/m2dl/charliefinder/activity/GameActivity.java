package com.m2dl.charliefinder.activity;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;

import java.util.ArrayList;
import java.util.List;

import com.m2dl.charliefinder.R;
import com.m2dl.charliefinder.metier.CustomObject;
import java.lang.reflect.Field;
import java.util.Random;

public class GameActivity extends AppCompatActivity {
    List<CustomObject> listObjects = new ArrayList<>();
    CustomDrawableView customDrawableView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        customDrawableView = (CustomDrawableView) findViewById(R.id.Canvas01);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);


        final R.drawable drawableResources = new R.drawable();
        final Class<R.drawable> c = R.drawable.class;
        final Field[] fields = c.getDeclaredFields();

        Display mDisplay = this.getWindowManager().getDefaultDisplay();
        final int maxWidth  = mDisplay.getWidth();
        final int maxHeight = mDisplay.getHeight();

        Field[] drawables = android.R.drawable.class.getFields();
        for (Field f : fields) {
            try {
                if (f.getName().startsWith("img_")) {
                    System.out.println("R.drawable." + f.getName());
                    Random r1 = new Random();
                    Bitmap tmp = decodeSampledBitmapFromResource(getResources(), f.getInt(null), 100, 100);

                    int res1 = r1.nextInt(maxHeight - tmp.getHeight() - 160);
                    int res2 = r1.nextInt(maxWidth - tmp.getWidth() - 30);


                    CustomObject co = new CustomObject(f.getName(), tmp, res2, res1);
                    listObjects.add(co);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        for (int i = 0 ; i < 1 ; i++) {
            //Bitmap bitmap= BitmapFactory.decodeResource(getResources(),
            //        R.drawable.apple);
            //String name = String.valueOf(R.drawable.apple);
            //listObjects.add(new CustomObject(name, bitmap));

        }

        customDrawableView.setListObjects(listObjects);
    }

    public static int calculateInSampleSize(
            BitmapFactory.Options options, int reqWidth, int reqHeight) {
        // Raw height and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {

            final int halfHeight = height / 2;
            final int halfWidth = width / 2;

            // Calculate the largest inSampleSize value that is a power of 2 and keeps both
            // height and width larger than the requested height and width.
            while ((halfHeight / inSampleSize) > reqHeight
                    && (halfWidth / inSampleSize) > reqWidth) {
                inSampleSize *= 2;
            }
        }

        return inSampleSize;
    }

    public static Bitmap decodeSampledBitmapFromResource(Resources res, int resId,
                                                         int reqWidth, int reqHeight) {

        // First decode with inJustDecodeBounds=true to check dimensions
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(res, resId, options);

        // Calculate inSampleSize
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);

        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeResource(res, resId, options);
    }
}
