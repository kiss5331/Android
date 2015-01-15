package com.dalkomsft02.gangongui.usercustom;

import android.app.FragmentTransaction;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.SeekBar;
import android.widget.TextView;

import com.dalkomsoft02.ganggongui.sound_balance_pross.MainActivity;
import com.dalkomsoft02.ganggongui.sound_balance_pross.R;
import com.dalkomsoft02.ganggongui.sound_balance_pross.SoundService;

public class UserCustomActivity extends ActionBarActivity implements SeekBar.OnSeekBarChangeListener {

    private Toolbar toolbar;

    private TextView DashBall_Tv;

    public static final int[] Sound = {4, 5, 6, 7, 8, 9, 10, 11};

    private final String ToolbarTitle = "Sound Balance";

    public static final int[] SeekBar_ID = {
            R.id.sickbar_1, R.id.sickbar_2, R.id.sickbar_3,
            R.id.sickbar_4, R.id.sickbar_5, R.id.sickbar_6,
            R.id.sickbar_7};

    public static SeekBar[] TubSeekbar;

    private int i = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_user_custom);

        setID();

        ToolBarSetting();


        new Thread(new Tv_Thread()).start();


    }


    /**
     * View 객체 아이디 지정
     */

    private void setID() {

        toolbar = (Toolbar) findViewById(R.id.main_toolbar);

        DashBall_Tv = (TextView) findViewById(R.id.TV_user_db);

        TubSeekbar = new SeekBar[SeekBar_ID.length];


        for (int ID : SeekBar_ID) {

            TubSeekbar[i] = (SeekBar) findViewById(ID);

            TubSeekbar[i].setProgress(MainActivity.preferences.getInt(MainActivity.preferences.getInt("MODE", 0) + "Sound" + i, Sound[i]));

            TubSeekbar[i].setOnSeekBarChangeListener(this);


            i++;
        }

        i = 0;

    }


    /**
     * 현재 데시벨 View 쓰레드
     */

    class Tv_Thread extends Handler implements Runnable {


        /**
         * Starts executing the active part of the class' code. This method is
         * called when a thread is started that has been created with a class which
         * implements {@code Runnable}.
         */
        @Override
        public void run() {

            while (true) {

                try {

                    Thread.sleep(200);

                    sendMessage(obtainMessage());

                } catch (Exception e) {
                    e.printStackTrace();
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

            DashBall_Tv.setText(SoundService.SOUND_BALANCE + "dB");
        }
    }


    /**
     * Notification that the progress level has changed. Clients can use the fromUser parameter
     * to distinguish user-initiated changes from those that occurred programmatically.
     *
     * @param seekBar  The SeekBar whose progress has changed
     * @param progress The current progress level. This will be in the range 0..max where max
     *                 was set by {@link android.widget.ProgressBar#setMax(int)}. (The default value for max is 100.)
     * @param fromUser True if the progress change was initiated by the user.
     */
    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {


        switch (seekBar.getId()) {

            case R.id.sickbar_1:

                setSound(0, progress, MainActivity.preferences.getInt("MODE", 0));

                break;

            case R.id.sickbar_2:

                setSound(1, progress, MainActivity.preferences.getInt("MODE", 0));

                break;

            case R.id.sickbar_3:

                setSound(2, progress, MainActivity.preferences.getInt("MODE", 0));

                break;

            case R.id.sickbar_4:

                setSound(3, progress, MainActivity.preferences.getInt("MODE", 0));


                break;

            case R.id.sickbar_5:

                setSound(4, progress, MainActivity.preferences.getInt("MODE", 0));


                break;

            case R.id.sickbar_6:

                setSound(5, progress, MainActivity.preferences.getInt("MODE", 0));


                break;

            case R.id.sickbar_7:

                setSound(6, progress, MainActivity.preferences.getInt("MODE", 0));


                break;


        }

    }

    /**
     * Notification that the user has finished a touch gesture. Clients may want to use this
     * to re-enable advancing the seekbar.
     *
     * @param seekBar The SeekBar in which the touch gesture began
     */
    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }

    /**
     * Notification that the user has started a touch gesture. Clients may want to use this
     * to disable advancing the seekbar.
     *
     * @param seekBar The SeekBar in which the touch gesture began
     */
    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {


    }

    /**
     * @param num      is progress number
     *                 f
     * @param progress is Sound
     */
    private void setSound(int num, int progress, int mode) {


        SoundService.Sound[num] = progress;

        MainActivity.editor.putInt(mode + "Sound" + num, SoundService.Sound[num]);

        MainActivity.editor.commit();


    }


    private void ToolBarSetting() {
        toolbar.setTitle(ToolbarTitle);

        toolbar.setTitleTextColor(getResources().getColor(android.R.color.white));

        toolbar.inflateMenu(R.menu.menu_main);

        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {

                if (menuItem.getItemId() == R.id.action_settings) {

                    showDialog();

                }

                return false;
            }
        });
    }

    private void showDialog() {

        FragmentTransaction transaction = getFragmentManager().beginTransaction();

        transaction.addToBackStack(null);

        UserPlusDialogFragment dialogFragment = new UserPlusDialogFragment();

        dialogFragment.show(transaction, "teg");

    }


}
