package colorset;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pkmmte.view.CircularImageView;

import ganggongui.dalkomsoft02.com.colorpad.R;
import ganggongui.dalkomsoft02.com.colorpad.Setting_Activity;

/**
 * Created by ganggongui on 15. 1. 28..
 */
public class Picker_Fragment extends Fragment implements View.OnClickListener {

    // 인터페이스 인스턴스 변수
    private OnForwardColorListener onForwardColorListener;

    private View view;

    // 클래스 코드
    private final String ERROR_CODE = Picker_Fragment.this.getClass().getName();

    // 유저의 색상 선택 개수를
    // 조절하기위한 카운터
    public int USER_PICK_COUNTER = 0;

    // 사이클 이미지뷰의 아이디 배열
    private final int COLOR_ID[] = {R.id.pick_red, R.id.pick_blue, R.id.pick_Green, R.id.pick_Brown, R.id.pick_Gray, R.id.pick_Pink, R.id.pick_Purple, R.id.pick_White};


    // 클릭 시 이미지 변경을 위한 전역 변수
    private CircularImageView circularImageView;

    private CircularImageView[] ColorBtn = new CircularImageView[8];


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_picker, container, false);

        // 인터페이스 인스턴스 변수에 액티비티 정보 전달
        onForwardColorListener = (OnForwardColorListener) getActivity();

        return view;
    }


    @Override
    public void onStart() {
        super.onStart();

        // 사이클 이미지 뷰 아이디 할당
        setCorlorBtnID();

    }


    private void setCorlorBtnID() {

        int i = 0;

        for (int ID : COLOR_ID) {

            ColorBtn[i] = (CircularImageView) view.findViewById(ID);

            ColorBtn[i].setOnClickListener(this);

            i++;

        }

    }

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     *          <p/>
     *
     * onClick 된 가진 뷰의 배열 값을 전달한다.
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {


            case R.id.pick_red:

                int NUM_ZERO = 0;

                onForwardColorListener.getPickColor(NUM_ZERO);

                onBtnPressChanger(ColorBtn[NUM_ZERO], NUM_ZERO);

                break;
            case R.id.pick_blue:

                int NUM_ONE = 1;

                onForwardColorListener.getPickColor(NUM_ONE);

                onBtnPressChanger(ColorBtn[NUM_ONE], NUM_ONE);

                break;
            case R.id.pick_Green:

                int NUM_TWE = 2;

                onForwardColorListener.getPickColor(NUM_TWE);

                onBtnPressChanger(ColorBtn[NUM_TWE], NUM_TWE);

                break;
            case R.id.pick_Brown:

                int NUM_THREE = 3;

                onForwardColorListener.getPickColor(NUM_THREE);

                onBtnPressChanger(ColorBtn[NUM_THREE], NUM_THREE);

                break;
            case R.id.pick_Gray:
                int NUM_FO = 4;

                onForwardColorListener.getPickColor(NUM_FO);

                onBtnPressChanger(ColorBtn[NUM_FO], NUM_FO);
                break;
            case R.id.pick_Pink:
                int NUM_FIVE = 5;

                onForwardColorListener.getPickColor(NUM_FIVE);

                onBtnPressChanger(ColorBtn[NUM_FIVE], NUM_FIVE);
                break;
            case R.id.pick_Purple:

                int NUM_SIX = 6;

                onForwardColorListener.getPickColor(NUM_SIX);

                onBtnPressChanger(ColorBtn[NUM_SIX], NUM_SIX);

                break;
            case R.id.pick_White:

                int NUM_SE = 7;

                onForwardColorListener.getPickColor(NUM_SE);

                onBtnPressChanger(ColorBtn[NUM_SE], NUM_SE);

                break;

            default:

                break;

        }
    }

    // 유져 카운터 초기화

    public void Initializ_USER_COUNTER() {
        USER_PICK_COUNTER = 0;
        Log.i(ERROR_CODE, "~~~~~~~void Initializ_USER_COUNTER");
    }

    // Picker_Fragment 에서 색상코드를 전달받아
    // View_Fragment 로 전달하기위한 인터페이스
    // onClick 된 가진 뷰의 배열 값을 전달한다.
    public interface OnForwardColorListener {

        public void getPickColor(int color);
    }

    // 색상 클릭시 이미지 0.2 초간 변경


    private void onBtnPressChanger(final CircularImageView circularImageView, final int counter) {

        final int RIMIT_TIME = 100;

        circularImageView.setBorderColor(getResources().getColor(R.color.PressColor));

        this.circularImageView = circularImageView;

        Handler handler = new Handler();

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                circularImageView.setBorderColor(getResources().getColor(Setting_Activity.COLOR_CODES[counter]));

            }
        }, RIMIT_TIME);

    }


}
