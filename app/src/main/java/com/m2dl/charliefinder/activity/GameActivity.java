package com.m2dl.charliefinder.activity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Chronometer;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import com.m2dl.charliefinder.R;
import com.m2dl.charliefinder.metier.CustomObject;
import java.lang.reflect.Field;

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

        for (int i = 0, max = fields.length; i < max; i++) {
            try {
                String name = fields[i].getName();
                System.out.println(fields[i].getType());
                System.out.println(name);
                //Bitmap bitmap= BitmapFactory.decodeResource(getResources(),);
            } catch (Exception e) {
                continue;
            }
        }

        for (int i = 0 ; i < 1 ; i++) {
            Bitmap bitmap= BitmapFactory.decodeResource(getResources(),
                    R.drawable.apple);
            String name = String.valueOf(R.drawable.apple);
            listObjects.add(new CustomObject(name, bitmap));

        }

        customDrawableView.setListObjects(listObjects);
        chronometer.start();
    }
}
