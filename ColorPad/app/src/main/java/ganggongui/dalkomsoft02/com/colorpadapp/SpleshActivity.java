package ganggongui.dalkomsoft02.com.colorpadapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;


public class SpleshActivity extends ActionBarActivity {

    //화면 이동 시간
    private final int RemiteTime = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splesh);

        // 메인화면 이동을 위한
        // 핸들러 객체 생성
        android.os.Handler handler = new Handler();

        //1초후 화면 전환
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                //액티비티 이동
                startActivity(new Intent(getApplicationContext(), MainActivity.class));

                //전환 후 스플래쉬 화면은 종료
                finish();

            }
        }, RemiteTime);


    }

}
