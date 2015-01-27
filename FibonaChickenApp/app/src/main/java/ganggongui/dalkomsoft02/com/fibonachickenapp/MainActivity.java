package ganggongui.dalkomsoft02.com.fibonachickenapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;

import fragments.Fragment_Checkin;
import fragments.Fragment_Main;
import fragments.Fragment_User;


public class MainActivity extends ActionBarActivity implements Fragment_Main.OnMainBtnClickListener, Fragment_User.onCheckinListener {

    private final String MainFragment_Tag = "1010";
    private final String UserFragment_Tag = "2020";
    private final String ChickenFragment_Tag = "3030";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        if (savedInstanceState != null) {
            finish();
        }


        setContentView(R.layout.activity_main);


        getSupportFragmentManager().
                beginTransaction().
                add(R.id.container, new Fragment_Main(), MainFragment_Tag).
                commit();


        // getSupportFragmentManager().beginTransaction().add(R.id.container, new Fragment_Checkin(), ChickenFragment_Tag).commit();

    }


    @Override
    public void onCheckinBtnListener(int checkin) {

        Fragment_Checkin fragment_checkin = new Fragment_Checkin();

        Bundle bundle = new Bundle();

        bundle.putInt("chicken", checkin);

        fragment_checkin.setArguments(bundle);


        replaceFragment(

                fragment_checkin, ChickenFragment_Tag
        );


        Log.i("~~~~~~~~~~~~~~~~~~~~~~~~~~", fragment_checkin + " ");


    }


    @Override
    public void MainBtnClickListener() {

        replaceFragment(

                new Fragment_User(), UserFragment_Tag

        );

    }


    private void replaceFragment(Fragment fragment, String Tag) {

        getSupportFragmentManager().
                beginTransaction().
                replace(R.id.container, fragment, Tag).
                addToBackStack(null).
                commit();

    }


}
