package com.dalkomsoft02.ganggongui.sound_balance_pross;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.dalkomsft02.gangongui.usercustom.SoundBalanceInfoActivity;
import com.dalkomsft02.gangongui.usercustom.UserCustomActivity;

import CustomClass.CustomToast;
import Main_data.EqDatas;
import Main_data.StringResources;


public class MainActivity extends ActionBarActivity implements View.OnClickListener {


    /**
     * 트라이얼버전 입니다.
     */

    public static AudioManager audioManager;

    public static SharedPreferences preferences;

    public static SharedPreferences.Editor editor;

    public final int[] Sound = {4, 5, 6, 7, 8, 9, 10, 11};

    private final int[] Thread_num = {0, 7, 14, 21, 28, 35, 42, 49, 56, 63, 70, 77, 84, 91, 98, 105, 112, 119, 126, 133};

    public static final int NOT_CODE = 020;

    private Toolbar toolbar;

    public static boolean Service;

    private CustomToast MyToast;

    private Button Start_Service_Btn;

    private Button Stop_Service_Btn;

    private Button Customization_Btn;

    private Button Reset_Btn;

    private Button Info_Btn;

    private ImageView[] IV_eq;

    private EqDatas eqDatas;

    private StringResources resources;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        /**
         *
         * 트라이얼버전 입니다.
         *
         * */


        resources = new StringResources(getApplicationContext());

        //setting  View ID


        setID();

        audioManager = (AudioManager) getSystemService(AUDIO_SERVICE);

        preferences = PreferenceManager.getDefaultSharedPreferences(this);

        editor = preferences.edit();

        SoundsettingsRecovery();

        //현재 서비스 가동여부

        Service = preferences.getBoolean("Service", false);

        //custom toast 메모리 할당

        MyToast = new CustomToast(getApplicationContext());

        //툴바 설정

        toolbar.setTitle(resources.ToolbarTitle);

        toolbar.setTitleTextColor(getResources().getColor(android.R.color.white));


        //Sound Serivce 초기화

        setSoundService();


        // 애니메이션 쓰레드 시작

