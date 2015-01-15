package com.dalkomsoft02.ganggongui.todayfeed;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.List;

import school_list_view.ListviewAdapter;


public class SettingSchoolActivity extends ActionBarActivity {

    private EditText Setting_ET;

    private ListView School_LV;

    public String SchoolCode;

    private ListviewAdapter adapter;

    private Bundle SchooldataBundle;

    private FragmentManager fm;

    private DaiaRogFragment dialogFragment;

    private Button Serch_Btn;

    private int positon;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting_school);

        setID();


        adapter = new ListviewAdapter(getApplicationContext());

        adapter.notifyDataSetChanged();

        School_LV.setAdapter(adapter);


        Serch_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                adapter.listDatas.clear();


                SearchCode(Setting_ET.getText().toString());

                Log.i("change", "ok");

            }
        });


        School_LV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                adapter.notifyDataSetChanged();

                positon = position;

                fm = getSupportFragmentManager();
                SchooldataBundle = null;
                SchooldataBundle = new Bundle();


                SchooldataBundle.putString("name", adapter.listDatas.get(position).SchoolName);
                //   System.out.println(SchooldataBundle.getString("name") + "~~~~~~~~~");


                SchooldataBundle.putString("code", adapter.listDatas.get(position).Code);
                // System.out.println(SchooldataBundle.getString("code") + "~~~~~~~~~");

                SchooldataBundle.putString("adress", adapter.listDatas.get(position).Joso);
                // System.out.println(SchooldataBundle.getString("code") + "~~~~~~~~~");
                dialogFragment = new DaiaRogFragment();

                dialogFragment.setArguments(SchooldataBundle);


                dialogFragment.show(fm, "fragment_dialog_test");


            }
        });


    }


    @Override
    protected void onRestart() {
        super.onRestart();

        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onStart() {
        super.onStart();


    }


    private void SearchCode(String SchooolName) {


        new ParseQuery<ParseObject>("schoolinfo").whereStartsWith("schoolname", SchooolName).findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> parseObjects, ParseException e) {


                if (parseObjects == null) {

                } else {
                    adapter.listDatas.clear();

                    for (ParseObject object : parseObjects) {
                        adapter.addItem(object.getString("name"), object.getString("sido"), object.getString("SchoolCode"), object.getString("adress"));

                    }
                    ;

                    if (Setting_ET.getText().toString().equals("")) {
                        adapter.removeData();
                    }


                }
            }

        });


    }


    @Override
    protected void onDestroy() {
        super.onDestroy();

        fm = null;
        adapter = null;
    }

    private void setID() {


        Setting_ET = (EditText) findViewById(R.id.Setting_Seth_ET);

        School_LV = (ListView) findViewById(R.id.Setting_School_LV);

        Serch_Btn = (Button) findViewById(R.id.serch_Btn);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_setting_school, menu);
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
}
