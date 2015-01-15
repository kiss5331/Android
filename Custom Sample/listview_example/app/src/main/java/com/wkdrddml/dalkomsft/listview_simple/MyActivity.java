package com.wkdrddml.dalkomsft.listview_simple;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;


public class MyActivity extends Activity {
    private EditText editText;
    private Button addbtn;
    private ListView listView;
    private ArrayAdapter<String> adapter;
    private ArrayList<String> arrayList;
    private InputMethodManager im;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
        setID();
        arrayList = new ArrayList<String>();
        adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, arrayList);
        listView.setAdapter(adapter);
        im = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);


        addbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                im.hideSoftInputFromWindow(editText.getWindowToken(), 0);
                arrayList.add(editText.getText().toString());
                editText.setText(" ");

            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(), position+1 + ""+" is Delete", Toast.LENGTH_SHORT).show();
                arrayList.remove(position);
                adapter.notifyDataSetChanged();
            }
        });


    }

    public void setID() {
        listView = (ListView) findViewById(R.id.listview);
        addbtn = (Button) findViewById(R.id.addbtn);
        editText = (EditText) findViewById(R.id.edittext);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.my, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
