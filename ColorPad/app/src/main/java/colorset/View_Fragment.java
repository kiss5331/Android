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
import android.widget.Toast;

import ganggongui.dalkomsoft02.com.colorpad.R;
import ganggongui.dalkomsoft02.com.colorpad.Setting_Activity;

/**
 * Created by ganggongui on 15. 1. 28..
 */
public class View_Fragment extends Fragment {

    private final String ERROR_CODE = View_Fragment.this.getClass().getName();

    public static View view;

    private ImageView check_Btn;

    private static CardView[] cardViews = new CardView[3];

    // 인터페이스 변수
    private onChangeBtn changeBtn;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_pview, container, false);


        changeBtn = (onChangeBtn) getActivity();


        return view;
    }


    @Override
    public void onStart() {
        super.onStart();

        setToolBar();

        check_Btn = (ImageView) view.findViewById(R.id.checkBtn);


        cardViews[0] = (CardView) view.findViewById(R.id.card_1);

        cardViews[1] = (CardView) view.findViewById(R.id.card2);

        cardViews[2] = (CardView) view.findViewById(R.id.card3);


        check_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                changeBtn.onclick();
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
    public interface onChangeBtn {
        public void onclick();
    }


    // 웨이브 뷰의 색상을 변경하기 위한 메소드

    public static void setWaveColor(int number) {


        cardViews[Picker_Fragment.USER_PICK_COUNTER].
                setCardBackgroundColor(Color.parseColor(Setting_Activity.COLOR_CODES[number]));
        // setBackground(view.getResources().getDrawable(R.drawable.red_button_background));


    }


    public void setToase() {
        Toast.makeText(getActivity(), "AAAAAAAAA", Toast.LENGTH_SHORT).show();
    }


}
