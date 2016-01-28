package com.m2dl.charliefinder.activity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.WindowManager;

import java.util.ArrayList;
import java.util.List;

import com.m2dl.charliefinder.R;
import com.m2dl.charliefinder.metier.CustomObject;
import java.lang.reflect.Field;

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
    }
}