        for (int NUMBER : Thread_num) {

            new Thread(new aniThread(NUMBER)).start();

        }


    }


    /**
     * 저장된 사용자 설정 복구 및 기본 설정 복구
     */

    private void setSoundService() {

        //preferences key []
        String[] preferences_Key

                =

                {"Sound0", "Sound1", "Sound2",
                        "Sound3", "Sound4", "Sound5",
                        "Sound6", "Sound7"};


        int i = 0;

        for (String key : preferences_Key) {


            if (preferences.getInt(key, 0) != 0) {

                SoundService.Sound[i] = preferences.getInt(key, 0);

                Log.e(SoundService.Sound[i] + "", "변경된 서비스 값");
            }

            Log.e(SoundService.Sound[i] + "", "서비스 값");

            i++;
        }

    }


    /**
     * View 객체 아이디 지정
     */

    private void setID() {


        IV_eq = new ImageView[140];

        eqDatas = new EqDatas();


        int i = 0;

        for (int[] Array_id : eqDatas.arrayList) {

            for (int id : Array_id) {

                IV_eq[i] = (ImageView) findViewById(id);

                IV_eq[i].setImageDrawable(getResources().getDrawable(R.drawable.main_eq_ff));

                i++;
            }

        }

        toolbar = (Toolbar) findViewById(R.id.main_toolbar);

        Start_Service_Btn = (Button) findViewById(R.id.main_startBtn);

        Stop_Service_Btn = (Button) findViewById(R.id.main_stopBtn);

        Customization_Btn = (Button) findViewById(R.id.main_customBtn);

        Reset_Btn = (Button) findViewById(R.id.main_resetBtn);

        Info_Btn = (Button) findViewById(R.id.main_infoBtn);

        Start_Service_Btn.setOnClickListener(this);

        Stop_Service_Btn.setOnClickListener(this);

        Customization_Btn.setOnClickListener(this);

        Reset_Btn.setOnClickListener(this);

        Info_Btn.setOnClickListener(this);


    }


    /**
     * 버튼 클릭 이벤트
     * <p/>
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.main_startBtn:


                if (!Service) {
                    MyToast.Toast(resources.Start_Meg_start);
                } else {
                    Strart_Service(Service);

                }

                Strart_Service(Service);

                break;

            case R.id.main_stopBtn:

                if (!Service) {

                    MyToast.Toast(resources.Start_Meg_stop_false);

                } else {

                    MyToast.Toast(resources.Start_Meg_stop);

                    Stop_Service(Service);
                }


                break;

            case R.id.main_customBtn:


                if (!Service) {

                    MyToast.Toast(resources.Start_Meg_false);

                    Stop_Service(Service);

                    Strart_Service(Service);

                    startActivity(

                            new Intent(getApplicationContext(), UserCustomActivity.class)

                    );
                } else {

                    startActivity(

                            new Intent(getApplicationContext(), UserCustomActivity.class)

                    );


                }

                break;

            case R.id.main_infoBtn:


                startActivity(

                        new Intent(getApplicationContext(), SoundBalanceInfoActivity.class)

                );

                break;

            case R.id.main_resetBtn:


                showDialog();

                break;


        }

    }


    /**
     * 서비스 종료
     * <p/>
     * Check Service Whether the operation
     *
     * @param Service is boolean
     */
    public void Stop_Service(boolean Service) {


        if (Service) {


            SoundService.running = true;

            getApplicationContext().

                    stopService(new Intent(getApplicationContext(), SoundService.class));


            this.Service = false;

            editor.putBoolean("Service", false);

            editor.commit();
        } else if (!Service) {


        }


    }


    /**
     * 서비스 시작
     * <p/>
     * Check Service Whether the operation
     *
     * @param Service is boolean
     */
    private void Strart_Service(boolean Service) {


        if (!Service) {

            SoundService.running = false;

            startService(

                    new Intent(getApplicationContext(), SoundService.class)

            );


            this.Service = true;

            editor.putBoolean("Service", true)
                    .commit();


        } else if (Service) {


            SoundService.running = false;

            startService(

                    new Intent(getApplicationContext(), SoundService.class)

            );


            editor.putBoolean("Service", true)
                    .commit();

        }


    }


    /**
     * 애니메이션 쓰레드 || 핸들러
     */

    class aniThread extends Handler implements Runnable {

        private boolean aBoolean;

        private int TheadNumber = 0;

        private final int SLEEP_TIME = 150;

        public aniThread(int TheadNumber) {

            this.TheadNumber = TheadNumber;
        }

        @Override
        public void run() {


            while (true) {

                if (!aBoolean) {
                    Message message = obtainMessage();

                    try {

                        Thread.sleep(SLEEP_TIME);


                    } catch (Exception e) {

                        e.printStackTrace();
                    }

                    aniThread
                            .this
                            .sendMessage(message);

                } else {

                }
            }
        }

        /**
         * Subclasses must implement this to receive messages.
         *
         * @param msg
         */
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            aBoolean = true;
            int a = ((int) (Math.random() * 8)) - 1;


            if (a != 6) {
                for (int k = 6; k >= a + 1; k--) {


                    IV_eq[k + TheadNumber].setImageDrawable(getResources().getDrawable(R.drawable.main_eq_ff));

                }
            }

            for (int k = 0; k <= a; k++) {


                IV_eq[k + TheadNumber].setImageDrawable(getResources().getDrawable(eqDatas.Eq_Image[k]));


            }

            aBoolean = false;
        }
    }


    /**
     * 유저 설정 초기화 다이얼로그  yes or no
     */
    private void showDialog() {
        DialogFragment newFragment = Initialization_dialog.newInstance(
                R.string.dierogtitle);
        newFragment.show(getSupportFragmentManager(), "dialog");
    }

    public void doPositiveClick() {

        int i = 0;

        for (int SOUND : Sound) {

            MainActivity.editor.putInt(MainActivity.preferences.getInt("MODE", 0) + "Sound" + i, SOUND);

            i++;
        }

        editor.commit();


        MyToast.Toast(getString(R.string.install));


    }

    public void doNegativeClick() {


    }

    public static class Initialization_dialog extends DialogFragment {

        public static Initialization_dialog newInstance(int title) {

            Initialization_dialog dialog = new Initialization_dialog();

            Bundle bundle = new Bundle();

            bundle.putInt("title", title);

            dialog.setArguments(bundle);

            return dialog;

        }


        @NonNull
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {

            int title = getArguments().getInt("title");

            return new AlertDialog.Builder(getActivity())
                    .setTitle(title)
                    .setPositiveButton("Yes",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int whichButton) {
                                    ((MainActivity) getActivity()).doPositiveClick();


                                }
                            }
                    )
                    .setNegativeButton("No",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int whichButton) {
                                    ((MainActivity) getActivity()).doNegativeClick();
                                }
                            }
                    )
                    .create();
        }
    }


    /**
     * 유저 설정 초기화
     */

    public void SoundsettingsRecovery() {

        int i = 0;

        for (int SOUND : Sound) {

            SoundService.Sound[i] = preferences.getInt(MainActivity.preferences.getInt("MODE", 0) + "Sound" + i, SOUND);

            i++;

        }


    }

    @Override
    protected void onStop() {
        super.onStop();
        SoundsettingsRecovery();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        SoundsettingsRecovery();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        SoundsettingsRecovery();
    }


}
