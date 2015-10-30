package com.motouch.android.demo.ui;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.github.znacloud.SectorProgressBar;
import com.motouch.android.demo.R;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private SectorProgressBar mCirclePbr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mCirclePbr = (SectorProgressBar) findViewById(R.id.spb_progress);
        //set custom attribute
//        mCirclePbr.setSectorBackgroundColor(Color.parseColor("#55000000"));
//        mCirclePbr.setBorderColor(Color.GRAY);
//        mCirclePbr.setBorderWidth(4);
//        mCirclePbr.setShowTextPercent(true);

        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                mCirclePbr.setProgress((mCirclePbr.getProgress() + 2) % 101);
                //random color
//                mCirclePbr.setSectorColor(Color.argb(255,(int)(Math.random()*255),(int)(Math.random()*255),(int)(Math.random()*255)));
                handler.postDelayed(this, 200);
            }
        });

    }

}
