package colorset;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import ganggongui.dalkomsoft02.com.colorpad.R;
import ganggongui.dalkomsoft02.com.colorpad.Setting_Activity;

/**
 * Created by ganggongui on 15. 1. 28..
 */
public class View_Fragment extends Fragment {

    private final String ERROR_CODE = View_Fragment.this.getClass().getName();

    private View view;

    private ImageView check_Btn;

    private ImageView reflish_Btn;

    private CardView[] cardViews = new CardView[3];

    private CardView mixView;

    // 인터페이스 변수
    private onChangeBtn changeBtn;

    private onReflishBtn onReflishBtn;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_pview, container, false);


        changeBtn = (onChangeBtn) getActivity();

        onReflishBtn = (onReflishBtn) getActivity();


        return view;
    }


    @Override
    public void onStart() {
        super.onStart();

        setToolBar();

        check_Btn = (ImageView) view.findViewById(R.id.checkBtn);

        reflish_Btn = (ImageView) view.findViewById(R.id.reflishBtn);

        cardViews[0] = (CardView) view.findViewById(R.id.card_1);

        cardViews[1] = (CardView) view.findViewById(R.id.card2);

        cardViews[2] = (CardView) view.findViewById(R.id.card3);

        mixView = (CardView) view.findViewById(R.id.mix_view);


        check_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                changeBtn.onclick();
            }
        });

        reflish_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                onReflishBtn.onReflishBtn();

                // 웨이브뷰 색상 초기화
                initWaveView();

            }
        });


    }

    private void setToolBar() {

        try {


            Toolbar toolbar;

            toolbar = (Toolbar) view.findViewById(R.id.toolbar);

            toolbar.setTitleTextColor(getResources().getColor(R.color.DeepWhite));

            toolbar.setSubtitleTextColor(getResources().getColor(R.color.DeepWhite));

            toolbar.setTitle(getString(R.string.Toolbar_Title));

            toolbar.setSubtitle(getString(R.string.Toolbar_subTitle));

        } catch (Exception e) {

            Log.e(ERROR_CODE, e + "");
        }

    }

    // 통신을 위한 인터페이스
    // 앱 선택 화면으로 전환한다.
    public interface onChangeBtn {
        public void onclick();
    }

    // 통신을 위한 인터페이스
    // 사용자가 선택한 색상을 초기화한다.
    public interface onReflishBtn {
        public void onReflishBtn();
    }


    // 웨이브 뷰의 색상을 변경하기 위한 메소드

    public void setWaveColor(int number, String mixcolor) {


        cardViews[Picker_Fragment.USER_PICK_COUNTER].
                setCardBackgroundColor(Color.parseColor(Setting_Activity.COLOR_CODES[number]));
        // setBackground(view.getResources().getDrawable(R.drawable.red_button_background));


        Log.e("산택된 새상\t", mixcolor);
        mixView.setCardBackgroundColor(Color.parseColor(mixcolor));


    }

    /**
     * Called when the fragment is visible to the user and actively running.
     * This is generally
     * tied to {@link Activity#onResume() Activity.onResume} of the containing
     * Activity's lifecycle.
     */
    @Override
    public void onResume() {
        super.onResume();

        Log.e(ERROR_CODE, "\t초기화");

        onReflishBtn.onReflishBtn();

        // 웨이브뷰 색상 초기화
        initWaveView();
    }

    // 웨이브 뷰 색상을 하얀색으로 초기화

    private void initWaveView() {

        for (CardView card : cardViews) {
            card.setCardBackgroundColor(getResources().getColor(android.R.color.white));
        }

        // 혼합 결과 웨이브 뷰 초기화

        mixView.setCardBackgroundColor(Color.parseColor("#ECEFF1"));
    }


}
