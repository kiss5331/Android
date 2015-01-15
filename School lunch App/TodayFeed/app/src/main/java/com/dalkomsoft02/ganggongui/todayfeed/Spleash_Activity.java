package com.dalkomsoft02.ganggongui.todayfeed;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.widget.ImageView;

import com.parse.Parse;
import com.parse.ParseACL;
import com.parse.ParseCrashReporting;
import com.parse.ParseUser;


public class Spleash_Activity extends ActionBarActivity {

    private Intent MainIntent;

    private Handler MoveHandler;

    private ImageView SpleashBackground;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spleash);


        MoveHandler = new Handler();


        try {
            Parse();
        } catch (Exception e) {
            e.printStackTrace();
        }


        MoveHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // get info for MainActivity
                MainIntent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(MainIntent);
                finish();

            }
            // time re mite 2 sec
        }, 1000);

    }


    @Override
    protected void onStart() {
        super.onStart();

        SpleashBackground = (ImageView) findViewById(R.id.IV_Spleash);


    }


    private void Parse() {

        ParseCrashReporting.enable(this);

        // Add your initialization code here
        Parse.initialize(this, "MJn8wZ63wBRFjp51VxZEzz2Vgb1zU9Bd4yLa57iW", "pMwQlMcBy5DV2srY9AYWwlJIItUaDHDWbm1jLzJ6");


        ParseUser.enableAutomaticUser();
        ParseACL defaultACL = new ParseACL();

        // If you would like all objects to be private by default, remove this line.
        defaultACL.setPublicReadAccess(true);

        ParseACL.setDefaultACL(defaultACL, true);

    }
}



