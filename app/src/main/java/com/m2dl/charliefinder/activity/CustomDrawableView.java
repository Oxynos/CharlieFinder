package com.m2dl.charliefinder.activity;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.media.MediaPlayer;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.m2dl.charliefinder.R;
import com.m2dl.charliefinder.metier.CustomObject;
import com.m2dl.charliefinder.metier.Settings;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CustomDrawableView extends View {
    Bitmap  bmp;
    private int maxH, maxW;

    private Handler mHandler, mHandler2, mHandler3;
    private int cptSeconds = 0;
    ImageView iv1, iv2, iv3;
    Integer currentImageToFind = 0;

    List<CustomObject> listObjects = new ArrayList<>();
    List<CustomObject> randomObjects = new ArrayList<>();

    public CustomDrawableView(Context context, AttributeSet attr) {
        super(context, attr);

        mHandler = new Handler();
        mHandler2 = new Handler();
        mHandler3 = new Handler();
        mHandler.postDelayed(changeDirection, 50);
        mHandler2.postDelayed(moveObject, 60);

        maxH = 100;
        maxW = 100;

        this.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View arg0, MotionEvent event) {
                float x = event.getX();
                float y = event.getY();

                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        checkResult(x, y);
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

    public int getMaxH() {
        return maxH;
    }

    public void setMaxH(int maxH) {
        this.maxH = maxH;
    }

    public int getMaxW() {
        return maxW;
    }

    public void setMaxW(int maxW) {
        this.maxW = maxW;
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

    public void setRandomObjects(List<CustomObject> listObjects) {
        this.randomObjects = listObjects;
        invalidate();
    }

    public void setImageViews(ImageView... iv) {
        iv1 = iv[0];
        iv2 = iv[1];
        iv3 = iv[2];

        mHandler3.postDelayed(changeImageToFind, 2000);
    }

    private Runnable changeDirection = new Runnable() {
        public void run() {
            for (CustomObject co : listObjects) {
                Random r1 = new Random();
                int direct = r1.nextInt(5);

                co.setDirection(direct);
                invalidate();
            }

            mHandler.postDelayed(this, 2000);
        }
    };

    private Runnable changeImageToFind = new Runnable() {
        public void run() {

            if (currentImageToFind < randomObjects.size())
                iv1.setImageBitmap(randomObjects.get(currentImageToFind).getBmp());

            if (currentImageToFind+1 < randomObjects.size())
                iv2.setImageBitmap(randomObjects.get(currentImageToFind+1).getBmp());

            if (currentImageToFind+2 < randomObjects.size())
                iv3.setImageBitmap(randomObjects.get(currentImageToFind+2).getBmp());

            if (currentImageToFind > randomObjects.size()) {
                currentImageToFind = 0;
            } else {
                currentImageToFind += 3;
            }

            mHandler.postDelayed(this, 2000);
            invalidate();
        }
    };

    public void checkResult(float x, float y) {
        int minX, minY, maxX, maxY;
        List<CustomObject> listMatch = new ArrayList<>();
        for (CustomObject co : listObjects) {
            minX = co.getX();
            maxX = co.getX() + co.getBmp().getWidth();
            minY = co.getY();
            maxY = co.getY() + co.getBmp().getHeight();

            if (x > minX && x < maxX && y > minY && y < maxY) {
                listMatch.add(co);
                System.out.println("Match");
            }
        }
        System.out.println(listMatch.size() + "éléments pour votre sélection");
        //System.out.println(listMatch);
    }

    private Runnable moveObject = new Runnable() {
        public void run() {

            for (CustomObject co : listObjects) {
                switch (co.getDirection()) {
                    case 1 :
                        if (co.getY() - 1 > 0) {
                            co.setY(co.getY() - 1);
                        }
                        break;
                    case 2 :
                        if (co.getX() + 1 < co.getMaxPosX()) {
                            co.setX(co.getX() + 1);
                        }
                        break;
                    case 3 :
                        if (co.getY() + 1 < co.getMaxPosY()) {
                            co.setY(co.getY() + 1);
                        }
                        break;
                    case 4 :
                        if (co.getX() - 1 > 0) {
                            co.setX(co.getX() - 1);
                        }
                        break;
                }

                invalidate();

                //int res1 = r1.nextInt(maxH - co.getBmp().getHeight() - 160);
                //int res2 = r1.nextInt(maxW - co.getBmp().getWidth() - 30);

                //co.setX(res2);
                //co.setY(res1);
            }


            mHandler.postDelayed(this, 10);
        }
    };
}