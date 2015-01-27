package ganggongui.dalkomsoft02.com.fibonachickenapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;


public class SpleahActivity extends ActionBarActivity {

    // 스플래쉬의 지속시간
    private final int REMIT_TIME = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spleah);


        // 액티비티 이동을 위한 핸들러 인스턴스

        Handler handler = new Handler();

        // 1초 후 실행되는 구문

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                // 메인 액티비티 이동
                startActivity(
                        new Intent(getApplicationContext(), MainActivity.class)
                );

                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);

                //스플래쉬 엑티비티 종료
                finish();

            }
        }, REMIT_TIME);


    }


}
