package ganggongui.dalkomsoft02.com.myapplication;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;


public class MainActivity extends ActionBarActivity {

    private CardViewAdapter adapter;

    private ListView listView;

    private SpeedScrollListener listener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.LV);

        listener = new SpeedScrollListener();
        listView.setOnScrollListener(listener);

        adapter = new CardViewAdapter (getApplicationContext(), listener);

        listView.setAdapter(adapter);

        adapter.addItem("장공의");

        adapter.notifyDataSetChanged();

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

            adapter.addItem("오늘의 날씨");


            adapter.notifyDataSetChanged();

            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
