package com.dalkomsoft02.ganggongui.todayfeed;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import toast.library.meal.MealLibrary;

/**
 * Created by ganggongui on 14. 12. 19..
 */
public class DaiaRogFragment extends DialogFragment {

    private Button ChoiceBtn;

    private TextView SchoolName;

    private TextView SchoolAdress;

    private TextView SteastTV;

    private String[] FoodNew;

    private String[] dateNew;

    private boolean aBoolean = false;

    public DaiaRogFragment() {
        super();
    }

    private View view;

    private Bundle bundle;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.dialogfragment, container);


        return view;
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        bundle = getArguments();

        SchoolName = (TextView) view.findViewById(R.id.fr_Schoolname_TV);

        SchoolAdress = (TextView) view.findViewById(R.id.fr_SchoolAdress_TV);

        ChoiceBtn = (Button) view.findViewById(R.id.fr_Choice_Btn);

        SteastTV = (TextView) view.findViewById(R.id.stast_TV);

        SchoolName.setText(bundle.getString("name"));


        TestfoodThread thread = new TestfoodThread();

        Thread thread1 = new Thread(thread);

        thread1.start();

        try {
            thread1.join();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (aBoolean) {
            SteastTV.setText("급식 정보 조회가 가능합니다.");
        } else {
            SteastTV.setText("급식 정보 조회 불가능!");
        }


        if (bundle.getString("adress").equals("0")) {
            SchoolAdress.setText("주소지 정보가 없습니다.");

        } else {
            SchoolAdress.setText(bundle.getString("adress"));
        }


        ChoiceBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                if (aBoolean) {

                    SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getActivity());

                    SharedPreferences.Editor editor = preferences.edit();

                    editor.putString("Code", bundle.getString("code"));

                    editor.putString("Name", bundle.getString("name") + " 급식정보");

                    editor.commit();

                    Toast.makeText(view.getContext(), SchoolName.getText().toString() + "  설정 되었습니다.", Toast.LENGTH_SHORT).show();
                    DaiaRogFragment.this.getActivity().finish();
                } else {
                    DaiaRogFragment.this.getActivity().finish();
                    Toast.makeText(view.getContext(),"설정 실패", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }


    class TestfoodThread implements Runnable {
        @Override
        public void run() {
            dateNew = MealLibrary.getDateNew("sen.go.kr", bundle.getString("code"), "4", "04", "2");
            FoodNew = MealLibrary.getMealNew("sen.go.kr", bundle.getString("code"), "4", "04", "2");

            try {
                Log.i("테스팅", FoodNew[4]);
                Log.i("테스팅", FoodNew[2]);
                Log.i("테스팅", FoodNew[1]);


                aBoolean = true;

            } catch (Exception e) {


                aBoolean = false;
            }

        }
    }

}


