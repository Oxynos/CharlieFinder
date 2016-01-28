package com.m2dl.charliefinder.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.TextView;

import com.m2dl.charliefinder.R;
import com.m2dl.charliefinder.metier.SeekBarListener;
import com.m2dl.charliefinder.metier.Settings;

public class SettingsActivity extends AppCompatActivity {

    TextView difficult, tvSeekBar1, tvSeekBar2, tvSeekBar3, tvSeekBar4;
    SeekBar sb1, sb2, sb3, sb4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        // Setting for nb clippart to display
        sb1 = (SeekBar) findViewById(R.id.seekBar1);
        tvSeekBar1 = (TextView) findViewById(R.id.tvSeekBar1);
        sb1.setMax(90);
        sb1.setOnSeekBarChangeListener(new SeekBarListener(tvSeekBar1, 100, 10, 1));

        // Seting for nb clippart to find
        sb2 = (SeekBar) findViewById(R.id.seekBar2);
        tvSeekBar2 = (TextView) findViewById(R.id.tvSeekBar2);
        sb2.setMax(19);
        sb2.setOnSeekBarChangeListener(new SeekBarListener(tvSeekBar2, 20, 1, 1));

        // Setting for nb covering allow
        sb3 = (SeekBar) findViewById(R.id.seekBar3);
        tvSeekBar3 = (TextView) findViewById(R.id.tvSeekBar3);
        sb3.setMax(9);
        sb3.setOnSeekBarChangeListener(new SeekBarListener(tvSeekBar3, 10, 1, 1));

        // Setting for given time
        sb4 = (SeekBar) findViewById(R.id.seekBar4);
        tvSeekBar4 = (TextView) findViewById(R.id.tvSeekBar4);
        sb4.setMax(110);
        sb4.setOnSeekBarChangeListener(new SeekBarListener(tvSeekBar4, 120, 10, 1));

        setEnableSeekBar(false);

        Settings.getInstance().setEasy();
        setValueSeekBar(12, 3, 8, 20);
    }

    public void onRadioButtonClicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();

        switch (view.getId()) {
            case R.id.rBtEasy:
                if (checked) {
                    setEnableSeekBar(false);
                    setValueSeekBar(12, 3, 8, 20);
                    Settings.getInstance().setEasy();
                }
                break;
            case R.id.rBtNormal:
                if (checked) {
                    setEnableSeekBar(false);
                    setValueSeekBar(20, 3, 5, 20);
                    Settings.getInstance().setNormal();
                }
                break;
            case R.id.rBtHard:
                if (checked) {
                    setEnableSeekBar(false);
                    setValueSeekBar(40, 10, 2, 10);
                    Settings.getInstance().setHard();
                }
                break;
            case R.id.rBtPerso:
                if (checked) {
                    setEnableSeekBar(true);
                }
                break;
        }
    }

    public void validate(View view) {
        Intent myIntent = new Intent(this, MainActivity.class);
        startActivity(myIntent);

        Settings.getInstance().setPerso(sb1.getProgress(), sb2.getProgress(), sb3.getProgress(), sb4.getProgress());

        this.finish();
    }

    private void setEnableSeekBar(boolean b) {
        sb1.setEnabled(b);
        sb2.setEnabled(b);
        sb3.setEnabled(b);
        sb4.setEnabled(b);
    }

    private void setValueSeekBar(int a, int b, int c, int d) {
        sb1.setProgress(a);
        sb2.setProgress(b);
        sb3.setProgress(c);
        sb4.setProgress(d);
    }
}
