package ganggongui.dalkomsoft02.com.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;


public class SpleshActivity extends ActionBarActivity {

    private final int RemiteTime = 1200;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        final Handler handler = new Handler();

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {


                startActivity(
                        new Intent(getApplicationContext(), MainActivity.class
                        )
                );


                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);

                finish();

            }
        }, RemiteTime);

    }

}
