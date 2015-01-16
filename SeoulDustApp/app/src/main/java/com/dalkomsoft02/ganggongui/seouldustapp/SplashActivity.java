package com.dalkomsoft02.ganggongui.seouldustapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;


public class SplashActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        final int REMIT_TIME = 700;

        final Handler handler = new Handler();

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                //메인 엑티비티 호출
                getApplication()
                        .startActivity(
                                new Intent(getApplicationContext(),
                                        MainActivity.class)
                                        .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK));

                //스플래시 액티비티 종료
                SplashActivity.this.finish();

            }

            //리미트 타임 0.7초
        }, REMIT_TIME);


    }
}
