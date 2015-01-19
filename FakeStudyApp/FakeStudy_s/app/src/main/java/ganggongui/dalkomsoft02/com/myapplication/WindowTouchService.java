package ganggongui.dalkomsoft02.com.myapplication;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.IBinder;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;

public class WindowTouchService extends Service {

    public static View mView;
    public static WindowManager mManager;

    private WindowManager.LayoutParams mParams;

    public WindowTouchService() {
    }

    /**
     * Called by the system when the service is first created.  Do not call this method directly.
     */
    @Override
    public void onCreate() {
        super.onCreate();

        LayoutInflater mInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mView = mInflater.inflate(R.layout.windowbutton, null);

        mParams = new WindowManager.LayoutParams(
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.TYPE_PHONE,
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
                PixelFormat.TRANSLUCENT);

        mParams.gravity = Gravity.TOP | Gravity.RIGHT;

        mManager = (WindowManager) getSystemService(WINDOW_SERVICE);
        mManager.addView(mView, mParams);

        mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity
                        (new Intent(getApplicationContext(), FakeStudyActivity.class)
                                        .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        );
            }
        });

    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");


    }

    @Override
    public boolean stopService(Intent name) {
        return super.stopService(name);


    }
}
