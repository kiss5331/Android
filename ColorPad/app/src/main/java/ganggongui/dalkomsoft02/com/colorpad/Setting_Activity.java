package ganggongui.dalkomsoft02.com.colorpad;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.widget.ViewFlipper;

import cn.pedant.SweetAlert.SweetAlertDialog;
import colorset.Picker_Fragment;
import colorset.View_Fragment;
import flibanimation.AnimationFactory;


public class Setting_Activity extends ActionBarActivity implements View_Fragment.onChangeBtn, Picker_Fragment.OnForwardColorListener {

    // 로딩 다이얼 로그
    private SweetAlertDialog pDialog;

    // 로딩 시간
    private final int REMIT_TIME = 1500;


    // 색상 코드
    public static String COLOR_CODES[];

    private final String ERROR_CODE = Setting_Activity.this.getClass().getName();


    // 처음 받는 색상 코드 값
    private int DEF_COLOR_CODE = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting_);


        COLOR_CODES = getResources().getStringArray(R.array.DeepColor);


        Log.e("불러온 배열", COLOR_CODES[0]);

        // 로딩 다이얼 로그 생성
        showDiarog();


    }

    // 체크 버튼 클릭시 프래그먼트 전환

    public void ViewChange() {

        final ViewFlipper viewFlipper = (ViewFlipper) findViewById(R.id.view_flipper);


        AnimationFactory.flipTransition(viewFlipper, AnimationFactory.FlipDirection.RIGHT_LEFT);

    }


    private void showDiarog() {

        pDialog = new SweetAlertDialog(this, SweetAlertDialog.PROGRESS_TYPE);
        pDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
        pDialog.setTitleText(getString(R.string.dialog_text));
        pDialog.setCancelable(false);
        pDialog.show();


        Handler handler = new Handler();

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                pDialog.cancel();

            }
        }, REMIT_TIME);

    }

    // 색상 선택 완료시 AppList_Fragment
    // 전환을 위한 인터페이스 내부 메소드 구현

    @Override
    public void onclick() {
        ViewChange();
    }

    // Picker_Fragment 에서 색상코드를 전달받아
    // View_Fragment 로 전달하기위한 인터페이스
    // 내부 메소드 구현


    /**
     * @param color 사용자가 선택한 색상의 인덱스 값을 인자값으로 받는다.
     */
    @Override
    public void getPickColor(int color) {

        View_Fragment.setWaveColor(color);

        // Log.i(ERROR_CODE, get);

        View_Fragment viewFragment = (View_Fragment)getSupportFragmentManager().findFragmentByTag("tegs");

        viewFragment.setToase();


    }


}
