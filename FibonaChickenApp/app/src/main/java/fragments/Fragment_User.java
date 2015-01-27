package fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import ganggongui.dalkomsoft02.com.fibonachickenapp.R;

/**
 * Created by ganggongui on 15. 1. 23..
 */
public class Fragment_User extends Fragment {


    private View view;

    private Button ok_btn;

    private EditText ok_ET;

    private onCheckinListener checkinListener;


    /**
     * @param activity
     */
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        checkinListener = (onCheckinListener) activity;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        this.view = inflater.inflate(R.layout.fragment_user_layout, container, false);

        return this.view;
    }


    /**
     * Called when the Fragment is visible to the user.  This is generally
     * tied to {@link android.app.Activity#onStart() Activity.onStart} of the containing
     * Activity's lifecycle.
     */
    @Override
    public void onStart() {
        super.onStart();

        ok_btn = (Button) view.findViewById(R.id.user_Btn);

        ok_ET = (EditText) view.findViewById(R.id.user_ET);

        // 버튼 클릭시 사용자가 입력한 인원 수를 가져옴
        ok_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (String.valueOf(ok_ET.getText()).equals("")) {
                    ok_ET.setText("0");
                }

                checkinListener.onCheckinBtnListener(


                        pibonaci(

                                Integer.valueOf(
                                        String.valueOf(

                                                ok_ET.getText()

                                        )
                                )
                        )
                );


            }
        });


    }


    // 피보나치 수열로 계산된 치킨수를
    // 메인 액티비티로 전달하기위한 인터페이스
    public interface onCheckinListener {
        public void onCheckinBtnListener(int checkin);
    }


    // 피보나치 수열을 계산하여 인원수에 따른
    // 치킨수를 리턴 합니다.

    private int pibonaci(int user) {


        int checkin = 0;

        int start = 0;

        int end = 1;

        do {
            checkin = start + end;

            start = end;

            end = checkin;
            Log.i("피보나치", String.valueOf(checkin));

        } while (!(user <= checkin));


        checkin = start - ((end - user) % 2) - ((end - user) / 2);


        Log.i("치킨 수", String.valueOf(checkin));

        return checkin;
    }


}
