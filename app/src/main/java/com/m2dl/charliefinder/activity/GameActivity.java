package com.m2dl.charliefinder.activity;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.Chronometer;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import com.m2dl.charliefinder.R;
import com.m2dl.charliefinder.metier.CustomObject;
import java.lang.reflect.Field;
import java.util.Random;

public class GameActivity extends AppCompatActivity {
    List<CustomObject> listObjects = new ArrayList<>();
    CustomDrawableView customDrawableView;
    Chronometer chronometer;
    TextView textView;
    int j =0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        Display mDisplay = this.getWindowManager().getDefaultDisplay();
        final int maxWidth  = mDisplay.getWidth();
        final int maxHeight = mDisplay.getHeight();

        textView = (TextView) findViewById(R.id.textView);
        customDrawableView = (CustomDrawableView) findViewById(R.id.Canvas01);
        chronometer = (Chronometer) findViewById(R.id.chronometer);
        chronometer.setBase(SystemClock.elapsedRealtime());

        chronometer.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {
            @Override
            public void onChronometerTick(Chronometer chronometer) {
                //TODO implementer la fin du jeu
                /*textView.setText(j + "");
                j++;*/
            }
        });

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);


        final R.drawable drawableResources = new R.drawable();
        final Class<R.drawable> c = R.drawable.class;
        final Field[] fields = c.getDeclaredFields();

        Field[] drawables = android.R.drawable.class.getFields();
        for (Field f : fields) {
            try {
                if (f.getName().startsWith("img_")) {
                    System.out.println("R.drawable." + f.getName());
                    Random r1 = new Random();
                    Bitmap tmp = decodeSampledBitmapFromResource(getResources(), f.getInt(null), 100, 100);

                    int maxPosX = maxWidth - tmp.getWidth() - 30;
                    int maxPosY = maxHeight - tmp.getHeight() - 160;
                    int res1 = r1.nextInt(maxPosX);
                    int res2 = r1.nextInt(maxPosY);


                    CustomObject co = new CustomObject(f.getName(), tmp, res2, res1, maxPosX, maxPosY);
                    listObjects.add(co);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        customDrawableView.setListObjects(listObjects);
        customDrawableView.setMaxW(maxWidth);
        customDrawableView.setMaxH(maxHeight);
        chronometer.start();
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
