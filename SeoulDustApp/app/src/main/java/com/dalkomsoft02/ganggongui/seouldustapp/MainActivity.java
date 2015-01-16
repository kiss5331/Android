package com.dalkomsoft02.ganggongui.seouldustapp;

import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends ActionBarActivity {

    private final String NAME_CODE = MainActivity.class.getName();

    private List<String> datalist = new ArrayList<String>();








    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setToolBar();


        // 관측소 명을 인자 값으로 전달

        new DustDataTesk().execute("송파구");

    }

    private void setToolBar() {

        Toolbar toolbar = (Toolbar) findViewById(R.id.main_toolbar);

        toolbar.setTitleTextColor(getResources().getColor(android.R.color.black));

        toolbar.setTitle(getString(R.string.app_name));

        setSupportActionBar(toolbar);

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
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    class DustDataTesk extends AsyncTask<String, Void, Void> {

        final private String URL = "http://openAPI.seoul.go.kr:8088";

        final private String KEY = "/46496f4f7864616c3739494e794151";

        final private String TYPE = "/json";

        final private String SERVICE = "/ForecastWarningUltrafineParticleOfDustService";

        final private String START_INDAX = "/0";

        final private String END_INDAX = "/1";

        final private String MSRDT_DE = "/20150115";


        private String JSONdata;


        @Override
        protected Void doInBackground(String... params) {

            String MSRSTE_NM = "/송파구";

            try {

                HttpClient httpClient = new DefaultHttpClient();

                HttpGet httpGet = new HttpGet(URL + KEY + TYPE + SERVICE + START_INDAX + END_INDAX + MSRDT_DE + MSRSTE_NM);

                ResponseHandler<String> responseHandler = new BasicResponseHandler();

                JSONdata = httpClient.execute(httpGet, responseHandler);

                Log.v("데이터", JSONdata);


            } catch (Exception e) {

                Log.e(NAME_CODE, String.valueOf(e));

            }


            return null;
        }


        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            datalist.clear();

            datalist.addAll(new JSONPaser(JSONdata).getJsonData());


        }
    }

}
