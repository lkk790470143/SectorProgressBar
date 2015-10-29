package com.motouch.android.timepickerdemo.ui;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.motouch.android.sectorprogressbar.SectorProgressBar;
import com.motouch.android.timepickerdemo.R;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private TextView mTimeTv;
    private SectorProgressBar mCirclePbr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTimeTv = (TextView) findViewById(R.id.tv_time);
        mTimeTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTimePicker();
            }
        });

        mCirclePbr = (SectorProgressBar) findViewById(R.id.spb_progress);
//        mCirclePbr.setSectorBackgroundColor(Color.parseColor("#55000000"));
//        mCirclePbr.setBorderColor(Color.GRAY);
//        mCirclePbr.setBorderWidth(4);
//        mCirclePbr.setShowTextPercent(true);
        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                mCirclePbr.setProgress((mCirclePbr.getProgress() + 2) % 101);
//                mCirclePbr.setSectorColor(Color.argb(255,(int)(Math.random()*255),(int)(Math.random()*255),(int)(Math.random()*255)));
                handler.postDelayed(this, 200);
            }
        });
//        TimerTask task = new TimerTask() {
//            @Override
//            public void run() {

//            }
//        };

//        Timer timer = new Timer();
//        timer.schedule(task,1000,3000);

    }

    private void showTimePicker() {
        final com.motouch.android.timepicker.TimePicker picker = new com.motouch.android.timepicker.TimePicker(this);
        picker.setInitTime(mTimeTv.getText().toString());
        new AlertDialog.Builder(this)
                .setView(picker)
                .setCancelable(true)
                .setTitle("选择时间")
                .setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String timeText = picker.getPickedTime();
                        Log.i(TAG, "time text =>" + timeText);
                        mTimeTv.setText(timeText);

                        int[] timeArray = picker.getPickedTimeArray();
                        Log.i(TAG, "time array =>" + Arrays.toString(timeArray));
                        dialog.dismiss();
                    }
                }).show();
    }
}
