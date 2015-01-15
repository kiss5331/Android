package com.wkdrddml.dalkomsft.datetimepicker_sample;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;
import java.util.GregorianCalendar;


public class MyActivity extends Activity implements View.OnClickListener {

    TextView mTimeview;
    Button mviewTimepicker;
    String mHour, mMinute;

    @Override
    public void onClick(View v) {
        if (v.getId() ==  R.id.onTimebtn){
            new DatePickerDialog(MyActivity.this,dDatepicker,2014,9,15).show();
            new TimePickerDialog(MyActivity.this,dTimepicker,2,02,false).show();

        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
        setID();

         mviewTimepicker.setOnClickListener(this);




    }


    private DatePickerDialog.OnDateSetListener  dDatepicker = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

        }
    };


    private TimePickerDialog.OnTimeSetListener dTimepicker = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            mHour = hourOfDay+"";
            mMinute = minute +"";
            mTimeview.setText(mHour+"시"+"  "+mMinute+"분");

        }
    };


    private void setID() {
        mTimeview = (TextView) findViewById(R.id.viewtime);
        mviewTimepicker = (Button)findViewById(R.id.onTimebtn);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.my, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
