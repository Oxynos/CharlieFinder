package com.m2dl.charliefinder.metier;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.m2dl.charliefinder.R;

/**
 * Created by cedricrohaut on 1/28/16.
 */
public class CustomObject {
    private String name ;
    private Bitmap bmp;
    private int x;
    private int y;

    public CustomObject(String name, Bitmap bmp) {
        this.name = name;
        this.bmp = bmp;
    }

    public CustomObject(String name, Bitmap bmp, int x, int y) {
        this.name = name;
        this.bmp = bmp;
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Bitmap getBmp() {
        return bmp;
    }

    public void setBmp(Bitmap bmp) {
        this.bmp = bmp;
    }
}
