package ganggongui.dalkomsoft02.com.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity implements View.OnClickListener {

    private final String EARR_CODE = MainActivity.class.getName();

    private Button StartBtn;

    private Button EndBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setID();


    }

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.Btnstart:

                Toast.makeText(getApplicationContext(), getString(R.string.start_text), Toast.LENGTH_SHORT).show();

                startService(
                        new Intent(this, WindowTouchService.class)
                );

                break;


            case R.id.BtnEnd:

                try {

                    Toast.makeText(getApplicationContext(), getString(R.string.exit_text), Toast.LENGTH_SHORT).show();
                    stopService(
                            new Intent(this, WindowTouchService.class)
                    );

                    WindowTouchService.mManager.removeView(WindowTouchService.mView);


                } catch (Exception e) {
                    Log.e(EARR_CODE, e.getMessage());
                }


                break;

            default:

                break;

        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_splesh, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void setID() {

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);


        StartBtn = (Button) findViewById(R.id.Btnstart);

        EndBtn = (Button) findViewById(R.id.BtnEnd);

        StartBtn.setOnClickListener(this);

        EndBtn.setOnClickListener(this);

    }
}
