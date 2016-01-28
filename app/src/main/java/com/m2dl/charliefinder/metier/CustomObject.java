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

    public CustomObject(String name, Bitmap bmp) {
        this.name = name;
        this.bmp = bmp;
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
