package ganggongui.dalkomsoft02.com.whatcolorgameapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;


public class SplashActivity extends ActionBarActivity {


    // 스플래시 지속 시간
    private final int RimitTime = 1000;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);


        // 스플래쉬 이후 메인 엑티비티 이동을 위한
        // 핸들러
        Handler handler = new Handler();


        // 1초후 이동
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                startActivity(
                        new Intent(getApplicationContext(), MainActivity.class)
                );


                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);

                // 이동 후 제거
                finish();

            }
        }, RimitTime);


    }


}
