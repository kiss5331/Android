package ganggongui.dalkomsoft02.com.myapplication;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;


public class MainActivity extends ActionBarActivity implements fragment_one.onTextChangeListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onTextChange(String text) {
        fragment_twe fragment_twe = (fragment_twe) getSupportFragmentManager().findFragmentById(R.id.fragtwe);

        fragment_twe.setText(text);

    }
}
