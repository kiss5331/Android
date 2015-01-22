package com.wkdrddml.dalkomsft.fragment_sample;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class MyActivity extends Activity implements View.OnClickListener {

    private int fragmentIndex;
    public final static int FRAG_ONE = 1;
    public final static int FRAG_TWO = 2;
    public final static int FRAG_THREE = 3;
    private Button[] btn = new Button[3];
    private int[] BtnID = {R.id.btn1, R.id.btn2, R.id.btn3};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
        set_ID_listener();

        fragmentIndex = FRAG_ONE;

        FagmentReplace(fragmentIndex);

    }


    public void FagmentReplace(int fragmentIndex) {

        Fragment fragment = null;

        switch (fragmentIndex) {

            case FRAG_ONE:
                fragment = new one();
                break;
            case FRAG_TWO:
                fragment = new two();
                break;
            case FRAG_THREE:
                fragment = new three();
                break;
        }

        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.fraglayout, fragment);

        transaction.commit();


    }


    @Override
    public void onClick(View v) {


        switch (v.getId()) {
            case R.id.btn1:
                fragmentIndex = FRAG_ONE;
                FagmentReplace(fragmentIndex);
                break;
            case R.id.btn2:
                fragmentIndex = FRAG_TWO;
                FagmentReplace(fragmentIndex);
                break;
            case R.id.btn3:
                fragmentIndex = FRAG_THREE;
                FagmentReplace(fragmentIndex);
                break;

        }

    }


    public void set_ID_listener() {
        int k = 0;
        for (int i : BtnID) {
            btn[k] = (Button) findViewById(i);
            btn[k].setOnClickListener(this);
            k += 1;
        }


    }


}
