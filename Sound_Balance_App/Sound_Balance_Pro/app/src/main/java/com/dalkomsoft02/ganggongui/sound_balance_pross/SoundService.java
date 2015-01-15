package com.dalkomsoft02.ganggongui.sound_balance_pross;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.drawable.BitmapDrawable;
import android.media.AudioManager;
import android.os.IBinder;
import android.util.Log;

public class SoundService extends Service {

    public static boolean running;

    public static int SOUND_BALANCE = 0;

    private int SOUND_COUNT = 0;

    private final int SOUND_VAR = 8000;

    private final int SOUND_MIL = 256;

    private PendingIntent pendingIntent;

    private Notification notification;


    private int i = 0;


    public SoundService() {


    }


    /**
     * Called by the system to notify a Service that it is no longer used and is being removed.  The
     * service should clean up any resources it holds (threads, registered
     * receivers, etc) at this point.  Upon return, there will be no more calls
     * in to this Service object and it is effectively dead.  Do not call this method directly.
     */

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        BitmapDrawable drawable = (BitmapDrawable) getResources().getDrawable(R.drawable.ic_icon);


        notification = new Notification(R.drawable.ic_icon, getString(R.string.notif_title), System.currentTimeMillis());
        notification.flags = Notification.FLAG_NO_CLEAR;
        notification.defaults = Notification.DEFAULT_VIBRATE;
        notification.icon = R.drawable.ic_icon;
        notification.largeIcon = drawable.getBitmap();
        notification.tickerText = getString(R.string.notif_subtitle);

        pendingIntent = PendingIntent.getActivity(this, 0, new Intent(this, MainActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP), PendingIntent.FLAG_UPDATE_CURRENT);
        notification.setLatestEventInfo(this, getString(R.string.notif_title), getString(R.string.notif_subtitle), pendingIntent);

        startForeground(MainActivity.NOT_CODE, notification);

        AudioReader audioReader = new AudioReader();


        audioReader.startReader(SOUND_VAR, SOUND_MIL, new AudioReader.Listener() {
            @Override
            public void onReadComplete(int decibel) {

                if (!running) {

                    AudioManager audioManager = (AudioManager) getSystemService(AUDIO_SERVICE);

                    SOUND_COUNT += decibel + 74;


                    if (i == 99) {

                        SOUND_BALANCE = SOUND_COUNT / 100;

                        Log.e("50회간 평균 데시벨", SOUND_BALANCE + "Db");

                        IntentFilter intentFilter = new IntentFilter();

                        intentFilter.addAction("android.intent.action.HEADSET_PLUG");

                        HeadsetReceiver headsetReceiver = new HeadsetReceiver();

                        registerReceiver(headsetReceiver, intentFilter);

                        if (HeadsetReceiver.HeadSet) {
                            Log.i("이어폰 상태", "연결됨");


                            if (SOUND_LV_6 <= SOUND_BALANCE && SOUND_LV_7 >= SOUND_BALANCE)

                            {

                                audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, Sound[6], 0);

                            } else if (SOUND_LV_7 <= SOUND_BALANCE)

                            {

                                audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, Sound[7], 0);

                            } else if (SOUND_LV_5 <= SOUND_BALANCE && SOUND_LV_6 >= SOUND_BALANCE)

                            {

                                audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, Sound[5], 0);

                            } else if (SOUND_LV_4 <= SOUND_BALANCE && SOUND_LV_5 >= SOUND_BALANCE)

                            {

                                audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, Sound[4], 0);

                            } else if (SOUND_LV_3 <= SOUND_BALANCE && SOUND_LV_4 >= SOUND_BALANCE)

                            {

                                audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, Sound[3], 0);

                            } else if (SOUND_LV_2 <= SOUND_BALANCE && SOUND_LV_3 >= SOUND_BALANCE)

                            {

                                audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, Sound[2], 0);

                            } else if (SOUND_LV_1 <= SOUND_BALANCE && SOUND_LV_2 >= SOUND_BALANCE)

                            {

                                audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, Sound[1], 0);

                            } else if (0 <= SOUND_BALANCE && SOUND_LV_1 >= SOUND_BALANCE)

                            {
                                audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, Sound[0], 0);
                            } else {

                            }


                        } else {

                            Log.i("이어폰 상태", "연결안됨");
                        }


                        i = 0;
                        SOUND_COUNT = 0;
                    } else {
                        i++;

                    }

                } else if (running) {

                }

            }

            @Override
            public void onReadError(int error) {

            }
        });
        return super.onStartCommand(intent, flags, startId);
    }

    public static int[] Sound = {4, 5, 6, 7, 8, 9, 10, 11};

    private final int SOUND_LV_1 = 20;

    private final int SOUND_LV_2 = 30;

    private final int SOUND_LV_3 = 40;

    private final int SOUND_LV_4 = 50;

    private final int SOUND_LV_5 = 60;

    private final int SOUND_LV_6 = 70;

    private final int SOUND_LV_7 = 80;


}
