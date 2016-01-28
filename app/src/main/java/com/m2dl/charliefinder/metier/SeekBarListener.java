package com.m2dl.charliefinder.metier;

import android.widget.SeekBar;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * Created by veoth on 28/01/16.
 */
public class SeekBarListener implements SeekBar.OnSeekBarChangeListener {

    TextView tv;
    Integer max, min, step;

    public SeekBarListener(TextView tv, Integer max, Integer min, Integer step) {
        this.tv = tv;
        this.max = max;
        this.min = min;
        this.step = step;

        tv.setText(Integer.toString(min));
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        int value = min + (progress * step);
        tv.setText(String.valueOf(value));
    }


    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }
}
