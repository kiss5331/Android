package com.dalkomsoft02.ganggongui.sound_balance_pross;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class HeadsetReceiver extends BroadcastReceiver {

    public static boolean HeadSet;

    public HeadsetReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.


        if (intent.hasExtra("state")) {

            if (0 == intent.getIntExtra("state", 0)) {
                HeadSet = false;

            } else {
                HeadSet = true;

            }
        }


    }
}
