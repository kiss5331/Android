package com.wkdrddml.dalkomsft.coustom_list_view_sample;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;


public class MyActivity extends Activity {

    private ArrayList<CostomData> arrayList;
    private CostomData costomData;
    private coustomAdapter adapter;
    private ListView listView;
    private EditText editText;
    private Button button;
    private InputMethodManager im;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
        im = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        editText = (EditText) findViewById(R.id.inputtext);
        listView = (ListView) findViewById(R.id.list);
        button = (Button) findViewById(R.id.addBtn);
        arrayList = new ArrayList<CostomData>();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                costomData = new CostomData(getApplicationContext(), R.drawable.h, editText.getText().toString());
                arrayList.add(costomData);
                editText.setText("");
                im.hideSoftInputFromWindow(editText.getWindowToken(),0);
            }
        });


        adapter = new coustomAdapter(this, android.R.layout.simple_list_item_1, arrayList);
        listView.setAdapter(adapter);


    }


}
