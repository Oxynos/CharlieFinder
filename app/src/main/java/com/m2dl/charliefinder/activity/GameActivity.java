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
    ImageView iv1, iv2, iv3;
    int j =0;
    private SensorManager sensorMgr;
    private Sensor s;
    //int j =0;
    private static final float SHAKE_THRESHOLD = 1.25f; // m/S**2
    private static final int MIN_TIME_BETWEEN_SHAKES_MILLISECS = 1200;
    private long mLastShakeTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        Display mDisplay = this.getWindowManager().getDefaultDisplay();
        final int maxWidth  = mDisplay.getWidth();
        final int maxHeight = mDisplay.getHeight();

        textView = (TextView) findViewById(R.id.textView);
        sensorMgr = (SensorManager) getSystemService(SENSOR_SERVICE);
        s = sensorMgr.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensorMgr.registerListener(this, s,
                SensorManager.SENSOR_DELAY_NORMAL);
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


                    CustomObject co = new CustomObject(f.getName(), tmp, res1, res2, maxPosX, maxPosY);
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

    public List<CustomObject> getRandomClippart() {

        Random randomGenerator = new Random();
        List<CustomObject> randomClippart = new ArrayList<CustomObject>();
        List<Integer> randomsInts = new ArrayList<Integer>();

        for (int i = 1; i < Settings.getInstance().getNbClippartToFind(); i++){
            Integer randomInt = randomGenerator.nextInt(Settings.getInstance().getNbClippartToDisplay());

            while (randomsInts.contains(randomInt)) {
                randomInt = randomGenerator.nextInt(Settings.getInstance().getNbClippartToDisplay());
            }

            randomsInts.add(randomInt);
            randomClippart.add(listObjects.get(randomInt));
        }

        return randomClippart;
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

    @Override
    public void onSensorChanged(SensorEvent event) {
        int sensor = event.sensor.getType();
        float [] values = event.values;
        long curTime = System.currentTimeMillis();

        synchronized (this) {
            if (sensor == Sensor.TYPE_ACCELEROMETER) {
                if ((curTime - mLastShakeTime) > MIN_TIME_BETWEEN_SHAKES_MILLISECS) {
                    float x = values[0];
                    float y = values[1];
                    float z = values[2];

                    double acceleration = Math.sqrt(Math.pow(x, 2) +
                            Math.pow(y, 2) +
                            Math.pow(z, 2)) - SensorManager.GRAVITY_EARTH;


                    if (acceleration > SHAKE_THRESHOLD) {
                        mLastShakeTime = curTime;
                        Collections.reverse(listObjects);
                    }
                }

            }
        }


    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
