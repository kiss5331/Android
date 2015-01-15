package com.dalkomsoft02.ganggongui.todayfeed;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Calendar;


public class MainActivity extends ActionBarActivity implements View.OnClickListener {

    public static String SCHOOLCODE = null;

    public final int RESULT_CODE = 0202;

    private Button searchScoolBtn;

    private TextView TodaystateTV;

    private TextView TodayfoodTV;

    private TextView SchoolName;

    public FoodparserThread foodparserThread;

    private SharedPreferences preferences;

    private String codes;

    private int i = 0;

    private int check = 0;

    private boolean aBoolean;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        preferences = PreferenceManager.getDefaultSharedPreferences(this);


        Log.i("데이터 예시+++", "시작" + preferences.getString("Code", ""));


        searchScoolBtn = (Button) findViewById(R.id.Btn_Schoolsetting);

        searchScoolBtn.setOnClickListener(this);

        TodaystateTV = (TextView) findViewById(R.id.TV_Todaystate);

        TodayfoodTV = (TextView) findViewById(R.id.TV_foodinfo);

        SchoolName = (TextView) findViewById(R.id.TV_SchoolName);

        TodayfoodTV.setOnClickListener(this);


        if (preferences.getString("Code", "").equals("")) {
            SchoolName.setText("학교를 지정해주세요.");

        } else {

            codes = FoodparserThread.CODE = preferences.getString("Code", "");
            SchoolName.setText(preferences.getString("Name", ""));
            paser();
        }


    }

    @Override
    protected void onStart() {
        super.onStart();

    }


    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.Btn_Schoolsetting:
                Intent intent = new Intent(getApplicationContext(), SettingSchoolActivity.class);

                startActivityForResult(intent, RESULT_CODE);
                break;

        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RESULT_CODE) {


            FoodparserThread.CODE = preferences.getString("Code", "");


            paser();

            SchoolName.setText(preferences.getString("Name", ""));


        }


    }

    private void settingText() {


        Calendar oCalendar = Calendar.getInstance();

        i = oCalendar.get(Calendar.DAY_OF_WEEK) - 1;


        if (FoodparserThread.FoodNew[i].length() < 3) {
            FoodparserThread.FoodNew[i] = "\n\n\n식단없음\n\n\n";
        }
        TodayfoodTV.setText(FoodparserThread.dateNew[i] + "\n" + FoodparserThread.FoodNew[i]);


        TodayfoodTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i++;
                if (i == 7) {
                    i = 0;
                }
                if (FoodparserThread.FoodNew[i].length() < 3) {
                    FoodparserThread.FoodNew[i] = "\n\n\n식단없음\n\n\n";
                }
                TodayfoodTV.setText(FoodparserThread.dateNew[i] + "\n" + FoodparserThread.FoodNew[i]);


            }
        });


    }

    private void paser() {
        Calendar oCalendar = Calendar.getInstance();

        i = oCalendar.get(Calendar.DAY_OF_WEEK);


        codes = preferences.getString("Code", "");

        foodparserThread = null;


        foodparserThread = new FoodparserThread();
        Thread thread = new Thread(foodparserThread);

        thread.start();

        try {
            thread.join();
            settingText();
        } catch (Exception e) {

        }


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
