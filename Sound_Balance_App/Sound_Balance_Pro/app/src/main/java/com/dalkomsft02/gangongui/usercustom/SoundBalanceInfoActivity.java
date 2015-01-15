package com.dalkomsft02.gangongui.usercustom;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;

import com.dalkomsoft02.ganggongui.sound_balance_pross.R;
import com.dalkomsoft02.ganggongui.sound_balance_pross.SoundService;

public class SoundBalanceInfoActivity extends ActionBarActivity {

    private final String ToolbarTitle = "Sound Balance";

    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sound_balance_info);

        SoundService.running = false;




        //툴바 설정

        toolbar = (Toolbar) findViewById(R.id.main_toolbar);

        toolbar.setTitle(ToolbarTitle);

        toolbar.setTitleTextColor(getResources().getColor(android.R.color.white));

    }


}
