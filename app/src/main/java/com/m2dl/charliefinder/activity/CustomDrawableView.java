package com.m2dl.charliefinder.activity;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

import com.m2dl.charliefinder.R;
import com.m2dl.charliefinder.metier.CustomObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CustomDrawableView extends View {
    public int width;
    public  int height;
    Bitmap  bmp, resBmp;
    private Path path;
    private Paint paint;

    List<CustomObject> listObjects = new ArrayList<>();

    public CustomDrawableView(Context context, AttributeSet attr) {
        super(context, attr);

        this.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View arg0, MotionEvent event) {
                float x = event.getX();
                float y = event.getY();

                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        invalidate();
                        break;
                    case MotionEvent.ACTION_MOVE:
                        invalidate();
                        break;
                    case MotionEvent.ACTION_UP:
                        Bitmap bitmap;
                        try {

                        } catch (Exception e) {
                        }
                        invalidate();
                        break;
                }
                return true;
            }
        });

    }

    protected void onDraw(Canvas canvas) {

        for (CustomObject co : listObjects) {
            canvas.drawBitmap(co.getBmp(), co.getX(), co.getY(), null);
        }

        invalidate();
    }

    public Bitmap getBmp() {
        return bmp;
    }

    public void setBmp(Bitmap bmp) {
        this.bmp = bmp;
        invalidate();
    }

    public List<CustomObject> getListObjects() {
        return listObjects;
    }

    public void setListObjects(List<CustomObject> listObjects) {
        this.listObjects = listObjects;
        invalidate();
    }
}